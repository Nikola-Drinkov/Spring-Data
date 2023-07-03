package entities;

import javax.persistence.*;
import java.lang.annotation.Target;
import java.util.List;

@Entity
@Table(name = "towns")
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(targetEntity = Team.class, mappedBy = "town")
    private List<Team> teams;
}
