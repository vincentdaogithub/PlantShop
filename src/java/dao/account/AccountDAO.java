package dao.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import business.account.Updates;
import obj.account.Account;
import security.util.HashUtils;
import util.DBUtils;
import util.SQLBuilder;

public class AccountDAO {
    public static final Account getAccount(String email, String password) {
        if (email == null || password == null) {
            throw new NullPointerException();
        }

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM Accounts");
        builder.addLine("WHERE email = ?");
        builder.addLine("COLLATE Latin1_General_CS_AS");

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        builder.toString(),
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {
            statement.setString(1, email);
            ResultSet results = statement.executeQuery();

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
            e.printStackTrace();
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

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(
                        builder.toString(),
                        ResultSet.TYPE_SCROLL_SENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);) {

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

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement statement = connection.prepareStatement(builder.toString());) {
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

    public static final boolean updateAccount(Updates updateType, Account account, String valueToChange) {
        if (updateType == null || account == null || valueToChange == null) {
            throw new NullPointerException();
        }

        String email = account.getEmail();
        SQLBuilder builder = new SQLBuilder();
        builder.addLine("UPDATE Accounts");

        switch (updateType) {
            case EMAIL:
                builder.addLine("SET email = ?");
                break;

            case PASSWORD:
                builder.addLine("SET password = ?");
                break;

            case FULLNAME:
                builder.addLine("SET fullname = ?");
                break;

            case PHONE:
                builder.addLine("SET phone = ?");
                break;

            default:
                throw new NullPointerException();
        }

        builder.addLine("WHERE email = ?");
        builder.addLine("COLLATE Latin1_General_CS_AS");

        try (
                Connection connection = DBUtils.makeConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());) {
            preparedStatement.setString(1, valueToChange);
            preparedStatement.setString(2, email);
            return preparedStatement.executeUpdate() != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
