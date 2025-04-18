package git.red.com.services;

import git.red.com.dto.PostDto;
import git.red.com.models.PostStats;
import git.red.com.models.Release;
import git.red.com.models.Technologies;
import git.red.com.models.User;
import git.red.com.repository.ReleaseRepository;
import git.red.com.repository.TechnologiesRepository;
import git.red.com.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    @Autowired
    private TechnologiesRepository technologiesRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Release> getAllRelease () {
        return releaseRepository.findAll();
    }

    public Release createRelease (PostDto postDto) {

        User user = userRepository.findById(postDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Set<Technologies> technologiesSet = new HashSet<>(technologiesRepository.findAllById(postDto.getTechnologiesIds()));

        Release rea = new Release();
        rea.setTitle(postDto.getTitle());
        rea.setDescription(postDto.getDescription());
        rea.setUser(user);
        rea.setTechnologies(technologiesSet);
        rea.setCreatedAt(LocalDateTime.now());

        // Asignar PostStats
        PostStats stats = new PostStats();
        stats.setImageUrl(postDto.getImageUrl());
        stats.setPost(rea); // Relación bidireccional
        stats.setStarred(false);
        stats.setLikesCount(0);
        rea.setPostStats(stats);



        return releaseRepository.save(rea);

    }

}
