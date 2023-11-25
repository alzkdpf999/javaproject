package soccer.deploy.user.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import soccer.deploy.entry.entity.Entry;
import soccer.deploy.entry.service.EntryService;
import soccer.deploy.match.entity.Match;
import soccer.deploy.match.myDto.matchMyDto;
import soccer.deploy.match.service.MatchService;
import soccer.deploy.user.dto.LoginForm;
import soccer.deploy.user.dto.UpdateUserDto;
import soccer.deploy.user.dto.UserDto;
import soccer.deploy.user.entity.User;
import soccer.deploy.user.repository.UserRepository;
import soccer.deploy.user.service.UserService;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntryService entryService;
	
	@Autowired
	private MatchService matchService;
	
	@GetMapping("/login")
	public String loginPage(@ModelAttribute("loginForm") LoginForm loginForm){
		return "view/user/login";
	}
	
	@PostMapping("/login")
	public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm , BindingResult bindingResult, HttpServletResponse response,
						@RequestParam(name = "redirect", defaultValue = "/") String redirect, Model model , HttpServletRequest request) {
		
		if(bindingResult.hasErrors()) {
			log.info("헤즈에러 부분");
			return "/view/user/login";
		}
		
		User loginUser = userService.login(loginForm.getEmail(), loginForm.getPasswd());
		
		if(loginUser==null) {
			log.info("null");
			bindingResult.reject("loginFail", "ID와 비밀번호를 확인하여 주십시오");
			return "/view/user/login";
		}
		
		/* Cookie로 로그인 할 경우 
		Cookie loginCookie = new Cookie("loginUser", String.valueOf(loginUser.getId()));
		loginCookie.setPath("/");
		log.info("{}",loginCookie.getValue());
		response.addCookie(loginCookie);
		model.addAttribute("loginUser", loginCookie);
		*/
		
		/* Session으로 로그인 */
		HttpSession session = request.getSession();
		session.setAttribute("loginUser", loginUser);
		
		/*
		 * 로그인 후 로그인 하기 전 페이지로 redirect하기
		 */
//		String uri = request.getHeader("Referer");
//		request.getSession().setAttribute("prevPage", uri);
//		redirect=(String)session.getAttribute("prevPage");
		return "redirect:"+redirect;
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
		/* Cookie 로그아웃
		Cookie cookie = new Cookie("loginUser", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
		*/
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}
	
	@GetMapping("/regist")
	public String registPage(@ModelAttribute("user") User user) {
		return "/view/user/signup";
	}
	
	@PostMapping("/idCheck")
	@ResponseBody
	public int idCheck(@RequestParam String email) {
		int cnt = userService.idCheck(email);
		return cnt;
	}
	
	@PostMapping("/regist")
	public String registerForm(@Validated @ModelAttribute("user") UserDto user, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model,
				@RequestParam String add1 , String add2 , String add3,
				@RequestParam MultipartFile imageFile) {
		
				if (bindingResult.hasErrors()) {
					log.info("bindingResults : {}", bindingResult);
					return "/view/user/signup";
				}
		
		/*
		 * 다음 API로 받은 주소를 다 조합해 address에 간단하게 저장		
		 */
		String address = add1+"  "+add2+"  "+add3;
		
		/*
		 * 프로필 사진 넣기
		 */
		String ranName = UUID.randomUUID().toString();	// 랜덤한 문자열을 생성해 붙여줘야 같은 이름으로 파일의 중복을 방지한다. (덮어쓰기 방지)
		String storedFileName = ranName + "_" + imageFile.getOriginalFilename();
		log.info("{}",imageFile.getOriginalFilename());
		try {
			imageFile.transferTo(new File(storedFileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		User registUser = new User();
		registUser.setEmail(user.getEmail());
		registUser.setPasswd(user.getPasswd());
		registUser.setAge(user.getAge());
		registUser.setName(user.getName());
		registUser.setAddress(address);
		registUser.setBackNum(user.getBackNum());
		registUser.setImgContType(imageFile.getContentType());
		registUser.setImgFileName(storedFileName); 
		registUser.setPosition(user.getPosition());
		registUser.setRegdate(user.getRegdate());
		registUser.setMemberAuth(user.getMemberAuth());
		
		Long userId = userService.register(registUser);
		
		redirectAttributes.addAttribute("userId", userId);
		redirectAttributes.addAttribute("status", true);
		return "redirect:/user/{userId}";
	}
	
	/* User에 관한 URL을 RESTful하게 작성 
	 * {userId}에 해당하는 값을 요청할 경우 해당 Id를 가진 회원 정보가 출력*/
	@GetMapping("/{userId}")
	public String registUserView(@PathVariable Long userId , Model model) {
		Optional<User> user = userService.findUser(userId);
		model.addAttribute("user", user.get());
		log.info("가입한 회원 정보{}",user);
		return "view/user/profile";
	}
	
	/*
	 *  User가 참가신청한 Match들을 보여주는 View
	 */
	@GetMapping("/{userId}/match")
	public String userMatchView(@PathVariable Long userId, Model model) {
//		List<matchMyDto> match = matchService.userMatch(userId);
		List<Match> match = matchService.userMatch(userId);
		model.addAttribute("entry", match);
		model.addAttribute("date",matchService.matchExpiration(match));
		return "view/user/userMatch";
	}
	
	/*
	 *  회원정보에서 탈퇴버튼을 클릭할 경우 passwdConfirm창으로 이동한다.
	 */
	@GetMapping("/{userId}/delete")
	public String deleteUserView(@PathVariable Long userId , Model model) {
		return "view/user/deleteForm";
	}
	
	/*
	 *  userId에 해당하는 user의 비밀번호와 deleteForm에서 받은 비밀번호와 일치할 경우
	 *  user를 삭제하고 session을 종료시킨다. 비밀번호가 일치하지 않을 시 
	 *  에러메세지를 담아 redirect에 Attribute와 함께 return
	 */
	@DeleteMapping("/{userId}/delete")
	public String deleteUser(@PathVariable Long userId,@RequestParam String passwd, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		HttpSession session = request.getSession();
		Optional<User> user = userService.findUser(userId);
		if(user.get().getPasswd().equalsIgnoreCase(passwd)) {
			userService.delete(userId);
			session.invalidate();
			return "redirect:/xMain";
		}
		else {
			redirectAttributes.addFlashAttribute("msg", "pwError");
			return "redirect:/user/{userId}/delete";
		}
	}
	/*
	 *  회원 정보 수정을 위한 view를 출력 세션에 저장된 @PathVariable로 userId의 값과 일치하는 User의 정보를 받아
	 *  Passwd를 한번 더 입력하게 한다.
	 */
	@GetMapping("{userId}/checkpw")
	public String checkPasswdView(@PathVariable Long userId, Model model) {
		return "view/user/passwdConfirm";
	}
	/*
	 *  세션에 저장된 @PathVariable로 userId의 값과 일치하는 User의 정보를 읽어온 후
	 *  해당 페이지의 입력받은 passwd값과 읽어온 passwd가 일치하는지 확인한다.
	 */
	@PostMapping("{userId}/checkpw")
	public String checkPasswdForm(@PathVariable Long userId,@RequestParam String passwd, Model model, RedirectAttributes redirectAttributes) {
		Optional<User> user = userService.findUser(userId);
		if(user.get().getPasswd().equalsIgnoreCase(passwd)) {
			return "redirect:/user/{userId}/modify";	// 비밀번호가 일치할 경우 회원 수정 페이지로 return
		}
		else {
			/* addFlashAttribute는 단 1회 요청되는 휘발성 Attribute. 에러메세지를 문자열로 담아 보낼 수 있음!*/
			redirectAttributes.addFlashAttribute("msg", "pwError");
			return "redirect:/user/{userId}/checkpw";
		}
	}
	/*
	 *  회원 수정페이지를 출력하는 View.
	 */
	@GetMapping("{userId}/modify")
	public String modifyUserView(@PathVariable Long userId, @ModelAttribute("updateUser") UpdateUserDto updateUser ,Model model) {
		Optional<User> user = userService.findUser(userId);
		model.addAttribute("user", user.get());
		return "view/user/modifyUser";
	}
	/*
	 *  회원 수정은 PutMapping을 이용해야한다. 
	 *  // Post 와 Put의 차이는 *멱등성 // 정확한 개념을 잡기 위해선 공부가 필요
	 *  SpringBoot에선 Put과 Delete Mapping을 사용하기 위해선 HiddenHttpMethodFilter를 @Bean으로 Application에 선언해줘야 한다. (헤멜 수 있음)
	 */
	@PutMapping("{userId}/modify")
	public String modifyForm(@PathVariable Long userId, @ModelAttribute("updateUser") UpdateUserDto user,Model model, RedirectAttributes redirectAttributes,
				@RequestParam String add1 , String add2) {
		
		String address = add1 + " " +add2;
		user.setAddress(address);
		Long update	 = userService.updateUser(userId, user);
		
		redirectAttributes.addAttribute("userId", update);
		redirectAttributes.addAttribute("status", true);
		
		return "redirect:/user/{userId}";
	}
	
	
	/*
	 *  22.12.07 현재는 List 뷰에 권한을 따로 요청하진 않지만
	 *  나중엔 권한을 통한 접근을 추가해주는게 좋을것 같음.
	 *  페이징 처리를 위한 Pageble을 사용하고 @RequstParam으로 User의 name을 입력받아 리스트 View에 결과값을 출력한다.
	 */
	@GetMapping("/list")
	public String userListView(Model model, @PageableDefault(size = 10, page=0) Pageable pageable,
								@RequestParam(required = false, defaultValue = "") String value) {
		
		Page<User> userList =userService.searchUser(value,pageable);
		log.info("검색 값 : {}", value);
		log.info("유저리스트 : {}", userList);
		int pageNumber = userList.getPageable().getPageNumber()+1;
		int totalPage = userList.getTotalPages();
		int pageBlock = 5;
		int startBlockPage = (pageNumber/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage = endBlockPage>totalPage ? totalPage : endBlockPage ;
		
		model.addAttribute("userList", userList);
		model.addAttribute("value", value);
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);	
		return "view/user/players";
	}
}