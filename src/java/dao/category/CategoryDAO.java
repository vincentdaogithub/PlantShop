package dao.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import util.DBUtils;

public class CategoryDAO {

    private static final String CATEGORIES_TABLE = "Categories";
    private static final String CATEGORY_ID = "CateID";
    private static final String CATEGORY_NAME = "CateName";

    private static final String GET_CATEGORIES_QUERY = String.format(
            "SELECT * FROM %s", CATEGORIES_TABLE);

    public static final Map<Integer, String> getCategories() {

        try (
                Connection connection = DBUtils.makeConnection();) {

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            try (
                    PreparedStatement getCategories = connection.prepareStatement(GET_CATEGORIES_QUERY);) {

                ResultSet results = getCategories.executeQuery();
                Map<Integer, String> categories = new HashMap<>();

                while (results.next()) {
                    categories.put(results.getInt(CATEGORY_ID), results.getString(CATEGORY_NAME));
                }

                connection.commit();
                return categories;

            } catch (Exception e) {
                connection.rollback();
                return new HashMap<>();
            }
        } catch (Exception e) {
            return new HashMap<>();
        }
    }
}
