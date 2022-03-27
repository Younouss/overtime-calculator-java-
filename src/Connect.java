
import java.sql.DriverManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Connect {
        public  java.sql.Connection getConnection()
    {
        java.sql.Connection con;
        try {
            //Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/overtime", "root", ""
                    + "");
            //con = DriverManager.getConnection("jdbc:mysql://192.168.8.120/overtime", "root","password");
            return con;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
       }
   }
}
