package mobile.mcleo.ca.foodii.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import mobile.mcleo.ca.foodii.interfaces.PlaceSearchMVP;
import mobile.mcleo.ca.foodii.pojo.Place;

/**
 * Created by leoman on 2017-09-05.
 */

public class PlaceSearchModel implements PlaceSearchMVP.ModelOps{


    PlaceSearchMVP.RequiredPresenterOps mPresenter;
    DatabaseReference mDatabase;


    public PlaceSearchModel(PlaceSearchMVP.RequiredPresenterOps presenter) {
        mPresenter = presenter;

        mDatabase = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void searchYelp(String keyword) {

    }

    @Override
    public void savePlace(Place place) {

//        Place place = new Place("Magic Noodles NY", "4321 Yonge street");
        String newId = mDatabase.child("places").push().getKey();
        mDatabase.child("places").child(newId).setValue(place);
    }
}
