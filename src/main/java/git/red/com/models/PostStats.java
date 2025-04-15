package git.red.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "post_stats")
public class PostStats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "likes_count")
    private int likesCount = 0;

    @Column(name = "is_starred")
    private boolean isStarred = false;

    @Column(name = "image_url")
    private String imageUrl;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "post_id", unique = true)
    private Release post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isStarred() {
        return isStarred;
    }

    public void setStarred(boolean starred) {
        isStarred = starred;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Release getPost() {
        return post;
    }

    public void setPost(Release post) {
        this.post = post;
    }
}
