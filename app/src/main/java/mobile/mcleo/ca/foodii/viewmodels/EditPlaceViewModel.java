package mobile.mcleo.ca.foodii.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import mobile.mcleo.ca.foodii.pojo.Place;

/**
 * Created by leoman on 2017-12-28.
 */

public class EditPlaceViewModel extends ViewModel {

    private MutableLiveData<Place> mCurrentPlace;
    private Place mOriginalCopy;

    public MutableLiveData<Place> getCurrentPlace() {
        if (mCurrentPlace == null) {
            mCurrentPlace = new MutableLiveData<>();
        }
        return mCurrentPlace;
    }

    public void init() {
        if (mCurrentPlace.getValue() == null) {
            Place place = new Place("Asian Busters", "321 Provost Dr.");
            mCurrentPlace.setValue(place);
            mOriginalCopy = place.clone();
        }
    }

    public boolean isChanged(){
        return mOriginalCopy != null && !mOriginalCopy.getName().equals(mCurrentPlace.getValue().getName());
    }

    public void updatePlace() {
        Place place = new Place("Asian Busters", Math.random()+" Provost Dr.");
        mCurrentPlace.postValue(place);
    }
}
