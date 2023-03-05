package Bank;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

class Bank {
    private Connection connection;

    public Bank(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO bankUsers (fullName, password, phoneNumber, email, accountNumber, balance) VALUES (?, ?, ?, ?, ?, ?)");
        statement.setString(1, user.getFullName());
        statement.setString(2, user.getPassword());
        statement.setString(3, user.getPhoneNumber());
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getAccountNumber().toString());
        statement.setDouble(6, user.getBalance());
        statement.executeUpdate();
    }

    public boolean removeUser(UUID accountNumber) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE accountNumber = ?");
        statement.setString(5, accountNumber.toString()); //originaliai buvo 1 vietoj 5
        int numRowsDeleted = statement.executeUpdate();
        return numRowsDeleted > 0;
    }

    public User findUser(UUID accountNumber) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE accountNumber = ?");
        statement.setString(5, accountNumber.toString());//originaliai buvo 1 vietoj 5
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String fullName = resultSet.getString("fullName");
            String password = resultSet.getString("password");
            String phoneNumber = resultSet.getString("phoneNumber");
            String email = resultSet.getString("email");
            UUID accountNumberDb = UUID.fromString(resultSet.getString("accountNumber"));
            double balance = resultSet.getDouble("balance");

            User user = new User(fullName, password, phoneNumber, email, accountNumberDb, balance);
            return user;
        } else {
            return null;
        }
    }

    public void replaceUser(User oldUser, User newUser) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE users SET fullName = ?, password = ?, phoneNumber = ?, email = ?, accountNumber = ?, balance = ? WHERE accountNumber = ?");
        statement.setString(1, newUser.getFullName());
        statement.setString(2, newUser.getPassword());
        statement.setString(3, newUser.getPhoneNumber());
        statement.setString(4, newUser.getEmail());
        statement.setString(5, newUser.getAccountNumber().toString());
        statement.setDouble(6, newUser.getBalance());
        statement.executeUpdate();
    }
}
