package Bank;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UserRegAndLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/banktask";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1234";


    public static void registerUser() {
        Scanner scanner = new Scanner(System.in);

        String fullName = "";
        String password = "";
        String phoneNumber = "";
        long personalId = 0;
        String email = "";
        double balance = 0;


        //System.out.println("Please enter your full name:");
        // fullName = scanner.nextLine();
        Pattern pattern = Pattern.compile("^[a-zA-Z ]+$");
        do {
            System.out.println("Please enter your full name:");
            fullName = scanner.nextLine();
            if (!pattern.matcher(fullName).matches()) {
                System.out.println("Invalid name. Only letters are allowed.");
            }
        } while (!pattern.matcher(fullName).matches());
        System.out.println("Name is registered");


        do {//validating password
            System.out.println("Please enter a password that contains at least one uppercase letter, one lowercase letter, one digit, and is at least 8 characters long:");
            password = scanner.nextLine();

            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {  // The ^ and $ anchors in regex ensure that the regular expression matches the entire input string.
                System.out.println("Password does not meet the requirements. Please try again.");
            }
        } while (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$"));
        System.out.println("Password is accepted.");


        do { //validating phone number \\d allows any digit
            System.out.println("Please enter a phone number that contains exactly 9 digits:");
            phoneNumber = scanner.nextLine();

            if (!phoneNumber.matches("^\\d{9}$")) {
                System.out.println("Phone number does not meet the requirements. Please try again.");
            }
        } while (!phoneNumber.matches("^\\d{9}$"));
        System.out.println("Phone number is registered.");


        do { //validating phone number - if 9 numeric digits and if it not exists in database
            System.out.println("Please enter a phone number that contains exactly 9 digits:");
            phoneNumber = scanner.nextLine();

            if (phoneNumber.matches("^\\d{9}$")) {
                try {
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement statement = conn.prepareStatement("SELECT * FROM bankUsers WHERE phoneNumber=?");
                    statement.setString(1, phoneNumber);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        System.out.println("This phone number is already registered. Please try again.");
                    } else {
                        System.out.println("Phone number is registered.");
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid phone number. Please try again.");
            }
        } while (true);


        do { //validating personal number - if 11 numeric digits and if it not exists in database
            System.out.println("Please enter a personal number that contains exactly 11 digits:");
            personalId = scanner.nextLong();
            scanner.nextLine();

            if (String.valueOf(personalId).matches("^\\d{11}$")) {
                try {
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement statement = conn.prepareStatement("SELECT * FROM bankUsers WHERE personalID=?");
                    statement.setLong(1, personalId);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        System.out.println("This personal ID is already registered. Please try again.");
                    } else {
                        System.out.println("Personal number is registered.");
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Invalid personal ID. Please try again.");
            }
        } while (true);


        do {
            System.out.println("Please enter your email address:");
            email = scanner.nextLine();
            if (!email.matches("^\\S+@\\S+\\.\\S+$")) {
                System.out.println("Email does not meet the requirements. Please try again.");
            } else {
                // checking if email is already registered in the database
                try {
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    String query = "SELECT COUNT(*) FROM bankUsers WHERE email = ?";
                    PreparedStatement statement = conn.prepareStatement(query);
                    statement.setString(1, email);
                    ResultSet rs = statement.executeQuery();
                    rs.next();
                    int count = rs.getInt(1);
                    if (count > 0) {
                        System.out.println("Email is already registered. Please use a different email.");
                        email = null; // resetting the email to repeat the loop
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } while (email == null);
        email = scanner.nextLine();
        System.out.println("Email is registered.");


        // add money to the account
        System.out.println("How much would you like to add to your account ? :");
        balance = scanner.nextDouble();
        scanner.nextLine();


        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement prepareStatement = conn.prepareStatement("INSERT INTO bankUsers (fullName, password, phoneNumber, personalID, email, balance) VALUES (?, ?, ?, ?, ?, ?)");
            prepareStatement.setString(1, fullName);
            prepareStatement.setString(2, password);
            prepareStatement.setString(3, phoneNumber);
            prepareStatement.setLong(4, personalId);
            prepareStatement.setString(5, email);
            prepareStatement.setDouble(6, balance);
            prepareStatement.executeUpdate();

            //information to the user about his new bank account and current balance
            ResultSet rs = prepareStatement.executeQuery("SELECT * FROM bankUsers ORDER BY accountNumber DESC LIMIT 1");
            if (rs.next()) {
                Long account = rs.getLong("accountNumber");
                Double bal = rs.getDouble("balance");
                System.out.println("User was registered successfully." + "Your account number is : " + account);
                System.out.println("Your current balance is: " + bal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void loginUser() {
        Scanner scanner = new Scanner(System.in);
        long personalId;
        String password;
        boolean loginSuccessful = false;

        while (!loginSuccessful) {
            System.out.println("Please enter your personal ID:");
            personalId = scanner.nextLong();
            scanner.nextLine();

            if (String.valueOf(personalId).length() != 11) {
                System.out.println("Invalid personal ID. Please try again.");
                continue;
            }

            System.out.println("Please enter your password:");
            password = scanner.nextLine();

            try {
                Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement statement = conn.prepareStatement("SELECT * FROM bankUsers WHERE personalID = ? AND password = ?");
                statement.setLong(1, personalId);
                statement.setString(2, password);
                ResultSet rs = statement.executeQuery();

                if (rs.next()) {
                    System.out.println("Login successful!");
                    double balance = rs.getDouble("balance");
                    System.out.println("Your current balance is: " + balance);
                    loginSuccessful = true;
                    break;
                } else {
                    System.out.println("Invalid personal ID or password. Please try again.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Do you want to try logging in again? (Y/N)");
            String choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Y")) {
                System.exit(0);
            }
        }
    }

    public static void changeBalance() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your personal ID number:");
        long personalId = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Please enter the amount you would like to add or withdraw (use a negative value to withdraw):");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement statement = conn.prepareStatement("SELECT balance FROM bankUsers WHERE personalId=?");
            statement.setLong(1, personalId);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                double balance = rs.getDouble("balance");
                double newBalance = balance + amount;

                if (newBalance < 0) {
                    System.out.println("Insufficient funds.");
                } else {
                    PreparedStatement updateStatement = conn.prepareStatement("UPDATE bankUsers SET balance=? WHERE personalId=?");
                    updateStatement.setDouble(1, newBalance);
                    updateStatement.setLong(2, personalId);
                    int numRowsUpdated = updateStatement.executeUpdate();
                    if (numRowsUpdated > 0) {
                        System.out.println("Balance updated successfully.");
                    } else {
                        System.out.println("Failed to update balance.");
                    }
                    System.out.println("Your new balance is: " + newBalance);
                    System.out.println("Do you want to change your balance ?");
                }
            } else {
                System.out.println("Invalid account number.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
// Reminder:
//^ : Matches the start of the string.
// (?=.*[a-z]) : Positive lookahead assertion to match any character 0 or more times until it finds a lowercase letter [a-z].
// (?=.*[A-Z]) : Positive lookahead assertion to match any character 0 or more times until it finds an uppercase letter [A-Z].
// (?=.*\d) : Positive lookahead assertion to match any character 0 or more times until it finds a digit [\d].
// .{8,} : Matches any character (except for line terminators) 8 or more times.
// $ : Matches the end of the string.