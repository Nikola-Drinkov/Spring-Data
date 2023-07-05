import entities.Project;

import javax.persistence.EntityManager;
import java.util.Comparator;
import java.util.List;

public class _09_FindLatest10 {
    private static final String projectFormat = "Project name: %s%n" +
            "       Project Description: %s%n" +
            "       Project Start Date: %s%n" +
            "       Project End Date: %s%n";
    public static void main(String[] args) {
        final EntityManager entityManager = Utils.factory.createEntityManager();
        List<Project> projects = entityManager.createQuery("FROM Project ORDER BY startDate DESC", Project.class)
                .setMaxResults(10)
                .getResultList();
        projects.stream().sorted(Comparator.comparing(Project::getName)).forEach(p-> System.out.printf(projectFormat,p.getName(),p.getDescription(),p.getStartDate(),p.getEndDate()));
        entityManager.close();
    }
}
