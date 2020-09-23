package com.example.farmermate;

public class Rice {
    private int ID;
    private String Name;
    // private int price;
    private String Description;
    private String Products;
    private String Advantage;

    public Rice(int ID, String Name, String Description, String Products, String Advantage) {
        this.ID = ID;
        this.Name = Name;
        this.Description = Description;
        this.Products = Products;
        this.Advantage = Advantage;
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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getAdvantage() {
        return Advantage;
    }
    public void setAdvantage(String Advantage) {
        this.Advantage = Advantage;
    }

    public String getProducts() {
        return Products;
    }
    public void setProducts(String Products) {
        this.Products = Products;
    }

}