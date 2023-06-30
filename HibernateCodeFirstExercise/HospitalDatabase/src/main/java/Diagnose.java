import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String name;

    @Column(columnDefinition = "TEXT")
    private String comments;

    @ManyToMany(targetEntity = Patient.class, mappedBy = "diagnoses")
    List<Patient> patients;
}
