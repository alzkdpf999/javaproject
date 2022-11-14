package soccer.deploy.match.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import soccer.deploy.match.entity.Match;
import soccer.deploy.match.repository.JpaMatchRepository;

@Service
@Transactional
public class MatchServiceImpl implements MatchService {

	@Autowired
	private JpaMatchRepository jpaMatchRepository; 
	
	@Override
	public Long findRecentMatchNum() {
		
		return  jpaMatchRepository.recentMatchNum();

	}

	@Override
	public Match findeRecentMatch(Long id) {
		
		Optional<Match> optional = jpaMatchRepository.recentMatch(id);
		if(optional.isEmpty()) {
			throw new RuntimeException(id+"�� �ش��ϴ� ����� ã�� �� �����ϴ�.");
		}
		return optional.get();
	}

}
