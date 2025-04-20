package git.red.com.controllers;

import git.red.com.models.Notification;
import git.red.com.repository.NotificationRepository;
import git.red.com.services.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    public NotificationController(NotificationRepository notificationRepository, NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }

    @GetMapping("/user/{userId}")
    public List<Notification> getUserNotifications(@PathVariable Integer userId) {
        return notificationRepository.findByUserIduserOrderByCreatedAtDesc(userId);
    }

    // Marcar una notificación como leída
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<String> markNotificationAsRead(
            @PathVariable Integer notificationId,
            @RequestParam("userId") Integer userId
    ) {
        boolean success = notificationService.markAsRead(notificationId, userId);
        if (success) {
            return ResponseEntity.ok("Notificación marcada como leída");
        } else {
            return ResponseEntity.badRequest().body("No se encontró la notificación o no pertenece al usuario");
        }
    }

//    // (Opcional) Marcar todas las notificaciones de un usuario como leídas
//    @PutMapping("/user/{userId}/read-all")
//    public ResponseEntity<String> markAllAsRead(@PathVariable Integer userId) {
//        List<Notification> unreadNotifications = notificationService.getUnreadNotificationsByUser(userId);
//        for (Notification notification : unreadNotifications) {
//            notification.setSeen(true);
//        }
//        notificationService.saveAll(unreadNotifications);
//        return ResponseEntity.ok("Todas las notificaciones han sido marcadas como leídas");
//    }
//
//    @GetMapping("/user/{userId}/unread")
//    public List<Notification> getUnreadNotifications(@PathVariable Integer userId) {
//        return notificationService.getUnreadNotifications(userId);
//    }

}
