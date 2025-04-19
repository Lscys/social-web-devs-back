package git.red.com.controllers;

import git.red.com.dto.LikeMessageDto;
import git.red.com.response.LikeResponse;
import git.red.com.services.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reaction")
@CrossOrigin(origins = "*")
public class ReactionController {

    private final LikeService likeService;

    public ReactionController(LikeService likeService) {
        this.likeService = likeService;
    }

    @MessageMapping("/like")
    @SendTo("/topic/post-likes")
    public LikeResponse handleLike(LikeMessageDto message) {
        if ("like".equalsIgnoreCase(message.getAction())) {
            LikeResponse response = likeService.registerLike(message);
            response.setAction("like");
            return response;
        } else if ("unlike".equalsIgnoreCase(message.getAction())) {
            LikeResponse response = likeService.removeLike(message);
            response.setAction("unlike");
            return response;
        } else {
            throw new IllegalArgumentException("Acción no válida: " + message.getAction());
        }
    }


}
