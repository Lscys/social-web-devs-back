package git.red.com.response;

import lombok.Data;

@Data
public class LikeResponse {
    private Integer postId;
    private Integer userId;
    private int totalLikes;
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LikeResponse(Integer postId, Integer userId, int totalLikes, String action) {
        this.postId = postId;
        this.userId = userId;
        this.totalLikes = totalLikes;
        this.action = action;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }
}
