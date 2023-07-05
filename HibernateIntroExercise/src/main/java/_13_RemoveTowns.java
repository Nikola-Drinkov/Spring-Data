import entities.Employee;

import javax.persistence.EntityManager;
import java.util.Scanner;

public class _13_RemoveTowns {
    private static String singleTownFormat = "%d address in %s deleted";
    private static String manyTownsFormat = "%d addresses in %s deleted";
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManager entityManager = Utils.factory.createEntityManager();
        String town = sc.nextLine();

        int townId = (int) entityManager.createQuery("SELECT id FROM Town WHERE name = :townName")
                .setParameter("townName",town)
                .getSingleResult();

        entityManager.getTransaction().begin();
        entityManager.createQuery("FROM Employee e WHERE e.address.town.name = :townName",Employee.class)
                .setParameter("townName",town).
                getResultList()
                .forEach(e->e.setAddress(null));
        entityManager.flush();

        int deletedTowns = entityManager.createQuery("DELETE FROM Address a WHERE a.town.id = :townId")
                .setParameter("townId",townId)
                .executeUpdate();
        if(deletedTowns==1){
            System.out.printf(singleTownFormat,deletedTowns,town);
        }
        else System.out.printf(manyTownsFormat,deletedTowns,town);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
