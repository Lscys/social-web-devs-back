package git.red.com.services;

import git.red.com.dto.PostDto;
import git.red.com.models.PostStats;
import git.red.com.models.Release;
import git.red.com.models.Technologies;
import git.red.com.models.User;
import git.red.com.repository.LikeRepository;
import git.red.com.repository.ReleaseRepository;
import git.red.com.repository.TechnologiesRepository;
import git.red.com.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private LikeRepository likeRepository;


    public Page<Release> getAllRelease (Pageable pageable) {
        return releaseRepository.findAll(pageable);
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

    @Transactional
    public void deleteRelease(Integer idrelease, Integer userId) {
        Release release = releaseRepository.findById(idrelease)
                .orElseThrow(() -> new RuntimeException("El post no existe con id: " + idrelease));

        if (!release.getUser().getIduser().equals(userId)) {
            throw new RuntimeException("No tienes permiso para eliminar este post");
        }

        releaseRepository.delete(release);
    }

    @Transactional
    public Release updateRelease(Integer idrelease, PostDto postDto) {
        Release release = releaseRepository.findById(idrelease)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        release.setTitle(postDto.getTitle());
        release.setDescription(postDto.getDescription());

        // Actualizar tecnologías
        Set<Technologies> technologiesSet = new HashSet<>(
                technologiesRepository.findAllById(postDto.getTechnologiesIds())
        );
        release.setTechnologies(technologiesSet);

        // Actualizar imagen (en PostStats)
        if (release.getPostStats() != null) {
            release.getPostStats().setImageUrl(postDto.getImageUrl());
        }

        return releaseRepository.save(release);
    }


}
