

import java.sql.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);


        String url = "jdbc:mysql://127.0.0.1:3306/northwind";
        String user = "root";
        String password = "yearup";

        Connection connection = null;


        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            int choice = 1;
            while (choice != 0) {

                System.out.println("\nWhat do you want to do?");
                System.out.println("1) Display all products");
                System.out.println("2) Display all customers");
                System.out.println("0) Exit");
                System.out.println("Select an option");
                choice = Integer.parseInt(scanner.nextLine());

                if (choice == 1) {
                    displayProducts(connection);
                } else if (choice == 2) {
                    displayCustomers(connection);
                } else if (choice == 0) {
                    System.out.println("Goodbye!");
                } else {
                    System.out.println("Invalid option! Try again!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error connecting to data base:");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void displayProducts(Connection connection) {
        String query = "SELECT ProductName FROM products ORDER BY ProductName";

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            System.out.println("\n--- ALL PRODUCTS ---");

            while (results.next()) {
                System.out.println(results.getString("ProductName"));
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("ERROR reading products:");
            e.printStackTrace();
        }
    }


    public static void displayCustomers(Connection connection) {
        String query = """
                SELECT ContactName, CompanyName, City, Country, Phone
                FROM customers
                ORDER BY Country, ContactName
                """;

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery(query);

            System.out.println("\n--- ALL CUSTOMERS ---");
            System.out.println("Contact Name | Company | City | Country | Phone");
            System.out.println("------------------------------------------------------------");

            while (results.next()) {
                String cName = results.getString("ContactName");
                String company = results.getString("CompanyName");
                String city = results.getString("City");
                String country = results.getString("Country");
                String phone = results.getString("Phone");

                System.out.printf("%s | %s | %s | %s | %s%n",
                        cName, company, city, country, phone);
            }

            results.close();
            statement.close();

        } catch (SQLException e) {
            System.out.println("ERROR reading customers:");
            e.printStackTrace();
        }
    }
}




