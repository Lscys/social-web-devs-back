package git.red.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
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

    @OneToOne(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private PostStats postStats;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> likes;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    public PostStats getPostStats() {
        return postStats;
    }

    public void setPostStats(PostStats postStats) {
        this.postStats = postStats;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<PostLike> getLikes() {
        return likes;
    }

    public void setLikes(List<PostLike> likes) {
        this.likes = likes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

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
