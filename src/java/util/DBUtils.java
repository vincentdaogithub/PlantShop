package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtils {

    private static final String ADDRESS = "jdbc:sqlserver://";
    private static final String IP = "localhost";
    private static final String INSTANCE_NAME = "DESKTOP-BH44E05";
    private static final String PORT = "1433";
    private static final String DATABASE = "PlantShop";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    public static final Connection makeConnection() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(ADDRESS);
        builder.append(IP);
        builder.append("\\");
        builder.append(INSTANCE_NAME);
        builder.append(":");
        builder.append(PORT);
        builder.append(";databasename=");
        builder.append(DATABASE);
        builder.append(";user=");
        builder.append(USER);
        builder.append(";password=");
        builder.append(PASSWORD);

        final String url = builder.toString();
        Class.forName(DRIVER);
        return DriverManager.getConnection(url);
    }
}
