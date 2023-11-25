package soccer.deploy.article.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import soccer.deploy.article.dto.ArticleDto;
import soccer.deploy.article.entity.Article;
import soccer.deploy.article.repository.JpaArticleRepository;

@Service

@Slf4j
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private JpaArticleRepository jpaArticleRepository;
	
	@Override
	@Transactional
	public Article RegArticleNextPk(Article article) {
		 
		jpaArticleRepository.registArticle(article);
		return jpaArticleRepository.findFirstByWriterOrderByRegdateDesc(article.getWriter()).orElseGet(Article :: new);   
	}
	@Override
	public Page<Article> findPageArticle(Long boardId, String searchValue, Pageable pageable) {

		return jpaArticleRepository.findArticle(boardId, searchValue, pageable);
	}
	
	@Override
	@Transactional
	public List<Article> detailArticle(Long boardId, Long groupNo) {
		jpaArticleRepository.UpdateHitcount(groupNo);
		return jpaArticleRepository.findAllByBoardIdAndGroupNoOrderByOrderNo(boardId, groupNo);
	}
	@Override
	@Transactional
	public void RegDatArticle(Article article) {
		jpaArticleRepository.UpdateHitcount(article.getGroupNo());
		jpaArticleRepository.save(article);
	}
	@Override
	@Transactional
	public void RegDaDatArticle(Article article) {
		jpaArticleRepository.UpdateHitcount(article.getGroupNo());
		jpaArticleRepository.UpdateOrderNo(article.getOrderNo(),article.getGroupNo());
		jpaArticleRepository.save(article);
		
	}
	
	@Override
	public List<ArticleDto> prevNext(Long boardId, Long groupNo, String searchValue) {
		
		return jpaArticleRepository.prevNext(boardId, groupNo, searchValue);
	}
}
