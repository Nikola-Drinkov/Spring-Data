import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class BankAccount extends BillingDetail{

    @Basic
    private String name;

    @Basic
    private String swiftCode;
}
