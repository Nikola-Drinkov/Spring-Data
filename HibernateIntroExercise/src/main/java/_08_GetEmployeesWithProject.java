import entities.Employee;
import entities.Project;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class _08_GetEmployeesWithProject {
    private static final String employeeFormat = "%s %s - %s%n";
    private static final String projectsFormat = "      %s%n";
    public static void main(String[] args) {
        final Scanner sc = new Scanner(System.in);
        final EntityManager entityManager = Utils.factory.createEntityManager();
        int employeeId = Integer.parseInt(sc.nextLine());

        Employee employee = entityManager.createQuery("FROM Employee WHERE id = :employeeId",Employee.class)
                .setParameter("employeeId",employeeId)
                .getSingleResult();
        System.out.printf(employeeFormat,employee.getFirstName(),employee.getLastName(),employee.getJobTitle());
        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(p-> System.out.printf(projectsFormat,p.getName()));
        entityManager.close();
    }
}
