package Bank;

import java.util.UUID;

public class User {

    private String fullName;
    private String password;
    private String phoneNumber;
    private String email;
    private UUID accountNumber;
    private double balance;

    public User( String fullName, String password, String phoneNumber, String email, UUID accountNumber, double balance) {
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountNumber = accountNumber;
        this.balance = 0.0;
    }



    public String getFullName() {
        return fullName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
    public UUID getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }


    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Name: " + fullName + ", Password: " + password + ", Phone Number: " + phoneNumber + ", Email: " + email + ", AccountNumber: " + accountNumber.toString() + ", Balance: " + balance;
    }
}

