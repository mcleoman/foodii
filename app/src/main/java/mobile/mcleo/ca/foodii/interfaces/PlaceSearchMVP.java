package mobile.mcleo.ca.foodii.interfaces;

import java.util.List;

import mobile.mcleo.ca.foodii.pojo.Place;

/**
 * Created by leoman on 2017-09-05.
 */

public interface PlaceSearchMVP {

    interface RequriedViewOps {
        void displaySearchResults(List<Object> results);
    }


    interface PresenterOps {
        void doYelpSearch(String input);
        void doSavePlaceToDB(Place place);
    }

    interface RequiredPresenterOps {
        void onSearchResults(List<Object> results);
    }

    interface ModelOps {
        void searchYelp(String keyword);
        void savePlace(Place place);
    }
}
