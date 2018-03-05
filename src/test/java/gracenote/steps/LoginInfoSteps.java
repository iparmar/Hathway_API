package gracenote.steps;

import cucumber.api.java.en.And;
import gracenote.resources.DBResults;
import gracenote.resources.HttpClient;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iparmar on 02/09/2018.
 */
public class LoginInfoSteps {
    private HttpClient client;
    private DBResults dbResults;

    public LoginInfoSteps (HttpClient client,DBResults dbResults) {
        this.client = client;
        this.dbResults = dbResults;
    }

    private Map<String, String> getLoginParams (String responseLanguage,String searchid,String searchtype) {
        Map<String, String> parameters = new HashMap<String, String>();
        parameters.put("context","headendid=403;applicationname=hathwayselfcaretest");
        parameters.put("apikey","3c13c8c0aa4c4dc60d407f2cb81d1d1496a8bfbd");
        parameters.put("responseformat","json");
        parameters.put("responselanguage",responseLanguage);
        parameters.put("searchid",searchid);
        parameters.put("searchtype",searchtype);
        return parameters;
    }

    private void validateLoginAPIWithDB () {
        String useridFromJSON;
        try {
            JSONObject mainJsonObject = new JSONObject(client.getJSONResponse());
            JSONArray basicDetailsArray = mainJsonObject.getJSONObject("userdetails").getJSONObject("basicdetails").getJSONArray("basicdetail");
            String userId=basicDetailsArray.getJSONObject(0).getString("userid");
            System.out.println(userId);

            int totaluserid = basicDetailsArray.length();

            for (int i = 0; i < totaluserid; i++) {
                JSONObject useridobject = basicDetailsArray.getJSONObject(i);
                useridFromJSON = useridobject.getString("userid");

                ResultSet resultSet = dbResults.getLoginInfoResultSet(useridFromJSON);
                System.out.println("Database data ");
                while(resultSet.next()){
                    System.out.println("Database data " +resultSet.getString("Userid"));
                }

                                if (resultSet.wasNull()) {
                    Assert.fail("No Data in Result Set for UserId : " + useridFromJSON);
                }
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage() + "\nTarget API : " + client.getAPIUrl());
        } finally {
            dbResults.cleanUp();
        }
    }


    @And("^User sets paramters for Login Info API \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\"$")
    public void userSetsParamtersForLoginInfoAPIResponseLanguageSearchIDSearchType(String responseLanguage,String searchid,String searchtype) throws Throwable {
        client.constructParameterString(getLoginParams(responseLanguage,searchid,searchtype));
    }

    @And("^Login Info API response should match with database data$")
    public void loginInfoAPIResponseShouldMatchWithDatabaseData() throws Throwable {
        validateLoginAPIWithDB();
    }
}

