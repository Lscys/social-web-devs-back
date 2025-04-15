package git.red.com.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "release")
public class Release {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idrelease;

    private String title;

    private String description;

    private String img;

    @ManyToMany
    @JoinTable(
            name = "release_technologies",
            joinColumns = @JoinColumn(name = "release_id"),
            inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    private Set<Technologies> technologies;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Integer getIdrelease() {
        return idrelease;
    }

    public void setIdrelease(Integer idrelease) {
        this.idrelease = idrelease;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Set<Technologies> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technologies> technologies) {
        this.technologies = technologies;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
