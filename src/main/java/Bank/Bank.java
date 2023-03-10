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
    protected Bank(Connection connection) {
       this.connection = connection;
   }
    //}

    protected static void findUser() {
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
    protected static void changeUserBalance() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Please enter customer's account number:");
    long accountNumber = scanner.nextLong();
    scanner.nextLine();

    System.out.println("Please enter the amount you would like to add or withdraw (use a negative value to withdraw):");
    double amount = scanner.nextDouble();
    scanner.nextLine();

    try {
        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        PreparedStatement statement = conn.prepareStatement("SELECT balance FROM bankUsers WHERE accountNumber=?");
        statement.setLong(1, accountNumber);
        ResultSet rs = statement.executeQuery();

        if (rs.next()) {
            double balance = rs.getDouble("balance");
            double newBalance = balance + amount;

            if (newBalance < 0) {
                System.out.println("Balance will be negative. Can not execute this operation.");
            } else {
                PreparedStatement updateStatement = conn.prepareStatement("UPDATE bankUsers SET balance=? WHERE accountNumber=?");
                updateStatement.setDouble(1, newBalance);
                updateStatement.setLong(2, accountNumber);
                int numRowsUpdated = updateStatement.executeUpdate();
                if (numRowsUpdated > 0) {
                    System.out.println("Balance updated successfully.");
                } else {
                    System.out.println("Failed to update balance.");
                }
                System.out.println("New balance for the user is: " + newBalance);

            }
        } else {
            System.out.println("Invalid accountNumber.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    protected static void updatePersonalInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter personal ID number:");
        long personalId = scanner.nextLong();
        scanner.nextLine();

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement checkPersonalIdStatement = conn.prepareStatement("SELECT * FROM bankUsers WHERE personalId=?");
             PreparedStatement updateStatement = conn.prepareStatement("UPDATE bankUsers SET fullName=?, phoneNumber=?, email=? WHERE personalId=?")) {

            checkPersonalIdStatement.setLong(1, personalId);
            ResultSet rs = checkPersonalIdStatement.executeQuery();

            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String phoneNumber = rs.getString("phoneNumber");
                String email = rs.getString("email");

                System.out.println("Please enter new name or surname (or press Enter to skip):");
                String newFullName = scanner.nextLine();
                if (newFullName.isEmpty()) {
                    newFullName = fullName;
                }

                System.out.println("Please enter new phone number (or press Enter to skip):");
                String newPhoneNumber = scanner.nextLine();
                if (newPhoneNumber.isEmpty()) {
                    newPhoneNumber = phoneNumber;
                }

                System.out.println("Please enter new email address (or press Enter to skip):");
                String newEmail = scanner.nextLine();
                if (newEmail.isEmpty()) {
                    newEmail = email;
                }

                updateStatement.setString(1, newFullName);
                updateStatement.setString(2, newPhoneNumber);
                updateStatement.setString(3, newEmail);
                updateStatement.setLong(4, personalId); // use personalId instead of accountNumber
                int numRowsUpdated = updateStatement.executeUpdate();
                if (numRowsUpdated > 0) {
                    System.out.println("Personal information updated successfully.");
                } else {
                    System.out.println("Failed to update personal information.");
                }
            } else {
                System.out.println("Invalid personalId.");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected static void deleteUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter the account number of the user you would like to delete:");
        long accountNumber = scanner.nextLong();
        scanner.nextLine();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Check if the account number exists in the database
            PreparedStatement checkAccountNumberStatement = conn.prepareStatement("SELECT * FROM bankUsers WHERE accountNumber=?");
            checkAccountNumberStatement.setLong(1, accountNumber);
            ResultSet rs = checkAccountNumberStatement.executeQuery();

            if (rs.next()) {
                // Delete the user from the database
                PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM bankUsers WHERE accountNumber=?");
                deleteStatement.setLong(1, accountNumber);
                int numRowsDeleted = deleteStatement.executeUpdate();
                if (numRowsDeleted > 0) {
                    System.out.println("User deleted successfully.");
                } else {
                    System.out.println("Failed to delete user.");
                }
            } else {
                System.out.println("Invalid account number.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}