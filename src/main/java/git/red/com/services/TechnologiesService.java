package git.red.com.services;

import git.red.com.models.Technologies;
import git.red.com.repository.TechnologiesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TechnologiesService {

    private final TechnologiesRepository technologiesRepository;

    public TechnologiesService(TechnologiesRepository technologiesRepository) {
        this.technologiesRepository = technologiesRepository;
    }

    public List<Technologies> getAllTechnologies () {
        return technologiesRepository.findAll();
    }

}
