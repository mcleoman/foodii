package mobile.mcleo.ca.foodii;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by leoman on 2017-08-09.
 */

public class FDApplication extends Application{

    private final static String YELP_CLIENT_ID = "7wopHUgOm90EJeI6IwFfjQ";
    private final static String YELP_SECRET = "4wjfR0HIySSVuK5S3tvAuniGlfd15iGKBBEKpPJmqdHGAFTvR0nKLKyyxoFxJRVW";
    private String mYelpAccessToken;


    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }

    public void setYelpAccessToken(String mYelpAccessToken) {
        this.mYelpAccessToken = mYelpAccessToken;
    }

    public String getYelpAccessToken() {
        return mYelpAccessToken;
    }

    public static String getYelpClientId() {
        return YELP_CLIENT_ID;
    }

    public static String getYelpSecret() {
        return YELP_SECRET;
    }
}
