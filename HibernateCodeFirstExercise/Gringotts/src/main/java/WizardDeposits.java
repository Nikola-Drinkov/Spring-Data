import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "wizard_deposits")
public class WizardDeposits {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "first_name",length = 50)
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 60)
    private String lastName;
    @Column(columnDefinition = "TEXT",length = 1000)
    private String notes;
    @Column(nullable = false)
    private Integer age;
    @Column(name = "magic_wand_creator",length = 100)
    private String magicWandCreator;
    @Column(name = "magic_wand_size")
    private Integer magicWandSize;
    @Column(name = "deposit_group",length = 20)
    private String depositGroup;
    @Column(name = "deposit_start_date",columnDefinition = "DATETIME")
    private Date depositStartDate;
    @Column(name = "deposit_amount")
    private BigDecimal depositAmount;
    @Column(name = "deposit_interest")
    private BigDecimal depositInterest;
    @Column(name = "deposit_charge")
    private BigDecimal depositCharge;
    @Column(name = "deposit_expiration_date",columnDefinition = "DATETIME")
    private Date depositExpirationDate;
    @Column(name = "is_deposit_expired")
    private Boolean isDepositExpired;
}
