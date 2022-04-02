
public class Equipment extends Store
{
    // variables declaration
    private String category; // e.g: rugby, football or swimming
    private String ageCategory; // children or adult
    
    // default constructor
    public Equipment()
    {
        category = null;
        ageCategory = null;
    }
    
    // normal constructor
    public Equipment(String id, int brand, int quantity, double price, String category, String ageCategory, String staffID)
    {
        super(id, brand, quantity, price, staffID);
        this.category = category;
        this.ageCategory = ageCategory;
    }
    
    // set method/ mutator
    public void setCategory(String category)
    {
        this.category = category;
    }
    public void setAgeCat(String ageCategory)
    {
        this.category = category;
    }
    
    // get method/ accessor
    public String getCategory()
    {
        return category;
    }
    public String getAgeCat()
    {
        return ageCategory;
    }
    
    // method overriding
    public String getID()
    {
        return super.getID();
    }
    
    // toString method
    public String toString()
    {
        return super.toString() + 
        "\nCategory: " + category +
        "\nAge Category: " + ageCategory ;
    }
    
    // to write in output file
    public String outputFile()
    {
        return super.getID() + ";" + super.getBrand() + ";" + super.getPrice() + ";" + super.getQty() + ";" + getCategory() + ";" + getAgeCat() + ";" + super.getStaffID() + "\n";
    }
}
