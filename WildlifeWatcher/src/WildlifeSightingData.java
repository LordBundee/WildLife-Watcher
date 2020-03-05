
/**  --------------------------------------------------------
 * Class: WildlifeSightingData
 * 
 * @author Troy Vaughn
 * 
 * Developed: 2019
 * 
 * Purpose: To record the - Wildlife/Location/Date/Description/SightingBy for a SINGLE wildlife sighting.
 * 
 * Demonstrating the implementation of:
 * - constructor overloading
 * - Getters and Setters
 * 
 * ----------------------------------------------------------
 */

class WildlifeSightingData
{
    // Declarations of 5 Strings, used to store the Wildlife, Location, Date, Description and SightingBy
    //      data in memory for EACH WildlifeSightingData.  That is: when one object is instantiated
	//      from this WildlifeSightingData class, it will record the detail for one sighting.
    // These properties - Wildlife, Location and Date, Description and SightingBy - are set to private so a calling
    //      class is not able to assign or access the respective values directly.
    //      Access to these properties would be best managed through the respective
    //      getters and setters - see below.
    private String Wildlife = new String();   
    private String Location = new String();
    private String Date = new String();
    private String Description = new String();
    private String SightingBy = new String();

    /** --------------------------------------------------------
    * Purpose: Constructor for the class: WildlifeSightingData.
    *          When a WildlifeSightingData is instantiated, and no default entries
    *          for the 5 properties - Wildlife, Location and Date, Description and SightingBy - are provided,
    *          this method will set default values for each.
    * param   None.
    * returns Not applicable.
    * ----------------------------------------------------------
    */    
    public WildlifeSightingData()
    {    
        Wildlife = "Wildlife";
        Location = "Location";
        Date = "Date";
        Description = "Description";
        SightingBy = "SightingBy";
    }


    /** --------------------------------------------------------
    * Purpose: A second constructor for the class: WildlifeSightingData
    *          When a WildlifeSightingData is instantiated and default entries
    *          for the 5 properties - Wildlife/Location/Date/Description/SightingBy 
    *          - are provided by the calling class, this constructor will run.
    * param   Wildlife,Location,Date,Description andSightingBy
    * returns Not applicable.
    * ----------------------------------------------------------
    */    
    public WildlifeSightingData(String wildlife, String location, String date, String description, String sightingBy)
    {    
        Wildlife = wildlife;
        Location = location;
        Date = date;
        Description = description;
        SightingBy = sightingBy;
    }


    /** --------------------------------------------------------
    * Purpose: A method that will allow the calling class to set the
    *          5 properties - Wildlife/Location/Date/Description/SightingBy - all at the one time.
    * param   Wildlife,Location,Date,Description andSightingBy
    * returns nothing (void).
    * ----------------------------------------------------------
    */    
   public void setWildlifeDataInfo(String wildlife, String location, String date,String description, String sightingBy)
    {    
        Wildlife = wildlife;
        Location = location;
        Date = date;
        Description = description;
        SightingBy = sightingBy;
    }


    //Setters - Allowing program to SET the relevant data proprty
    //
   
    public void setWildlife(String wildlife)
    {    
        Wildlife = wildlife;
    }

    public void setLocation(String location)
    {    
        Location = location;
    }

    public void setDate(String date)
    {    
        Date = date;
    }

    public void setDescription(String description)
    {
        Description = description;
    }
    
    public void setSightingBy(String sightingBy)
    {
        Description = sightingBy;
    }
 
    //Getters - Allowing program to GET the relevant data proprty
    //
    
    public String getWildlife()
    {    
        return Wildlife;
    }

    public String getLocation()
    {    
        return Location;
    }

    public String getDate()
    {    
        return Date;
    }
    
    public String getDescription()
    {
        return Description;
    }
    
    public String getSightingBy()
    {
        return SightingBy;
    }

}
