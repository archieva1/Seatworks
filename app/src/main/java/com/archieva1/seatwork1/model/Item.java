package com.archieva1.seatwork1.model;

import android.net.Uri;

import java.util.ArrayList;

public class Item {
    /**
     * Item Attributes
     */
    //handles the item Code
    private String itemCode;
    //handles the item Desctionption
    private String itemDesc;
    //handles the unit price
    private double unitPrice;
    //handles the unit quantity
    private double unitQuantity;
    //additional attribute that handles the images of item
    private ArrayList<Uri> imageByteList;

    /**
     * Constructors
     */
    //default Constructor
    public Item() {
        this.itemCode = "";
        this.itemDesc = "";
        this.unitPrice = 0.0;
        this.unitQuantity = 0.0;
        this.imageByteList = new ArrayList<>();
    }
    //Constructor with parameter
    public Item(String itemCode, String itemDesc, double unitPrice, double unitQuantity, ArrayList<Uri> imageByteList) {
        this.itemCode = itemCode;
        this.itemDesc = itemDesc;
        this.unitPrice = unitPrice;
        this.unitQuantity = unitQuantity;
        this.imageByteList = imageByteList;
    }

    /**
     * Getters and Setters
     * @return
     */
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getUnitQuantity() {
        return unitQuantity;
    }

    public void setUnitQuantity(double unitQuantity) {
        this.unitQuantity = unitQuantity;
    }

    public ArrayList<Uri> getImageByteList() {
        return imageByteList;
    }

    public void setImageByteList(ArrayList<Uri> imageByteList) {
        this.imageByteList = imageByteList;
    }

    public ArrayList<String> getImageLinkList() {
        ArrayList<String> imageLink = new ArrayList<>();
        for (Uri uri: imageByteList){
            imageLink.add(uri.toString());
        }
        return imageLink;
    }
}
