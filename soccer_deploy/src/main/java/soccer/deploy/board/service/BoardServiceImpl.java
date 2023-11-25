package soccer.deploy.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import soccer.deploy.board.entity.board;
import soccer.deploy.board.repository.JpaBoardRepository;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private JpaBoardRepository jpaBoardRepository;
@Override
		public List<board> findAll() {
			return jpaBoardRepository.findAll(Sort.by(Sort.Direction.ASC,"boardId"));
			
		}	
}
