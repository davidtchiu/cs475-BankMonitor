public class BankSimulator {
  /**
   * Entry point for program
   * 
   * @param args Command line arguments, should contain the number of customers.
   */
  public static void main(String[] args) {

    if (args.length != 1) {
      printUsage();
    }

    int num = 0;
    try {
      num = Integer.parseInt(args[0]);
    } catch (NumberFormatException e) {
      printUsage();
    }
    // start timing
    long start = System.nanoTime();

    // Create a bank object
    TheBank bank = new TheBank();

    // Dispatch the threads
    Thread[] custs = new Thread[num];
    for (int i = 0; i < num; i++) {
      custs[i] = new Thread(new Customer(i, bank));
      custs[i].start();
    }

    // Join them back up
    // (should never reach here, since threads are in an infinite
    // loop)
    for (int i = 0; i < num; i++) {
      try {
        custs[i].join();
      } catch (InterruptedException e) {
        System.out.println(e.toString());
      }
    }

    long end = System.nanoTime();
    System.out.println("Time: " + (end - start) / 1000000.0 + "ms");
  }

  private static void printUsage() {
    System.out.println("Usage: java BankSimulator <num>");
    System.out.println("  <num>: Number of customers.");
    System.exit(-1);
  }
}
