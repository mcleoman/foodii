package mobile.mcleo.ca.foodii.pojo;

import mobile.mcleo.ca.foodii.services.YelpService;

/**
 * Created by leoman on 2017-09-16.
 */

public class YelpAuthObject {

    String access_token;
    Integer expires_in;
    String token_type;

    public YelpAuthObject(){}

    public String getAccessToken() {
        return access_token;
    }
}
