

import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.SpringLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// File Management-related Imports 
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.util.Arrays;
import javax.swing.JOptionPane;

/**
 * @author Troy Vaughn
 */

public class WildlifeWatcher extends Frame implements WindowListener, ActionListener
{
    
    // DECLARATIONS --------------------------------------------------------------------------

    int maxEntries = 100;     // Global variable to define the maximum size of the 3 arrays.
    int numberOfEntries = 0;  // Global variable to remember how many actual entries are currently in the 3 arrays.
    int myCurrentEntry = 0;     // Global variable to remember which entry in the arrays we are currently focused on.
    
    // Declare the 3 arrays for storing the PC/IP data in memory - each has a maximum size of
	//         maxEntries (currently equal to 100 entries)
    WildlifeSightingData[] WLData = new WildlifeSightingData[maxEntries];
    String[] sortArray = new String[maxEntries];

    // ---------------------------------------------------------
    
    
    // Declaration of a global variable to store the data file name
    String dataFileName = "WildlifeSightings.txt";

    
    Label lblWildlife, lblLocation, lblDate,lblDescription,lblSighting, lblFind;
    TextField txtWildlife, txtLocation, txtDate,txtSighting, txtFind, txtSearchID;
    Button btnNew, btnSave, btnDel, btnFind, btnExit, btnFirst, btnPrev, btnNext, btnLast, btnSort, btnBinary, btnDisplay;

    TextArea txtDescription, texWildlifeList;
    
    
    public static void main(String[] args)
    {
        Frame myFrame = new WildlifeWatcher();
        myFrame.setSize(650,500);
        myFrame.setLocation(400, 200);
        myFrame.setResizable(false);
        myFrame.setVisible(true);
    }

