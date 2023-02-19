package dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import obj.account.Account;
import security.util.HashUtils;
import util.DBUtils;
import util.SQLBuilder;

public class AccountDAO {
    public static final Account getAccount(String email, String password) {
        if (email == null || password == null) {
            return null;
        }

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM Accounts");
        builder.addLine("WHERE email = ?");
        builder.addLine("COLLATE Latin1_General_CS_AS");
        String query = builder.toString();

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();

            if (results == null) {
                return null;
            }

            if (results.next() && results.isLast()) {
                if (HashUtils.checkPassword(password, results.getString("password"))) {
                    return new Account(
                            results.getInt("accID"),
                            results.getString("email"),
                            HashUtils.extractHashPassword(results.getString("password")),
                            results.getString("fullname"),
                            results.getString("phone"),
                            results.getInt("status"),
                            results.getInt("role"));
                }
            }

            return null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static final boolean addAccount(Account account) {
        if (account == null) {
            throw new NullPointerException();
        }

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM Accounts");
        builder.addLine("WHERE email = ?");
        builder.addLine("COLLATE Latin1_General_CS_AS");
        String query = builder.toString();

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, account.getEmail());
            ResultSet results = statement.executeQuery();

            if (results == null || results.next()) {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }

        builder = new SQLBuilder();
        builder.addLine("INSERT INTO Accounts (email, password, fullname, phone, status, role)");
        builder.addLine("VALUES (?, ?, ?, ?, ?, ?)");
        query = builder.toString();

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(query);) {
            statement.setString(1, account.getEmail());
            statement.setString(2, HashUtils.hashPassword(account.getPassword()));
            statement.setString(3, account.getFullname());
            statement.setString(4, account.getPhone());
            statement.setInt(5, account.getStatus());
            statement.setInt(6, account.getRole());

            return statement.executeUpdate() != 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
