package git.red.com.services;

import git.red.com.dto.CommentDto;
import git.red.com.dto.UpdateCommentDto;
import git.red.com.models.Comment;
import git.red.com.models.Notification;
import git.red.com.models.Release;
import git.red.com.models.User;
import git.red.com.repository.CommentRepository;
import git.red.com.repository.NotificationRepository;
import git.red.com.repository.ReleaseRepository;
import git.red.com.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReleaseRepository releaseRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public CommentService(CommentRepository commentRepository, ReleaseRepository releaseRepository, UserRepository userRepository, NotificationRepository notificationRepository) {
        this.commentRepository = commentRepository;
        this.releaseRepository = releaseRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    public Comment createComment(CommentDto request) {
        Release post = releaseRepository.findById(request.getPostId())
                .orElseThrow(() -> new RuntimeException("Post not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);

        // Notificar al autor del post si no es el mismo que comentó
        if (!post.getUser().getIduser().equals(user.getIduser())) {
            Notification notification = new Notification();
            notification.setUser(post.getUser());
            notification.setTitle("Nuevo comentario");
            notification.setMessage(user.getUsername() + " " + user.getLast_name() + " comentó tu publicación \"" + post.getTitle() + "\".");
            notification.setSeen(false);
            notification.setCreatedAt(LocalDateTime.now());

            notificationRepository.save(notification);
        }

        return savedComment;
    }

    public List<Comment> getCommentsByPost(Long postId) {
        return commentRepository.findByPost_Idrelease(postId);
    }

    public Comment updateComment(UpdateCommentDto request) {
        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getIduser().equals(request.getUserId())) {
            throw new RuntimeException("You are not authorized to edit this comment");
        }

        comment.setContent(request.getContent());
        comment.setCreatedAt(LocalDateTime.now());

        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Integer userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getIduser().equals(userId)) {
            throw new RuntimeException("You are not authorized to delete this comment");
        }

        commentRepository.delete(comment);
    }



}
