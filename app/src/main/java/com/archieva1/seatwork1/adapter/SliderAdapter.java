package com.archieva1.seatwork1.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.archieva1.seatwork1.R;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<String> imageLinks;

    public SliderAdapter() {
        this.context = null;
        this.imageLinks = null;
    }
    public SliderAdapter(Context context, List<String> imageLinks) {
        this.context = context;
        this.imageLinks = imageLinks;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_image_layout, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, int position) {
               Picasso.get()
                       .load(imageLinks.get(position))
                       .placeholder(R.drawable.default_image)
                       .error(R.drawable.default_image)
                       //.networkPolicy(NetworkPolicy.OFFLINE,NetworkPolicy.NO_STORE ,NetworkPolicy.NO_CACHE)
                       .centerInside()
                       .resize(500, 500)
                       .into(viewHolder.imageViewBackground);
    }

    @Override
    public int getCount() {
        //categories view count could be dynamic size
        return imageLinks.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.image_value);
            this.itemView = itemView;
        }
    }
}