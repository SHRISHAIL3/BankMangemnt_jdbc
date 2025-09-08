package ui;

import Service.AccountService;
import util.InputUtil;

public class Main {
    public static void main(String[] args) {
        AccountService accountService = new AccountService();

        while (true) {
            System.out.println("\n=== Bank Management System ===");
            System.out.println("1. Create Account");
            System.out.println("2. Login");
            System.out.println("3. Exit");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    String name = InputUtil.getString("Enter name: ");
                    String phone = InputUtil.getString("Enter phone: ");
                    String password = InputUtil.getString("Enter password: ");
                    double balance = InputUtil.getDouble("Enter initial balance: ");
                    accountService.createAccount(name, phone, password, balance);
                    break;
                case 2:
                    String loginPhone = InputUtil.getString("Enter phone: ");
                    String loginPass = InputUtil.getString("Enter password: ");
                    boolean loggedIn = accountService.login(loginPhone, loginPass);
                    if (loggedIn) {
                        showAccountMenu(accountService);
                    }
                    break;
                case 3:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void showAccountMenu(AccountService accountService) {
        while (true) {
            System.out.println("\n--- Account Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. View Transactions");
            System.out.println("5. Logout");

            int choice = InputUtil.getInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    double depAmount = InputUtil.getDouble("Enter deposit amount: ");
                    accountService.deposit(depAmount);
                    break;
                case 2:
                    double withAmount = InputUtil.getDouble("Enter withdraw amount: ");
                    accountService.withdraw(withAmount);
                    break;
                case 3:
                    accountService.checkBalance();
                    break;
                case 4:
                    accountService.viewTransactions();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice . Try again.");
            }
        }
    }
}
