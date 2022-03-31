import java.util.Random;

/**
 * The Customer class for our bank simulation.
 * 
 */
public class Customer implements Runnable {

    private int id; // Customer ID
    private TheBank bank;

    /**
     * Customer Constructor
     * 
     * @param id   ID for this customer
     * @param bank reference to the Bank
     */
    public Customer(int id, TheBank bank) {
        this.id = id;
        this.bank = bank;
    }

    /**
     * This is what each thread will run.
     * (Gets called automatically when thread is started)
     */
    @Override
    public void run() {
        // cust 0 always deposits
        // all other customers withdraw
        Random rng = new Random();
        while (true) {
            try {
                if (this.id == 0) {
                    Thread.sleep(rng.nextInt(100));
                    this.bank.deposit(this.id, 10);
                } else {
                    Thread.sleep(rng.nextInt(10));
                    this.bank.withdraw(this.id, 5);
                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());

            }
        }
    }
}