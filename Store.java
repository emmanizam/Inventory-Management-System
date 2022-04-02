/**
 * Project OOP: Inventory Management System
 */

import java.util.Scanner;
import java.text.DecimalFormat;

public class Store
{
    // variables declaration
    protected String id;
    protected int brand;
    protected int quantity;
    protected double price;
    protected String staffID;
    
    DecimalFormat df = new DecimalFormat("0.00");
    
    // default constructor
    public Store()
    {
        id = null;
        brand = 0;
        quantity = 0;
        price = 0.0;
        staffID = null;
    }
    
    // normal constructor
    public Store(String id, int brand, int quantity, double price, String staffID)
    {
        this.id = id;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
        this.staffID = staffID;
    }

    // set method/ mutator
    public void setID(String id)
    {
        this.id = id;
    }
    public void setBrand(int brand)
    {
        this.brand = brand;
    }
    public void setQty(int quantity)
    {
        this.quantity = quantity;
    }
    public void setPrice(double price)
    {
        this.price = price;
    }
    public void setStaffID(String staffID)
    {
        this.staffID = staffID;
    }
    
    // get method/ accessor
    public String getID(){return id;}
    public int getBrand(){return brand;}
    public int getQty(){return quantity;}
    public double getPrice(){return price;}
    public String getStaffID(){return staffID;}
    
    // operation methods
    public String brandName(int brand)
    {
        String brandName;
        
        if(brand == 1)
            brandName = "Nike";
        else if(brand == 2)
            brandName = "Adidas";
        else if(brand == 3)
            brandName = "Under Armour";
        else if(brand == 4)
            brandName = "Puma";
        else
            brandName = "Reebok";
            
        return brandName;
    }
    
    // toString method
    public String toString()
    {
        return "\nItem ID: " + id + 
        "\nBrand: " + brandName(brand) +
        "\nPrice: RM" + df.format(price) +
        "\nTotal Quantity: " + quantity;
        
    }
    
}
