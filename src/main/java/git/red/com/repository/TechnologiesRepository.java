package git.red.com.repository;

import git.red.com.models.Technologies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnologiesRepository extends JpaRepository<Technologies, Integer> {
}
