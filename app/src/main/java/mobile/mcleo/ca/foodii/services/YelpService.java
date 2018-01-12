package mobile.mcleo.ca.foodii.services;

import java.util.List;

import mobile.mcleo.ca.foodii.pojo.Place;
import mobile.mcleo.ca.foodii.pojo.YelpAuthObject;
import mobile.mcleo.ca.foodii.pojo.YelpBusiness;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by leoman on 2017-09-16.
 */

public interface YelpService {

    static final String API_VERSION = "v3/";

    @POST("oauth2/token")
    Call<YelpAuthObject> authYelp(@Query("client_id") String clientId, @Query("client_secret") String clientSecret);

    @GET(API_VERSION+"businesses/search")
    Call<YelpBusinessResponse> searchYelp(@Header("Authorization") String authorization, @Query("term") String keyword, @Query("location") String location);


}
