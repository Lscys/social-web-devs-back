package git.red.com.dto;

import git.red.com.models.Technologies;
import git.red.com.models.User;
import lombok.Data;

import java.util.Set;

@Data
public class PostDto {

    private String title;

    private String description;

    private String img;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private Set<Technologies> technologies;

    public Set<Technologies> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(Set<Technologies> technologies) {
        this.technologies = technologies;
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
}
