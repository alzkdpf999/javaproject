package soccer.deploy;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import soccer.deploy.article.entity.Article;
import soccer.deploy.article.repository.JpaArticleRepository;
import soccer.deploy.article.service.ArticleService;
import soccer.deploy.board.service.BoardService;
import soccer.deploy.entry.service.EntryService;
import soccer.deploy.match.entity.Match;
import soccer.deploy.match.myService.MatchChoungService;
import soccer.deploy.match.service.MatchService;

@SpringBootTest
@Transactional
@Slf4j
public class choungTest {
	@Autowired
	private MatchService matchService;
	@Autowired
	private MatchChoungService matchChoungService;
	@Autowired
	private BoardService boardService;
	@Autowired
	private EntryService entryService;
	@Autowired
	private MatchService m;
	
	@Autowired
	private JpaArticleRepository jp;
	@Autowired
	private ArticleService a;
	
	@Test
	void test3() throws ParseException {
		Article article = new Article();
		
		article.setBoardId(10L);
		article.setContent("asd");
		article.setWriter("ksa@ac.kr");
		article.setSubject("asd");
		
log.info("{}",a.RegArticleNextPk(article).getArticleId());
	}
}

