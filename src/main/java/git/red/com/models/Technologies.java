package git.red.com.models;

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

    @ManyToMany(mappedBy = "technologies")
    private Set<Release> release;

}
