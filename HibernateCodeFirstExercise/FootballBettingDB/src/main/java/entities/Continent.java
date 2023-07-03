package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "continents")
public class Continent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;

    @ManyToMany
    @JoinTable(name = "countries_continents", joinColumns =
    @JoinColumn(name = "country_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "continents_id",referencedColumnName = "id"))
    private List<Country> countries;
}
