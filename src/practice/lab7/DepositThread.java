package practice.lab7;


public class DepositThread implements Runnable {
    private Account1 account1;
    private Account2 account2;
    private double money;

    public DepositThread(Account1 account1, Account2 account2, double money) {
        this.account1 = account1;
        this.account2 = account2;
        this.money = money;
    }

    @Override
    public void run() {
        account1.deposit(money);
        account2.deposit(money);
    }
}
