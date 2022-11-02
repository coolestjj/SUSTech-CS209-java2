package practice.lab7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account1 {
    private double balance;
    private final Lock balanceChangeLock = new ReentrantLock();

    /**
     * @param money
     */
    public void deposit(double money) {

        balanceChangeLock.lock();
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
        balanceChangeLock.unlock();
    }

    public double getBalance() {
        return balance;
    }
}