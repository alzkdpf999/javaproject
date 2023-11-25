package soccer.deploy.quarter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import soccer.deploy.lineUp.entity.LineUp;
import soccer.deploy.match.service.MatchService;
import soccer.deploy.quarter.entity.Quarter;
import soccer.deploy.quarter.repository.JpaQuarterRepository;

@Service

public class QuarterServiceImpl implements QuarterService {
	@Autowired
	private JpaQuarterRepository jpaQuarterRepository;

	@Override //�ֽ� ��� �������� ��������
	public List<Quarter> findQuarterRecentMatch(Long id) {
		
		return jpaQuarterRepository.findAllByMatchId(id,Sort.by("id"));
	}
	@Override
	@Transactional
	public void updateInsertQuarter(List<Quarter> outcome) {
		jpaQuarterRepository.saveAll(outcome);
	}
	
	@Override
	@Transactional
	public void registQuarter(List<Quarter> quarter) {
		jpaQuarterRepository.saveAll(quarter);
	}
	
	public List<Long> checkQuarter(Long matchId,List<LineUp> lineUp){
		List<Long> quarterId =new ArrayList<Long>();
		for(LineUp l : lineUp) {
			 quarterId.add(l.getQuarterId());
		}
		List<Long> line = jpaQuarterRepository.findByMatchId(matchId);
		line.retainAll(quarterId);
		return line;
	}
}
