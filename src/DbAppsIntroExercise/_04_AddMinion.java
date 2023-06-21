package DbAppsIntroExercise;

import java.sql.*;
import java.util.Scanner;

public class _04_AddMinion {

    private static final String VILLAIN_ADDED = "Villain %s was added to the database.%n";
    private static final String TOWN_ADDED = "Town %s was added to the database.%n";
    private static final String MINION_ADDED = "Successfully added %s to be minion of %s.%n";
    private static final String GET_TOWN_QUERY = "SELECT * FROM towns WHERE name = ?;";
    private static final String GET_VILLAIN_QUERY = "SELECT * FROM villains WHERE name = ?;";
    private static final String INSERT_TOWN = "INSERT INTO towns(name) VALUES (?);";
    private static final String INSERT_VILLAIN = "INSERT INTO villains(name,evilness_factor) VALUES (?,?);";
    private static final String INSERT_MINION = "INSERT INTO minions(name,age,town_id) VALUES (?,?,?);";
    private static final String GET_TOWN_ID = "SELECT id FROM towns WHERE name = ?;";
    private static final String EVILNESS_FACTOR = "evil";


    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final Connection connection = Utils.getConnection();

        String minionLine = sc.nextLine();
        String minionData = minionLine.split(": ")[1];
        String minionName = minionData.split(" ")[0];
        int minionAge = Integer.parseInt(minionData.split(" ")[1]);
        String minionTown = minionData.split(" ")[2];

        String villainLine = sc.nextLine();
        String villainName = villainLine.split(": ")[1];

        final PreparedStatement getTown = connection.prepareStatement(GET_TOWN_QUERY);
        getTown.setString(1,minionTown);
        final PreparedStatement getVillain = connection.prepareStatement(GET_VILLAIN_QUERY);
        getVillain.setString(1,villainName);

        final ResultSet townResult = getTown.executeQuery();
        final ResultSet villainResult = getVillain.executeQuery();

        if(!townResult.next()){
            PreparedStatement insertTown = connection.prepareStatement(INSERT_TOWN);
            insertTown.setString(1,minionTown);
            insertTown.executeUpdate();
            System.out.printf(TOWN_ADDED,minionTown);
        }
        if(!villainResult.next()){
            PreparedStatement insertVillain = connection.prepareStatement(INSERT_VILLAIN);
            insertVillain.setString(1,villainName);
            insertVillain.setString(2,EVILNESS_FACTOR);
            insertVillain.executeUpdate();
            System.out.printf(VILLAIN_ADDED,villainName);
        }
        final PreparedStatement getTownId = connection.prepareStatement(GET_TOWN_ID);
        getTownId.setString(1,minionTown);
        final ResultSet townIdResult = getTownId.executeQuery();
        int townId = 0;
        if(townIdResult.next()){
            townId = townIdResult.getInt("id");
        }

        final PreparedStatement insertMinion = connection.prepareStatement(INSERT_MINION);
        insertMinion.setString(1,minionName);
        insertMinion.setInt(2,minionAge);
        insertMinion.setInt(3,townId);
        insertMinion.executeUpdate();
        System.out.printf(MINION_ADDED,minionName,villainName);

        connection.close();
    }
}
