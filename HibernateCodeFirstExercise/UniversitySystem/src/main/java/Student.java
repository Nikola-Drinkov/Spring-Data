import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student extends Person{
    @Basic
    private Double averageGrade;

    @Basic
    private Boolean attendance;

    @ManyToMany(targetEntity = Course.class, mappedBy = "students")
    private List<Course> courses;
    public Student(Integer id, String firstName, String lastName, String phoneNumber,Double averageGrade, Boolean attendance) {
        super(id,firstName,lastName,phoneNumber);
        this.averageGrade = averageGrade;
        this.attendance = attendance;
    }
}
