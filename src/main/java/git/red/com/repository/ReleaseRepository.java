package git.red.com.repository;

import git.red.com.dto.PostDto;
import git.red.com.models.Release;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReleaseRepository extends JpaRepository<Release, Integer> {

}
