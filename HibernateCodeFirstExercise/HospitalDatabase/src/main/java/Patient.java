import java.util.List;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic
    private String firstName;

    @Basic
    private String lastName;

    @Basic
    private String address;

    @Basic
    private String email;

    @Column(columnDefinition = "DATE")
    private Date dateOfBirth;

    @Column(columnDefinition = "BLOB")
    private byte[] picture;

    @Basic
    private Boolean insured;

    @ManyToMany
            @JoinTable(name = "patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id",referencedColumnName = "id"))
    List<Medicament> medicaments;

    @OneToMany(targetEntity = Visitation.class, mappedBy = "patient")
    List<Visitation> visitations;

    @ManyToMany
    @JoinTable(name = "patients_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id",referencedColumnName = "id"))
    List<Diagnose> diagnoses;
}
