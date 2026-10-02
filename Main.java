import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    private static double readNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("That is not a number. Please try again.");
            }
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    public static void main(String[] args) {
        Bank bank = new Bank();
        boolean running = true;

        System.out.println("Welcome to the Bank Management System");

        while (running) {
            System.out.println();
            System.out.println("1. Create account");
            System.out.println("2. Deposit money");
            System.out.println("3. Withdraw money");
            System.out.println("4. Transfer money");
            System.out.println("5. Show all accounts");
            System.out.println("6. Exit");
            String choice = readText("Choose an option (1-6): ");

            switch (choice) {
                case "1":
                    String name = readText("Holder name: ");
                    String type = readText("Type (savings or current): ").toLowerCase();
                    double opening = readNumber("Opening balance: ");
                    Account created = bank.createAccount(type, name, opening);
                    System.out.println("Account created. Your account number is " + created.getAccountNumber());
                    break;

                case "2":
                    Account depositTo = bank.findAccount(readText("Account number: "));
                    if (depositTo == null) {
                        System.out.println("Account not found.");
                    } else {
                        depositTo.deposit(readNumber("Amount to deposit: "));
                    }
                    break;

                case "3":
                    Account withdrawFrom = bank.findAccount(readText("Account number: "));
                    if (withdrawFrom == null) {
                        System.out.println("Account not found.");
                    } else {
                        withdrawFrom.withdraw(readNumber("Amount to withdraw: "));
                    }
                    break;

                case "4":
                    String from = readText("From account number: ");
                    String to = readText("To account number: ");
                    double amount = readNumber("Amount to transfer: ");
                    bank.transfer(from, to, amount);
                    break;

                case "5":
                    bank.showAllAccounts();
                    break;

                case "6":
                    running = false;
                    System.out.println("Thank you for banking with us. Goodbye.");
                    break;

                default:
                    System.out.println("Please choose a number from 1 to 6.");
            }
        }
    }
}
