package DbAppsIntroExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {

    private static final String PROCEDURE_QUERY = "CREATE PROCEDURE usp_get_older(minion_id INT)" +
            " BEGIN" +
            "    UPDATE minions SET age = age + 1" +
            "    WHERE id = minion_id;" +
            "    SELECT name, age" +
            "    FROM minions" +
            "    WHERE id = minion_id;" +
            " END";

    private static final String CALL_PROCEDURE_QUERY = "CALL usp_get_older(?);";
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final Connection connection = Utils.getConnection();
        final int minionId = Integer.parseInt(sc.nextLine());

        final PreparedStatement createProcedure = connection.prepareStatement(PROCEDURE_QUERY);
        createProcedure.executeUpdate();

        final PreparedStatement callProcedure = connection.prepareStatement(CALL_PROCEDURE_QUERY);
        callProcedure.setInt(1,minionId);
        ResultSet procedureResult = callProcedure.executeQuery();

        while (procedureResult.next()){
            System.out.println(procedureResult.getString(1)+" "+procedureResult.getString(2));
        }
        connection.close();
    }
}
