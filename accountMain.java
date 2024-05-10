import java.util.Scanner;

public class accountMain {
    public static void main(String[] args) {
        AccountDatabaseManager dbManager = new AccountDatabaseManager("jdbc:mysql://localhost:3306/codechum", "root", "");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose from the menu:");
            System.out.println("1.) Create account");
            System.out.println("2.) Open account");
            System.out.println("0.) Exit\n");

            System.out.print("Choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    System.out.print("\nEnter Account Details\nName: ");
                    String name = scanner.nextLine();
                    dbManager.registerAccount(name);
                    break;
                case 2:
                    System.out.print("\nEnter account ID: ");
                    int accountId = scanner.nextInt();
                    scanner.nextLine(); 
                    Account account = dbManager.retrieveAccount(accountId);
                    if (account != null) {
                        while (true) {
                            System.out.println("\nChoose from the menu:");
                            System.out.println("1.) Check Account");
                            System.out.println("2.) Deposit");
                            System.out.println("3.) Withdraw");
                            System.out.println("0.) Exit\n");

                            System.out.print("Choice: ");
                            int innerChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (innerChoice) {
                                case 1:
                                    System.out.println("\n" + account + "\n");
                                    break;
                                case 2:
                                    System.out.print("\nAmount: ");
                                    double depositAmount = scanner.nextDouble();
                                    scanner.nextLine(); // Consume newline
                                    if (dbManager.deposit(accountId, depositAmount)) {
                                        account = dbManager.retrieveAccount(accountId);
                                        System.out.println("\nAccount updated successfully.\n" + account + "\n");
                                    } else {
                                        System.out.println("\nFailed to deposit amount.\n");
                                    }
                                    break;
                                case 3:
                                    System.out.print("\nAmount: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine(); // Consume newline
                                    if (dbManager.withdraw(accountId, withdrawAmount)) {
                                        account = dbManager.retrieveAccount(accountId);
                                        System.out.println("\nAccount updated successfully.\n" + account + "\n");
                                    } else {
                                        System.out.println("\nFailed to withdraw amount.\n");
                                    }
                                    break;
                                case 0:
                                    System.out.println("\nExited.");
                                    System.exit(0);
                                default:
                                    System.out.println("\nInvalid choice. Please try again.\n");
                                    break;
                            }
                        }
                    }
                    break;
                case 0:
                    System.out.println("\nExited.");
                    System.exit(0);
                default:
                    System.out.println("\nInvalid choice. Please try again.\n");
                    break;
            }
        }
    }
}