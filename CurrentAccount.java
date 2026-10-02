public class CurrentAccount extends Account {

    private static final double OVERDRAFT_LIMIT = 1000;

    public CurrentAccount(String accountNumber, String holderName, double openingBalance) {
        super(accountNumber, holderName, openingBalance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Please enter an amount greater than zero.");
            return false;
        }
        if (!withinDailyLimit(amount)) {
            return false;
        }
        if (balance - amount < -OVERDRAFT_LIMIT) {
            System.out.println("Not allowed. Overdraft limit of " + OVERDRAFT_LIMIT + " would be crossed.");
            return false;
        }
        balance = balance - amount;
        recordWithdrawal(amount);
        System.out.println("Withdrawal successful. New balance: " + balance);
        return true;
    }

    @Override
    public String getAccountType() {
        return "Current";
    }
}