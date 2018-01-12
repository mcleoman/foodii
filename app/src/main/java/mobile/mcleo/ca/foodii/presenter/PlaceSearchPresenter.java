package mobile.mcleo.ca.foodii.presenter;

import java.util.List;

import mobile.mcleo.ca.foodii.interfaces.PlaceSearchMVP;
import mobile.mcleo.ca.foodii.model.PlaceSearchModel;
import mobile.mcleo.ca.foodii.pojo.Place;

/**
 * Created by leoman on 2017-09-05.
 */

public class PlaceSearchPresenter implements PlaceSearchMVP.PresenterOps, PlaceSearchMVP.RequiredPresenterOps{

    PlaceSearchMVP.ModelOps mModel;

    public PlaceSearchPresenter(){
        mModel = new PlaceSearchModel(this);
    }

    @Override
    public void doYelpSearch(String input) {

    }

    @Override
    public void doSavePlaceToDB(Place place) {
        mModel.savePlace(place);
    }

    @Override
    public void onSearchResults(List<Object> results) {

    }
}
