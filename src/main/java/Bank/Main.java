package Bank;



import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;
        import java.util.Scanner;
        import java.util.UUID;

public class Main {

    private static final String DB_URL = "jdbc:mysql://localhost/banktask";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "1234";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Create a bank object
            Bank bank = new Bank(connection);

            // Register a new user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your full name:");
            String fullName = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$")) {
                System.out.println("Password must contain at least one uppercase letter, one lowercase letter, one digit, and be at least 8 characters long.");

            }
            System.out.println("Enter your phone number:");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter your email address:");
            String email = scanner.nextLine();
            UUID accountNumber = UUID.randomUUID();
            double balance = 0.0;
            User newUser = new User(fullName, password, phoneNumber, email, accountNumber, balance);
            bank.addUser(newUser);
            System.out.println("Registration successful. Your account number is " + accountNumber.toString());
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
        }
    }
}