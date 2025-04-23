package git.red.com.services;

import git.red.com.dto.LikeMessageDto;
import git.red.com.models.*;
import git.red.com.repository.LikeRepository;
import git.red.com.repository.NotificationRepository;
import git.red.com.repository.ReleaseRepository;
import git.red.com.repository.UserRepository;
import git.red.com.response.LikeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final ReleaseRepository releaseRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public LikeService(LikeRepository likeRepository, ReleaseRepository releaseRepository, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.likeRepository = likeRepository;
        this.releaseRepository = releaseRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    public LikeResponse registerLike(LikeMessageDto message) {

        // Buscar las entidades completas
        Release post = releaseRepository.findById(message.getPostId())
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        User user = userRepository.findById(message.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        boolean alreadyLiked = likeRepository.findByPost_IdreleaseAndUser_Iduser(message.getPostId(), message.getUserId()).isPresent();

        if (alreadyLiked) {
            // Ya dio like, devolver estado actual sin hacer nada
            int existingLikes = likeRepository.countLikesByPostId(message.getPostId());
            return new LikeResponse(message.getPostId(), message.getUserId(), existingLikes, message.getAction()); // acción puede mantenerse en "like"
        }

        PostLike like = new PostLike();
        like.setPost(post);
        like.setUser(user);
        likeRepository.save(like);

        int totalLikes = likeRepository.countLikesByPostId(message.getPostId());

        // Actualizar y guardar el PostStats
        PostStats stats = post.getPostStats();
        stats.setLikesCount(totalLikes);
        // Guardar el Release, que a su vez guarda el PostStats
        releaseRepository.save(post); // Cascade debería guardar stats también

        // Crear notificación solo si el usuario que da like es diferente al dueño del post
        if (!user.getIduser().equals(post.getUser().getIduser())) {
            Notification notification = new Notification();
            notification.setUser(post.getUser()); // dueño del post
            notification.setTitle("¡Nuevo like!");
            notification.setMessage(user.getName()+" "+ user.getLast_name() +" le dio like a tu publicación \"" + post.getTitle() + "\"");
            notification.setCreatedAt(LocalDateTime.now());
            notificationRepository.save(notification);

        }

        return new LikeResponse(message.getPostId(), message.getUserId(), totalLikes, message.getAction());
    }

    public LikeResponse removeLike(LikeMessageDto message) {

        // Buscar el post y usuario
        Release post = releaseRepository.findById(message.getPostId())
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        User user = userRepository.findById(message.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Buscar el like existente
        PostLike existingLike = likeRepository.findByPost_IdreleaseAndUser_Iduser(message.getPostId(), message.getUserId())
                .orElseThrow(() -> new RuntimeException("El like no existe"));

        // Eliminar el like
        likeRepository.delete(existingLike);

        // Contar los likes actualizados
        int totalLikes = likeRepository.countLikesByPostId(message.getPostId());

        // Actualizar y guardar el PostStats
        PostStats stats = post.getPostStats();
        stats.setLikesCount(totalLikes);
        releaseRepository.save(post);

        return new LikeResponse(message.getPostId(), message.getUserId(), totalLikes, message.getAction());
    }




}
