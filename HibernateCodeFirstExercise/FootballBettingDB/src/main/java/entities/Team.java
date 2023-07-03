package entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "teams")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;

    @Basic
    private String logo;

    @Basic
    private String initials;

    @ManyToOne
    @JoinColumn(name = "primary_kit_color")
    private Color primaryKitColor;

    @ManyToOne
    @JoinColumn(name = "secondary_kit_color")
    private Color secondaryKitColor;

    @ManyToOne
    @JoinColumn(name = "town_id")
    private Town town;

    @Column
    private BigDecimal budget;

    @OneToMany(targetEntity = Player.class, mappedBy = "team")
    List<Player> players;

}
