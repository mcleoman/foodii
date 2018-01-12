package mobile.mcleo.ca.foodii;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import mobile.mcleo.ca.foodii.view.ActivityPlaceSearch;

/**
 * Created by leoman on 2017-08-09.
 */

public class SplashActivity extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_activity_splash);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, ActivityPlaceSearch.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }


}
