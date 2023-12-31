package soccer.deploy.match.repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import soccer.deploy.match.entity.Match;
import soccer.deploy.match.myDto.MatchDto;

public interface JpaMatchRepository extends JpaRepository<Match, Long> {
	

	@Query(value = "SELECT max(m.id) FROM Match m  where m.id not in(select m.id from LineUp l join l.entry e join e.match m group by m.id)")
	public Long recentMatchNum();
	
	@Query(value = "SELECT max(m.id) FROM Match m")
	public Long recentViewMatch();
    
	@Query(value = "SELECT m FROM Match m WHERE m.id =:id")
	public Optional<Match> recentMatch(@Param("id") Long id);
	
	@Query(value= "SELECT new soccer.deploy.match.myDto.MatchDto(m.id,m.matchDate,m.opteam,m.matchImgType,m.matchImgName,m.matchPlace, SUM(q.outcome),COUNT(q.id)) from Quarter q join q.match m WHERE m.id in (select distinct m.id from LineUp l join l.quarter q join q.match m where m.matchDate Like CONCAT(:date,'%') )  GROUP BY m.id,m.matchDate,m.opteam,m.matchImgType,m.matchImgName,m.matchPlace ORDER BY m.matchDate DESC")
	public List<MatchDto> resultMatch(@Param("date") String date);
    
	@Query(value= "SELECT new soccer.deploy.match.myDto.MatchDto(m.id,m.matchDate,m.opteam,m.matchImgType,m.matchImgName,m.matchPlace, SUM(q.outcome),COUNT(q.id)) from Quarter q join q.match m WHERE m.id in (select distinct m.id from LineUp l join l.quarter q join q.match m where m.id =:id )  GROUP BY m.id,m.matchDate,m.opteam,m.matchImgType,m.matchImgName,m.matchPlace ORDER BY m.matchDate DESC")
	public Optional<MatchDto> resultMatchById(@Param("id") Long id);
	
	@Query(value = "SELECT m FROM Match m WHERE TO_CHAR(m.matchDate,'YY/MM') = :matchDate")
	public List<Match> findAllByMatchDate(@Param("matchDate") String matchDate);
	
	@Query(value="        select match_id from("
			+ "select distinct m.match_id,m.match_date"
			+ "         from match m join entry e"
			+ "            on m.match_id = e.match_id  join lineup l"
			+ "                    on e.entry_id = l.entry_id"
			+ "        order by match_date desc)"
			+ "        where rownum <= 2 ",nativeQuery = true)
	public List<Long> findRecentTwoResultMatchId();
	
	@Query(value="SELECT m FROM Match m WHERE m.matchDate Like CONCAT(:date,'%')")
	public List<Match> findByDate(@Param("date")String date);
}
	