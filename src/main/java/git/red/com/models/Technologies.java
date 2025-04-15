package git.red.com.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
@Table(name = "technologies")
public class Technologies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idtech;

    @Column
    private String name;

    @Column
    private String image;

    @JsonIgnore
    @ManyToMany(mappedBy = "technologies")
    private Set<Release> release;

    public Integer getIdtech() {
        return idtech;
    }

    public void setIdtech(Integer idtech) {
        this.idtech = idtech;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Release> getRelease() {
        return release;
    }

    public void setRelease(Set<Release> release) {
        this.release = release;
    }
}
