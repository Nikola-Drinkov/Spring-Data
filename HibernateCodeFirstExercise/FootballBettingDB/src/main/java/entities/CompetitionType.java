package entities;

import javax.persistence.*;

@Entity
@Table(name = "competition_type")
public class CompetitionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String type;
}
