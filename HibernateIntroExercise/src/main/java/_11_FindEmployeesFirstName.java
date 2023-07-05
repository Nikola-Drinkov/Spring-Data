import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class _11_FindEmployeesFirstName {
    private static final String employeeFormat = "%s %s - %s - ($%s)%n";
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final EntityManager entityManager = Utils.factory.createEntityManager();
        String start = sc.nextLine();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE firstName LIKE :start",Employee.class)
                .setParameter("start",start+"%")
                .getResultList();
        employees.forEach(e-> System.out.printf(employeeFormat,e.getFirstName(),e.getLastName(),e.getJobTitle(),e.getSalary()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
