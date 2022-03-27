/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Employee {
    private String registration_number;
    private String surname;
    private String name;
    private String function;
    private int edited;
    private String beginning_date;
    private String end_date;
    private String beginning_hour;
    private String end_hour;
    private int total_time;
    private int total_overtime;
    private String comment;
    private String date_created;
    private String user_date_created;
    private String date_modified;
    private String user_date_modified;
    public Employee(String registration_number, String surname, String name, String function, String beginning_date, String end_date, String beginning_hour, String end_hour,int total_time, int total_overtime,String comment, String date_created, String user_date_created, String date_modified, String user_date_modified){
        this.registration_number = registration_number;
        this.surname = surname;
        this.name = name;
        this.function = function;
        this.total_time = total_time;
        this.total_overtime = total_overtime;
        this.beginning_date = beginning_date;
        this.end_date = end_date;
        this.beginning_hour = beginning_hour;
        this.end_hour = end_hour;
        this.comment = comment;
        this.date_created = date_created;
        this.user_date_created = user_date_created;
        this.date_modified = date_modified;
        this.user_date_modified = user_date_modified;
    }
    public Employee(String registration_number, String surname, String name, String function, int edited, String date_created, String user_date_created, String date_modified, String user_date_modified){
        this.registration_number = registration_number;
        this.surname = surname;
        this.name = name;
        this.function = function;
        this.edited = edited;
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
    public int getEdited()
    {
        return edited;
    }
    public int getTotal_overtime()
    {
        return total_overtime;
    }
    public int getTotal_time()
    {
        return total_time;
    }
    public String getBeginning_date()
    {
        return beginning_date;
    }
    public String getEnd_date()
    {
        return end_date;
    }
    public String getBeginning_hour()
    {
        return beginning_hour;
    }
    public String getEnd_hour()
    {
        return end_hour;
    }
    public String getComment()
    {
        return comment;
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
