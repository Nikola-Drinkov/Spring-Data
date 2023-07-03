package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(length = 3)
    private String id;

    @Basic
    private String name;

    @OneToMany(targetEntity = Town.class, mappedBy = "country")
    private List<Town> towns;

    @ManyToMany(targetEntity = Continent.class,mappedBy = "countries")
    private List<Continent> continents;

}
