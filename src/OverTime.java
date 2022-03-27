
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class OverTime extends javax.swing.JFrame {

    /**
     * Creates new form OverTime
     */
    public OverTime() {
        initComponents();
        setInvisible();
        jTable1.setFocusable(false);
        IDList = new ArrayList<Integer>();
        String query = "SELECT o.ID,registration_number,surname,name,function,beginning_date,end_date,fifteenpercent,fiftypercent,seventyfivepercent,hundredpercent,o.date_created,o.user_date_created,o.date_modified,o.user_date_modified FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1";
        Show_Overtime_In_JTable(query);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_OvertimeMouseClicked(evt) ;
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String query = "SELECT o.ID,registration_number,surname,name,function,beginning_date,end_date,fifteenpercent,fiftypercent,seventyfivepercent,hundredpercent,o.date_created,o.user_date_created,o.date_modified,o.user_date_modified FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1";
                Show_Overtime_In_JTable(query);
                setInvisible();
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {      
                    //System.out.println(overtime_beginning_date.toString());
                    delete();
                    String query = "SELECT o.ID,registration_number,surname,name,function,beginning_date,end_date,fifteenpercent,fiftypercent,seventyfivepercent,hundredpercent,o.date_created,o.user_date_created,o.date_modified,o.user_date_modified FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1";
                    Show_Overtime_In_JTable(query);
                } catch (SQLException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setVisible();
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    export1();
                } catch (IOException ex) {
                    Logger.getLogger(OverTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                /*Export2 export = new Export2();
                export.getJTable_Display_Employee(jTable1);
                export.setVisible(true);*/
                try {
                    export2();
                } catch (IOException ex) {
                    Logger.getLogger(OverTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                /*Export3 export = new Export3();
                export.getJTable_Display_Employee(jTable1);
                export.setVisible(true);*/
                try {
                    export3();
                } catch (IOException ex) {
                    Logger.getLogger(OverTime.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                search();
            }
        });
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear();
            }
        });
        datePicker1.addDateChangeListener(new DateChangeListener(){

            @Override
            public void dateChanged(DateChangeEvent dce) {
                if(datePicker1.getDate() != null)
                checkDate1();
                //System.out.println(datePicker1.getDate().toString());
                //System.out.println(LocalDate.parse(holiday_dateList.get(0),DateTimeFormatter.ofPattern("yyyy-LL-dd")));
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        });
        datePicker2.addDateChangeListener(new DateChangeListener(){

            @Override
            public void dateChanged(DateChangeEvent dce) {
                if(datePicker2.getDate() != null)
                checkDate2();
                //System.out.println(datePicker1.getDate().toString());
                //System.out.println(LocalDate.parse(holiday_dateList.get(0),DateTimeFormatter.ofPattern("yyyy-LL-dd")));
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void setInvisible(){
        jLabel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
        jLabel6.setVisible(false);
        jTextField1.setVisible(false);
        jTextField2.setVisible(false);
        jTextField3.setVisible(false);
        jTextField4.setVisible(false);
        datePicker1.setVisible(false);
        datePicker2.setVisible(false);
        jButton1.setVisible(false);
        jButton2.setVisible(false);
    }
    public void setVisible(){
        jLabel1.setVisible(true);
        jLabel2.setVisible(true);
        jLabel3.setVisible(true);
        jLabel4.setVisible(true);
        jLabel5.setVisible(true);
        jLabel6.setVisible(true);
        jTextField1.setVisible(true);
        jTextField2.setVisible(true);
        jTextField3.setVisible(true);
        jTextField4.setVisible(true);
        datePicker1.setVisible(true);
        datePicker2.setVisible(true);
        jButton1.setVisible(true);
        jButton2.setVisible(true);
    }
    public void clear(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        datePicker1.setText("");
        datePicker2.setText("");
    }
    public void checkDate1(){
        LocalDate date1 = datePicker1.getDate();
        if(date1.getDayOfMonth() != 20){
            JOptionPane.showMessageDialog(null, "Veuillez selectionner le 20 d un mois ");
            datePicker1.setText(" ");
        }
            
    }
    public void checkDate2(){
        LocalDate date2 = datePicker2.getDate();
        if(date2.getDayOfMonth() != 20){
            JOptionPane.showMessageDialog(null, "Veuillez selectionner le 20 d un mois ");
            datePicker2.setText(" ");
        }
    }
    public void search(){
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("") && jTextField3.getText().equals("") && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"'";;
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("")  && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("")  && jTextField3.getText().equals("")  && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND function = '"+jTextField4.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("")  && jTextField3.getText().equals("")  && jTextField4.getText().equals("") && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND beginning_date >= '"+datePicker1.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("")  && jTextField3.getText().equals("")  && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND surname = '"+jTextField2.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("")  && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("")  && jTextField3.getText().equals("")  && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND function = '"+jTextField4.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("")  && jTextField3.getText().equals("")  && jTextField4.getText().equals("") && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("")  && jTextField3.getText().equals("")  && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"' AND function = '"+jTextField4.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"' AND function = '"+jTextField4.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("") == false && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND registration_number = '"+jTextField1.getText()+"' AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"' AND function = '"+jTextField4.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"' AND beginning_date < '"+datePicker2.getDate().toString()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND function = '"+jTextField4.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") && jTextField4.getText().equals("") && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"' AND function = '"+jTextField4.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"' AND function = '"+jTextField4.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") == false && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND surname = '"+jTextField2.getText()+"' AND name = '"+jTextField3.getText()+"' AND function = '"+jTextField4.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"' AND beginning_date < '"+datePicker2.getDate().toString()+"' AND end_date <= '"+datePicker2.getDate().toString()+"' ";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND function = '"+jTextField4.getText()+"' AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND beginning_date >= '"+datePicker1.getDate().toString()+"' AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND end_date <= '"+datePicker2.getDate().toString()+"' AND name = '"+jTextField3.getText()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("") == false && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND function = '"+jTextField4.getText()+"' AND name = '"+jTextField3.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"' AND beginning_date < '"+datePicker2.getDate().toString()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("")  && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() == null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND function = '"+jTextField4.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("")  && jTextField4.getText().equals("") == false && datePicker1.getDate() == null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND function = '"+jTextField4.getText()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("")  && jTextField4.getText().equals("") == false && datePicker1.getDate() != null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND function = '"+jTextField4.getText()+"' AND beginning_date >= '"+datePicker1.getDate().toString()+"' AND beginning_date < '"+datePicker2.getDate().toString()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("")  && jTextField4.getText().equals("") && datePicker1.getDate() != null && datePicker2.getDate() != null){
            String query = "SELECT * FROM overtime o LEFT JOIN employee e on o.ID_employee = e.ID WHERE o.status = 1 AND beginning_date >= '"+datePicker1.getDate().toString()+"' AND beginning_date < '"+datePicker2.getDate().toString()+"' AND end_date <= '"+datePicker2.getDate().toString()+"'";
            Show_Overtime_In_JTable(query);
        }
        if(jTextField1.getText().equals("")  && jTextField2.getText().equals("") && jTextField3.getText().equals("")  && jTextField4.getText().equals("") && datePicker1.getDate() == null && datePicker2.getDate() == null){
            JOptionPane.showMessageDialog(null, "Veuillez remplir un champ");
        }
    }
    public void export1() throws IOException{
        Path path = Paths.get("C:\\Optimum");
         if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatedDateTime = dateTime.format(formatter);
        String dateTimeString = formatedDateTime.replace(':', ' ');
        File f = new File("C:\\Optimum\\"+dateTimeString+".xlsx");
        f.createNewFile();
        new WorkbookFactory();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(); //WorkSheet
        Row row = sheet.createRow(1); //Row created at line 2
        TableModel model = jTable1.getModel();
        Row headerRow = sheet.createRow(0);
        for(int headings = 0; headings < 10; headings++){ //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
        }
        wb.write(new FileOutputStream(f));
        JOptionPane.showMessageDialog(null, "Données exportées ");
    }
    public void export2() throws IOException{
        Path path = Paths.get("C:\\Optimum");
         if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatedDateTime = dateTime.format(formatter);
        String dateTimeString = formatedDateTime.replace(':', ' ');
        File f = new File("C:\\Optimum\\"+dateTimeString+".xlsx");
        f.createNewFile();
        new WorkbookFactory();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(); //WorkSheet
        Row row = sheet.createRow(1); //Row created at line 2
        TableModel model = jTable1.getModel();
        Row headerRow = sheet.createRow(0);
        /*for(int headings = 0; headings < 10; headings++){ //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }*/
        headerRow.createCell(0).setCellValue("Matricule");
        headerRow.createCell(1).setCellValue("References");
        headerRow.createCell(2).setCellValue("Montant");
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1401");
        row.createCell(2).setCellValue(model.getValueAt(rows, 4).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1402");
        row.createCell(2).setCellValue(model.getValueAt(rows, 5).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((row.getRowNum() + 1)); 
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1403");
        row.createCell(2).setCellValue(model.getValueAt(rows, 6).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((row.getRowNum() + 1)); 
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1404");
        row.createCell(2).setCellValue(model.getValueAt(rows, 7).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((row.getRowNum() + 1)); 
        }
        wb.write(new FileOutputStream(f));
        JOptionPane.showMessageDialog(null, "Données exportées ");
    }
     public void export3() throws IOException{
        Path path = Paths.get("C:\\Optimum");
         if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formatedDateTime = dateTime.format(formatter);
        String dateTimeString = formatedDateTime.replace(':', ' ');
        File f = new File("C:\\Optimum\\"+dateTimeString+".xlsx");
        f.createNewFile();
        new WorkbookFactory();
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet(); //WorkSheet
        Row row = sheet.createRow(1); //Row created at line 2
        TableModel model = jTable1.getModel();
        Row headerRow = sheet.createRow(0);
        /*for(int headings = 0; headings < 10; headings++){ //For each column
            headerRow.createCell(headings).setCellValue(model.getColumnName(headings));//Write column name
        }*/
        headerRow.createCell(0).setCellValue("Matricule");
        headerRow.createCell(1).setCellValue("References");
        headerRow.createCell(2).setCellValue("Montant");
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1417");
        row.createCell(2).setCellValue(model.getValueAt(rows, 4).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((rows + 2)); 
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1418");
        row.createCell(2).setCellValue(model.getValueAt(rows, 5).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((row.getRowNum() + 1)); 
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1419");
        row.createCell(2).setCellValue(model.getValueAt(rows, 6).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((row.getRowNum() + 1)); 
        }
        for(int rows = 0; rows < model.getRowCount(); rows++){ //For each table row
        /*for(int cols = 0; cols < 10; cols++){ //For each table column
            row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); //Write value
        }*/
        row.createCell(0).setCellValue(model.getValueAt(rows, 0).toString());
        row.createCell(1).setCellValue("1420");
        row.createCell(2).setCellValue(model.getValueAt(rows, 7).toString());
        //row.createCell(7).setCellValue(model.getValueAt(rows, 11).toString());
        //Set the row to the next one in the sequence 
        row = sheet.createRow((row.getRowNum() + 1)); 
        }
        wb.write(new FileOutputStream(f));
        JOptionPane.showMessageDialog(null, "Données exportées ");
    }
      public ArrayList<OverTimeClass> getOvertimeList(String query)
   {
       ArrayList<OverTimeClass> overtimeList = new ArrayList<OverTimeClass>();
       Connect con = new Connect();
       Connection connection = con.getConnection();
       
       //String query = "SELECT ID,registration_number,surname,name,function,beginning_date,end_date,fifteenpercent,fiftypercent,seventyfivepercent,hundredpercent,date_created,user_date_created,date_modified,user_date_modified FROM overtime  WHERE status = 1";
       Statement st;
       ResultSet rs;
       
       try {
           
           st = connection.createStatement();   
           rs = st.executeQuery(query);
           OverTimeClass overtimeClass;
           IDList.clear();
           String contract;
           while(rs.next())
           {
               IDList.add(rs.getInt("ID"));
               overtimeClass = new OverTimeClass(rs.getString("registration_number"),rs.getString("surname"),rs.getString("name"),rs.getString("function"),rs.getInt("fifteenpercent"),rs.getInt("fiftypercent"),rs.getInt("seventyfivepercent"),rs.getInt("hundredpercent"),rs.getString("beginning_date"),rs.getString("end_date"),rs.getString("date_created"),rs.getString("user_date_created"),rs.getString("date_modified"),rs.getString("user_date_modified"));
               overtimeList.add(overtimeClass);
           }
       } catch (Exception e) {
                  
           e.printStackTrace();
       }
       return overtimeList;
   }

    public void Show_Overtime_In_JTable(String query)
   {
       ArrayList<OverTimeClass> list = getOvertimeList(query);
       DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
       model.setRowCount(0);
       Object[] row = new Object[17];
       for(int i = 0; i < list.size(); i++)
       {   
           row[0] = list.get(i).getRegistration_number(); 
           row[1] = list.get(i).getSurname();
           row[2] = list.get(i).getName();
           row[3] = list.get(i).getFunction();
           row[4] = list.get(i).getFifteenPercent();
           row[5] = list.get(i).getFiftyPercent();
           row[6] = list.get(i).getSeventyfivePercent();
           row[7] = list.get(i).getHundredPercent();
           row[8] = list.get(i).getBeginning_date();
           row[9] = list.get(i).getEnd_date();
           row[10] = list.get(i).getDate_created(); 
           row[11] = list.get(i).getUser_date_created();
           row[12] = list.get(i).getDate_modified();
           row[13] = list.get(i).getUser_date_modified();
           model.addRow(row);
       }
    }
    private void jTable_Display_OvertimeMouseClicked(java.awt.event.MouseEvent evt) {
        int i = jTable1.getSelectedRow();
        ID = IDList.get(i);
    }
    public void delete() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        String query = "UPDATE overtime SET status = 0 WHERE ID = "+ID;
        Statement st;
        st = connection.createStatement(); 
        st.executeUpdate(query);
        JOptionPane.showMessageDialog(null, "Heures supplémentaires supprimées");
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        datePicker2 = new com.github.lgooddatepicker.components.DatePicker();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricule", "Nom", "Prénom", "Fonction", "15%", "50%", "75%", "100%", "Date de début", "Date de fin", "Date de création", "Créateur", "Date de modification", "Modificateur"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Matricule");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Nom");

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });

        jLabel3.setText("Prénom");

        jLabel4.setText("Fonction");

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel5.setText("Date de début");

        jLabel6.setText("Date de fin");

        jButton1.setText("Rechercher");

        jButton2.setText("Effacer");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jMenu1.setText("Heures supplémentaires");

        jMenuItem1.setText("Actualiser");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Supprimer");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Recherche");

        jMenuItem3.setText("Rechercher");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Données");

        jMenuItem4.setText("Exporter");
        jMenu3.add(jMenuItem4);

        jMenuItem5.setText("Exporter au codage 1");
        jMenu3.add(jMenuItem5);

        jMenuItem6.setText("Exporter au codage 2");
        jMenu3.add(jMenuItem6);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField3)
                                .addComponent(jTextField1)
                                .addComponent(jTextField2)
                                .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton1)
                        .addGap(27, 27, 27)
                        .addComponent(jButton2)))
                .addGap(44, 44, 44)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 768, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 22, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OverTime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OverTime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OverTime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OverTime.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OverTime().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
private ArrayList<Integer> IDList;
private int ID;
}
