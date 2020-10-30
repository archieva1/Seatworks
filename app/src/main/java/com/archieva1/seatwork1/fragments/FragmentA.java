package com.archieva1.seatwork1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.archieva1.seatwork1.ActivityB;
import com.archieva1.seatwork1.MainActivity;
import com.archieva1.seatwork1.R;
import com.archieva1.seatwork1.adapter.SliderAdapter;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class FragmentA extends Fragment implements View.OnClickListener{
    private static final String TAG = "FragmentA";
    private List<String> imageLinks;
    //slider view
    private SliderView sliderView;
    //slider adapter
    private SliderAdapter adapter;
    //frament bottons
    private CardView inputItem, viewItem, modifyPrice, modifyQuantity, removeItem,authorInfo;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_a_layout,container,false);
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //adding new image links
        imageLinks = new ArrayList<String>();
        imageLinks.add("https://i.pinimg.com/originals/4a/b9/94/4ab994aa22ef942921a39f48a489ab0d.jpg");
        imageLinks.add("https://brandsvice.com/wp-content/uploads/2019/01/BRANDSVICE-SHOPPING-BANNER-1.jpg");
        imageLinks.add("https://i.pinimg.com/736x/cf/1f/db/cf1fdb8a18f48473a2859267279bae60.jpg");
        imageLinks.add("https://i.pinimg.com/originals/66/43/cd/6643cd97e23054d97b6d665237c4ac9d.jpg");
        imageLinks.add("https://www.citrusplaza.com/wp-content/uploads/2016/05/main-banner-shopping-1.png");
        imageLinks.add("https://cdn2.f-cdn.com/contestentries/768451/17781028/57cd69aec20fe_thumb900.jpg");
        imageLinks.add("https://i.pinimg.com/originals/6b/01/ce/6b01cee0eb95524c30f3030d9ea02dbe.jpg");
        //assigning silder view id
        sliderView = view.findViewById(R.id.image_slider);
        //set the sliderview adapter
        setSliderViewAdapter(sliderView);
        sliderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setSliderViewAdapter(sliderView);
            }
        });
        setButtonReferences(view);


    }

    private void setButtonReferences(@NonNull View view) {
        //assigning ids
        inputItem = view.findViewById(R.id.input_new_item_button);
        viewItem = view.findViewById(R.id.view_item_button);
        modifyPrice = view.findViewById(R.id.modify_item_price_button);
        modifyQuantity = view.findViewById(R.id.modify_item_quantity_button);
        removeItem = view.findViewById(R.id.remove_item_button);
        authorInfo = view.findViewById(R.id.authors_info_button);
        //assigning onclick listener
        inputItem.setOnClickListener(this);
        viewItem.setOnClickListener(this);
        modifyPrice.setOnClickListener(this);
        modifyQuantity.setOnClickListener(this);
        removeItem.setOnClickListener(this);
        authorInfo.setOnClickListener(this);
    }

    private void setSliderViewAdapter(SliderView sliderView) {
        adapter = new SliderAdapter(getContext(),imageLinks);
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        // set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!//sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        intent = new Intent(getContext(), ActivityB.class);
        switch (view.getId()) {
            case R.id.input_new_item_button:
                intent.putExtra("SelectedPage", 0);
                break;
            case R.id.view_item_button:
                if (MainActivity.getItem() != null) {
                    intent.putExtra("SelectedPage", 1);
                } else {
                    Toast.makeText(getContext(), "No Item has been added", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.modify_item_price_button:
                if (MainActivity.getItem() != null) {
                    intent.putExtra("SelectedPage", 2);
                }else {
                    Toast.makeText(getContext(), "No Item has been added", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.modify_item_quantity_button:
                if (MainActivity.getItem() != null) {
                intent.putExtra("SelectedPage", 3);
                }else {
                    Toast.makeText(getContext(), "No Item has been added", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.remove_item_button:
                if (MainActivity.getItem() != null) {
                    MainActivity.removeItem();
                }else {
                    Toast.makeText(getContext(), "No Item has been added", Toast.LENGTH_LONG).show();
                    return;
                }
                break;
            case R.id.authors_info_button:
                intent.putExtra("SelectedPage", 4);
                break;
        }
        getActivity().startActivity(intent);
    }
}
