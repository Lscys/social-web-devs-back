package git.red.com.controllers;

import git.red.com.dto.PostDto;
import git.red.com.dto.UserDto;
import git.red.com.models.Release;
import git.red.com.models.User;
import git.red.com.response.PostResponseDto;
import git.red.com.services.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
public class ReleaseController {

    @Autowired
    private ReleaseService releaseService;

    @PostMapping("/create")
    private Release createPost (@RequestBody PostDto postDto) {
        return releaseService.createRelease(postDto);
    }

    @GetMapping("/all")
    private Page<PostResponseDto> listAllPosts (@RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        return releaseService.getAllRelease(pageable);
    }

    @PutMapping("/update/{idrelease}")
    public ResponseEntity<Release> updatePost(@PathVariable Integer idrelease, @RequestBody PostDto postDto) {
        Release updated = releaseService.updateRelease(idrelease, postDto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/delete/{idrelease}")
    public ResponseEntity<?> deletePost(@PathVariable Integer idrelease, @RequestParam Integer userId) {
        releaseService.deleteRelease(idrelease, userId);
        return ResponseEntity.ok().build();
    }


}
