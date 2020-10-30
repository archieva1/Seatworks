package com.archieva1.seatwork1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.archieva1.seatwork1.MainActivity;
import com.archieva1.seatwork1.R;
import com.archieva1.seatwork1.model.Item;
import com.google.android.material.textfield.TextInputLayout;

public class FragmentModifyQuantity extends Fragment{
    private static final String TAG = "FragmentModifyQuantityItem";
    private TextInputLayout unitQuantityEdittext;
    private Button saveButton;
    private Item item;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_modify_unit_quantity_layout,container,false);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        item = MainActivity.getItem();
        setWidgetReferences(view);

    }

    private void setWidgetReferences(View view) {
        unitQuantityEdittext = view.findViewById(R.id.modify_unit_quantity_edittext);
        unitQuantityEdittext.getEditText().setText(String.format("%.2f", item.getUnitQuantity()));
        unitQuantityEdittext.getEditText().selectAll();
        saveButton = view.findViewById(R.id.modify_unit_quantity_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePrice();
            }
        });

    }

    private void updatePrice() {
        if(!validateInputValues(unitQuantityEdittext,"Unit Quantity",8))
            return;
        double unitQuantity = Double.parseDouble(unitQuantityEdittext.getEditText().getText().toString().trim());
        //modifying the item quantity to the main activity
        MainActivity.updateQuantity(unitQuantity);
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
