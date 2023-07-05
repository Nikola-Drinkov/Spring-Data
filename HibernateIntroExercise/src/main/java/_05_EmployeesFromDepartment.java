import entities.Employee;

import javax.persistence.EntityManager;
import java.util.List;

public class _05_EmployeesFromDepartment {
    private static final String EMPLOYEE_FORMAT = "%s %s from %s - $%s%n";

    public static void main(String[] args) {
        final EntityManager entityManager = Utils.factory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE department.name = 'Research and Development' ORDER BY salary,id").getResultList();
        employees.forEach(e-> System.out.printf(EMPLOYEE_FORMAT,e.getFirstName(),e.getLastName(),e.getDepartment().getName(),e.getSalary()));
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
