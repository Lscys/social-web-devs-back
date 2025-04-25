package git.red.com.response;

import git.red.com.models.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class PostResponseDto {

    private Integer idrelease;
    private String title;
    private String description;
    private Set<Technologies> technologies;
    private UserResponse user;
    private PostStats postStats;
    private List<Comment> comments;
    private List<PostLike> likes;
    private LocalDateTime createdAt;

    public PostResponseDto(Release release) {
        this.idrelease = release.getIdrelease();
        this.title = release.getTitle();
        this.description = release.getDescription();
        this.technologies = release.getTechnologies();
        User userEntity = release.getUser();
        this.user = new UserResponse(userEntity.getIduser(), userEntity.getName(), userEntity.getLast_name(), userEntity.getImage());
        this.postStats = release.getPostStats();
        this.comments = release.getComments();
        this.likes = release.getLikes();
        this.createdAt = release.getCreatedAt();
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

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }

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
}
