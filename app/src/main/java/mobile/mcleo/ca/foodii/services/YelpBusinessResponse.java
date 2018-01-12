package mobile.mcleo.ca.foodii.services;

import java.util.List;

import mobile.mcleo.ca.foodii.pojo.YelpBusiness;

/**
 * Created by leoman on 2017-09-16.
 */

public class YelpBusinessResponse {

    List<YelpBusiness> businesses;

    public YelpBusinessResponse(){

    }

    public List<YelpBusiness> getBusinesses() {
        return businesses;
    }
}
