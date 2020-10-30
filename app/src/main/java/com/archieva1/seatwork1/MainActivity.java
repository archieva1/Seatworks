package com.archieva1.seatwork1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.archieva1.seatwork1.adapter.SectionStatePagerAdapter;
import com.archieva1.seatwork1.fragments.FragmentA;
import com.archieva1.seatwork1.model.Item;

public class MainActivity extends AppCompatActivity {
    //handles the viewpage or the container of the fragment
    private ViewPager viewPager;
    //handles the adapter of the fragment
    private SectionStatePagerAdapter sectionStatePagerAdapter;
    //handles the item
    private static Item item;

    private static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        viewPager = findViewById(R.id.activity_a_container);
        setupViewPager();
    }

    /**
     * methods for setting up the fragment A
     * in the main activity
     */
    //method for setting up the viewpager, adding the fragment A  and the fragment adapter
    private void setupViewPager() {
        sectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        sectionStatePagerAdapter.addFragment(new FragmentA(),"Fragment A");
        viewPager.setAdapter(sectionStatePagerAdapter);
        viewPager.setCurrentItem(0);
    }

    /**
     * Methods in accessing/modifying the Item
     * @param newItem
     */

    //setting new input item
    @NonNull
    public static void setItem(@NonNull Item newItem){
        if(newItem!=null) {
            item = newItem;
            Toast.makeText(context, "Item Saving sucessfully!",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Item Saving failed!",Toast.LENGTH_LONG).show();
        }
    }
    //method for removing the Item turning the item value in null
    public static void removeItem(){
        item = null;
        Toast.makeText(context, "Item removed sucessfully!",Toast.LENGTH_LONG).show();
    }
    @NonNull
    //method for updating the unit price of the item
    public static void updatePrice(@NonNull double price){
        if(price>0) {
            item.setUnitPrice(price);
            Toast.makeText(context, "Unit price updated sucessfully!", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Unit price updated  failed!",Toast.LENGTH_LONG).show();
        }
    }
    //method for updating the quantity of the item
    public static void updateQuantity(double quantity){
        if(quantity>0) {
        item.setUnitQuantity(quantity);
            Toast.makeText(context, "Unit quantity updated sucessfully!", Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(context, "Unit quantity updated  failed!",Toast.LENGTH_LONG).show();
        }
    }
    //method for getting the item info
    //returns the object item
    public static Item getItem(){
        return item;
    }
}
