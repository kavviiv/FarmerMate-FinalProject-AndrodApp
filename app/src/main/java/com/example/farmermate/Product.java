package com.example.farmermate;

public class Product {
    private int ID;
    private String Name;
    // private int price;
    private String Description;

    public Product(int ID, String Name, String Description) {
        this.ID = ID;
        this.Name = Name;
        //this.price = price;
        this.Description = Description;
    }

    public int getId() {
        return ID;
    }

    public void setId(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
}

