import java.time.LocalDate;

public abstract class Account {

    private static final double DAILY_WITHDRAWAL_LIMIT = 5000;

    private String accountNumber;
    private String holderName;
    protected double balance;

    private double withdrawnToday = 0;
    private LocalDate lastWithdrawalDate = LocalDate.now();

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

    private void resetIfNewDay() {
        LocalDate today = LocalDate.now();
        if (!today.equals(lastWithdrawalDate)) {
            withdrawnToday = 0;
            lastWithdrawalDate = today;
        }
    }

    public double getRemainingDailyLimit() {
        resetIfNewDay();
        return DAILY_WITHDRAWAL_LIMIT - withdrawnToday;
    }

    protected boolean withinDailyLimit(double amount) {
        double remaining = getRemainingDailyLimit();
        if (amount > remaining) {
            System.out.println("Not allowed. Daily withdrawal limit is " + DAILY_WITHDRAWAL_LIMIT
                    + ". You can still withdraw " + remaining + " today.");
            return false;
        }
        return true;
    }

    protected void recordWithdrawal(double amount) {
        withdrawnToday = withdrawnToday + amount;
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

    public void checkBalance() {
        System.out.println("Current balance of " + accountNumber + " is " + balance);
    }

    public void showDetails() {
        System.out.println("Account Number        : " + accountNumber);
        System.out.println("Holder Name           : " + holderName);
        System.out.println("Account Type          : " + getAccountType());
        System.out.println("Balance               : " + balance);
        System.out.println("Daily Limit           : " + DAILY_WITHDRAWAL_LIMIT);
        System.out.println("Left To Withdraw Today: " + getRemainingDailyLimit());
        System.out.println("-----------------------------");
    }
}