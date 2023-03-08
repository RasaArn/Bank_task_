package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Scanner;



class Bank {
    private static final String URL = "jdbc:mysql://localhost:3306/banktask";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";
    private Connection connection;

    public Bank(Connection connection) {
        this.connection = connection;
    }

    public static void findUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the personal ID of the user you would like to find:");
        long personalId = scanner.nextLong();
        scanner.nextLine();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM bankUsers WHERE personalId=?");
            statement.setLong(1, personalId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");
                double balance = rs.getDouble("balance");
                long accountNumber = rs.getLong("accountNumber");

                System.out.println("User found:");
                System.out.println("Full name: " + fullName);
                System.out.println("Password: " + password);
                System.out.println("Phone number: " + phoneNumber);
                System.out.println("Email: " + email);
                System.out.println("Balance: " + balance);
                System.out.println("Account number: " + accountNumber);
            } else {
                System.out.println("User not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}