package soccer.deploy.lineUp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import soccer.deploy.lineUp.entity.LineUp;
import soccer.deploy.lineUp.repository.JpaLineUpRepository;

@Service

public class LineUpServiceImpl implements LineUpService {
	@Autowired
	private JpaLineUpRepository jpaLineUpRepository;
	
	@Override
	@Transactional
	public void insertLineUp(List<LineUp> LineUp) {
		// TODO Auto-generated method stub
		jpaLineUpRepository.saveAll(LineUp);
	}
	
}
