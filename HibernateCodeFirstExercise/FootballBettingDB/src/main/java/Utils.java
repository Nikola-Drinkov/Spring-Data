import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utils {
    public static EntityManagerFactory factory = Persistence.createEntityManagerFactory("football_db");
}
