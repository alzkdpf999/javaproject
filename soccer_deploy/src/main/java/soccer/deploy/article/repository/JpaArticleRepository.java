package soccer.deploy.article.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import soccer.deploy.article.dto.ArticleDto;
import soccer.deploy.article.entity.Article;


public interface JpaArticleRepository extends JpaRepository<Article, String>{
	@Modifying
	@Transactional
	@Query(value="INSERT INTO article(article_id,board_id,writer,subject,content,group_no,level_no,order_no)"
			+ "VALUES(article_seq.NEXTVAL,:#{#artic.boardId},:#{#artic.writer},:#{#artic.subject},:#{#artic.content},article_seq.CURRVAL,0,0)",nativeQuery = true)
	public void registArticle(@Param("artic") Article article);
	
	public Optional<Article> findFirstByWriterOrderByRegdateDesc(String writer);
	
	@Query(value="SELECT a FROM Article a where a.articleId = a.groupNo AND a.boardId=:boardId AND (LOWER(a.content) LIKE LOWER(CONCAT(:searchValue,'%')) OR LOWER(a.subject) LIKE LOWER(CONCAT(:searchValue,'%')))")
	public Page<Article> findArticle(@Param("boardId") Long boardId, @Param("searchValue") String searchValue , Pageable pageable);
	
	@Query(value= "select prev, next from (SELECT group_no as groupNo,LAG(group_no) over (ORDER By group_no ) as prev,LEAD(group_no)  over (ORDER By group_no ) as next from article  where article.board_id=:boardId and (article.content like concat(:searchValue,'%') or article.subject like concat(:searchValue,'%')) ) where groupNo = :groupNo and (prev is null or next is null or prev <> :groupNo or next <> :groupNo) ",nativeQuery = true)
	public List<ArticleDto> prevNext(@Param("boardId") Long boardId,@Param("groupNo") Long groupNo, @Param("searchValue") String searchValue);
	
	public List<Article> findAllByBoardIdAndGroupNoOrderByOrderNo(Long boardId,Long groupNo);
	@Modifying
	@Transactional
	@Query(value="Update Article a set a.orderNo = a.orderNo+1 WHERE a.orderNo >=:orderNo AND a.groupNo=:groupNo")
	public void UpdateOrderNo(@Param("orderNo") Long orderNo,@Param("groupNo") Long groupNo);
	
	@Modifying
	@Transactional
	@Query(value="Update Article a set a.hitcount = a.hitcount+1 WHERE a.articleId = :articleId")
	public void UpdateHitcount(@Param("articleId") Long articleId);
	

	
}