package DbAppsIntroExercise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class _07_PrintAllMinionNames {
    private static final String GET_MINION_NAMES_QUERY = "SELECT name FROM minions;";
    public static void main(String[] args) throws SQLException {
        final Connection connection = Utils.getConnection();
        final PreparedStatement getMinions = connection.prepareStatement(GET_MINION_NAMES_QUERY);
        final ResultSet minionsRes = getMinions.executeQuery();

        List<String> minionNames = new ArrayList<>();
        while (minionsRes.next()) minionNames.add(minionsRes.getString(1));

        int first = 0; int last = minionNames.size()-1;
        for(int i=0; i<minionNames.size()/2; i++){
            if(first==last) break;
            System.out.println(minionNames.get(first));
            System.out.println(minionNames.get(last));
            first++;
            last--;
        }
        connection.close();
    }
}
