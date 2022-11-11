package soccer.deploy.match.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soccer.deploy.match.entity.Match;

public interface JpaMatchRepository extends JpaRepository<Match, Long> {
	
	@Query(value = "SELECT max(m.id) FROM Match m ")
	public Long recentMatch();
}
