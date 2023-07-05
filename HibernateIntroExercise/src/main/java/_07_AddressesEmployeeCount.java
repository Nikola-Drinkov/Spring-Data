import entities.Address;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

public class _07_AddressesEmployeeCount {
    private static final String addressFormat = "%s - %d employees%n";
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.factory.createEntityManager();
        List<Address> addresses = entityManager.createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();
        addresses.forEach(a-> System.out.printf(addressFormat,a.getText(),a.getEmployees().size()));
        entityManager.close();
    }
}
