public class SavingsAccount extends Account {

    private static final double MINIMUM_BALANCE = 500;

    public SavingsAccount(String accountNumber, String holderName, double openingBalance) {
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
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println("Not allowed. A savings account must keep at least " + MINIMUM_BALANCE);
            return false;
        }
        balance = balance - amount;
        recordWithdrawal(amount);
        System.out.println("Withdrawal successful. New balance: " + balance);
        return true;
    }

    @Override
    public String getAccountType() {
        return "Savings";
    }
}