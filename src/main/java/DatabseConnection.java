import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
class DatabaseConnection {
    public static Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/banktask", "root", "1234");
            //System.out.println("Connected to the DB.");
            return conn;
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}