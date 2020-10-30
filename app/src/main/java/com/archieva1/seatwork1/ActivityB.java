package com.archieva1.seatwork1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TextView;

import com.archieva1.seatwork1.adapter.SectionStatePagerAdapter;
import com.archieva1.seatwork1.fragments.FragmentAuthorInfo;
import com.archieva1.seatwork1.fragments.FragmentInputItem;
import com.archieva1.seatwork1.fragments.FragmentModifyPrice;
import com.archieva1.seatwork1.fragments.FragmentModifyQuantity;
import com.archieva1.seatwork1.fragments.FragmentViewItem;

public class ActivityB extends AppCompatActivity {
    private ViewPager viewPager;
    private SectionStatePagerAdapter sectionStatePagerAdapter;
    private Toolbar toolbar;
    private TextView mTitle;
    private int selectedPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setToolbar();
        selectedPage = getIntent().getIntExtra("SelectedPage",0);
        viewPager = findViewById(R.id.activity_b_container);
        setupViewPager(selectedPage);
    }

    private void setToolbar() {
        toolbar = findViewById(R.id.activity_b_toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Activity B");
        toolbar.setNavigationIcon(R.drawable.ic_navigate_before_black_24dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setupViewPager(int selectedPage) {
        sectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        switch (selectedPage) {
            case 0:
                sectionStatePagerAdapter.addFragment(new FragmentInputItem(), "Input Item");
            break;
            case 1:
                sectionStatePagerAdapter.addFragment(new FragmentViewItem(), "View Item");
                break;
            case 2:
                sectionStatePagerAdapter.addFragment(new FragmentModifyPrice(), "Modify Unit Price");
                break;
            case 3:
                sectionStatePagerAdapter.addFragment(new FragmentModifyQuantity(), "Modify Unit Quantity");
                break;
            case 4:
                sectionStatePagerAdapter.addFragment(new FragmentAuthorInfo(), "Author Info");
            break;
        }
        viewPager.setAdapter(sectionStatePagerAdapter);
        mTitle.setText(sectionStatePagerAdapter.getItemTitle(0));
    }


    public void setViewPager(int fragmentNumber){
        viewPager.setCurrentItem(fragmentNumber);
    }

}
