package com.archieva1.seatwork1.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.archieva1.seatwork1.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ItemImageAdapter extends RecyclerView.Adapter<ItemImageAdapter.ItemImageViewHolder> {
    private Context context;
    private List<Uri> itemImageList;
    private List<String> filename;
    //constructor
    public ItemImageAdapter(Activity context, List<String> filename, List<Uri> itemImageList) {
        this.context = context;
        this.itemImageList = itemImageList;
        this.filename = filename;
    }
    @NonNull
    @Override
    public ItemImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View inflate = inflater.inflate(R.layout.item_image_layout,parent,false);
        return new ItemImageViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemImageViewHolder holder, int position) {
        holder.imageName.setText("image:"+(position+1));
        Picasso.get()
                .load(itemImageList.get(position))
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                //.networkPolicy(NetworkPolicy.OFFLINE,NetworkPolicy.NO_STORE ,NetworkPolicy.NO_CACHE)
                .centerInside()
                .resize(500, 500)
                .into(holder.image);
    }


    @Override
    public int getItemCount() {
        return itemImageList.size();
    }


    private OnItemClickListener mListener;

    public interface OnItemClickListener
    {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }

    public class ItemImageViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public ImageButton close;
        public TextView imageName;
        public ItemImageViewHolder(@NonNull View itemView) {
            super(itemView); image = itemView.findViewById(R.id.item_image);
            close = itemView.findViewById(R.id.cancel_image);
            imageName = itemView.findViewById(R.id.image_name);
            //method for the close if clicked
            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(getAdapterPosition() !=   RecyclerView.NO_POSITION){
                        int position = getAdapterPosition();
                        if (position!= RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                        return;
                    }
                }
            });

        }
    }
}
