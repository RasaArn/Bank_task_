package Bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    protected static void MenuCall (){
        UserRegAndLogin userRegAndLogin = new UserRegAndLogin();

        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("Welcome to the CatBank! Please press 1 if you are employee and 2 if you are customer");


        Scanner scanner = new Scanner(System.in);
        int choice;


        while (true){
            try {
                choice = scanner.nextInt();

                if (choice == 1) {
                    Menu.bankEmployeeMenu();


                    int employeeChoice = 0;
                    do { //try catch block and do while loop to validate user input and not stop program with invalid input
                        try {
                            employeeChoice= scanner.nextInt();
                            if (employeeChoice < 1 || employeeChoice > 4) {
                                throw new InputMismatchException();
                            }
                            switch (employeeChoice) {
                                case 1 -> Bank.updatePersonalInfo();
                                case 2 -> Bank.deleteUser();
                                case 3 -> Bank.findUser();
                                case 4 -> Bank.changeUserBalance();
                                default -> System.out.println("Invalid choice. Please try again.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid choice entered. Please enter a number between 1 and 4.");
                        }
                    }while (employeeChoice < 1 || employeeChoice > 4);
                    scanner.nextLine();


                } else if (choice == 2) {
                    Menu.customerMenu();


                    int customerChoice=0; // try catch block and do while loop to validate user input and not stop program with invalid input
                    do { //try catch block and do while loop to validate user input and not stop program with invalid input
                        try {
                            customerChoice= scanner.nextInt();
                            if (customerChoice != 1 && customerChoice != 2 && customerChoice != 3 ) {
                                throw new InputMismatchException();
                            }
                            switch (customerChoice) {
                                case 1 -> userRegAndLogin.loginUser();
                                case 2 -> UserRegAndLogin.registerUser();
                                case 3 -> UserRegAndLogin.changeBalance();

                                default -> System.out.println("Invalid choice. Please reconnect and try again.");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Try again");
                        }
                    }while (customerChoice != 1 && customerChoice != 2 && customerChoice != 3);
                    scanner.nextLine();


                } else {
                    throw new InputMismatchException();
                } break; //stop while loop if input is valid

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                scanner.nextLine();
            }
        }

    }
    private static void bankEmployeeMenu() {
        System.out.println("Please choose action:");
        System.out.println("1- update user's personal information");
        System.out.println("2- remove user");
        System.out.println("3- find user");
        System.out.println("4- change balance for user");

    }
    private static void customerMenu(){
        System.out.println("Please choose action:");
        System.out.println("1 - log in and check balance");
        System.out.println("2 - register");
        System.out.println("3 - log in and change balance");
    }
}
