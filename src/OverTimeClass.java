/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class OverTimeClass {
    private String registration_number;
    private String surname;
    private String name;
    private String function;
    private String beginning_date;
    private String end_date;
    private int fifteenPercent;
    private int fiftyPercent;
    private int seventyfivePercent;
    private int hundredPercent;
    private String date_created;
    private String user_date_created;
    private String date_modified;
    private String user_date_modified;
    public OverTimeClass(String registration_number, String surname, String name, String function, int fifteenPercent , int fiftyPercent,int seventyfivePercent, int hundredPercent,String beginning_date, String end_date,String date_created, String user_date_created, String date_modified, String user_date_modified){
        this.registration_number = registration_number;
        this.surname = surname;
        this.name = name;
        this.function = function;
        this.fifteenPercent = fifteenPercent;
        this.fiftyPercent = fiftyPercent;
        this.beginning_date = beginning_date;
        this.end_date = end_date;
        this.seventyfivePercent = seventyfivePercent;
        this.hundredPercent = hundredPercent;
        this.date_created = date_created;
        this.user_date_created = user_date_created;
        this.date_modified = date_modified;
        this.user_date_modified = user_date_modified;
    }
     public String getRegistration_number()
    {
        return registration_number;
    }
      public String getSurname()
    {
        return surname;
    }
    
    public String getName()
    {
        return name;
    }
    public String getFunction()
    {
        return function;
    }
    public int getFifteenPercent()
    {
        return fifteenPercent;
    }
    public int getFiftyPercent()
    {
        return fiftyPercent;
    }
    public String getBeginning_date()
    {
        return beginning_date;
    }
    public String getEnd_date()
    {
        return end_date;
    }
    public int getSeventyfivePercent()
    {
        return seventyfivePercent;
    }
    public int getHundredPercent()
    {
        return hundredPercent;
    }
    public String getDate_created()
    {
        return date_created;
    }  
    public String getUser_date_created()
    {
        return user_date_created;
    }
    
    public String getDate_modified()
    {
        return date_modified;
    }
   public String getUser_date_modified()
    {
        return user_date_modified;
    } 
}
