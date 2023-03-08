package Bank;

public class User {

    private String fullName;
    private String password;
    private String phoneNumber;
    private String email;
    static long accountNumber=10000000000L;
    private double balance;
    protected static long nextAccountNumber = accountNumber++; // a start point for account number. With each new user number increases by 1

    public User( String fullName, String password, String phoneNumber, String email, long accountNumber, double balance) {
        this.fullName = fullName;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.accountNumber = nextAccountNumber++;
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
    public long getAccountNumber() {
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
        return "Name: " + fullName + ", Password: " + password + ", Phone Number: " + phoneNumber + ", Email: " + email + ", AccountNumber: " + accountNumber + ", Balance: " + balance;
    }
}

