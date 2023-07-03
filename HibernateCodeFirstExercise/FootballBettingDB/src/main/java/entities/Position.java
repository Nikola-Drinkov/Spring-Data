package entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "positions")
public class Position {
    @Id
    @Column(name = "id", length = 2)
    private String position;

    @Column(name = "position_description")
    private String positionDescription;

    @OneToMany(targetEntity = Player.class, mappedBy = "position")
    private List<Player> players;
}
