package git.red.com.repository;

import git.red.com.models.Notification;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    List<Notification> findByUserIduserOrderByCreatedAtDesc(Integer userId);

    List<Notification> findByUser_IduserAndSeenIsFalse(Integer userId);

    Optional<Notification> findByIdAndUser_Iduser(Integer notificationId, Integer userId);// ✅ Correcto

    List<Notification> findByUserIduserAndSeenFalseOrderByCreatedAtDesc(Integer userId);



}
