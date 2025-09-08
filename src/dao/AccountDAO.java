package dao;

import MODEL.Account;
import java.sql.*;
import java.util.Optional;

public class AccountDAO {

    // Create new account
    public void createAccount(Account account) {
        String sql = "INSERT INTO accounts(name, phone, password, balance) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, account.getName());
            stmt.setString(2, account.getPhone());
            stmt.setString(3, account.getPassword());
            stmt.setDouble(4, account.getBalance());
            stmt.executeUpdate();

            // Retrieve auto-generated ID from database
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    account.setId(rs.getInt(1));  // ✅ Important: set account ID
                }
            }

            System.out.println("✅ Account created successfully with ID: " + account.getId());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Login and return account with ID
    public Optional<Account> login(String phone, String password) {
        String sql = "SELECT * FROM accounts WHERE phone=? AND password=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, phone);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Fetch ID from database and pass to Account constructor
                    Account account = new Account(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("password"),
                            rs.getDouble("balance")
                    );
                    return Optional.of(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Update balance
    public void updateBalance(int accountId, double newBalance) {
        String sql = "UPDATE accounts SET balance=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setInt(2, accountId);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get account by ID
    public Optional<Account> getAccountById(int accountId) {
        String sql = "SELECT * FROM accounts WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Account account = new Account(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("phone"),
                            rs.getString("password"),
                            rs.getDouble("balance")
                    );
                    return Optional.of(account);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
