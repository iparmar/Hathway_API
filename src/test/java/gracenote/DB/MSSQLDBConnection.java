package gracenote.DB;

import gracenote.resources.GetDataFromPropertyFile;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by iparmar on 02/09/2018.
 */
public class MSSQLDBConnection {
    private Connection connection = null;
    private GetDataFromPropertyFile getDataFromPropertyFile;

    public MSSQLDBConnection() {
        this.getDataFromPropertyFile = new GetDataFromPropertyFile();
    }

    public Connection getConnection(String schema) {
        String DBHost = getDataFromPropertyFile.getDbHost();
        String DBInstance = getDataFromPropertyFile.getDbInstance();
        //String schema = getDataFromPropertyFile.getSchema();
        String userName = getDataFromPropertyFile.getUsername();
        String password = getDataFromPropertyFile.getPassword();

        String host = "jdbc:sqlserver://" + DBHost + ":" + DBInstance + ";databaseName=" + schema;
        System.out.println("Database Host "  + host);
        try {
            String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            Class.forName(sqlDriver);
            connection = DriverManager.getConnection(host, userName, password);
        } catch (Exception e) {
            System.out.println("Exception occurred : " + e);
        }
        return connection;
    }

}
