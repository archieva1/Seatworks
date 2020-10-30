package com.archieva1.seatwork1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.archieva1.seatwork1.MainActivity;
import com.archieva1.seatwork1.R;
import com.archieva1.seatwork1.adapter.SliderAdapter;
import com.archieva1.seatwork1.model.Item;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class FragmentViewItem  extends Fragment {
    private static final String TAG = "FragmentViewItem";
    //slider view
    private SliderView sliderView;

    private SliderAdapter adapter;
    //handles the item
    private Item item;
    private TextView itemCodeTextview, itemDescTextview, unitPriceTextview,ratingTextview, unitQuantTextview, unitTotalPriceTextview;
    private RatingBar ratingBar;
    private ImageButton fav;
    private boolean love;
    private Button confirm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_view_item_layout,container,false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setWidgetReferences(view);

    }

    private void setWidgetReferences(View view) {
        this.item = MainActivity.getItem();
        love = false;
        //assigning silder view id
        sliderView = view.findViewById(R.id.item_image_slider_view);
        setSliderViewAdapter(sliderView);
        itemCodeTextview = view.findViewById(R.id.item_code_textview);
        itemDescTextview = view.findViewById(R.id.item_description_textview);
        unitPriceTextview = view.findViewById(R.id.unit_price_textview);
        ratingTextview = view.findViewById(R.id.item_rating_textview);
        ratingBar = view.findViewById(R.id.item_rating_star);
        fav = view.findViewById(R.id.fav_button);
        confirm = view.findViewById(R.id.confirm_button);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               fav.setImageResource((love)?R.drawable.ic_favorite_full_24dp: R.drawable.ic_favorite_border_24dp);
               love = !love;
            }
        });
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Confirmed Product",Toast.LENGTH_LONG).show();
            }
        });
        unitQuantTextview = view.findViewById(R.id.unit_quantity_textview);
        unitTotalPriceTextview = view.findViewById(R.id.unit_total_price_textview);

        itemCodeTextview.setText(item.getItemCode());
        itemDescTextview.setText(item.getItemDesc());
        unitPriceTextview.setText(String.format("P %.2f",item.getUnitPrice()));
        unitQuantTextview.setText(String.format("%.2f",item.getUnitQuantity()));
        unitTotalPriceTextview.setText(String.format("P %.2f",item.getUnitPrice()*item.getUnitQuantity()));
        ratingTextview.setText(String.format("%.1f",5.0));
        ratingBar.setRating(5);
    }

    private void setSliderViewAdapter(SliderView sliderView) {
        adapter = new SliderAdapter(getContext(),item.getImageLinkList());
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        // set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!//sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setScrollTimeInSec(4); //set scroll delay in seconds :
        sliderView.startAutoCycle();
    }

}
