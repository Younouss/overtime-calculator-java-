
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
public class InfoReader extends javax.swing.JFrame {

    /**
     * Creates new form InfoReader
     */
    public InfoReader() {
        initComponents();
        
        jButton1.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(InfoReader.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                   file = new File("");
                   file = c.getSelectedFile(); 
                   jLabel1.setText(file.getName());
                } 
         }}); 
         jButton2.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(java.awt.event.ActionEvent evt){  
                try {
                    Upload();
                } catch (IOException ex) {
                    Logger.getLogger(InfoReader.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(InfoReader.class.getName()).log(Level.SEVERE, null, ex);
                }
        }}); 
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void Upload() throws FileNotFoundException, IOException, SQLException{
         FileInputStream inputStream = null;
            //inputStream = new FileInputStream(new File(excelFilePath));
            if (file == null){
                JOptionPane.showMessageDialog(null, "Veuillez sélectionner un fichier au format excel");
            }
            else{
                inputStream = new FileInputStream(file);
            }
        Workbook workbook = null;
        workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Row row;
        Connect con = new Connect();
        Connection connection = con.getConnection();
        Statement st = null;
        ResultSet rs;
        String delete_employee_query = "UPDATE employee SET status = 0";
        /*String delete_contract_query = "DELETE FROM contract";
        String delete_function_query = "DELETE FROM function";
        String delete_observation_query = "DELETE FROM observation";
        String delete_employee_query = "DELETE FROM employee";*/
        UIManager.put("OptionPane.yesButtonText", "OUI");
        UIManager.put("OptionPane.noButtonText", "NON");
        int input = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer les données existantes?","",JOptionPane.YES_NO_OPTION);
        st = connection.createStatement();
        if (input == 0){          
            st.executeUpdate(delete_employee_query);
            JOptionPane.showMessageDialog(null, "Informations supprimées avec succès");
        }
        for (int i = 1; i <= firstSheet.getLastRowNum (); i++) {
            row=(Row) firstSheet.getRow(i);
            String registration_number = row.getCell(1).getStringCellValue();
            String surname = row.getCell(4).getStringCellValue();
            String name = row.getCell(5).getStringCellValue();
            String function = row.getCell(30).getStringCellValue();
            String verify_employee_query = "SELECT * FROM employee WHERE registration_number = '"+registration_number+"' AND status = 1";
            String insert_employee_query = "INSERT INTO employee(registration_number,surname, name, function, edited, status,  date_created, user_date_created, date_modified, user_date_modified) VALUES ('"+registration_number+"','"+surname+"','"+name+"','"+function+"',0,1,NOW(),'"+username+"',NOW(),'"+username+"')";
            rs = st.executeQuery(verify_employee_query);
            if(rs.next() == false){
                st.executeUpdate(insert_employee_query);
        }
        
    }
    JOptionPane.showMessageDialog(null, "Enregistrement terminé"); 
        workbook.close();
        inputStream.close();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Choisir un fichier");

        jButton2.setText("Importer");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(114, 114, 114)
                        .addComponent(jButton1)
                        .addGap(38, 38, 38)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton2)))
                .addContainerGap(137, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1))
                .addGap(68, 68, 68)
                .addComponent(jButton2)
                .addContainerGap(104, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(InfoReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoReader.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InfoReader().setVisible(true);
            }
        });
    }
    public void setUsername(String username){
        this.username =username;
    }
    public void setEmployeeInfos(EmpoyeeInfos info){
        this.info = info;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
    private File file;
    private String username;
    private EmpoyeeInfos info;
}
