package dao;

import MODEL.Transaction;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {

    // Record a transactions
    public void recordTransaction(Transaction txn) {
        String sql = "INSERT INTO transactions(accountId, amount, date, type) VALUES(?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, txn.getAccountId());             // Must be real account ID
            stmt.setDouble(2, txn.getAmount());
            stmt.setTimestamp(3, Timestamp.valueOf(txn.getDate())); // LocalDateTime → Timestamp
            stmt.setString(4, txn.getType());

            stmt.executeUpdate();
            System.out.println("✅ Transaction recorded!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get transactions for an account
    public List<Transaction> getTransactionsByAccount(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transactions WHERE accountId=? ORDER BY date DESC";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Transaction txn = new Transaction(
                        rs.getInt("id"),
                        rs.getInt("accountId"),
                        rs.getDouble("amount"),
                        rs.getTimestamp("date").toLocalDateTime(),
                        rs.getString("type")
                );
                transactions.add(txn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
}
