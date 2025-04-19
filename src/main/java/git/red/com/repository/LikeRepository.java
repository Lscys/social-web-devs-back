package git.red.com.repository;

import git.red.com.models.PostLike;
import git.red.com.models.Release;
import git.red.com.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<PostLike, Integer> {

    @Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post.idrelease = :postId")
    Integer countLikesByPostId(@Param("postId") Integer postId);

    Optional<PostLike> findByPost_IdreleaseAndUser_Iduser(Integer postId, Integer userId);


}
