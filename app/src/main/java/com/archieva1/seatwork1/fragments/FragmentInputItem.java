package com.archieva1.seatwork1.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.archieva1.seatwork1.MainActivity;
import com.archieva1.seatwork1.R;
import com.archieva1.seatwork1.adapter.ItemImageAdapter;
import com.archieva1.seatwork1.model.Item;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class FragmentInputItem extends Fragment{
    private static final String TAG = "FragmentInputItem";
    private static final int PICK_IMAGE_REQUEST = 71;
    private RecyclerView recyclerView;
    //
    private Button addImageButton,addItemButton;
    private TextView promptMessage;
    private TextInputLayout itemCodeInputTextLayout, itemDescriptionInputTextLayout, unitPriceInputTextLayout, unitQuantityInputTextLayout;
    private ItemImageAdapter itemImageAdapter;
    private Uri filePath;
    ArrayList<Uri> itemImagesList;
    public  static ArrayList<String> imageList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_input_new_item_layout,container,false);
        return  view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setWidgetReferences(view);

    }

    /**
     * Sets up the references of the widgets
     * @param view
     */
    private void setWidgetReferences(View view) {
        recyclerView = view.findViewById(R.id.item_image_recyclerview);
        addImageButton = view.findViewById(R.id.add_image_button);
        addItemButton = view.findViewById(R.id.add_item_button);
        itemCodeInputTextLayout = view.findViewById(R.id.item_code_edittext);
        itemDescriptionInputTextLayout = view.findViewById(R.id.item_description_edittext);
        unitPriceInputTextLayout = view.findViewById(R.id.unit_price_edittext);
        unitQuantityInputTextLayout = view.findViewById(R.id.unit_quantity_edittext);
        promptMessage = view.findViewById(R.id.empty_prompt_message);
        addImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemToMainActivity();
            }
        });
        imageList = new ArrayList<>();
        itemImagesList = new ArrayList<>();
        itemImageAdapter = new ItemImageAdapter(getActivity(),imageList,itemImagesList);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
        itemImageAdapter.setOnItemClickListener(new ItemImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                itemImagesList.remove(position);
                imageList.remove(position);
                itemImageAdapter.notifyDataSetChanged();
                if(itemImagesList.isEmpty())
                    promptMessage.setVisibility(View.VISIBLE);
                else
                    promptMessage.setVisibility(View.GONE);
            }
        });
        recyclerView.setAdapter(itemImageAdapter);
    }
    //method for adding the item to the main activity
    private void addItemToMainActivity() {
        if (!validateInputValues(itemCodeInputTextLayout,"Item Code",10) |
                !validateInputValues(itemDescriptionInputTextLayout,"Item Description",100) |
                !validateInputValues(unitPriceInputTextLayout,"Unit Price",8) |
                !validateInputValues(unitQuantityInputTextLayout,"Unit Quantity",8) |
                !validateImage()
         )
            return;
        //Getting all the String values
        String itemCode = itemCodeInputTextLayout.getEditText().getText().toString().trim();
        String itemDesc = itemDescriptionInputTextLayout.getEditText().getText().toString().trim();
        double unitPrice = Double.parseDouble(unitPriceInputTextLayout.getEditText().getText().toString().trim());
        double unitQuanity = Double.parseDouble(unitQuantityInputTextLayout.getEditText().getText().toString().trim());
        //creating object item
        Item item = new Item(itemCode,itemDesc,unitPrice,unitQuanity,itemImagesList);
        //setting the item to the main activity
        MainActivity.setItem(item);
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
    //method for the image value validation
    private boolean validateImage(){
        //checks if the image is empty
        if (itemImageAdapter.getItemCount() == 0) {
            Toast.makeText(getActivity(),"No image has been added",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            return true;
        }
    }


    //intent method for accessing external storage images
    public void chooseImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE,true);
        startActivityForResult(intent.createChooser(intent,"Select Photos"), PICK_IMAGE_REQUEST);

    }
    //override method for accessing images and getting the image url
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if(requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK
                    && data != null)
            {
                if (data.getClipData() != null){

                    int countClipData = data.getClipData().getItemCount();
                    for(int currentImageSelect = 0; currentImageSelect<countClipData;currentImageSelect++){
                        filePath = data.getClipData().getItemAt(currentImageSelect).getUri();
                        if(!imageList.contains(filePath)){
                            imageList.add(filePath.toString());
                            itemImagesList.add(filePath);
                        }
                    }
                    itemImageAdapter.notifyDataSetChanged();

                }
                else{
                    if(!imageList.contains(data.getData().toString())){
                        imageList.add(data.getData().toString());
                        itemImagesList.add(data.getData());
                        itemImageAdapter.notifyDataSetChanged();
                    }else
                        Toast.makeText(getContext(), " Image is already selected", Toast.LENGTH_LONG).show();
                }
                if(itemImagesList.isEmpty())
                    promptMessage.setVisibility(View.VISIBLE);
                else
                    promptMessage.setVisibility(View.GONE);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            Toast.makeText(getContext(), "Error Selecting Images", Toast.LENGTH_LONG).show();
        }

    }


}
