package Service;

import dao.AccountDAO;
import dao.TransactionDAO;
import MODEL.Account;
import MODEL.Transaction;

import java.time.LocalDateTime;
import java.util.Optional;

public class AccountService {
    private AccountDAO accountDAO = new AccountDAO();
    private TransactionDAO transactionDAO = new TransactionDAO();
    private Account loggedInUser = null;

    public void createAccount(String name, String phone, String password, double balance) {
        Account account = new Account(name, phone, password, balance);
        accountDAO.createAccount(account);
    }

    public boolean login(String phone, String password) {
        Optional<Account> account = accountDAO.login(phone, password);
        if (account.isPresent()) {
            loggedInUser = account.get();
            System.out.println(" Login successful. Welcome " + loggedInUser.getName());
            return true;
        } else {
            System.out.println(" Invalid phone or password");
            return false;
        }
    }

    public void deposit(double amount) {
        if (loggedInUser == null) {
            System.out.println(" Please login first!");
            return;
        }
        double newBalance = loggedInUser.getBalance() + amount;
        accountDAO.updateBalance(loggedInUser.getId(), newBalance);
        loggedInUser.setBalance(newBalance);
        transactionDAO.recordTransaction(
        	    new Transaction(
        	        loggedInUser.getId(), 
        	        amount,               
        	        LocalDateTime.now(),  
        	        "DEPOSIT"
        	    )
        	);
        System.out.println("Deposit successful. New Balance: " + newBalance);
    }

    public void withdraw(double amount) {
        if (loggedInUser == null) {
            System.out.println(" Please login first!");
            return;
        }
        if (loggedInUser.getBalance() >= amount) {
            double newBalance = loggedInUser.getBalance() - amount;
            accountDAO.updateBalance(loggedInUser.getId(), newBalance);
            loggedInUser.setBalance(newBalance);
            transactionDAO.recordTransaction(
            	    new Transaction(
            	        loggedInUser.getId(), 
            	        amount,               
            	        LocalDateTime.now(),  
            	        "WITHDRAW"
            	    )
            	);


            System.out.println(" Withdrawal successful. New Balance: " + newBalance);
        } else {
            System.out.println(" Insufficient balance!");
        }
    }

    public void checkBalance() {
        if (loggedInUser == null) {
            System.out.println(" Please login first!");
            return;
        }
        System.out.println("Balance: " + loggedInUser.getBalance());
    }

    public void viewTransactions() {
        if (loggedInUser == null) {
            System.out.println(" Please login first!");
            return;
        }
        transactionDAO.getTransactionsByAccount(loggedInUser.getId())
                .forEach(tx -> System.out.println(
                        tx.getDate() + " - " + tx.getType() + " - " + tx.getAmount()
                ));
    }
}
