package dao.account;

import business.account.Updates;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import obj.account.Account;
import util.DBUtils;
import util.HashUtils;
import util.SQLBuilder;

public class AccountDAO {

    private static final String ACCOUNTS_TABLE = "Accounts";
    private static final String ACCOUNT_ID = "accID";
    private static final String ACCOUNT_EMAIL = "email";
    private static final String ACCOUNT_PASSWORD = "password";
    private static final String ACCOUNT_FULLNAME = "fullname";
    private static final String ACCOUNT_PHONE = "phone";
    private static final String ACCOUNT_STATUS = "status";
    private static final String ACCOUNT_ROLE = "role";

    private static final String GET_ACCOUNT_EMAIL_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ? COLLATE Latin1_General_CS_AS",
            ACCOUNTS_TABLE, ACCOUNT_EMAIL);

    private static final String GET_ACCOUNT_QUERY = String.format(
            "SELECT * FROM %s WHERE %s = ?", ACCOUNTS_TABLE, ACCOUNT_ID);

    private static final String GET_ACCOUNTS_QUERY = String.format(
            "SELECT * FROM %s", ACCOUNTS_TABLE);

    private static final String UPDATE_ACCOUNT_STATUS = String.format(
            "UPDATE %s SET %s = ? WHERE %s = ?",
            ACCOUNTS_TABLE, ACCOUNT_STATUS, ACCOUNT_ID);

    public static final Account getAccount(String email, String password) {

        if (email == null || password == null) {
            throw new NullPointerException();
        }

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM " + ACCOUNTS_TABLE);
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
            return null;
        }
    }

    public static final Account getAccount(String email) {

        if (email == null) {
            throw new NullPointerException();
        }

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM " + ACCOUNTS_TABLE);
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
                return new Account(
                        results.getInt("accID"),
                        results.getString("email"),
                        HashUtils.extractHashPassword(results.getString("password")),
                        results.getString("fullname"),
                        results.getString("phone"),
                        results.getInt("status"),
                        results.getInt("role"));
            }

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public static final List<Account> getAccounts() {

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement getAccounts = connection.prepareStatement(GET_ACCOUNTS_QUERY);) {

                ResultSet results = getAccounts.executeQuery();
                List<Account> accounts = new ArrayList<>();

                while (results.next()) {
                    Account account = new Account(
                            results.getInt(ACCOUNT_ID),
                            results.getString(ACCOUNT_EMAIL),
                            results.getString(ACCOUNT_PASSWORD),
                            results.getString(ACCOUNT_FULLNAME),
                            results.getString(ACCOUNT_PHONE),
                            results.getInt(ACCOUNT_STATUS),
                            results.getInt(ACCOUNT_ROLE)
                    );

                    accounts.add(account);
                }

                connection.commit();
                return accounts;

            } catch (Exception e) {
                connection.rollback();
                return new ArrayList<>();
            }
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public static final boolean addAccount(Account account) {
        if (account == null) {
            throw new NullPointerException();
        }

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("SELECT *");
        builder.addLine("FROM " + ACCOUNTS_TABLE);
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
            return false;
        }

        builder = new SQLBuilder();
        builder.addLine("INSERT INTO " + ACCOUNTS_TABLE + " (email, password, fullname, phone, status, role)");
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
            return false;
        }
    }

    public static final boolean updateAccount(Updates update, String email, String updateValue) {

        SQLBuilder builder = new SQLBuilder();
        builder.addLine("UPDATE " + ACCOUNTS_TABLE);
        String updateString = updateValue;

        switch (update) {
            case EMAIL:
                builder.addLine("SET email = ?");
                break;

            case PASSWORD:
                builder.addLine("SET password = ?");
                updateString = HashUtils.hashPassword(updateValue);
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
            preparedStatement.setString(1, updateString);
            preparedStatement.setString(2, email);

            return preparedStatement.executeUpdate() == 1;
        } catch (Exception e) {
            return false;
        }
    }

    public static final boolean updateStatus(Integer accountID, Integer status) {
        if (accountID == null || status == null) {
            throw new NullPointerException();
        }

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (
                    PreparedStatement updateStatus = connection.prepareStatement(UPDATE_ACCOUNT_STATUS);) {

                updateStatus.setInt(1, status);
                updateStatus.setInt(2, accountID);

                if (updateStatus.executeUpdate() != 1) {
                    throw new SQLException("Can't update status");
                }

                connection.commit();
                return true;

            } catch (Exception e) {
                connection.rollback();
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
