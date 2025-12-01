

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root";
        String password ="yearup";


        String query = "SELECT * FROM Products WHERE ProductID = ? OR ProductName LIKE ?";
        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(query);

//            System.out.printf("Hey, this is a string %s\n", "Okra");
            System.out.print("Hey, type in a name: ");
            String name = scanner.nextLine();

            statement.setInt(1, 1); //auto sanitize mal inputs
            statement.setString(2, "%" + name + "%");

            // Executing query
            ResultSet results = statement.executeQuery();

            // Processing the result set
            while (results.next()) {
                // Replace with your column names and types
                System.out.println(results.getString("ProductName"));
            }

            // Closing resources
            results.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}