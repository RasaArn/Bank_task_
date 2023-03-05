/*package Bank;

import java.sql.*;

public class UsersRegLogIn {
    private static final String dbURL = "jdbc:mysql://localhost:3306/java34";
    private static final String user ="root" ;
    private static final String password ="1234";


    public static void registerUser (Connection connection, String fullName, String password, long phoneNumber, String email) throws SQLException {

        String sql = "INSERT INTO bankUsers (fullName, password, phoneNumber, email) VALUES (?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(2, fullName);
        statement.setString(3, password);
        statement.setLong(4, phoneNumber);
        statement.setString(5, email);


        int rowInserted = statement.executeUpdate();

        if (rowInserted > 0) {
            System.out.println("User registered successfully!");
        } else {
            System.out.println("Sorry, something went wrong...");
        }
    }

    public static boolean logIn(Connection connection, String existingUserName) throws SQLException {

        String sql;
        try (Connection connection1 = DriverManager.getConnection(dbURL, user, password)) {
            sql = "SELECT FullName FROM bankUsers WHERE UserName = '"+ existingUserName+ "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet.next()){
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }






}*/
