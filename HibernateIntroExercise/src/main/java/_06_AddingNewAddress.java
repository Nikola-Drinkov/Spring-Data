import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class _06_AddingNewAddress {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lastName = sc.nextLine();
        final EntityManager entityManager = Utils.factory.createEntityManager();
        entityManager.getTransaction().begin();

        Town town = entityManager.createQuery("FROM Town WHERE id = 1", Town.class).getSingleResult();
        Address newAddress = new Address();
        newAddress.setTown(town);
        newAddress.setEmployees(null);
        newAddress.setText("Vitoshka 15");
        entityManager.persist(newAddress);

        Employee employee = entityManager.createQuery("FROM Employee WHERE lastName = :lastName", Employee.class)
                        .setParameter("lastName", lastName)
                                .getSingleResult();
        employee.setAddress(newAddress);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
