import java.io.*;
import java.util.Vector;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.StringTokenizer;
import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;   

public class TestStore
{ 
    
    public static void main(String args[]) throws IOException
    {
        DecimalFormat df = new DecimalFormat("0.00");
        Scanner scan = new Scanner(System.in);
        scan.useDelimiter("\n");
        
        // variables declaration 
        // **Item details variables
        final int S = 5;
        int[] size = new int[S];
        String id, gender, category, ageCategory;
        String staffID, password, staffName;
        String loginID, loginPW;
        int brand, quantity, genderInput, categoryInput, ageCategoryInput;
        double price;

        // **General variables
        int option, type;
        boolean menuLoop = true;
        //double totalAmount = 0.0;
        boolean loginStatus = false;
        
        // Array declaration 
        final String[] brandName = { "Adidas","Nike", "Puma", "Reebok", "Under Armour"};
        final String[] sizeName = {"XS","S","M","L","XL"};
        
        // create object
        Store store = new Store();
        Apparel apparel = new Apparel();
        Equipment equipment = new Equipment();
        Staff staff = new Staff();
        
        // create vector of object
        Vector <Store> storeList = new Vector <Store>();
        Vector <Apparel> apparelList = new Vector <Apparel>();
        Vector <Equipment> equipmentList = new Vector <Equipment>();
        Vector <Staff> staffList = new Vector <Staff>();
        
        // login loop starts here
        do
        {
            // declare read file of staff information
            String inData = null;
            FileReader staffInput= new FileReader("staff.txt"); // open file to read
            BufferedReader readStaff = new BufferedReader(staffInput); // to read file
            
            try // handle run time error
            {
                while((inData = readStaff.readLine()) != null) // read from file staff
                {
                    StringTokenizer input = new StringTokenizer(inData,";");
                    
                    staffID = input.nextToken();
                    password = input.nextToken();
                    staffName= input.nextToken();
                
                    staff = new Staff(staffID, password, staffName);
                    staffList.add(staff);
                }
            }
            catch(FileNotFoundException fnfe) // display error message if file not found
            {
                System.out.print("fnfe.getMessage()");
            }

            // insert login information
            System.out.print("\n\t\tUSER LOGIN\n\t\t*************\n ");
            System.out.print("\t\tStaff ID: ");
            loginID = scan.next();
            System.out.print("\t\tPassword: ");
            loginPW = scan.next();
            
            // verify login information
            for(int i = 0; i < staffList.size(); i++)
            {
                if((loginID.equals(staffList.elementAt(i).getStaffID()) == false)||(loginPW.equals(staffList.elementAt(i).getPassword()) == false))
                {
                    // login fail
                    continue;
                }
                else
                {
                    // login successful
                    loginStatus = true;
                    break;
                }
            }
            
            if(loginStatus == false) // enter only if login unsuccessful
            {
                System.out.print("\n\t\tLogin unsuccessful!\n");
            }
            else if(loginStatus == true) // enter only if login successful
            {
                System.out.print("\n\t\tLogin successful!\n*******************************************************************************************\n\n");
                
                inData = null; // set inData to null
                
                // open files to read
                FileReader apparelInput= new FileReader("apparel.txt"); // apparel
                FileReader equipmentInput= new FileReader("equipment.txt"); // equipment
                
                // to read files
                BufferedReader readApparel = new BufferedReader(apparelInput);  // apparel
                BufferedReader readEquipment = new BufferedReader(equipmentInput);  // equipment
                
               // read from file apparel
                try 
                {
                    while((inData = readApparel.readLine()) != null) 
                    {
                        quantity = 0;
                        StringTokenizer input = new StringTokenizer(inData,";");

                        id = input.nextToken();
                        brand = Integer.parseInt(input.nextToken());
                        price = Double.parseDouble(input.nextToken());
                        for(int j = 0; j < 5; j++)// read qty for each size
                        {
                            size[j] = Integer.parseInt(input.nextToken());
                            quantity += size[j];
                        }
                        gender = input.nextToken();
                        staffID = input.nextToken();
                        
                        // create object
                        apparel = new Apparel(id, brand, quantity, price, size,gender, staffID); 
                        store = new Store(id, brand, quantity, price,staffID);
                        
                        // store object in vector
                        apparelList.add(apparel); 
                        storeList.add(store);
                    }
                }
                catch(FileNotFoundException fnfe) // display error message if file not found
                {
                    System.out.print("fnfe.getMessage()");
                }
                
                inData = null;
                // read from file equipment
                try 
                {
                    while((inData = readEquipment.readLine()) != null) 
                    {
                        StringTokenizer input = new StringTokenizer(inData,";");
                        
                        id = input.nextToken();
                        brand = Integer.parseInt(input.nextToken());
                        price = Double.parseDouble(input.nextToken());
                        quantity = Integer.parseInt(input.nextToken()); 
                        category = input.nextToken();
                        ageCategory = input.nextToken();
                        staffID = input.nextToken();
                        
                        // create object
                        equipment = new Equipment(id, brand, quantity, price, category, ageCategory, staffID); 
                        store = new Store(id, brand, quantity, price,staffID);
                        
                        // store object in vector
                        equipmentList.add(equipment); 
                        storeList.add(store);
                    }
                }
                catch(FileNotFoundException fnfe) // display error message if file not found
                {
                    System.out.print("fnfe.getMessage()");
                }
                
                // menu loop starts here
                do
                {
                    System.out.print("\n\t\tINVENTORY MANAGEMENT SYSTEM\n\t\t***************************\n");
                    System.out.print("\n\t\t[1] Search item\n\t\t[2] Display list \n\t\t[3] Add list \n\t\t[4] Update list \n\t\t[5] Delete list\n\t\t[6] Display inventory report\n\t\t[7] Exit");
                    System.out.print("\n\t\tEnter an option: ");
                    option = scan.nextInt();
                    
                    if(option == 1)
                    {
                        String search = null;
                        boolean findStatus = false;
                        
                        System.out.print("\nSEARCH ITEM\n-----------\n");
                        System.out.print("\nEnter item ID: ");
                        search = scan.next();
                        if((apparelList.size() == 0)&&(equipmentList.size() == 0))
                            System.out.print("\n--NO DATA AVAILABLE--\n");
                        else
                        {
                            for(int i = 0; i < apparelList.size(); i++)
                            {
                                if(search.equals(apparelList.elementAt(i).getID()) == true)
                                { 
                                    findStatus = true;
                                    System.out.print("Item ID found in apparel!\n");
                                    System.out.print("\nITEM INFORMATION");
                                    for(int j = 0; j < staffList.size(); j++)
                                    {
                                        if(apparelList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                        {
                                            System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(apparelList.elementAt(i).getStaffID()));
                                        }   
                                    }
                                    System.out.print(apparelList.elementAt(i).toString() +"\n");
                                    break;
                                }
                                else{continue;}
                            }
                            
                            if(findStatus != true)
                            {
                                for(int i = 0; i < equipmentList.size(); i++)
                                {
                                    if(search.equals(equipmentList.elementAt(i).getID()) == true)
                                    {
                                        findStatus = true;
                                        System.out.print("Item ID found in equipment!\n");
                                        System.out.print("\nITEM INFORMATION");
                                        for(int j = 0; j < staffList.size(); j++)
                                        {
                                            if(equipmentList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                            {
                                                System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(equipmentList.elementAt(i).getStaffID()));
                                            }   
                                        }
                                        System.out.print(equipmentList.elementAt(i).toString() +"\n");
                                        break;
                                    }
                                    else{continue;}
                                }
                            }
                            
                            if(findStatus != true)
                            {
                                System.out.print("Item ID is not found in the list!\n");
                            }
                        }
                    }
                    else if(option == 2)  // display list statements start here
                    {
                        System.out.print("\nDISPLAY LIST\n-----------\n");
                        if((apparelList.size() == 0)&&(equipmentList.size() == 0))
                            System.out.print("\n--NO DATA AVAILABLE--\n");
                        else
                        {
                            if(apparelList.size() != 0) // display apparel list
                            {
                                for(int i = 0; i < apparelList.size(); i++)
                                {
                                    System.out.print("\n Apparel "+ (i+1) + "\n***************");
                                    for(int j = 0; j < staffList.size(); j++)
                                    {
                                        if(apparelList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                        {
                                            System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(apparelList.elementAt(i).getStaffID()));
                                        }   
                                    }
                                    System.out.print(apparelList.elementAt(i).toString());
                                    System.out.print("\n");
                                }
                            }
                            if(equipmentList.size() != 0) // display equipment list
                            {
                                for(int i = 0; i < equipmentList.size(); i++)
                                {
                                    System.out.print("\n Equipment "+ (i+1) + "\n***************");
                                    for(int j = 0; j < staffList.size(); j++)
                                    {
                                        if(equipmentList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                        {
                                            System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(equipmentList.elementAt(i).getStaffID()));
                                        }   
                                    }
                                    System.out.print(equipmentList.elementAt(i).toString());
                                    System.out.print("\n");
                                }
                            }
                        }
                    }
                    else if(option == 3) // add list statemements start here
                    {
                        boolean addlistLoop = true;
                        boolean typeLoop = true;
                        int loop = 0;
                        int list = 0;
                        
                        System.out.print("\nADD ITEM\n-----------\n");
                        
                        do
                        {
                            boolean duplicateID = true;
                            System.out.print("\nItem "+ (list+1));
                            
                            if(storeList.size() == 0) // verify unique ID
                            {
                                System.out.print("\nItem ID: ");
                                id = scan.next();
                                System.out.print("\n");
                            }
                            else
                            {
                                do // check uniqueness of item ID
                                {
                                    System.out.print("\nItem ID: ");
                                    id = scan.next();
                                    System.out.print("\n");
                                    for(int i = 0; i < storeList.size(); i++)
                                    {
                                       if(id.equals(storeList.elementAt(i).getID()) == true)
                                       {
                                            System.out.print("Item ID is already in the system. \nDuplicate item ID is not allowed!");
                                            break;
                                       }
                                       else
                                       {
                                           duplicateID = false;
                                           break;
                                       }    
                                    }
                                   
                                        
                                }while(duplicateID != false);
                            }
                            
                            // display brand 
                            for(int i = 0; i < brandName.length; i++)
                            {
                                 System.out.print("["+ (i+1) + "]" + brandName[i] + " ");
                            }
                            System.out.print("\nBrand: ");
                            brand = scan.nextInt();
                            System.out.print("Price (per one item): ");
                            price = scan.nextDouble();
                            System.out.print("[1] Apparel\t[2] Equipment ");
                            System.out.print("\nType: ");
                            type = scan.nextInt();
                            do
                            {
                                if(type == 1) // Apparel
                                {
                                    quantity = 0;
                                    System.out.print("\nQuantity based on sizes\n");
                                    for(int i = 0; i < S; i++)
                                    {
                                        System.out.print(sizeName[i] + ": ");
                                        size[i] = scan.nextInt();
                                        quantity += size[i];
                                    }
                                    
                                    System.out.print("\n[1]Male [2]Female [3]Unisex \nGender: ");
                                    genderInput = scan.nextInt();
                                     if(genderInput == 1)
                                    {
                                        gender = "Male";
                                    }
                                    else if(genderInput == 2)
                                    {
                                        gender = "Female";
                                    }
                                    else
                                    {
                                        gender = "Unisex";
                                    }
                                    apparel = new Apparel(id, brand, quantity, price, size,gender, loginID);
                                    apparelList.add(apparel);
                                    typeLoop = false; 
                                }
                                else if(type == 2) // Equipment
                                {
                                    System.out.print("Quantity: ");
                                    quantity = scan.nextInt();
                                    System.out.print("\n[1]Running [2]Swimming [3]Football [4]Gym [5]Other  \nCategory: ");
                                    categoryInput = scan.nextInt();
                                    if(categoryInput == 1)
                                    {
                                        category = "Running";
                                    }
                                    else if(categoryInput == 2)
                                    {
                                        category = "Swimming";
                                    }
                                    else if(categoryInput == 3)
                                    {
                                        category = "Football";
                                    }
                                    else if(categoryInput == 4)
                                    {
                                        category = "Gym";
                                    }
                                    else
                                    {
                                        category = "Other";
                                    }
                                    
                                    System.out.print("\n[1]Children [2]Adult [3]None \nAge Category: ");
                                    ageCategoryInput = scan.nextInt(); 
                                    if(ageCategoryInput == 1)
                                    {
                                        ageCategory = "Children";
                                    }
                                    else if (ageCategoryInput == 2)
                                    {
                                        ageCategory = "Adult";
                                    }
                                    else
                                    {
                                        ageCategory = "None";
                                    }
                                    equipment = new Equipment(id, brand, quantity, price, category, ageCategory, loginID);
                                    equipmentList.add(equipment);
                                    typeLoop = false;
                                }
                                else
                                {
                                    System.out.print("\nType chosen is unavailable! \nPlease choose between 1 or 2 only: ");
                                    type = scan.nextInt();
                                }
                            }while(typeLoop != false);
                            
                            System.out.print("\nDo you want to add more data? \n\t[1] Yes \t[2] No \nEnter option: ");
                            loop = scan.nextInt();
                            if(loop == 1)
                            {
                                addlistLoop = true;
                                list++;
                            }
                            else
                                addlistLoop = false;
                            
                        }while(addlistLoop != false);
                        
                    }
                    else if(option == 4) // update list statemements start here
                    {
                        String searchID = null;
                        boolean searchStatus = false;
                        System.out.print("\nUPDATE LIST\n-----------");
                        System.out.print("\nNote: Only price and quantity is allowed to be updated. ");
                        System.out.print("\nEnter an item ID: ");
                        searchID = scan.next();

                        if((apparelList.size() == 0)&&(equipmentList.size() == 0))
                            System.out.print("\n--NO DATA AVAILABLE--\n");
                        else
                        {
                            Apparel newApparel;
                            Equipment newEquipment; 
                            quantity = 0;
                            price = 0.0;
                            
                            // update statements
                            for(int i = 0; i < apparelList.size(); i++)
                            {
                                if(searchID.equals(apparelList.elementAt(i).getID()) == true)
                                { 
                                    searchStatus = true;
                                    System.out.print("Item ID found in apparel!\n");
                                    System.out.print("\nCURRENT INFORMATION\n");
                                    for(int j = 0; j < staffList.size(); j++)
                                    {
                                        if(apparelList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                        {
                                            System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(apparelList.elementAt(i).getStaffID()));
                                        }   
                                    }
                                    System.out.print(apparelList.elementAt(i).toString() +"\n");
                                    
                                    // user insert new data
                                    System.out.print("\nUPDATE INFORMATION");
                                    System.out.print("\nPrice: RM");
                                    price = scan.nextDouble();
                                    System.out.print("\nQuantity based on sizes\n");
                                    for(int j = 0; j < S; j++)
                                    {
                                        System.out.print(sizeName[j] + ": ");
                                        size[j] = scan.nextInt();
                                        quantity += size[j];
                                    }
                                    
                                    //update new data in the object
                                    apparelList.elementAt(i).setSize(size);
                                    storeList.elementAt(i).setQty(quantity);
                                    storeList.elementAt(i).setPrice(price);
                                    
                                    //update new data in the vector list
                                    newApparel = new Apparel(apparelList.elementAt(i).getID(), apparelList.elementAt(i).getBrand(), quantity, price, size,apparelList.elementAt(i).getGender(), loginID); 
                                    apparelList.set(i, newApparel);
                                    //storeList.set(i, store);
                                    
                                    System.out.print("\nNEW UPDATED INFORMATION\n");
                                    System.out.print(apparelList.elementAt(i).toString() +"\n");
                                    break; 
                                }
                                else{ continue; }
                            }
                            
                            if(searchStatus == false)
                            {
                                for(int i = 0; i < equipmentList.size(); i++)
                                {
                                    if(searchID.equals(equipmentList.elementAt(i).getID()) == true)
                                    {
                                        searchStatus = true;
                                        System.out.print("Item ID found in equipment!\n");
                                        System.out.print("\nCURRENT INFORMATION\n");
                                        for(int j = 0; j < staffList.size(); j++)
                                        {
                                            if(equipmentList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                            {
                                                System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(equipmentList.elementAt(i).getStaffID()));
                                            }   
                                        }
                                        System.out.print(equipmentList.elementAt(i).toString() +"\n");
                                        
                                        // user insert new data
                                        System.out.print("\nUPDATE INFORMATION");
                                        System.out.print("\nPrice: RM");
                                        price = scan.nextDouble();
                                        System.out.print("\nQuantity: ");
                                        quantity = scan.nextInt();
                                        
                                        //update new data in the list
                                        storeList.elementAt(i).setQty(quantity);
                                        storeList.elementAt(i).setPrice(price);
                                        
                                        //update new data in the vector list
                                        newEquipment = new Equipment(equipmentList.elementAt(i).getID(), equipmentList.elementAt(i).getBrand(), quantity, price, equipmentList.elementAt(i).getCategory(),equipmentList.elementAt(i).getAgeCat(), loginID); 
                                        equipmentList.set(i, newEquipment);
                                        
                                        System.out.print("\nNEW UPDATED INFORMATION\n");
                                        System.out.print(equipmentList.elementAt(i).toString() +"\n");
                                        break; 
                                    }
                                    else{ continue; }
                                }
                            }
                            
                            if(searchStatus == false)
                            {
                                System.out.print("\nItem ID is not found!\n");
                            }
                        }
                    }
                    else if(option == 5) // delete list statemements start here
                    {
                        String searchID = null;
                        boolean searchStatus = false;
                        int confirmDelete = 0;
                        System.out.print("\nDELETE ITEM\n-----------");
                        System.out.print("\nNote: Deleted item cannot be undone. ");
                        System.out.print("\nEnter an item ID: ");
                        searchID = scan.next();
                        
                        // Delete list statmenet
                        for(int i = 0; i < apparelList.size(); i++)
                        {
                            if(searchID.equals(apparelList.elementAt(i).getID()) == true)
                            { 
                                searchStatus = true;
                                System.out.print("Item ID found in equipment!\n");
                                System.out.print("\nCURRENT INFORMATION\n");
                                for(int j = 0; j < staffList.size(); j++)
                                {
                                    if(apparelList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                    {
                                        System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(apparelList.elementAt(i).getStaffID()));
                                    }   
                                }
                                System.out.print(apparelList.elementAt(i).toString() +"\n");
                                
                                // confirm deletion process
                                System.out.print("\nAre you sure you want to delete this item? [1]Yes [2]No");
                                System.out.print("\nEnter choice: ");
                                confirmDelete = scan.nextInt();
                                
                                if(confirmDelete == 1) 
                                {
                                    apparelList.removeElementAt(i);
                                    System.out.print("\nItem is successfully deleted!. Thank you.");
                                }
                                else
                                {
                                    System.out.print("\nDeletion process is cancelled! Thank you.");
                                }
                                break; 
                            }
                            else{ continue; }
                        }
                        
                        for(int i = 0; i < equipmentList.size(); i++)
                        {
                            if(searchID.equals(equipmentList.elementAt(i).getID()) == true)
                            { 
                                searchStatus = true;
                                System.out.print("Item ID found in equipment!\n");
                                System.out.print("\nCURRENT INFORMATION\n");
                                for(int j = 0; j < staffList.size(); j++)
                                {
                                    if(equipmentList.elementAt(i).getStaffID().equals(staffList.elementAt(j).getStaffID()))
                                    {
                                        System.out.print("\nLast updated by: "+ staffList.elementAt(j).getStaffName(equipmentList.elementAt(i).getStaffID()));
                                    }   
                                }
                                System.out.print(equipmentList.elementAt(i).toString() +"\n");
                                
                                // confirm deletion process
                                System.out.print("\nAre you sure you want to delete this item? [1]Yes [2]No");
                                System.out.print("\nEnter choice: ");
                                confirmDelete = scan.nextInt();
                                
                                if(confirmDelete == 1) 
                                {
                                    equipmentList.removeElementAt(i);
                                    System.out.print("\nItem is successfully deleted!. Thank you.\n\n");
                                }
                                else
                                {
                                    System.out.print("\nDeletion process is cancelled! Thank you.\n\n");
                                }
                                break; 
                            }
                            else{ continue; }
                        }
                    }
                    else if(option == 6) // display inventory report statemements start here
                    {
                        int confirmPrint = 0;
                        
                        // Display inventory report
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
                        LocalDateTime now = LocalDateTime.now();  
                          
                        System.out.print("\n***************************\n   INVENTORY REPORT\n***************************\n");
                        System.out.print("\n" + dtf.format(now));
                        for(int j = 0; j < staffList.size(); j++)
                        {
                            if(loginID.equals(staffList.elementAt(j).getStaffID()))
                            {
                                System.out.print("\nReport by " + staffList.elementAt(j).getStaffName(loginID));
                                break;
                            }   
                        }
                        
                        int apparelHighest = 0, apparelLowest = 0, equipmentHighest = 0, equipmentLowest = 0;
                        
                        System.out.print("\n\nAPPAREL SUMMARY");
                        System.out.print("\nTotal Item in Apparel: " + apparelList.size());
                        for(int j = 1; j < apparelList.size(); j++)
                        {
                            if(apparelList.elementAt(apparelHighest).getQty() < apparelList.elementAt(j).getQty())
                            {
                                apparelHighest = j;
                            }
                            
                            if(apparelList.elementAt(apparelLowest).getQty() > apparelList.elementAt(j).getQty())
                            {
                                apparelLowest = j;
                            }
                        }
                        System.out.print("\nHighest Quantity Item:\n" );
                        System.out.print(apparelList.elementAt(apparelHighest).toString() +"\n");
                        System.out.print("\nLowest Quantity Item:\n" );
                        System.out.print(apparelList.elementAt(apparelLowest).toString() +"\n");

                        System.out.print("\nEQUIPMENT SUMMARY");
                        System.out.print("\nTotal Item in Equipment: " + equipmentList.size());
                        for(int j = 1; j < equipmentList.size(); j++)
                        {
                            if(equipmentList.elementAt(equipmentHighest).getQty() < equipmentList.elementAt(j).getQty())
                            {
                                equipmentHighest = j;
                            }
                            
                            if(equipmentList.elementAt(equipmentLowest).getQty() > equipmentList.elementAt(j).getQty())
                            {
                                equipmentLowest = j;
                            }
                        }
                        System.out.print("\nHighest Quantity Item:\n" );
                        System.out.print(equipmentList.elementAt(equipmentHighest).toString() +"\n");
                        System.out.print("\nLowest Quantity Item:\n" );
                        System.out.print(equipmentList.elementAt(equipmentLowest).toString() +"\n");
                        
                        System.out.print("\n\t\t\t------End------\n\n");
                        
                        // print report
                        System.out.print("\n\nDo you want to print report? [1]Yes [2]No");
                        System.out.print("\nEnter choice: ");
                        confirmPrint = scan.nextInt();
                        
                        if(confirmPrint == 1) 
                        {
                           // print report operations
                           PrintWriter reportOutput = new PrintWriter(new BufferedWriter(new FileWriter("inventoryReport.txt")));
                           reportOutput.print("\n***************************\n   INVENTORY REPORT\n***************************\n");
                           reportOutput.print("\n" + dtf.format(now));
                           for(int j = 0; j < staffList.size(); j++)
                            {
                                if(loginID.equals(staffList.elementAt(j).getStaffID()))
                                {
                                    reportOutput.print("\nReport by " + staffList.elementAt(j).getStaffName(loginID));
                                    break;
                                }   
                            }
                           reportOutput.print("\n\nAPPAREL SUMMARY");
                           reportOutput.print("\nTotal Item in Apparel: " + apparelList.size());
                           //reportOutput.print
                           reportOutput.print("\n\nAPPAREL SUMMARY");
                           reportOutput.print("\nTotal Item in Apparel: " + apparelList.size());
                           reportOutput.print("\nHighest Quantity Item:\n" );
                           reportOutput.print(apparelList.elementAt(apparelHighest).toString() +"\n");
                           reportOutput.print("\nLowest Quantity Item:\n" );
                           reportOutput.print(apparelList.elementAt(apparelLowest).toString() +"\n");
                           reportOutput.print("\nEQUIPMENT SUMMARY");
                           reportOutput.print("\nTotal Item in Equipment: " + equipmentList.size());
                           reportOutput.print("\nHighest Quantity Item:\n" );
                           reportOutput.print(equipmentList.elementAt(equipmentHighest).toString() +"\n");
                           reportOutput.print("\nLowest Quantity Item:\n" );
                           reportOutput.print(equipmentList.elementAt(equipmentLowest).toString() +"\n");
                           reportOutput.print("\n\t\t\t------End------\n\n");
                           reportOutput.close();
                           System.out.print("\n\nReport Successfully Printed!\n\n");
                        }
                        else{continue;}
                        
                    }
                    else if(option == 7)
                    {
                        menuLoop = false;
                    }
                    else
                    {
                        System.out.print("\n\t\tOption is not available.");
                        System.out.print("\n\t\tPlease re-enter an option: ");
                        option = scan.nextInt();
                    }
                    apparelInput.close(); // close file
                    equipmentInput.close(); // close file
                }while(menuLoop != false); 
                
                // open files to write
                PrintWriter apparelOutput = new PrintWriter(new BufferedWriter(new FileWriter("apparel.txt")));
                PrintWriter equipmentOutput = new PrintWriter(new BufferedWriter(new FileWriter("equipment.txt")));
                
                // update file apparel with current vector
                for(int i = 0; i < apparelList.size(); i++)
                {
                    apparelOutput.print(apparelList.elementAt(i).outputFile());
                }
                
                // update file equipment with current vector
                for(int i = 0; i < equipmentList.size(); i++)
                {
                    equipmentOutput.print(equipmentList.elementAt(i).outputFile());
                }
                
                apparelOutput.close();
                equipmentOutput.close();
            } 
             staffInput.close(); // close file
        }while(loginStatus != true);
    }
}
