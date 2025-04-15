package git.red.com.controllers;

import git.red.com.dto.PostDto;
import git.red.com.dto.UserDto;
import git.red.com.models.Release;
import git.red.com.models.User;
import git.red.com.services.ReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private List<Release> listAllPosts () {
        return releaseService.getAllRelease();
    }


}
