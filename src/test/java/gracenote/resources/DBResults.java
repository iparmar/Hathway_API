package gracenote.resources;

import gracenote.DB.Queries;
import gracenote.DB.QueryResult;
import org.junit.Assert;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by iparmar on 02/10/2018.
 */
public class DBResults {
    private Queries query;
    private QueryResult queryResult;

    public DBResults() {
        this.query = new Queries();
        this.queryResult = new QueryResult();
        //queryResult.setConnectivity();
    }

    public ResultSet getLoginInfoResultSet(String id) {
        ResultSet result = null;
        try {
            result = queryResult.getMSSQLData("EPG",query.getLoginInfo(id));
        } catch (Exception e) {
            System.out.println("Exception thrown while Fetching DB Results : " + e);
        }
        return result;
    }

    public void cleanUp() {
        try {
            queryResult.CleanUpDBConnections();
        } catch (SQLException e) {
            System.out.println("Exception thrown while DB Cleanup : " + e);
        }
    }
}
