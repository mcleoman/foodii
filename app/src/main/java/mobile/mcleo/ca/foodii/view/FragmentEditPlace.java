package mobile.mcleo.ca.foodii.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import mobile.mcleo.ca.foodii.R;
import mobile.mcleo.ca.foodii.pojo.Place;
import mobile.mcleo.ca.foodii.viewmodels.EditPlaceViewModel;

/**
 * Created by leoman on 2017-12-28.
 */

public class FragmentEditPlace extends Fragment{

    TextView mStatus;
    TextInputEditText mEditName;
    TextInputEditText mEditAddress;
    TextInputEditText mEditType;
    Button mButtonUpdate;

    EditPlaceViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View viewFrame = inflater.inflate(R.layout.ui_fragment_edit_place, null);
        return viewFrame;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mStatus = view.findViewById(R.id.place_edit_status);
        mEditName = view.findViewById(R.id.place_edit_name_txt);
        mEditAddress = view.findViewById(R.id.place_edit_address_txt);
        mEditType = view.findViewById(R.id.place_edit_type_txt);
        mButtonUpdate = view.findViewById(R.id.button_update);

        mButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewModel != null) {
                    mViewModel.updatePlace();
                }
            }
        });

        mEditName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String newStr = editable.toString();
                Place place = mViewModel.getCurrentPlace().getValue();
                if (!newStr.equals(place.getName())) {
                    place.setName(editable.toString());
                    mViewModel.getCurrentPlace().setValue(place);
                }
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(getActivity()).get(EditPlaceViewModel.class);
        mViewModel.getCurrentPlace().observe(getActivity(), new Observer<Place>() {
            @Override
            public void onChanged(@Nullable Place place) {
                updateUI();
            }
        });

        updateUI();

    }

    private void updateUI(){
        if (mViewModel != null && mViewModel.getCurrentPlace() != null) {
            Place place = mViewModel.getCurrentPlace().getValue();
            mEditName.setText(place.getName());
            mEditAddress.setText(place.getAddress());
            mEditType.setText(place.getType());
            mStatus.setText(mViewModel.isChanged() ? "changed" : "unchanged");
        }
    }
}
