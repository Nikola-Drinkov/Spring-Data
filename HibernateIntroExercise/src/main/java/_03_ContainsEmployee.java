import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class _03_ContainsEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final EntityManager entityManager = Utils.factory.createEntityManager();

        entityManager.getTransaction().begin();
        String name = sc.nextLine();
        String result = entityManager.createQuery("FROM Employee WHERE CONCAT(firstName, ' ',lastName) = :fullName", Employee.class)
                .setParameter("fullName",name)
                        .getResultList()
                                .isEmpty() ? "No":"Yes";
        System.out.println(result);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
