package Bank;

public class Menu {
    protected static void bankEmployeeMenu() {
        System.out.println("Please choose action:");
        System.out.println("1- update user's personal information");
        System.out.println("2- remove user");
        System.out.println("3- find user");
        System.out.println("4- change balance for user");

    }
    protected static void customerMenu(){
        System.out.println("Please choose action:");
        System.out.println("1 - log in and check balance");
        System.out.println("2 - register");
        System.out.println("3 - log in and change balance");
    }
}
