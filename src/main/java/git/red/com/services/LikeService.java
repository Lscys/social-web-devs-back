package git.red.com.services;

import git.red.com.dto.LikeMessageDto;
import git.red.com.models.PostLike;
import git.red.com.models.PostStats;
import git.red.com.models.Release;
import git.red.com.models.User;
import git.red.com.repository.LikeRepository;
import git.red.com.repository.ReleaseRepository;
import git.red.com.repository.UserRepository;
import git.red.com.response.LikeResponse;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final ReleaseRepository releaseRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, ReleaseRepository releaseRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.releaseRepository = releaseRepository;
        this.userRepository = userRepository;
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
