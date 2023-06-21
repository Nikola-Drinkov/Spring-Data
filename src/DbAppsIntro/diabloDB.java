package DbAppsIntro;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class diabloDB {
    public static void main(String[] args) throws SQLException {

        Scanner sc = new Scanner(System.in);
        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","1234");

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo",properties);

        PreparedStatement statement = connection.prepareStatement("SELECT first_name, last_name, COUNT(game_id) AS count" +
                " FROM users_games" +
                " JOIN users u on users_games.user_id = u.id" +
                " WHERE u.last_name = ?" +
                " GROUP BY first_name,last_name;");

        String lastName = sc.nextLine();
        statement.setString(1, lastName);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            System.out.println("User: "+lastName);
            System.out.printf("%s %s has played %d games",resultSet.getString("first_name"),resultSet.getString("last_name"),resultSet.getInt("count"));
        }
        else {
            System.out.println("No such user exists");
        }
    }
}
