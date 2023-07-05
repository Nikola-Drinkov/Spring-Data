import entities.Employee;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class _10_IncreaseSalaries {
    private static final String employeeFormat = "%s %s ($%s)%n";
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.factory.createEntityManager();
        entityManager.getTransaction().begin();
        List<Employee> employees = entityManager.createQuery("FROM Employee WHERE department.name IN('Engineering','Tool Design','Marketing','Information Services')", Employee.class).getResultList();
        for(Employee employee : employees){
            employee.setSalary(employee.getSalary().add(employee.getSalary().multiply(BigDecimal.valueOf(0.12))));
            entityManager.flush();
        }
        entityManager.getTransaction().commit();
        employees.forEach(e-> System.out.printf(employeeFormat,e.getFirstName(),e.getLastName(),e.getSalary()));
        entityManager.close();
    }
}
