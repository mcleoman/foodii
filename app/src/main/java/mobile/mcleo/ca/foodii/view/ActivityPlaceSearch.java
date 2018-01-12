package mobile.mcleo.ca.foodii.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import mobile.mcleo.ca.foodii.FDApplication;
import mobile.mcleo.ca.foodii.R;
import mobile.mcleo.ca.foodii.interfaces.PlaceSearchMVP;
import mobile.mcleo.ca.foodii.pojo.Place;
import mobile.mcleo.ca.foodii.pojo.YelpAuthObject;
import mobile.mcleo.ca.foodii.pojo.YelpBusiness;
import mobile.mcleo.ca.foodii.presenter.PlaceSearchPresenter;
import mobile.mcleo.ca.foodii.services.YelpBusinessResponse;
import mobile.mcleo.ca.foodii.services.YelpService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by leoman on 2017-08-09.
 */

public class ActivityPlaceSearch extends AppCompatActivity implements PlaceSearchMVP.RequriedViewOps{

//    private DatabaseReference mDatabase;

    @BindView(R.id.button_save) Button mSaveButton;
    @BindView(R.id.search_view_main) SearchView mSearchView;
    @BindView(R.id.search_result_list) RecyclerView mRecycler;

    private PlaceSearchMVP.PresenterOps mPresenter;
    private PlaceSearchAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.ui_activity_search);
        ButterKnife.bind(this);

        mPresenter = new PlaceSearchPresenter();

        Observable<String> searchStream = Observable.create(new ObservableOnSubscribe<String>(){
            @Override
            public void subscribe(final @NonNull ObservableEmitter<String> e) throws Exception {
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        e.onComplete();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        e.onNext(newText);
                        return false;
                    }
                });
            }
        });

        searchStream.debounce(1, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
//                        Toast.makeText(ActivityPlaceSearch.this, "input="+s, Toast.LENGTH_SHORT).show();
                        searchYelp(s);
                    }
                });

        doAuth();

        mAdapter = new PlaceSearchAdapter();
        mRecycler.setAdapter(mAdapter);
        mRecycler.setHasFixedSize(false);
        mRecycler.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }


    private YelpService createService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.yelp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        YelpService service = retrofit.create(YelpService.class);
        return service;
    }

    private void doAuth(){
        YelpService authService = createService();
        Call<YelpAuthObject> call = authService.authYelp(FDApplication.getYelpClientId(), FDApplication.getYelpSecret());
        call.enqueue(new Callback<YelpAuthObject>() {
            @Override
            public void onResponse(Call<YelpAuthObject> call, Response<YelpAuthObject> response) {
                String token = response.body().getAccessToken();
                Toast.makeText(ActivityPlaceSearch.this, token, Toast.LENGTH_LONG).show();
                ((FDApplication)getApplicationContext()).setYelpAccessToken(token);

            }

            @Override
            public void onFailure(Call<YelpAuthObject> call, Throwable t) {
                Toast.makeText(ActivityPlaceSearch.this, "auth failed", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void searchYelp(String term){
        String token = ((FDApplication)getApplicationContext()).getYelpAccessToken();
        YelpService searchService = createService();
        Call<YelpBusinessResponse> call = searchService.searchYelp("Bearer "+token, term, "toronto");
        call.enqueue(new Callback<YelpBusinessResponse>() {
            @Override
            public void onResponse(Call<YelpBusinessResponse> call, Response<YelpBusinessResponse> response) {
                String name = response.body().getBusinesses().get(0).getName();
                Toast.makeText(ActivityPlaceSearch.this, name, Toast.LENGTH_LONG).show();
                if (mAdapter != null) {
                    mAdapter.updatePlaceResults(response.body().getBusinesses());
                }
            }

            @Override
            public void onFailure(Call<YelpBusinessResponse> call, Throwable t) {
                Toast.makeText(ActivityPlaceSearch.this, "search failed", Toast.LENGTH_LONG).show();

            }
        });
    }


    @OnClick(R.id.button_save)
    public void doSave(){

        Place place = new Place("Magic Noodles NY", "4321 Yonge street");
        mPresenter.doSavePlaceToDB(place);

//        Toast.makeText(this, "saving...", Toast.LENGTH_LONG).show();
//        Place place = new Place("Magic Noodles NY", "4321 Yonge street");
//        String newId = mDatabase.child("places").push().getKey();
//        mDatabase.child("places").child(newId).setValue(place);
    }



    @Override
    public void displaySearchResults(List<Object> results) {

    }
}
