package DbAppsIntro;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class AccessingSoftuni {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        Properties properties = new Properties();
        properties.setProperty("user","root");
        properties.setProperty("password","1234");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni",properties);

        PreparedStatement statement = connection.prepareStatement("SELECT first_name, last_name FROM employees WHERE salary > ?");
        double salary = Double.parseDouble(sc.nextLine());

        statement.setDouble(1,salary);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString(1)+" "+resultSet.getString(2));
        }
    }
}
