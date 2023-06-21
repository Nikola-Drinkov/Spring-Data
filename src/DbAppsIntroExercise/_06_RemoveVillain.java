package DbAppsIntroExercise;

import javax.lang.model.type.NoType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _06_RemoveVillain {

    private static final String DELETED_VILLAIN = "%s was deleted%n";
    private static final String RELEASED_MINIONS = "%d minions released%n";
    private static final String NO_VILLAIN_FOUND = "No such villain was found";
    private static final String DELETE_VILLAIN_QUERY = "DELETE FROM villains WHERE id = ?;";
    private static final String FIND_VILLAIN_NAME = "SELECT name FROM villains WHERE id = ?;";
    private static final String RELEASE_MINIONS_QUERY = "DELETE  FROM minions_villains" +
            " WHERE villain_id = ?;";
    private static final String GET_MINIONS_COUNT = "SELECT COUNT(*) FROM minions_villains WHERE villain_id = ?";


    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Connection connection = Utils.getConnection();

        int villainId = Integer.parseInt(sc.nextLine());
        final PreparedStatement findVillain = connection.prepareStatement(FIND_VILLAIN_NAME);
        findVillain.setInt(1,villainId);
        final ResultSet villainResult = findVillain.executeQuery();

        if(villainResult.next()){
            connection.setAutoCommit(false);
            try {
                final PreparedStatement getMinionsCount = connection.prepareStatement(GET_MINIONS_COUNT);
                final PreparedStatement releaseMinions = connection.prepareStatement(RELEASE_MINIONS_QUERY);
                final PreparedStatement deleteVillain = connection.prepareStatement(DELETE_VILLAIN_QUERY);
                getMinionsCount.setInt(1,villainId);
                final ResultSet minionsCount = getMinionsCount.executeQuery();
                minionsCount.next();
                releaseMinions.setInt(1, villainId);
                releaseMinions.executeUpdate();
                deleteVillain.setInt(1, villainId);
                deleteVillain.executeUpdate();

                connection.commit();
                System.out.printf(DELETED_VILLAIN,villainResult.getString(1));
                System.out.printf(RELEASED_MINIONS, minionsCount.getInt(1));
            }catch (SQLException e){
                e.printStackTrace();
                connection.rollback();
            }
        }
        else {
            System.out.println(NO_VILLAIN_FOUND);
        }
        connection.close();
    }
}
