public abstract class Account {

    private String accountNumber;
    private String holderName;
    protected double balance;

    public Account(String accountNumber, String holderName, double openingBalance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = openingBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return;
        }
        balance = balance + amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public abstract boolean withdraw(double amount);

    public abstract String getAccountType();

    public void showDetails() {
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Holder Name    : " + holderName);
        System.out.println("Account Type   : " + getAccountType());
        System.out.println("Balance        : " + balance);
        System.out.println("-----------------------------");
    }
}