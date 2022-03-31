/**
 * The bank simulator! Demonstates synchronized blocks and
 * the use of condition variables (notEnough).
 * 
 * @author David
 */
public class TheBank {

    private int balance; // shared balance
    private Object notEnough; // shared condition variable

    /**
     * Default constructor
     */
    public TheBank() {
        this.balance = 0;
        this.notEnough = new Object();
    }

    /**
     * Deposit
     * 
     * @param id  customer ID
     * @param amt the amount to give
     * @throws InterruptedException
     */
    public void deposit(int id, int amt) throws InterruptedException {
        synchronized (notEnough) {
            this.balance += amt;
            System.out.println("Cust " + id + " deposited " + amt + " Balance: " +
                    this.balance);
            if (this.balance > 0) {
                // Wake up all waiting threads (if any)!
                notEnough.notifyAll();
            }

        }
    }

    /**
     * Withdraws an amount. Must wait if withdrawing would mean negative balance
     * 
     * @param id  customer ID
     * @param amt amount to take out
     * @throws InterruptedException
     */
    public void withdraw(int id, int amt) throws InterruptedException {
        synchronized (notEnough) {
            while (amt > this.balance) {
                System.out.println("Cust " + id + " waits. Withdraw " + amt + ". Balance now:" + this.balance);
                notEnough.wait();
            }
            this.balance -= amt;
            System.out.println("Cust " + id + " withdrew " + amt + ". Balance: "
                    + this.balance);
        }
    }
}
