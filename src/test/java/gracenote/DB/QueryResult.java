package gracenote.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by iparmar on 02/10/2018.
 */
public class QueryResult {
    private MSSQLDBConnection mssqldbConnection = new MSSQLDBConnection();
    private Statement statement = null;
    private Connection dbConnection = null;
    private ResultSet resultSet;

    public ResultSet getMSSQLData(String databaseName,String Query)
    {
        try {
            dbConnection = mssqldbConnection.getConnection(databaseName);
            statement = dbConnection.createStatement();
            resultSet = statement.executeQuery(Query);
        } catch (Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    public void CleanUpDBConnections() throws SQLException {
        if (dbConnection != null) {
            this.dbConnection.close();
        }
        if (statement != null) {
            this.statement.close();
        }
        if (resultSet != null) {
            this.resultSet.close();
        }
    }
}
