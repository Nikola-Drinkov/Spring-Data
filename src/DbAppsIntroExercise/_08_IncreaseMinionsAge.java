package DbAppsIntroExercise;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class _08_IncreaseMinionsAge {
    private static final String GET_MINIONS = "SELECT name,age FROM minions;";
    private static final String UPDATE_MINION_BY_ID_QUERY = "UPDATE minions" +
            " SET name = LOWER(name), age = age+1" +
            " WHERE id = ?;";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final Connection connection = Utils.getConnection();

        List<Integer> minionsIds = Arrays.stream(sc.nextLine().split(" ")).map(Integer::parseInt).toList();
        for(int minionId : minionsIds){
            final PreparedStatement updateMinion = connection.prepareStatement(UPDATE_MINION_BY_ID_QUERY);
            updateMinion.setInt(1,minionId);
            updateMinion.executeUpdate();
        }
        final PreparedStatement getChangedMinion = connection.prepareStatement(GET_MINIONS);
        final  ResultSet minionResult = getChangedMinion.executeQuery();
        while (minionResult.next()) {
            System.out.printf("%s %d%n", minionResult.getString("name"), minionResult.getInt("age"));
        }
        connection.close();
    }
}
