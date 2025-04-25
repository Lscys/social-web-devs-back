package git.red.com.response;

import lombok.Data;

@Data
public class UserResponse {

    private Integer iduser;

    private String name;

    private String last_name;

    private String image;

    public UserResponse(Integer iduser, String name, String last_name, String image) {
        this.iduser = iduser;
        this.name = name;
        this.last_name = last_name;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getIduser() {
        return iduser;
    }

    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
