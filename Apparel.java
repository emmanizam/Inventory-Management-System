
public class Apparel extends Store
{
    // variables declaration
    final int S = 5;
    private int[] size = new int[S]; // XS, S, M, L, XL
    private String gender; // male or female clothes 
    
    // default constructor
    public Apparel()
    {
        size = null;
        gender = null;
    }
    
    // normal constructor
    public Apparel(String id, int brand, int quantity, double price, int[] size, String gender, String staffID)
    {
        super(id, brand, quantity, price, staffID);
        for(int x = 0; x < S; x++)
        {
           this.size[x] = size[x];
        }
        this.gender = gender;
    }
    
    // set method/ mutator
    public void setSize(int[] size)
    {
        for(int x = 0; x < S; x++)
        {
           this.size[x] = size[x];
        }
    }
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    // get method/ accessor
    public int getSize(int s)
    {
        return size[s];
    }
    public String getGender()
    {
        return gender;
    }
    
    // method overriding
    public String getID(){ return super.getID();}
    public int getBrand(){return super.getBrand();}
    public int getQty(){return super.getQty();}
    public double getPrice(){return super.getPrice();}
    public String getStaffID(){return super.getStaffID();}
    
    // toString method
    public String toString()
    {
        return super.toString() + 
        "\nSize: [XS = " + size[0] + "\tS = " + size[1] + "\tM = " + size[2] + "\tL = " + size[3] + "\tXL = " + size[4] +"]" +
        "\nGender: " + gender; 
    }
    
    // to write in output file
    public String outputFile()
    {
        return super.getID() + ";" + super.getBrand() + ";" + super.getPrice() + ";" + size[0] + ";" + size[1] + ";" + size[2] + ";" +
        size[3] + ";" + size[4] + ";" + getGender() + ";" + super.getStaffID() + "\n";
    }
}
