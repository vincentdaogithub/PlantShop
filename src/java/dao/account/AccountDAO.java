package dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import obj.account.Account;
import security.HashUtils;
import util.DBUtils;
import util.SQLBuilder;

public class AccountDAO {
    public static final Account getAccount(String email, String password) {
        if (email == null || password == null) {
            return null;
        }

        try {
            ResultSet results = getAccountsByEmail(email);

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
                        results.getInt("role")
                    );
                }
            }

            return null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static final ResultSet getAccountsByEmail(String email) {
        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM Accounts");
        builder.addLine("WHERE email = ?");
        builder.addLine("COLLATE Latin1_General_CS_AS");
        String query = builder.toString();

        try (
            Connection connection = DBUtils.makeConnection();
            PreparedStatement statement = connection.prepareStatement(
                    query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ) {
            statement.setString(1, email);
            return statement.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public static final boolean addAccount(Account account) {
        if (account == null) {
            throw new NullPointerException();
        }

        try {
            ResultSet results = getAccountsByEmail(account.getAccountEmail());

            if (results == null || results.next()) {
                return false;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }


        SQLBuilder builder = new SQLBuilder();
        builder.addLine("INSERT INTO Accounts (email, password, fullname, phone, status, role)");
        builder.addLine("VALUES (?, ?, ?, ?, ?, ?)");
        String query = builder.toString();

        try (
            Connection connection = DBUtils.makeConnection();
            PreparedStatement statement = connection.prepareStatement(query);
        ) {
            statement.setString(1, account.getAccountEmail());
            statement.setString(2, account.getAccountPassword());
            statement.setString(3, account.getAccountFullName());
            statement.setString(4, account.getAccountPhone());
            statement.setInt(5, account.getAccountStatus());
            statement.setInt(6, account.getAccountRole());

            return statement.executeUpdate() != 0;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}
