package DbAppsIntroExercise;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum Utils {
    ;
    static Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty(Constants.NAME_KEY,Constants.NAME_VALUE);
        properties.setProperty(Constants.PASS_KEY,Constants.PASS_VALUE);

        return DriverManager.getConnection(Constants.CONNECTION_URL,properties);
    }
}
