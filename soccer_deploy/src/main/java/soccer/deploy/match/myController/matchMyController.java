package soccer.deploy.match.myController;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.slf4j.Slf4j;
import soccer.deploy.entry.entity.Entry;
import soccer.deploy.entry.repository.JpaEntryRepository;
import soccer.deploy.match.myDao.matchDao;
import soccer.deploy.match.myDto.matchMyDto;
import soccer.deploy.match.myService.MatchChoungService;
import soccer.deploy.match.service.MatchService;
import soccer.deploy.user.entity.User;

@Controller
@RequestMapping("/match")
@Slf4j
public class matchMyController {
	
	@Autowired
	private matchDao MatchDao; 
	@Autowired
	private JpaEntryRepository entryService;
	@Autowired
	private MatchService m;
	@Autowired
	private MatchChoungService matchService;
	@GetMapping
	public String matchList(Model model) {
		List<matchMyDto> list = MatchDao.list();
		model.addAttribute("list", list);
		log.info("{}",list);
		
		return "view/match/match"; 
	}
	
	//match �벑濡�
//	@PostMapping("/schedule")
//	public String matchRegist() {
//		return null;
//	}
	
	@GetMapping("/asd")
	public String test(@RequestParam("matchId")String matchId,  HttpSession session)
	{   //濡쒓렇�씤 �꽭�뀡媛�
		Entry entry =new Entry();
		entry.setMatch(m.findeRecentMatch(Long.parseLong(matchId)));
		entry.setUser((User) session.getAttribute("loginUser"));
		log.info("{}",matchId);
		entryService.save(entry);
		return "view/match/match";
	}
	
	@GetMapping("/schedule")
	public String matchSchedule() {
		
		return "view/match/schedule";
	}
	
	@GetMapping("/result")
	public String matchResult() {
		
		return "view/match/matchResult";
	}
	@GetMapping("/test")
	public String rank(@RequestParam(value= "rankYear", required = false, defaultValue = "first") String rankYear, @RequestParam(value= "rankMonth", required = false, defaultValue = "first") String rankMonth, Model model) {
//		log.info("year:{}",matchService.year());
		log.info("year:{} , month:{}",rankYear, rankMonth);
		log.info("rank:{}",matchService.rank(rankYear, rankMonth));
		model.addAttribute("rank", matchService.rank(rankYear, rankMonth));
		model.addAttribute("year", matchService.year());
		model.addAttribute("month",matchService.month());

		return "view/match/rank";
	}
}
