package soccer.deploy.entry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soccer.deploy.entry.entity.Entry;

public interface JpaEntryRepository extends JpaRepository<Entry, Long> {
	List<Entry> findAllByMatchId(Long Id);
	

	 
}
