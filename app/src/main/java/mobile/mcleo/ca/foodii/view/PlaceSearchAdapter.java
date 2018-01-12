package mobile.mcleo.ca.foodii.view;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import mobile.mcleo.ca.foodii.R;
import mobile.mcleo.ca.foodii.pojo.YelpBusiness;

/**
 * Created by leoman on 2017-09-17.
 */

public class PlaceSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<YelpBusiness> mPlaceResults;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_search_grid_place, parent, false);
        return new PlaceGridViewHolder(v);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder != null) {
            ((PlaceGridViewHolder)holder).setItem(mPlaceResults.get(position));
        }
    }

    public void updatePlaceResults(List<YelpBusiness> mPlaceResults) {
        this.mPlaceResults = mPlaceResults;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mPlaceResults != null ? mPlaceResults.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    public class PlaceGridViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.grid_place_image)ImageView placeImage;
        @BindView(R.id.grid_place_name) TextView placeName;

        public PlaceGridViewHolder(View viewItem){
            super(viewItem);
            ButterKnife.bind(this, viewItem);
        }

        public void setItem(YelpBusiness business){
            placeName.setText(business.getName());
            Picasso.with(itemView.getContext())
                    .load(TextUtils.isEmpty(business.getImageUrl()) ? "http" : business.getImageUrl())
                    .placeholder(R.drawable.forknknife)
                    .into(placeImage);
        }

    }

}