    public WildlifeWatcher()
    {
        setTitle("Wildlife Watcher");
        setBackground(Color.green);

        SpringLayout myLayout = new SpringLayout();
        setLayout(myLayout);
        
	// Call the methods below to instantiate and place the various screen components
        LocateLabels(myLayout);
        LocateTextFields(myLayout);
        LocateButtons(myLayout);
        LocateTextAreas(myLayout);

        this.addWindowListener(this);
    }

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Labels to the screen.
    // Each line requests the LocateALabel method to instantiate, add and place a specific Label  
    public void LocateLabels(SpringLayout myLabelLayout)
    {
        lblWildlife = LocateALabel(myLabelLayout, lblWildlife, "Wildlife:", 30, 25);
        lblLocation = LocateALabel(myLabelLayout, lblLocation, "Location (REF):", 30, 50);
        lblDate = LocateALabel(myLabelLayout, lblDate, "Date:", 30, 75);
        lblDescription = LocateALabel(myLabelLayout, lblDescription, "Description:", 30, 100);
        lblSighting = LocateALabel(myLabelLayout, lblSighting, "Sighting By:", 30, 151);
        
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual labels:
    *    - reduces overall code, especially in the
    *         LocateLabels method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public Label LocateALabel(SpringLayout myLabelLayout, Label myLabel, String  LabelCaption, int x, int y)
    {
        myLabel = new Label(LabelCaption);
        add(myLabel);        
        myLabelLayout.putConstraint(SpringLayout.WEST, myLabel, x, SpringLayout.WEST, this);
        myLabelLayout.putConstraint(SpringLayout.NORTH, myLabel, y, SpringLayout.NORTH, this);
        return myLabel;
    }
   

    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple TextFields to the screen.
    // Each line requests the LocateATextField method to instantiate, add and place a specific TextField  
    public void LocateTextFields(SpringLayout myTextFieldLayout)
    {
        txtWildlife  = LocateATextField(myTextFieldLayout, txtWildlife, 30, 130, 25);
        txtLocation = LocateATextField(myTextFieldLayout, txtLocation, 30, 130, 50);
        txtDate = LocateATextField(myTextFieldLayout, txtDate, 30, 130, 75);
        txtSighting = LocateATextField(myTextFieldLayout, txtSighting, 15, 130,152);
        txtFind = LocateATextField(myTextFieldLayout, txtFind, 10, 525,25);
        txtSearchID = LocateATextField(myTextFieldLayout, txtSearchID, 15, 500,225);
    }
    
     public void LocateTextAreas(SpringLayout myTextAreaLayout)
    {
        txtDescription = LocateATextArea(myTextAreaLayout, txtDescription, 30, 2, 130,100, TextArea.SCROLLBARS_NONE);
        texWildlifeList = LocateATextArea(myTextAreaLayout, texWildlifeList,84,10,21,255,TextArea.SCROLLBARS_VERTICAL_ONLY);             
    }
    
    
    public TextField LocateATextField(SpringLayout myTextFieldLayout, TextField myTextField, int width, int x, int y)
    {
        myTextField = new TextField(width);
        add(myTextField);        
        myTextFieldLayout.putConstraint(SpringLayout.WEST, myTextField, x, SpringLayout.WEST, this);
        myTextFieldLayout.putConstraint(SpringLayout.NORTH, myTextField, y, SpringLayout.NORTH, this);
        return myTextField;
    }
    
     public TextArea LocateATextArea(SpringLayout myTextAreaLayout, TextArea myTextArea, int width,int rows, int x, int y, int bars)
    {
        myTextArea = new TextArea("",rows,width,bars);
        add(myTextArea);        
        myTextAreaLayout.putConstraint(SpringLayout.WEST, myTextArea, x, SpringLayout.WEST, this);
        myTextAreaLayout.putConstraint(SpringLayout.NORTH, myTextArea, y, SpringLayout.NORTH, this);
        return myTextArea;
    }


    //------------------------------------------------------------------------------------------
    // Method that manages the adding of multiple Buttons to the screen.
    // Each line requests the LocateAButton method to instantiate, add and place a specific Button  
    public void LocateButtons(SpringLayout myButtonLayout)
    {
        btnNew = LocateAButton(myButtonLayout, btnNew, "New", 475, 75, 150, 25);
        btnSave = LocateAButton(myButtonLayout, btnSave, "Save", 475, 100, 150, 25);
        btnDel = LocateAButton(myButtonLayout, btnDel, "Delete", 475, 125, 150, 25);
        btnFind = LocateAButton(myButtonLayout, btnFind, "Search", 475, 25, 50, 25);
        btnExit = LocateAButton(myButtonLayout, btnExit, "Exit", 477, 435, 150, 25);
        btnFirst = LocateAButton(myButtonLayout, btnFirst, "|<", 475, 175, 30, 25);
        btnPrev = LocateAButton(myButtonLayout, btnPrev, "<", 515, 175, 30, 25);
        btnNext = LocateAButton(myButtonLayout, btnNext, ">", 555, 175, 30, 25);
        btnLast = LocateAButton(myButtonLayout, btnLast, ">|", 595, 175, 30, 25);
        btnSort =  LocateAButton(myButtonLayout, btnSort, "Sort by Location & Type", 25, 225, 150, 25);
        btnBinary =  LocateAButton(myButtonLayout, btnBinary, "Binary Search by Location & type", 175, 225, 200, 25);
        btnDisplay   =LocateAButton(myButtonLayout, btnDisplay, "Display for Location", 375, 225, 125, 25);
    }

    /**
    * Method with low coupling and high cohesion 
    *    for adding individual buttons:
    *    - reduces overall code, especially in the
    *         LocateButtons method.
    *    - makes this method re-usable with minimal
    *         adjustment as it is moved from one
    *         program to another.
    */
    public Button LocateAButton(SpringLayout myButtonLayout, Button myButton, String  ButtonCaption, int x, int y, int w, int h)
    {    
        myButton = new Button(ButtonCaption);
        add(myButton);
        myButton.addActionListener(this);
        myButtonLayout.putConstraint(SpringLayout.WEST, myButton, x, SpringLayout.WEST, this);
        myButtonLayout.putConstraint(SpringLayout.NORTH, myButton, y, SpringLayout.NORTH, this);
        myButton.setPreferredSize(new Dimension(w,h));
        return myButton;
    }
    
     public void actionPerformed(ActionEvent e)
    {
        // BUTTON FIRST
        if(e.getSource() == btnFirst)
        {
            myCurrentEntry = 0;
            displayEntry(myCurrentEntry);
        }

        // BUTTON PREVIOUS
        if(e.getSource() == btnPrev)
        {
            if (myCurrentEntry > 0)
            {
                myCurrentEntry--;
                displayEntry(myCurrentEntry); 
            }
            
        }

        // BUTTON NEXT
        if (e.getSource()== btnNext)
        {
            if (myCurrentEntry < numberOfEntries -1)
            {
                myCurrentEntry++;
            displayEntry(myCurrentEntry); 
            }
            
        }

        // BUTTON LAST
        if(e.getSource() == btnLast)
        {
            myCurrentEntry = numberOfEntries -1;
            displayEntry(myCurrentEntry);
        }

        // BUTTON NEW
        if(e.getSource() == btnNew)
        {
             // Only if the array is large enough to store another record...
            if (numberOfEntries < maxEntries - 1)
            {
                // Increment the numberOfEntries
                numberOfEntries++;
                // Set the current entry to the new record
                myCurrentEntry = numberOfEntries - 1;
                // Blank out any existing data in the 3 arrays, ready
                //       for adding the new record.
                WLData[myCurrentEntry] = new WildlifeSightingData("","","","","");
                
                // Display this new blank entry on screen
                displayEntry(myCurrentEntry);
                btnSave.setEnabled(true);
                btnNew.setEnabled(false);
                btnDel.setEnabled(false);
            }
        }

        // BUTTON SAVE
        if(e.getSource() == btnSave)
        {
            if(txtWildlife.getText().isEmpty() || txtLocation.getText().isEmpty() || txtDate.getText().isEmpty()
                    || txtDescription.getText().isEmpty() || txtSighting.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(null, "Please complete all fields before saving!");
            }
            else
            {
             saveEntry(myCurrentEntry);
             btnNew.setEnabled(true);
             btnDel.setEnabled(true);
            }
        }

        // BUTTON DELETE
        if(e.getSource()== btnDel)
        {
              // Move all the later entries up one line each in the arrays, covering over
            //      the current entry in the process
            for (int i = myCurrentEntry; i < numberOfEntries - 1; i++)
            {
                if(WLData[i + 1] != null)
                {
                WLData[i] = WLData[i + 1];
                }
                else
                {
                    WLData[i] = null;
                }
            }
            // Reduce the current total number of entries stored in the array by one.
			// Then check if the current entry is now further down the array than
            //      the last entry.  If so, reduce the value of myCurrentEntry by 1.
            numberOfEntries--;
            if (myCurrentEntry > numberOfEntries - 1)
            {
                myCurrentEntry = numberOfEntries - 1;
            }
            // Display the myCurrentEntry
            displayEntry(myCurrentEntry);
        }

        // BUTTON FIND
        if(e.getSource() == btnFind)
        {   
              // Declare a boolean valuable: found (to remember whether
            //         the required entry has been found yet.)
            boolean found = false;
			// Declare a counter (i)
            int i = 0;
            // While there are more entries to check and the 'search' entry has not been found... 
            while (i < numberOfEntries && found == false)
            {
                // If the current PCName is equal to the 'search' entry...
                if (WLData[i].getWildlife().equalsIgnoreCase( txtFind.getText()))
                {
                    // Set found = true
                    found = true;
                }
                // Increment the counter (i) so the loop will move onto the next record
                i++;
            }
            // If the entry was found, then set the value of myCurrentEntry and then display the entry.
            if (found) 
            {
                myCurrentEntry = i - 1;
                displayEntry(myCurrentEntry);
            }
        }
        
        // BUTTON EXIT
        if(e.getSource() == btnExit)
        {
            // Exit the Program
            writeFile();
            System.exit(0);
        }
      
         // BUTTON SORT -------------------------------------------
        if(e.getSource() == btnSort || e.getSource() == btnBinary)
        {
            // You are able to sort an array of objects using a comparator.
            // This has been left as an exercise: research and implement.
            // This code will copy the PC_Names to a new sortArray and 
            //      will then sort and display the sorted list.
            // Note that this sort is also applicable to a binary search
            
            // Copy the PC Names to the sortArray 
            for(int i = 0; i < numberOfEntries; i++)
            {
                sortArray[i] = WLData[i].getWildlife().toUpperCase() + "-" + WLData[i].getLocation().toUpperCase();
            }
            // Sort the numberOfEntries in the sortArray
            Arrays.sort(sortArray, 0, numberOfEntries);
            // Display the sorted list in the textArea
            // Note:  \n - go to a new line in the TextArea
            texWildlifeList.setText("Sorted Wildlife:\n");
            texWildlifeList.append("--------------------------\n");
            for(int i = 0; i < numberOfEntries; i++)
            {
                texWildlifeList.append(sortArray[i] + "\n");
            }
        }
          // BUTTON BINARY SEARCH -----------------------------------
        if(e.getSource() == btnBinary)
        {
            // Complete a binary search for the a PC_Name typed into the
            //    txtFind TextField.  Note that a Binary search works 
            //    best on a sorted list.  Therefore...
            // Note that the code above that sorts the list of PC_Names
            //    will run before the binary search code below.
            // Search through the numberOfEntries in the sortArray
            // Note: the result will be >= 0 if the search string is found
            int result = Arrays.binarySearch(sortArray, 0, numberOfEntries, txtSearchID.getText());
            // Note:  \n - go to a new line in the TextArea
            texWildlifeList.append("\nBinary Search result = " + result);
        }
        // BUTTON DISPLAY LIST -------------------------------------
        if(e.getSource() == btnDisplay)
        {
            // Use .setText to clear the TextArea and add the field headings
            texWildlifeList.setText("Wildlife Sighted at Requested Location\n");
            texWildlifeList.append("--------------------\n");
            // Loop through the various records and add each one to a new line within the TextArea
            for(int i = 0; i < numberOfEntries; i++)
            {
                if (txtSearchID.getText().equalsIgnoreCase(WLData[i].getLocation()))
                {
                    texWildlifeList.append(WLData[i].getDate()+ " - " + WLData[i].getWildlife().toUpperCase() + " - " + WLData[i].getDescription().toUpperCase() + "\n");
                }
            }
        }
           
    }
  

    // Manage responses to the various Window events
    public void windowClosing(WindowEvent we)
    {
        writeFile();
        System.exit(0);
    }
    
    
    public void windowIconified(WindowEvent we)
    {
    }

    public void windowOpened(WindowEvent we)
    {
        // Call the method below that reads the data from the data file (when the Frame first opens)
        readFile();
	// Display the first data entry (record) in the Frame
        displayEntry(myCurrentEntry);
    }

    public void windowClosed(WindowEvent we)
    {
        writeFile();
        System.exit(0);
    }

    public void windowDeiconified(WindowEvent we)
    {
    }

    public void windowActivated(WindowEvent we)
    {
    }

    public void windowDeactivated(WindowEvent we)
    {
    }
    
      // NEW METHODS: --------------------------------------------------------------------------------

    // Display the required data entry (record) in the Frame
    // The calling method must specify the number (index) of the entry that this
    //     method needs to currently display on screen.
    public void displayEntry(int index)
    {
        // Take the required entry from the Wildlife array and display it
        //      in the txtWildlife TextField.
        txtWildlife.setText(WLData[index].getWildlife());
        txtLocation.setText(WLData[index].getLocation());
        txtDate.setText(WLData[index].getDate());
        txtDescription.setText(WLData[index].getDescription());
        txtSighting.setText(WLData[index].getSightingBy());
    }

    // Take the current record displayed on screen and save it back into the 'myCurrentEntry' position
    //      of the 3 arrays.
    public void saveEntry(int index)
    {
        // Take the current entry in the txtWildlife TextField (on screen) and copy it 
        //      into the appropriate (myCurrentEntry) position of the Wildlife array.
        WLData[index].setWildlifeDataInfo(txtWildlife.getText(),txtLocation.getText(), txtDate.getText(), 
                                            txtDescription.getText(), txtSighting.getText());
        	
        // (If required) Call the method below that writes the data back to the data file.
        writeFile();
    }
   
    // Read in the data from the data file - Date.txt - one line at a time and store in the 3 arrays.
    // Remember the number of entries read in, in the global variable: numberOfEntries.
    public void readFile()
    {
        // Try to read in the data and if an exception occurs go to the Catch section 
        try
        {
            // Set up vaious streams for reading in the content of the data file.
            FileInputStream fstream = new FileInputStream(dataFileName);
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            
            int i = 0;   // i is used as the line counter
            String line; // line is used to temporarily store the line read in from the data file
			
            // Read a line from the data file into the buffer and then check whether
            //      it is null.  The while loop continues until a line read in is null.
            while ((line = br.readLine()) != null)
            {
                // Split the line of data (from the text file) and put each entry into the
                //                                             temporary array - temp[]
                String[] temp = line.split(",,");

                // Save each entry into its respective array:
                  if (temp.length !=0)
                {
                  WLData[i] = new WildlifeSightingData(temp[0],temp[1],temp[2],temp[3],temp[4]); 
                  i++;  // Increment i so we can keep a count of how many entries have been read in.
                }  
            }

            numberOfEntries = i;   // Set numberOfEntries equal to i, so as to remember how many entries are now in the arrays 

            br.close();            // Close the BufferedReader
            in.close();            // Close the DataInputStream
            fstream.close();       // Close the FileInputStream
        }
        catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.
            System.err.println("Error Reading File: " + e.getMessage());
        }
    }

    // Write the data back out to the data file - one line at a time
    // Note: You may wish to use a different data file name while initially
    //       developing, so as not to accidently corrupt your input file.
    public void writeFile()
    {
        // Try to print out the data and if an exception occurs go to the Catch section 
        try
        {
            // After testing has been completed, replace the hard-coded filename: "Date_New.txt"
            //       with the parameter variable: fileName 
            // Set up a PrintWriter for printing the array content out to the data file.
            PrintWriter out = new PrintWriter(new FileWriter(dataFileName));
            
            // Print out each line of the array into your data file.
            // Each line is printed out in the format:  Wildlife,Location,IPAddress
            for(int m = 0; m < numberOfEntries; m++)
            {
                out.println(WLData[m].getWildlife() +",," + WLData[m].getLocation() + ",," + WLData[m].getDate()
                                +",," + WLData[m].getDescription() + ",," + WLData[m].getSightingBy()); 
            }

            // Close the printFile (and in so doing, empty the print buffer)
             out.close();
        }
        catch (Exception e)
        {
            // If an exception occurs, print an error message on the console.
            System.err.println("Error Writing File: " + e.getMessage());
        }
    }
}
