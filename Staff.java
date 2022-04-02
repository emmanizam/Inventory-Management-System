import java.io.*;
import java.util.Vector;
import java.util.Scanner;

public class Staff
{
    // instance variables - replace the example below with your own
    private String staffID;
    private String password;
    private String staffName;
    
    // default constructor
    public Staff()
    {
        staffID = null;
        password = null;
        staffName = null;
    }
    
    // normal constructor
    public Staff(String staffID, String password, String staffName)
    {
        this.staffID = staffID;
        this.password = password;
        this.staffName = staffName;
    }
     
    // set method/mutator
    public void setPassword(String password)
    {
        this.password = password;
    }
    
    // get method/accessor
    public String getStaffID()
    {
        return staffID;
    }
    public String getPassword()
    {
        return password;
    }
    public String getStaffName(String staffID)
    {
        return staffName;
    }
    
    //

     
}
