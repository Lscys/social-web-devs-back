package git.red.com.services;

import git.red.com.models.Notification;
import git.red.com.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;


    // Obtener notificaciones no leídas de un usuario
    public List<Notification> getUnreadNotificationsByUser(Integer userId) {
        return notificationRepository.findByUser_IduserAndSeenIsFalse(userId);
    }

    // Marcar una notificación como leída
    public boolean markAsRead(Integer notificationId, Integer userId) {
        Optional<Notification> notificationOpt = notificationRepository.findByIdAndUser_Iduser(notificationId, userId);
        if (notificationOpt.isPresent()) {
            Notification notification = notificationOpt.get();
            notification.setSeen(true);
            notificationRepository.save(notification);
            return true;
        }
        return false;
    }

    public void saveAll(List<Notification> notifications) {
        notificationRepository.saveAll(notifications);
    }

    public List<Notification> getUnreadNotifications(Integer userId) {
        return notificationRepository.findByUserIduserAndSeenFalseOrderByCreatedAtDesc(userId);
    }

}
