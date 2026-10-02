import java.util.ArrayList;

public class Bank {

    private ArrayList<Account> accounts = new ArrayList<>();
    private int nextNumber = 1001;

    public Account createAccount(String type, String holderName, double openingBalance) {
        String accountNumber = "ACC" + nextNumber;
        nextNumber++;

        Account newAccount;
        if (type.equals("savings")) {
            newAccount = new SavingsAccount(accountNumber, holderName, openingBalance);
        } else {
            newAccount = new CurrentAccount(accountNumber, holderName, openingBalance);
        }

        accounts.add(newAccount);
        return newAccount;
    }

    public Account findAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void transfer(String fromNumber, String toNumber, double amount) {
        Account from = findAccount(fromNumber);
        Account to = findAccount(toNumber);

        if (from == null || to == null) {
            System.out.println("One of the account numbers was not found.");
            return;
        }

        boolean worked = from.withdraw(amount);
        if (worked) {
            to.deposit(amount);
            System.out.println("Transfer complete.");
        }
    }

    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("There are no accounts yet.");
            return;
        }
        for (Account account : accounts) {
            account.showDetails();
        }
    }
}
    

