package git.red.com.controllers;


import git.red.com.models.Technologies;
import git.red.com.models.User;
import git.red.com.services.TechnologiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/technologies")
public class TechnologiesController {

    private final TechnologiesService technologiesService;

    public TechnologiesController(TechnologiesService technologiesService) {
        this.technologiesService = technologiesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Technologies>> getAllTechnologies() {
        List <Technologies> technologies = technologiesService.getAllTechnologies();

        return ResponseEntity.ok(technologies);
    }

}
