package com.archieva1.seatwork1.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.archieva1.seatwork1.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class FragmentAuthorInfo extends Fragment implements View.OnClickListener{
    private static final String TAG = "FragmentInputItem";
    CircleImageView author_image;
    ImageView fbButton, linkedInButton, gitHubButton;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_author_layout,container,false);
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        author_image = view.findViewById(R.id.author_image);
        fbButton = view.findViewById(R.id.fb_link_botton);
        linkedInButton = view.findViewById(R.id.linkedin_link_botton);
        gitHubButton = view.findViewById(R.id.github_link_botton);
        fbButton.setOnClickListener(this);
        linkedInButton.setOnClickListener(this);
        gitHubButton.setOnClickListener(this);
        author_image.setOnClickListener(this);
        String imageLink = "https://scontent.fceb1-1.fna.fbcdn.net/v/t1.0-9/78856509_2547233195352205_2493820649584197632_o.jpg?" +
                "_nc_cat=104&ccb=2&_nc_sid=09cbfe&_nc_ohc=0NoxyPxwRhsAX-19a_k&_nc_ht=scontent.fceb1-1" +
                ".fna&oh=4a8212a7517c729c0e5951631c2a302a&oe=5FB9A05C";
        Picasso.get()
                .load(imageLink)
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                //.networkPolicy(NetworkPolicy.OFFLINE,NetworkPolicy.NO_STORE ,NetworkPolicy.NO_CACHE)
                .centerInside()
                .resize(500, 500)
                .into(author_image);


    }

    @Override
    public void onClick(View view) {
        Intent viewIntent;
        switch (view.getId()){
            case R.id.author_image:
                viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.facebook.com/archieva1"));
                startActivity(viewIntent);
            case R.id.fb_link_botton:
                viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.facebook.com/archieva1"));
                startActivity(viewIntent);
                break;
            case R.id.linkedin_link_botton:
                viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://www.linkedin.com/in/archie-val-273935177?fbclid=IwAR22DjsfGyrI90lMCgu7FaYzlpdAs7AnrllBjjh7TszIa4uCAm1vFxP5O8g"));
                startActivity(viewIntent);
                break;
            case R.id.github_link_botton:
                viewIntent =
                        new Intent("android.intent.action.VIEW",
                                Uri.parse("https://github.com/archieva1"));
                startActivity(viewIntent);
                break;
        }
    }
}
