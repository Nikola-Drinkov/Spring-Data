package entities;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "player_statistics")

public class PlayerStatistic implements Serializable {

    @Id
    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    @Id
    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;

    @Column
    private Integer playerAssists;

    @Column
    private Integer scoredGoals;

    @Column
    private Integer playedMinutes;

}
