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
        // try {
        // while (true) {
        // if (this.id == 0) {
        // Thread.sleep(50);
        // this.bank.deposit(this.id, 500);
        // Thread.sleep(50);
        // this.bank.deposit(this.id, 500);
        // Thread.sleep(50);
        // this.bank.deposit(this.id, 500);
        // } else if (this.id == 1) {
        // this.bank.withdraw(this.id, 600);
        // } else {
        // // Thread.sleep(5);
        // this.bank.withdraw(this.id, 300);
        // }
        // }
        // } catch (InterruptedException e) {
        // System.out.println(e.toString());
        // }

        // cust 0 always deposits a small amount ($0 - $49)
        // all other customers withdraw larger amounts ($100 - $499)
        Random rng = new Random();
        while (true) {
            try {
                if (this.id == 0) {
                    Thread.sleep(rng.nextInt(100));
                    this.bank.deposit(this.id, rng.nextInt(50));
                } else {
                    Thread.sleep(rng.nextInt(10));
                    this.bank.withdraw(this.id, rng.nextInt(400) + 100);
                }
            } catch (InterruptedException e) {
                System.out.println(e.toString());
            }
        }
    }

}
