
import static com.github.lgooddatepicker.durationpicker_underconstruction.DurationUnit.Hour;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputMethodListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.Platform.exit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static sun.net.www.http.HttpClient.New;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Input extends javax.swing.JFrame {

    /**
     * Creates new form OverTimeFrame
     */
    public Input() throws SQLException {
        initComponents();
        PopulateRegistration_number();
        getInfos();
        jComboBox1.addActionListener (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    getInfos();
                } catch (SQLException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        IDList = new ArrayList<Integer>();
        holidayList = new ArrayList<String>();
        holiday_dateList = new ArrayList<String>();
        Show_Employee_In_JTable();
        jTable1.setFocusable(false);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_Display_EmployeeMouseClicked(evt) ;
            }
        });
        //Show_Employee_In_JTable();
        
        datePicker1.addDateChangeListener(new DateChangeListener(){

            @Override
            public void dateChanged(DateChangeEvent dce) {
                if(datePicker1.getDate() != null)
                settings();
                //System.out.println(datePicker1.getDate().toString());
                //System.out.println(LocalDate.parse(holiday_dateList.get(0),DateTimeFormatter.ofPattern("yyyy-LL-dd")));
               // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        
        });
  
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear();
            }
        });
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {      
                    int i = checkInput();
                    if(i == 0)
                        return;
                    setOvertime_date();
                    //System.out.println(overtime_beginning_date.toString());
                    insert();
                    fifteenPercent = 0;
                    fiftyPercent = 0;
                    seventyfivePercent = 0;
                    hundredPercent = 0;
                    Show_Employee_In_JTable();
                    Clear();
                } catch (SQLException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {      
                    int i = checkInput();
                    if(i == 0)
                        return;
                    setOvertime_date();
                    //System.out.println(overtime_beginning_date.toString());
                    update();
                    fifteenPercent = 0;
                    fiftyPercent = 0;
                    seventyfivePercent = 0;
                    hundredPercent = 0;
                    Show_Employee_In_JTable();
                    Clear();
                } catch (SQLException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {      
                    int i = checkInput();
                    if(i == 0)
                        return;
                    setOvertime_date();
                    //System.out.println(overtime_beginning_date.toString());
                    delete();
                    fifteenPercent = 0;
                    fiftyPercent = 0;
                    seventyfivePercent = 0;
                    hundredPercent = 0;
                    Show_Employee_In_JTable();
                    Clear();
                } catch (SQLException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Clear();
                Show_Employee_In_JTable();               
            }
        });
       jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Holiday holiday = new Holiday();
                holiday.setVisible(true);
                holiday.setUsername(username);
                               
            }
        });
       jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new OverTime().setVisible(true);               
            }
        });
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmpoyeeInfos info = new EmpoyeeInfos();  
                info.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent e)
                    {
                        try {
                            PopulateRegistration_number();
                            getInfos();
                        } catch (SQLException ex) {
                            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                        }                      
                        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                    }
                }); 
                info.setVisible(true);
                info.setUsername(username);
            }
        });
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        timePicker1 = new com.github.lgooddatepicker.components.TimePicker();
        timePicker2 = new com.github.lgooddatepicker.components.TimePicker();
        datePicker1 = new com.github.lgooddatepicker.components.DatePicker();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jRadioButton3 = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        datePicker2 = new com.github.lgooddatepicker.components.DatePicker();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<String>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel11.setText("Matricule");

        jLabel12.setText("Nom");

        jLabel13.setText("Prénom");

        jLabel14.setText("Fonction");

        jLabel15.setText("Date  de début");

        jLabel16.setText("Paramètres");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Ouvrable");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Dimanche");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Heure de début ");

        jLabel2.setText("Heure de fin ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Matricule", "Nom", "Prénom", "Fonction", "Date de début", "Date de fin", "Heure de début", "Heure de fin", "Total d'heures", "Total d'heures supplémentaires", "Commentaires", "Date de création", "Créateur", "Date de modification", "Modificateur"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Rappel");
        jRadioButton3.setName(""); // NOI18N

        jButton1.setText("Effacer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Date de fin");

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Férié");

        buttonGroup1.add(jRadioButton5);
        jRadioButton5.setText("Jour off");
        jRadioButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton5ActionPerformed(evt);
            }
        });

        jTextField2.setEditable(false);

        jTextField3.setEditable(false);

        jTextField4.setEditable(false);

        jMenu1.setText("Employé");

        jMenuItem1.setText("Ajouter");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Modifier");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Supprimer");
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Actualiser");
        jMenu1.add(jMenuItem4);

        jMenuItem7.setText("Créer");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Heures supplémentaires");

        jMenuItem6.setText("Consulter");
        jMenu2.add(jMenuItem6);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Fériés");

        jMenuItem5.setText("Ajouter");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15)
                            .addComponent(jLabel4))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(datePicker1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(datePicker2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(timePicker2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                    .addComponent(timePicker1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11))
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton1)
                                    .addComponent(jRadioButton5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jRadioButton3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jRadioButton2)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton4)))))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 880, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(463, 463, 463))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(datePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(datePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel1))
                            .addComponent(timePicker1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(timePicker2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel16)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jRadioButton1)
                                .addComponent(jRadioButton2)
                                .addComponent(jRadioButton4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton5)
                            .addComponent(jRadioButton3))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addGap(215, 215, 215))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem7ActionPerformed

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
            java.util.logging.Logger.getLogger(Input.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Input.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Input.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Input.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Input().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    public void FillHoliday(){
        try {
            Connect con = new Connect();
            Connection connection = con.getConnection();
            Statement st;
            ResultSet rs;
            String query = "SELECT name,holiday_date from holidays WHERE status = 1";
            st = connection.createStatement();
            rs = st.executeQuery(query);
            holidayList.clear();
            holiday_dateList.clear();
            while (rs.next()){
                holidayList.add(rs.getString("name"));
                holiday_dateList.add(rs.getString("holiday_date"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void settings(){
        FillHoliday();
        if(datePicker1.getDate().getDayOfWeek() != DayOfWeek.SUNDAY)
           buttonGroup1.setSelected(jRadioButton1.getModel(), true);
        if(datePicker1.getDate().getDayOfWeek() == DayOfWeek.SUNDAY)
            buttonGroup1.setSelected(jRadioButton2.getModel(), true);
      
        for(int i =0; i < holidayList.size(); i++){
            if(datePicker1.getDate().isEqual(LocalDate.parse(holiday_dateList.get(i),DateTimeFormatter.ofPattern("yyyy-LL-dd")))){
                buttonGroup1.setSelected(jRadioButton4.getModel(), true);
                holiday = "/"+holidayList.get(i);
            }
        }
    }
    public void Clear(){
         /*jTextField1.setText("");
         jTextField2.setText("");
         jTextField3.setText("");
         jTextField4.setText("");*/
         datePicker1.setText("");
         datePicker2.setText("");
         timePicker1.setText("");
         timePicker2.setText("");
         buttonGroup1.clearSelection();       
    }
    public void calculate(){
        /*if(jTextField2 .getText().matches("[0-9]+"))
            //jTextField16.setText("empty");
           day = Integer.parseInt(jTextField2.getText()); 
        else
            JOptionPane.showMessageDialog(null, "Veuillez entrer le Jour en format numérique");*/
        //jTextField15.setText(Double.toString(timeday));
        if((beginning_hour.isAfter(LocalTime.of(5,59)) && beginning_hour.isBefore(LocalTime.of(18,0))) &&  (end_hour.isAfter(LocalTime.of(18,0)) || end_hour.equals(LocalTime.of(00,00)) || end_hour.isBefore(LocalTime.of(6,01)))){
            if(end_hour.isAfter(LocalTime.of(04,59)) && end_hour.isBefore(LocalTime.of(06,01))){  
                if(end_hour.isAfter(LocalTime.of(05,0))){
                    if(holidayList.size() > 0){
                    for(int i =0; i < holidayList.size(); i++){
                     if((beginning_date.plusDays(1).getDayOfWeek()==DayOfWeek.SUNDAY) || (beginning_date.plusDays(1).isEqual(LocalDate.parse(holiday_dateList.get(i),DateTimeFormatter.ofPattern("yyyy-LL-dd"))))) {
                         seventyfivePercent += 1;
                     }
                     else{
                         if(fifteenPercent < 8)                                
                             fifteenPercent += 1;
                            else
                                fiftyPercent += 1;
                     }
                    }
                    }
                    else if((holidayList.size() == 0) && beginning_date.plusDays(1).getDayOfWeek()==DayOfWeek.SUNDAY)
                        seventyfivePercent += 1;
                    else if((holidayList.size() == 0) && beginning_date.plusDays(1).getDayOfWeek()!=DayOfWeek.SUNDAY){
                        if(fifteenPercent < 8)                                
                             fifteenPercent += 1;
                            else
                                fiftyPercent += 1;
                    }
                }
                    if(comment.equalsIgnoreCase("Rappel")){
                        seventyfivePercent += 11;
                        if(end_hour.isAfter(LocalTime.of(05,00))){
                            if(time - 12 > 0){
                                if(fifteenPercent < 8){
                                if(fifteenPercent+(time -12) > 8){
                                    fiftyPercent += (fifteenPercent+(time - 12)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time - 12;
                            }
                            else
                                fiftyPercent += time - 12;
                        }
                        else{
                        if(time - 11 > 0){
                        if(fifteenPercent < 8){
                                if(fifteenPercent+(time -11) > 8){
                                    fiftyPercent += (fifteenPercent+(time - 11)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time - 11;
                            }
                            else
                                fiftyPercent += time - 11;
                        }
                        }
                    }
                    }
                    if(comment.equalsIgnoreCase("Jour Ouvrable")){
                        if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere"))
                            seventyfivePercent+=1;
                        else
                        seventyfivePercent+=3;
                        if(end_hour.isBefore(LocalTime.of(05,01))){
                            if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                                if(time - 21 > 0){
                                   if(fifteenPercent < 10){
                                if(fifteenPercent+(time -21) > 10){
                                    fiftyPercent += (fifteenPercent+(time - 21)) - 10;
                                    fifteenPercent = 0;
                                    fifteenPercent = 10;
                                    
                                }
                                else
                                    fifteenPercent += time - 21;
                            }
                            else
                                fiftyPercent += time - 21;
                            } 
                                
                            }
                            else{
                            if(time - 19 > 0){
                                if(fifteenPercent < 8){
                                if(fifteenPercent+(time -19) > 8){
                                    fiftyPercent += (fifteenPercent+(time - 19)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time - 19;
                            }
                            else
                                fiftyPercent += time - 19;
                            }
                            }
                         /*else if(overtime > 0 && time - 19 < 0){
                             if(fifteenPercent < 8){
                                if(fifteenPercent+overtime > 8){
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    fiftyPercent += (fifteenPercent+overtime) - 8;
                                }
                                else
                                    fifteenPercent += overtime;
                            }
                            else
                                fiftyPercent += overtime;
                        }*/
                        }
                        else{
                            if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                                if(time - 22 > 0){
                                   if(fifteenPercent < 10){
                                if(fifteenPercent+(time -22) > 10){
                                    fiftyPercent += (fifteenPercent+(time - 22)) - 10;
                                    fifteenPercent = 0;
                                    fifteenPercent = 10;
                                    
                                }
                                else
                                    fifteenPercent += time - 22;
                            }
                            else
                                fiftyPercent += time - 22;
                            } 
                               
                            }
                            else{
                            if(time - 20 > 0){
                                if(fifteenPercent < 8){
                                if(fifteenPercent+(time - 20) > 8){
                                    fiftyPercent += (fifteenPercent+(time - 20)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time - 20;
                            }
                            else
                                fiftyPercent += time - 20;
                            }
                            
                         /*else if(overtime > 0 && time - 20 < 0){
                             if(fifteenPercent < 8){
                                if(fifteenPercent+(time-9) > 8){
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    fiftyPercent += (fifteenPercent+(time - 9)) - 8;
                                }
                                else
                                    fifteenPercent += time - 9;
                            }
                            else
                                fiftyPercent += time - 9;
                        }*/
                        }
                        }
                    }
                    if( comment.equalsIgnoreCase("Dimanche")|| comment.contains("Férié")){
                        if(end_hour.isAfter(LocalTime.of(05,0))){
                            if(time - 12  >0)
                            seventyfivePercent += time - 12;
                        }
                        else{
                            if(time - 11  >0)
                            seventyfivePercent += time - 11;
                        }
                        hundredPercent += 11;
                        
                    }
                    if(comment.equalsIgnoreCase("Jour Off")){
                        fifteenPercent += 0;
                        fiftyPercent += 0;
                        seventyfivePercent += 0;
                        hundredPercent += 0;
                    }
                    
                    
            }
                
            else{
                if(comment.equalsIgnoreCase("Rappel")){
                    if(fifteenPercent < 8){
                    if(fifteenPercent+time > 8){
                        fiftyPercent += (fifteenPercent+time) - 8;
                        fifteenPercent = 0;
                        fifteenPercent = 8;
                        
                    }
                    else
                        fifteenPercent += time;
                }
                else
                fiftyPercent += time;
                }
                if(comment.equalsIgnoreCase("Jour Ouvrable")){
                    if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                                if(time - 10 > 0){
                                   if(fifteenPercent < 10){
                                if(fifteenPercent+(time - 10) > 10){
                                    fiftyPercent += (fifteenPercent+(time - 10)) - 10;
                                    fifteenPercent = 0;
                                    fifteenPercent = 10;
                                    
                                }
                                else
                                    fifteenPercent += time - 22;
                            }
                            else
                                fiftyPercent += time - 22;
                            } 
                               
                            }
                    else{
                    if(time - 8 > 0){
                if(fifteenPercent < 8){
                    if(fifteenPercent+(time - 8) > 8){
                        fiftyPercent += (fifteenPercent+(time - 8)) - 8;
                        fifteenPercent = 0;
                        fifteenPercent = 8;
                    }
                    else
                        fifteenPercent += time - 8;
                }
                else
                fiftyPercent += time - 8;
                }
                }
                }
                if( comment.equalsIgnoreCase("Dimanche")|| comment.contains("Férié")){
                    seventyfivePercent += time;
                }
                if(comment.equalsIgnoreCase("Jour Off")){
                        fifteenPercent += 0;
                        fiftyPercent += 0;
                        seventyfivePercent += 0;
                        hundredPercent+= 0;
                    }
                
            }
        }
        if((beginning_hour.isAfter(LocalTime.of(5,59)) && beginning_hour.isBefore(LocalTime.of(18,0))) && (end_hour.isBefore(LocalTime.of(18,01)) && end_hour.isAfter(LocalTime.of(6,0)))){
            
            if(comment.equalsIgnoreCase("Rappel")){
                    if(fifteenPercent < 8){
                    if(fifteenPercent+time > 8){
                        fiftyPercent += (fifteenPercent+time) - 8;
                        fifteenPercent = 0;
                        fifteenPercent = 8;
                        
                    }
                    else
                        fifteenPercent += time;
                }
                else
                fiftyPercent += time;
            }
            if(comment.equalsIgnoreCase("Jour Ouvrable")){  
                
                if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                                if(time - 10 > 0){
                                   if(fifteenPercent < 10){
                                if(fifteenPercent+(time -10) > 10){
                                    fiftyPercent += (fifteenPercent+(time - 10)) - 10;
                                    fifteenPercent = 0;
                                    fifteenPercent = 10;
                                }
                                else
                                    fifteenPercent += time - 10;
                            }
                            else
                                fiftyPercent += time - 10;
                            }
                }
                else{
            if(time - 8 > 0){
                if(fifteenPercent < 8){
                    if(fifteenPercent+(time -8) > 8){
                        fiftyPercent += (fifteenPercent+(time -8)) - 8;
                        fifteenPercent = 0;
                        fifteenPercent = 8;
                        
                    }
                    else
                        fifteenPercent += time -8;
                        
                    
                }
                else
                fiftyPercent += time - 8;
            }
            }
            }
            if( comment.equalsIgnoreCase("Dimanche")|| comment.contains("Férié")){
                seventyfivePercent += time;
            }
            if(comment.equalsIgnoreCase("Jour Off")){
                        fifteenPercent += 0;
                        fiftyPercent += 0;
                        seventyfivePercent += 0;
                        hundredPercent += 0;
                    }
        }
        if((beginning_hour.isAfter(LocalTime.of(17,59)) || beginning_hour.equals(LocalTime.of(00,00)) ||( beginning_hour.isAfter(LocalTime.of(00,00)) && beginning_hour.isBefore(LocalTime.of(06,00)))/*&& timePicker1.getTime().isBefore(LocalTime.of(6,0))*/)&& (end_hour.isBefore(LocalTime.of(6,01)) || end_hour.isAfter(LocalTime.of(18,0)) || end_hour.equals(LocalTime.of(00,00)) )){
            //System.out.println(Double.toString(time));
            if(end_hour.isAfter(LocalTime.of(04,59)) && end_hour.isBefore(LocalTime.of(06,01))){ 
                if(end_hour.isAfter(LocalTime.of(05,0))){
                    if(holidayList.size() > 0){
                    for(int i =0; i < holidayList.size(); i++){
                     if((beginning_date.plusDays(1).getDayOfWeek()==DayOfWeek.SUNDAY) || (beginning_date.plusDays(1).isEqual(LocalDate.parse(holiday_dateList.get(i),DateTimeFormatter.ofPattern("yyyy-LL-dd"))))) {
                         seventyfivePercent += 1;
                     }
                     else{
                         if(fifteenPercent < 8)                                
                             fifteenPercent += 1;
                            else
                                fiftyPercent += 1;
                     }
                    }
                    }
                    else if((holidayList.size() == 0) && beginning_date.plusDays(1).getDayOfWeek()==DayOfWeek.SUNDAY)
                        seventyfivePercent += 1;
                    else if((holidayList.size() == 0) && beginning_date.plusDays(1).getDayOfWeek()!=DayOfWeek.SUNDAY){
                        if(fifteenPercent < 8)                                
                             fifteenPercent += 1;
                            else
                                fiftyPercent += 1;
                    }
                }   
            if(comment.equalsIgnoreCase("Rappel")){
                if(end_hour.isAfter(LocalTime.of(05,0))){
                    if(beginning_hour.isBefore(LocalTime.of(21,01))){
                        seventyfivePercent += time - 1;
                    }
                    else{
                        if(fifteenPercent < 8){
                                if(fifteenPercent+(time -1) > 8){
                                    fiftyPercent += (fifteenPercent+(time - 1)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time - 1;
                            }
                            else
                                fiftyPercent += time - 1;
                    }
                }
                else{
                    if(beginning_hour.isBefore(LocalTime.of(21,01))){
                        seventyfivePercent += time;
                    }
                    else{
                        if(fifteenPercent < 8){
                                if(fifteenPercent+(time ) > 8){
                                    fiftyPercent += (fifteenPercent+(time)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time;
                            }
                            else
                                fiftyPercent += time;
                    }
                    
                }
            }    
            if(comment.equalsIgnoreCase("Jour Ouvrable")){
                if(end_hour.isAfter(LocalTime.of(05,0))){
                    if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                        if(time - 10 > 0)
                           seventyfivePercent += overtime - 1;
                    }
                    else{
                    if(overtime > 0)
                        seventyfivePercent += overtime - 1;
                    }
                }
                else{
                    if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                        if(time - 10 > 0)
                           seventyfivePercent += overtime;
                    }
                    else{
                if(overtime > 0)
                    seventyfivePercent += overtime;
                    }
                }
            }
            if(comment.equalsIgnoreCase("Dimanche")|| comment.contains("Férié")){
                if(end_hour.isAfter(LocalTime.of(05,0))){
                if(beginning_hour.isBefore(LocalTime.of(21,01)))
                    hundredPercent += time - 1;
                else
                   seventyfivePercent += time - 1; 
                }
                else{
                    if(beginning_hour.isBefore(LocalTime.of(21,01)))
                    hundredPercent += time;
                else
                   seventyfivePercent += time;
                }
            }
            if(comment.equalsIgnoreCase("Jour Off")){
                        fifteenPercent += 0;
                        fiftyPercent += 0;
                        seventyfivePercent += 0;
                        hundredPercent += 0;
                    }
            }
            else{
                if(comment.equalsIgnoreCase("Rappel")){
                    if(fifteenPercent < 8){
                                if(fifteenPercent+(time ) > 8){
                                    fiftyPercent += (fifteenPercent+(time)) - 8;
                                    fifteenPercent = 0;
                                    fifteenPercent = 8;
                                    
                                }
                                else
                                    fifteenPercent += time;
                            }
                            else
                                fiftyPercent += time;
                }
                if(comment.equalsIgnoreCase("Jour Ouvrable")){
                    if(jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")){
                                if(time - 10 > 0){
                                   if(fifteenPercent < 10){
                                if(fifteenPercent+(time -10) > 10){
                                    fiftyPercent += (fifteenPercent+(time - 10)) - 10;
                                    fifteenPercent = 0;
                                    fifteenPercent = 10;
                                    
                                }
                                else
                                    fifteenPercent += time - 10;
                            }
                            else
                                fiftyPercent += time - 10;
                            } 
                                
                            }
                    else{
                    if(overtime > 0){
                if(fifteenPercent < 8){
                    if(fifteenPercent+overtime > 8){
                        fiftyPercent += (fifteenPercent+overtime) - 8;
                        fifteenPercent = 0;
                        fifteenPercent = 8;
                        
                    }
                    else
                        fifteenPercent += overtime;
                }
                else
                fiftyPercent += overtime;
            }
                    }
                }
                if(comment.equalsIgnoreCase("Dimanche")|| comment.contains("Férié")){
                    seventyfivePercent += time;
                }
                if(comment.equalsIgnoreCase("Jour Off")){
                        fifteenPercent += 0;
                        fiftyPercent += 0;
                        seventyfivePercent += 0;
                        hundredPercent += 0;
                    }
            }
        }
    }
        //if(overTime_date == null || datePicker1.getDate().isAfter(overTime_date)  ){
            
       // }

            
            
        /*if((timePicker1.getTime().isAfter(LocalTime.of(4,59)) && timePicker1.getTime().isBefore(LocalTime.of(20,59))) && (timePicker2.getTime().isAfter(LocalTime.of(20,59)) && timePicker2.getTime().isBefore(LocalTime.of(5,1)))){
            
        }
        if((timePicker2.getTime().isAfter(LocalTime.of(4,59)) && timePicker2.getTime().isBefore(LocalTime.of(20,59))) && (timePicker1.getTime().isAfter(LocalTime.of(20,59)) && timePicker1.getTime().isBefore(LocalTime.of(5,1)))){
            
        }*/
        /*if(jComboBox1.getSelectedItem() == "Terminée"){
            String update_query = "Update overtime set overtime = '0', day = '0' where registration_number = '"+jTextField15.getText()+"'";
            st.executeUpdate(update_query);
        }*/
        //if(datePicker1.getDate() != null)
        //jTextField15.setText(Integer.toString(seventyfivePercent));
        
        //jTextField16.setText(Integer.toString(fiftyPercent));
    public void setOvertime_date(){
        if((20 <= datePicker1.getDate().getDayOfMonth()) && (datePicker1.getDate().getDayOfMonth() <= datePicker1.getDate().lengthOfMonth())){
                if(datePicker1.getDate().getMonth().getValue() == 12){
                    overtime_beginning_date = LocalDate.of(beginning_date.getYear(),Month.DECEMBER,20);
                    overtime_end_date = LocalDate.of(datePicker1.getDate().plusYears(1).getYear(), Month.JANUARY, 20);
                }      
                else{
                    overtime_beginning_date = LocalDate.of(beginning_date.getYear(),beginning_date.getMonth().getValue(), 20);
                    overtime_end_date = LocalDate.of(datePicker1.getDate().getYear(), datePicker1.getDate().plusMonths(1).getMonth().getValue(), 20);
                }
            }
            if((1 <= datePicker1.getDate().getDayOfMonth()) && (datePicker1.getDate().getDayOfMonth() < 20)){
                if(datePicker1.getDate().getMonth().getValue() == 1){
                    overtime_beginning_date =LocalDate.of(datePicker1.getDate().minusYears(1).getYear(), Month.DECEMBER, 20);
                    overtime_end_date = LocalDate.of(datePicker1.getDate().getYear(), datePicker1.getDate().getMonth().getValue(), 20);
                }
                else{
                    overtime_beginning_date = LocalDate.of(datePicker1.getDate().getYear(), datePicker1.getDate().minusMonths(1).getMonth().getValue(), 20);
                    overtime_end_date = LocalDate.of(datePicker1.getDate().getYear(), datePicker1.getDate().getMonth().getValue(), 20);
                }
            }
    }
    public void comment(){
        if(buttonGroup1.getSelection()==jRadioButton1.getModel())
            comment = "Jour Ouvrable";
        if(buttonGroup1.getSelection()==jRadioButton2.getModel()){
            comment = "Dimanche";
            overtime = 0;
            overtime =time;
        }
        if(buttonGroup1.getSelection()==jRadioButton4.getModel()){
            comment = "Férié"+holiday;
            overtime = 0;
            overtime =time;
        }
        if(buttonGroup1.getSelection()==jRadioButton5.getModel()){
            comment = "Jour Off";
            overtime = 0;
        }
        if(buttonGroup1.getSelection()==jRadioButton3.getModel()){
            comment = "Rappel";
            overtime = 0;
            overtime =time;
        }
    }
    public void insert() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        comment();
        String query = "INSERT INTO input(beginning_hour,end_hour,beginning_date,end_date,total_time,total_overtime,comment,status,date_created,user_date_created,date_modified,user_date_modified,ID_employee) VALUES('"+timePicker1.getTime()+"','"+timePicker2.getTime()+"','"+datePicker1.getDate()+"','"+datePicker2.getDate()+"','"
                +time+"','"+overtime+"','"+comment+"',1,NOW(),'"+username+"',NOW(),'"+username+"',(SELECT ID FROM employee WHERE registration_number = '"+jComboBox1.getSelectedItem()+"' AND status = 1))";
        Statement st;
        st = connection.createStatement(); 
        st.executeUpdate(query);
        getOvertime();
        insertOvertime();
        JOptionPane.showMessageDialog(null, "Employé enregistré!");
    }
    public void update() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        comment();
        String query = "UPDATE input SET ID_employee = (SELECT ID FROM employee WHERE registration_number = '"+jComboBox1.getSelectedItem()+"'),beginning_hour = '"+timePicker1.getTime()+"',end_hour = '"+timePicker2.getTime()+"',beginning_date = '"+datePicker1.getDate()+"',end_date = '"+datePicker2.getDate()+"',total_time = '"+time+"',total_overtime = '"+overtime+"',comment = '"+comment+"',date_modified = NOW(),user_date_modified = '"+username+"' WHERE ID = "+ID;
        Statement st;
        st = connection.createStatement(); 
        st.executeUpdate(query);
        getOvertime();
        insertOvertime();
        JOptionPane.showMessageDialog(null, "Employé Modifié");
    }
    public void delete() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        comment();
        String query = "UPDATE input SET status = 0 WHERE ID = "+ID;
        Statement st;
        st = connection.createStatement(); 
        st.executeUpdate(query);
        getOvertime();
        insertOvertime();
        JOptionPane.showMessageDialog(null, "Employé supprimé");
    }
    public int checkInput(){
        time = 0;
        overtime = 0;
        /*if(jTextField1.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Veuillez entrer le Matricule de l'employé");
            return 0;
        }
        if(jTextField2.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom de l'employé");
            return 0;
        }
        if(jTextField3.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Veuillez entrer le(s)prénom(s) de l'employé");
            return 0;
        }
        if(jTextField4.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Veuillez entrer la Fonction de l'employé");
            return 0;
        }*/
        if(datePicker1.getDate() == null){
            JOptionPane.showMessageDialog(null, "Veuillez entrer la date de début");
            return 0;
        }
        else{
            beginning_date = datePicker1.getDate();
        }
        
        
        if(datePicker2.getDate() == null){
            JOptionPane.showMessageDialog(null, "Veuillez entrer la date de fin");
            return 0;
        }
        else{
            end_date = datePicker2.getDate();
        }
        if (beginning_date.isAfter(end_date)){
            JOptionPane.showMessageDialog(null, "la date de début doit être avant la date de fin");
            return 0;
        }
        if(timePicker1.getTime() != null)
        hour1 = timePicker1.getTime().getHour()+((double)timePicker1.getTime().getMinute()/60);
        else{
            JOptionPane.showMessageDialog(null, "Veuillez entrer l'heure de début");
            return 0;
        }
        if(timePicker2.getTime() != null)
        hour2 = timePicker2.getTime().getHour()+((double)timePicker2.getTime().getMinute()/60);
        else{
            JOptionPane.showMessageDialog(null, "Veuillez entrer l'heure de fin");
            return 0;
        }
        if(timePicker2.getTime().equals(LocalTime.of(00,00)) || (timePicker2.getTime().isAfter(LocalTime.of(00,00)) && timePicker2.getTime().isBefore(LocalTime.of(06,01)))){
            if(datePicker2.getDate().equals(datePicker1.getDate())){
                JOptionPane.showMessageDialog(null, "La date de fin doit être après celle du début");
                return 0;
            }   
        }
        if(hour2 > hour1)
            time = (int) Math.ceil(Math.abs(hour2 - hour1));
        else
           time = (int) Math.ceil(Math.abs((24 - hour1) + hour2));
        if(time > 16)
            overtime = time - 16;
        else if((jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")) && time > 20)
            overtime = time - 20;
        else if((jTextField4.getText().equalsIgnoreCase("cuisinier") || jTextField4.getText().equalsIgnoreCase("washboy") || jTextField4.getText().equalsIgnoreCase("washlady") || jTextField4.getText().toString().equalsIgnoreCase("yardboy") || jTextField4.getText().equalsIgnoreCase("assistant cuisinier") || jTextField4.getText().equalsIgnoreCase("cleaner") || jTextField4.getText().equalsIgnoreCase("macon") || jTextField4.getText().equalsIgnoreCase("ouvrier") || jTextField4.getText().equalsIgnoreCase("cuisiniere")) && 20 > time)
            overtime = time - 10;
        else
            overtime = time - 8;
        if(overtime < 0)
            overtime = 0;
            return 1;
        //System.out.println(Integer.toString(time));
    }
    public void insertOvertime() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        Statement st;
        ResultSet rs;
        String verify_query = "SELECT * FROM overtime o LEFT JOIN employee e ON o.ID_employee = e.ID WHERE beginning_date = '"+overtime_beginning_date+"' AND end_date = '"+overtime_end_date+"' AND registration_number = '"+jComboBox1.getSelectedItem()+"' AND o.status = 1";
        String insert_query = "INSERT INTO overtime(fifteenpercent,fiftypercent,seventyfivepercent,hundredpercent,beginning_date,end_date,status,date_created,user_date_created,date_modified,user_date_modified,ID_employee) VALUES ('"+fifteenPercent+"','"+fiftyPercent+"','"+seventyfivePercent+"','"+hundredPercent+"','"+overtime_beginning_date+"','"+overtime_end_date+"',1,NOW(),'"+username+"',NOW(),'"+username+"',(SELECT ID FROM employee WHERE registration_number = '"+jComboBox1.getSelectedItem()+"' AND status = 1))";
        String update_query = "UPDATE overtime set fifteenpercent = '"+fifteenPercent+"', fiftypercent = '"+fiftyPercent+"', seventyfivepercent = '"+seventyfivePercent+"', hundredpercent = '"+hundredPercent+"' WHERE beginning_date = '"+overtime_beginning_date+"' AND end_date = '"+overtime_end_date+"' AND ID_employee = (SELECT ID FROM employee WHERE registration_number = '"+jComboBox1.getSelectedItem()+"' AND status = 1) AND status = 1";
        st = connection.createStatement();
        rs = st.executeQuery(verify_query);
                if(rs.next() == false){
                    st.executeUpdate(insert_query);
                    
                }
                else{
                   st.executeUpdate(update_query); 
                   
                }
    }
     public ArrayList<Employee> getEmployeeList()
   {
       ArrayList<Employee> employeeList = new ArrayList<Employee>();
       Connect con = new Connect();
       Connection connection = con.getConnection();
       
       String query = "SELECT i.ID,registration_number,surname,name,function,beginning_date,end_date,beginning_hour,end_hour,total_time,total_overtime,comment,i.date_created,i.user_date_created,i.date_modified,i.user_date_modified FROM input i LEFT JOIN employee e ON i.ID_employee = e.ID  WHERE i.status = 1";
       Statement st;
       ResultSet rs;
       
       try {
           
           st = connection.createStatement();   
           rs = st.executeQuery(query);
           Employee employee;
           IDList.clear();
           String contract;
           while(rs.next())
           {
               IDList.add(rs.getInt("ID"));
               employee = new Employee(rs.getString("registration_number"),rs.getString("surname"),rs.getString("name"),rs.getString("function"),rs.getString("beginning_date"),rs.getString("end_date"),rs.getString("beginning_hour"),rs.getString("end_hour"),rs.getInt("total_time"),rs.getInt("total_overtime"),rs.getString("comment"),rs.getString("date_created"),rs.getString("user_date_created"),rs.getString("date_modified"),rs.getString("user_date_modified"));
               employeeList.add(employee);
           }
       } catch (Exception e) {
                  
           e.printStackTrace();
       }
       return employeeList;
   }

    public void Show_Employee_In_JTable()
   {
       ArrayList<Employee> list = getEmployeeList();
       DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
       model.setRowCount(0);
       Object[] row = new Object[17];
       for(int i = 0; i < list.size(); i++)
       {   
           row[0] = list.get(i).getRegistration_number(); 
           row[1] = list.get(i).getSurname();
           row[2] = list.get(i).getName();
           row[3] = list.get(i).getFunction();
           row[4] = list.get(i).getBeginning_date();
           row[5] = list.get(i).getEnd_date();
           row[6] = list.get(i).getBeginning_hour();
           row[7] = list.get(i).getEnd_hour();
           row[8] = list.get(i).getTotal_time();
           row[9] = list.get(i).getTotal_overtime();
           row[10] = list.get(i).getComment();
           row[11] = list.get(i).getDate_created(); 
           row[12] = list.get(i).getUser_date_created();
           row[13] = list.get(i).getDate_modified();
           row[14] = list.get(i).getUser_date_modified();
           model.addRow(row);
       }
    }
    public void getOvertime() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        Statement st = null;
        ResultSet rs;
        String query = "SELECT beginning_hour,end_hour,beginning_date,end_date,total_time,total_overtime,comment from input WHERE beginning_date >= '"+overtime_beginning_date.toString()+"' AND beginning_date < '"+overtime_end_date.toString()+"' AND end_date <= '"+overtime_end_date.toString()+"' AND ID_employee = (SELECT ID FROM employee WHERE registration_number = '"+jComboBox1.getSelectedItem()+"') AND status = 1";
        try {
            st = connection.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Input.class.getName()).log(Level.SEVERE, null, ex);
        }
        rs = st.executeQuery(query);
        System.out.println(time - 8);
        /*if(rs.next()== false){
            fifteenPercent = 0;
            fiftyPercent = 0;
            seventyfivePercent = 0;
            hundredPercent = 0;
        }*/
        while(rs.next())
           {
               time = 0;
               overtime = 0;
               beginning_hour = LocalTime.parse(rs.getString("beginning_hour"), DateTimeFormatter.ofPattern("HH:mm:ss"));
               end_hour = LocalTime.parse(rs.getString("end_hour"), DateTimeFormatter.ofPattern("HH:mm:ss"));
               beginning_date = LocalDate.parse(rs.getString("beginning_date"),DateTimeFormatter.ofPattern("yyyy-LL-dd"));
               end_date = LocalDate.parse(rs.getString("end_date"),DateTimeFormatter.ofPattern("yyyy-LL-dd"));
               time = rs.getInt("total_time");
               overtime = rs.getInt("total_overtime");
               comment = rs.getString("comment");
               //System.out.println(Integer.toString(time));
               //System.out.println(comment);
               
               calculate();
           }
       
    }
    private void jTable_Display_EmployeeMouseClicked(java.awt.event.MouseEvent evt) {
        int i = jTable1.getSelectedRow();
        ID = IDList.get(i);
        TableModel model = jTable1.getModel();
        jComboBox1.setSelectedItem(model.getValueAt(i,0).toString());
        jTextField2.setText(model.getValueAt(i,1).toString());
        jTextField3.setText(model.getValueAt(i,2).toString());
        jTextField4.setText(model.getValueAt(i,3).toString());
        LocalDate date = LocalDate.parse(model.getValueAt(i,4).toString(),DateTimeFormatter.ofPattern("yyyy-LL-dd"));
        datePicker1.setDate(date);
        date = LocalDate.parse(model.getValueAt(i,5).toString(),DateTimeFormatter.ofPattern("yyyy-LL-dd"));
        datePicker2.setDate(date);
        LocalTime time = LocalTime.parse(model.getValueAt(i,6).toString(),DateTimeFormatter.ofPattern("HH:mm:ss"));
        timePicker1.setTime(time);
        time = LocalTime.parse(model.getValueAt(i,7).toString(),DateTimeFormatter.ofPattern("HH:mm:ss"));
        timePicker2.setTime(time);
        if(model.getValueAt(i,10).toString().contains("Ouvrable"))
            buttonGroup1.setSelected(jRadioButton1.getModel(), true);
        if(model.getValueAt(i,10).toString().equals("Dimanche"))
            buttonGroup1.setSelected(jRadioButton2.getModel(), true);
        if(model.getValueAt(i,10).toString().contains("Férié"))
            buttonGroup1.setSelected(jRadioButton4.getModel(), true); 
        if(model.getValueAt(i,10).toString().equals("Jour Off"))
            buttonGroup1.setSelected(jRadioButton5.getModel(), true);
        if(model.getValueAt(i,10).toString().equals("Rappel"))
            buttonGroup1.setSelected(jRadioButton3.getModel(), true);
        //datePicker1.setText(model.getValueAt(i,1).toString());
    }
    public void setUsername(String username){
        this.username =username;
    }
    public void PopulateRegistration_number() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        Statement st;
        ResultSet rs;
        String query = "SELECT registration_number FROM employee WHERE status = 1";
        st = connection.createStatement();   
        rs = st.executeQuery(query);
        while(rs.next()){
            jComboBox1.addItem(rs.getString("registration_number"));
        }
        
    }
    public void getInfos() throws SQLException{
        Connect con = new Connect();
        Connection connection = con.getConnection();
        Statement st;
        ResultSet rs;
        String query = "SELECT * FROM employee WHERE registration_number = '"+jComboBox1.getSelectedItem()+"' AND status = 1";
        st = connection.createStatement();   
        rs = st.executeQuery(query);
       while(rs.next()){
            jTextField2.setText(rs.getString("surname"));
            jTextField3.setText(rs.getString("name"));
            jTextField4.setText(rs.getString("function"));
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private com.github.lgooddatepicker.components.DatePicker datePicker1;
    private com.github.lgooddatepicker.components.DatePicker datePicker2;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
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
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private com.github.lgooddatepicker.components.TimePicker timePicker1;
    private com.github.lgooddatepicker.components.TimePicker timePicker2;
    // End of variables declaration//GEN-END:variables
    private int fifteenPercent = 0;
    private int fiftyPercent = 0;
    private int seventyfivePercent = 0;
    private int hundredPercent = 0;
    private int day,time = 0,overtime = 0;
    private double hour1,hour2;
    private LocalDate overtime_beginning_date = null, overtime_end_date = null, beginning_date = null, end_date = null;
    private LocalTime beginning_hour = null,end_hour = null;
    private String comment,holiday = "";
    private ArrayList<Integer> IDList;
    private ArrayList<String> holidayList,holiday_dateList;
    private int ID;
    private String username;
}
