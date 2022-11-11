package soccer.deploy.lineUp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import soccer.deploy.entry.entity.Entry;
import soccer.deploy.entry.service.EntryService;
import soccer.deploy.match.service.MatchService;
import soccer.deploy.quarter.entity.Quarter;
import soccer.deploy.quarter.service.QuarterService;

@Controller
@RequestMapping("/lineup")
public class LineUpController {
	@Autowired
	private MatchService matchService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private QuarterService quarterService;
	
	//ó�� ȭ�� ��ºκ�
	@GetMapping
	public String lineUpview(Model model) {
		Long recentMatch = matchService.findRecentMatch();
		List<Entry> entry = entryService.findEntryRecentMatch(recentMatch);
		List<Quarter> quarter = quarterService.findQuarterRecentMatch(recentMatch);
		model.addAttribute("entry", entry);
		model.addAttribute("quarter", quarter);
		return "view/linUp/lineUp";
	}
	// �˻� �񵿱�� post��
	// ������ �̵��� �ڵ����� ���ǿ� ���� ������ ������ ����� â ������ �ؼ� ��ҽô� ���� x Ȯ�ν� ����
}
