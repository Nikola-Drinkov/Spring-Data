package DbAppsIntroExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {
    private static final String GET_TOWNS_BY_COUNTRY_QUERY = "SELECT name FROM towns WHERE country = ?;";
    private static final String UPDATE_TOWNS = "UPDATE towns SET name = UPPER(name) WHERE country = ?";
    private static final String  AFFECTED_TOWNS = "%d town names were affected.%n";
    private static final String NO_TOWNS_AFFECTED = "No town names were affected.";

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        final Connection connection = Utils.getConnection();

        String country = sc.nextLine();
        final PreparedStatement getTowns = connection.prepareStatement(GET_TOWNS_BY_COUNTRY_QUERY);
        getTowns.setString(1, country);
        final ResultSet townsResult = getTowns.executeQuery();

        final PreparedStatement updateTowns = connection.prepareStatement(UPDATE_TOWNS);
        updateTowns.setString(1, country);
        final int updatedCount = updateTowns.executeUpdate();

        if (updatedCount>0) {
            List<String> towns = new ArrayList<>();
            System.out.printf(AFFECTED_TOWNS,updatedCount);
            while (townsResult.next()) towns.add(townsResult.getString("name"));
            System.out.println(towns);
        }
        else{
            System.out.println(NO_TOWNS_AFFECTED);
        }
    }
}
