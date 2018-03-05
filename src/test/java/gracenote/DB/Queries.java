package gracenote.DB;

/**
 * Created by iparmar on 02/10/2018.
 */
public class Queries {
    public String getLoginInfo(String id) {
        return "Select UserId from UserProfileAttributeDetails where AccountNo=" + id;
    }
}
