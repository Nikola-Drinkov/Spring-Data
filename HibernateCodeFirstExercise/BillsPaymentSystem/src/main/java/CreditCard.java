import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class CreditCard extends BillingDetail{
    @Basic
    private String type;

    @Basic
    private Integer expirationMonth;

    @Basic
    private Integer expirationYear;

}
