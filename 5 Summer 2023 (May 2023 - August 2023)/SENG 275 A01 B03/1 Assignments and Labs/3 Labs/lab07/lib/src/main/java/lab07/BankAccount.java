package lab07;

/* BankAccount

 * A bank account has a balance and an interest rate.
 * (note - in real life, NEVER use floating point numbers
 * to represent currency - use a specialized data type like
 * Java's BigDecimal instead.
 *
 * The rules of bank accounts:
 *
 *  Interest rates and balances can never be negative
 *  Accounts are either open or closed - if they're open,
 *  they can be closed if they have < 1 cent left in them.
 *  Attempting to close an account with >= 1 cent should
 *  throw an IllegalStateException.
 *
 */

public class BankAccount {

    private float balance;
    private float interestRate;
    private boolean open;

    public boolean isOpen() {
        return open;
    }
//    private void checkInvariant() {
//        assert balance >= 0 : "Balance cannot be negative.";
//        assert interestRate >= 0 : "Interest rate cannot be negative.";
//        assert !(balance >= 0.01 && open) : "Account with balance >= 0.01 cannot be closed.";
//    }
    public BankAccount() {
        this(0, 0);
    }

    public BankAccount(float balance, float interestRate) {
        this.balance = balance;
        this.interestRate = interestRate;
        this.open = true;
        if (balance <= 0.01) {
            throw new IllegalStateException("Account with negative balance cannot be closed.");
        }
    }

    public float getBalance() {
        return this.balance;
    }

    public void setBalance(float newBalance) {
        if ((newBalance <= 0.01)) {
            throw new IllegalStateException("Account with balance >= 0.01 cannot be closed.");
        }
        this.balance = newBalance;
    }

    public float getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(float newInterestRate) {
        if (newInterestRate<=0) {
            throw new IllegalStateException("Interest rate cannot be negative.");
        }
        this.interestRate = newInterestRate;
    }

    public void applyInterest() {
        float oldBalance = this.balance;
        float newInterest = this.calculateInterest();
        this.setBalance(oldBalance + newInterest);
    }

    private float calculateInterest() {
        return this.balance * this.interestRate;
    }

    public void close() {
        if (balance >= 0.01) {
            throw new IllegalStateException("Account with balance >= 0.01 cannot be closed.");
        }
        if (!open) return;
        this.open = false;
    }
}
