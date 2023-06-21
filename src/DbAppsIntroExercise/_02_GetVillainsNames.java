package DbAppsIntroExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _02_GetVillainsNames {
    private static final String query = "SELECT name AS villain_name, COUNT(DISTINCT minion_id) AS minions_count" +
            " FROM villains" +
            " JOIN minions_villains mv on villains.id = mv.villain_id" +
            " GROUP BY villain_id" +
            " HAVING minions_count > 15" +
            " ORDER BY minions_count DESC;";

    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getConnection();
        final PreparedStatement statement = connection.prepareStatement(query);
        final ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("villain_name")+" "+resultSet.getInt("minions_count"));
        }
        connection.close();
    }
}
