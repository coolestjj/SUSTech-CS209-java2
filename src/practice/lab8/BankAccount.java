package practice.lab8;
/**
   A bank account has a balance that can be changed by 
   deposits and withdrawals.
*/
public class BankAccount {  
   private double balance;

   /**
      Constructs a bank account with a zero balance.
   */
   public BankAccount() {   
      balance = 0.0;
   }

   /**
      Constructs a bank account with a given balance.
      @param initialBalance the initial balance
   */
   public BankAccount (double initialBalance) {   
      balance = initialBalance;
   }

   /**
      Deposits money into the bank account.
      @param amount the amount to deposit
   */
   public synchronized void deposit (double amount) {
      balance = balance + amount;
      notifyAll();
   }

   /**
      Withdraws money from the bank account.
      @param amount the amount to withdraw
   */
   public synchronized void withdraw (double amount) {
      try {
         while (balance < amount) wait();

         balance = balance - amount;
      } catch (InterruptedException e) {}
   }

   /**
      Gets the current balance of the bank account.
      @return the current balance
   */
   public double getBalance() {   
      return balance;
   }
}

