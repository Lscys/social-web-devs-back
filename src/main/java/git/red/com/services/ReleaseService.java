package git.red.com.services;

import git.red.com.dto.PostDto;
import git.red.com.models.Release;
import git.red.com.repository.ReleaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReleaseService {

    @Autowired
    private ReleaseRepository releaseRepository;

    public List<Release> getAllRelease () {
        return releaseRepository.findAll();
    }

    public Release createRelease (PostDto postDto) {

        Release rea = new Release();

        rea.setTitle(postDto.getTitle());
        rea.setDescription(postDto.getDescription());
        rea.setImg(postDto.getImg());
        rea.setUser(postDto.getUser());
//        rea.setTechnologies(postDto.getTechnologies());

        return releaseRepository.save(rea);

    }

}
