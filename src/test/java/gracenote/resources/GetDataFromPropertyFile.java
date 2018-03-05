package gracenote.resources;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by iparmar on 02/09/2018.
 */
public class GetDataFromPropertyFile {
    private Properties properties;
    private String dbHost, dbInstance, username, password, schema;
    private String apiServer;
    private String loginInfo;

    public GetDataFromPropertyFile() {

        readPropertyFile();
        setApiServer();
        setDbHost();
        setDbInstance();
        setUsername();
        setPassword();
        setSchema();
        setLoginInfo();
    }

    private void readPropertyFile() {
        InputStream inputStream = null;
        try {
            properties = new Properties();
            String propFileName = "config.properties";
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("Property File " + propFileName + " not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception " + e);

        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                System.out.println("Error : " + e);
            }
        }
    }

    public String getApiServer() {
        return apiServer;
    }

    private void setApiServer() {
        this.apiServer = properties.getProperty("apiServer");
    }

    public String getLoginInfo() {
        return loginInfo;
    }

    private void setLoginInfo() {
        this.loginInfo = properties.getProperty("loginInfo");
    }

    public String getDbHost() {
        return dbHost;
    }

    private void setDbHost() {
        this.dbHost = properties.getProperty("dbHost");
    }

    public String getDbInstance() {
        return dbInstance;
    }

    private void setDbInstance() {
        this.dbInstance = properties.getProperty("dbInstance");
    }

    public String getUsername() {
        return username;
    }

    private void setUsername() {
        this.username = properties.getProperty("username");
    }

    public String getPassword() {
        return password;
    }

    private void setPassword() {
        this.password = properties.getProperty("password");
    }

    public String getSchema() {
        return schema;
    }

    private void setSchema() {
        this.schema = properties.getProperty("schema");
    }
}
