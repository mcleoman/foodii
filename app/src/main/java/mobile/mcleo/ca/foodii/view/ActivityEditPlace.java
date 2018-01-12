package mobile.mcleo.ca.foodii.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import mobile.mcleo.ca.foodii.R;
import mobile.mcleo.ca.foodii.pojo.Place;
import mobile.mcleo.ca.foodii.viewmodels.EditPlaceViewModel;

/**
 * Created by leoman on 2017-12-28.
 */

public class ActivityEditPlace extends AppCompatActivity{


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_activity_edit_place);

        EditPlaceViewModel viewModel = ViewModelProviders.of(this).get(EditPlaceViewModel.class);
        viewModel.getCurrentPlace().observe(this, new Observer<Place>() {
            @Override
            public void onChanged(@Nullable Place place) {
                onPlaceChanged(place);
            }
        });
        viewModel.init();

        initFragment();


    }


    private void initFragment(){

        FragmentEditPlace fragmentEditPlace = new FragmentEditPlace();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.layout_frame, fragmentEditPlace, "EditFrag").commit();

    }


    private void onPlaceChanged(Place place){

        Toast.makeText(this, place.toString(), Toast.LENGTH_SHORT).show();
    }


}
