package soccer.deploy.entry.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import soccer.deploy.entry.entity.Entry;

public interface JpaEntryRepository extends JpaRepository<Entry, String> {

}
