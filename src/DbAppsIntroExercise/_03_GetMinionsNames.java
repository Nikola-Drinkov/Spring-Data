package DbAppsIntroExercise;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class _03_GetMinionsNames {
    private static final String query = "SELECT DISTINCT v.name AS villain_name, m.name AS minion_name, m.age AS minion_age" +
            " FROM villains v" +
            " JOIN minions_villains mv on v.id = mv.villain_id" +
            " JOIN minions m on m.id = mv.minion_id" +
            " WHERE villain_id = ?" +
            " ORDER BY minion_name;";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        final Connection connection = Utils.getConnection();
        final PreparedStatement statement = connection.prepareStatement(query);
        int id = Integer.parseInt(sc.nextLine());
        statement.setInt(1,id);

        final ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            System.out.println("Villain: "+resultSet.getString("villain_name"));
            int count = 0;
            while (resultSet.next()){
                count++;
                System.out.println(count+". "+resultSet.getString("minion_name")+" "+resultSet.getInt("minion_age"));
            }
        }
        else{
            System.out.println("No villain with ID "+id+" exists in the database.");
        }
        connection.close();
    }
}
