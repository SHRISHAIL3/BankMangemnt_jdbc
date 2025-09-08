package Service;
import dao.TransactionDAO;
import MODEL.Transaction;

import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO = new TransactionDAO();

    // Record a transaction (can be used if needed directly)
    public void recordTransaction(Transaction txn) {
        transactionDAO.recordTransaction(txn);
    }

    // View all transactions for an account
    public void viewTransactions(int accountId) {
        List<Transaction> transactions = transactionDAO.getTransactionsByAccount(accountId);

        if (transactions.isEmpty()) {
            System.out.println("❌ No transactions found for account ID: " + accountId);
        } else {
            System.out.println("\n📜 Transaction History for Account ID: " + accountId);
            for (Transaction txn : transactions) {
                System.out.println(txn);
            }
        }
    }
}

