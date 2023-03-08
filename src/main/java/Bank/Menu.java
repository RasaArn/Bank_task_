package Bank;

public class Menu {
    protected static void bankEmployeeMenu() {
        System.out.println("Please choose action:");
        System.out.println("1- replace an existing user with a new one");
        System.out.println("2- remove user");
        System.out.println("3- find user");
        System.out.println("4- change personal information for user");

    }
    protected static void customerMenu(){
        System.out.println("Please choose action:");
        System.out.println("1 - log in");
        System.out.println("2 - register");
        System.out.println("3 - change balance");
    }
}
