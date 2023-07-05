import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class _04_EmployeesWithOver5000 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final EntityManager entityManager = Utils.factory.createEntityManager();

        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE salary>50000", Employee.class)
                .getResultList();
        employees.forEach(e-> System.out.println(e.getFirstName()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
