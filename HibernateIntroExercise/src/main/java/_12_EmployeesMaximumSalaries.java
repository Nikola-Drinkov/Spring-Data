import entities.Department;

import javax.persistence.EntityManager;

public class _12_EmployeesMaximumSalaries {
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.factory.createEntityManager();
        entityManager.createQuery("SELECT department.name, max(salary) " +
                "FROM Employee GROUP BY department.name " +
                "HAVING max(salary) NOT BETWEEN 30000 AND 70000",Object[].class)
                .getResultList()
                .forEach(objects -> System.out.println(objects[0]+" "+objects[1]));
        entityManager.close();
    }
}
