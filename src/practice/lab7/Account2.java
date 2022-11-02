package practice.lab7;

public class Account2 {
    private double balance;

    /**
     * @param money
     */
    public synchronized void deposit(double money) {

        try {
            double newBalance = balance + money;
            try {
                Thread.sleep(10);   // Simulating this service takes some processing time
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            balance = newBalance;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public double getBalance() {
        return balance;
    }
}
