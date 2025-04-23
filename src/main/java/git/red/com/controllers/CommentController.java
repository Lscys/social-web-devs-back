package git.red.com.controllers;


import git.red.com.dto.CommentDto;
import git.red.com.dto.UpdateCommentDto;
import git.red.com.models.Comment;
import git.red.com.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create")
    public ResponseEntity<Comment> createComment (@RequestBody CommentDto request) {
        Comment comment = commentService.createComment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPost(@PathVariable Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPost(postId));
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody UpdateCommentDto updateCommentDto) {
        Comment updateComment = commentService.updateComment(updateCommentDto);
        return ResponseEntity.ok(updateComment);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentId,
            @RequestParam Integer userId
    ) {
        commentService.deleteComment(commentId, userId);
        return ResponseEntity.noContent().build();
    }
}
