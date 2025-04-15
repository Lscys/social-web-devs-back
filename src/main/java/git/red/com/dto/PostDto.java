package git.red.com.dto;

import git.red.com.models.Technologies;
import git.red.com.models.User;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class PostDto {
    private String title;
    private String description;
    private List<Integer> technologiesIds; // IDs de tecnologías seleccionadas
    private Integer userId;                // ID del usuario que postea
    private String imageUrl;           // Imagen del post

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

    public List<Integer> getTechnologiesIds() {
        return technologiesIds;
    }

    public void setTechnologiesIds(List<Integer> technologiesIds) {
        this.technologiesIds = technologiesIds;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
