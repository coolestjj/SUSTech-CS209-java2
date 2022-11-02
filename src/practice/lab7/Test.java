package practice.lab7;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        Account1 account1 = new Account1();
        Account2 account2 = new Account2();
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 1; i <= 100; i++) {
            service.execute(new DepositThread(account1, account2, 10));
        }

        service.shutdown();

        while (!service.isTerminated()) {}

        System.out.println("Account1 Balance: " + account1.getBalance());
        System.out.println("Account2 Balance: " + account2.getBalance());
    }
}
