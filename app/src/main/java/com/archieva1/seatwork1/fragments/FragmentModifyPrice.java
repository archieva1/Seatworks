package com.archieva1.seatwork1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.archieva1.seatwork1.MainActivity;
import com.archieva1.seatwork1.R;
import com.archieva1.seatwork1.model.Item;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentModifyPrice extends Fragment{
    private static final String TAG = "FragmentModifyItem";
    private TextInputLayout unitPriceEdittext;
    private Button saveButton;
    private Item item;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_modify_unit_price_layout,container,false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        item = MainActivity.getItem();
        setWidgetReferences(view);

    }
    //setting up the id of the widgets and the onclick listener
    private void setWidgetReferences(View view) {
        unitPriceEdittext = view.findViewById(R.id.modify_unit_price_edittext);
        unitPriceEdittext.getEditText().setText(String.format("%.2f", item.getUnitPrice()));
        unitPriceEdittext.getEditText().selectAll();
        saveButton = view.findViewById(R.id.modify_unit_price_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePrice();
            }
        });

    }
    //method for modifying the item price from this activity to the main activity
    private void updatePrice() {
        if(!validateInputValues(unitPriceEdittext,"Unit Price",8))
            return;
        double unitPrice = Double.parseDouble(unitPriceEdittext.getEditText().getText().toString().trim());
        //setting the item to the main activity
        MainActivity.updatePrice(unitPrice);
        //returning to the main activity
        getActivity().onBackPressed();
    }

    /**
     * Text input Validations
     */
    //method for the text validation
    private boolean validateInputValues(TextInputLayout textInputLayout, String inputName, int length) {
        //handles the value of the text within the text input layout edit text
        String inputText = textInputLayout.getEditText().getText().toString().trim();
        //checks if the input value is empty
        if (inputText.isEmpty()) {
            textInputLayout.setError(inputName+" is required.");
            return false;
        }
        //checks if the input is above the required length
        else if(inputText.length() > length){
            textInputLayout.setError(inputName+" is too long.");
            return false;
        }else {
            textInputLayout.setError("");
            return true;
        }
    }
}
