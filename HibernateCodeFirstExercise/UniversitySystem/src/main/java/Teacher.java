import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Getter
@NoArgsConstructor
@Setter
@Entity
public class Teacher extends Person{
    @Basic
    private String email;

    @Basic
    private Double salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher(Integer id, String firstName, String lastName, String phoneNumber, String email, Double salaryPerHour) {
        super(id, firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }
}
