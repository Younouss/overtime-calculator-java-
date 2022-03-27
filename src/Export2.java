
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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
 * @author h
 */
public class Export2 extends javax.swing.JFrame {
    File file;
    Path path;
    private JTable jTable1;
    /**
     * Creates new form Export1
     */
    public Export2() {
        initComponents();
        jButton1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){  
                JFileChooser c = new JFileChooser();
                int rVal = c.showOpenDialog(Export2.this);
                if (rVal == JFileChooser.APPROVE_OPTION) {
                   file = new File("");
                   file = c.getSelectedFile(); 
                   jLabel1.setText(file.getName());
                   path = Paths.get(file.getPath());
                } 
         }
        }); 
        jButton2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    export2();
                    //JOptionPane.showMessageDialog(null, "Données exportées avec succès");
                } catch (IOException ex) {
                    Logger.getLogger(Export2.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, "Problème lors de l'exportation des données");
                }
            }
        });
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }
    public void getJTable_Display_Employee(JTable jTable1){
        this.jTable1 = jTable1;
    }
    public void export2() throws IOException{
        new WorkbookFactory();
        //Workbook wb = new XSSFWorkbook();
        InputStream input = new FileInputStream(file);
        //HSSFWorkbook wb = new HSSFWorkbook(input);
        Workbook wb = new XSSFWorkbook(input);
        Sheet sheet = wb.getSheetAt(0);
        TableModel model = jTable1.getModel();
        
            for (Row row : sheet) {
                for (Cell cell : row) {
                    //Iterator<Cell> cellIterator = row.cellIterator();
                    for(int rows = 0; rows < model.getRowCount(); rows++){
                    if(cell.getCellType() == CellType.STRING ){
                        Cell cell2 = row.getCell(2);
                    if (cell.getRichStringCellValue().getString().trim().equals(model.getValueAt(rows, 0).toString()) && cell2.getNumericCellValue() == 1401.0) {
                        //System.out.println("found");
                        int rowNumber = row.getRowNum(); 
                        Row row2 = sheet.getRow(rowNumber);
                        row2.createCell(3).setCellValue(model.getValueAt(rows, 4).toString());
                    }
                    if (cell.getRichStringCellValue().getString().trim().equals(model.getValueAt(rows, 0).toString()) && cell2.getNumericCellValue() == 1402.0) {
                        //System.out.println("found");
                        int rowNumber = row.getRowNum(); 
                        Row row2 = sheet.getRow(rowNumber);
                        row2.createCell(3).setCellValue(model.getValueAt(rows, 5).toString());
                    }
                    if (cell.getRichStringCellValue().getString().trim().equals(model.getValueAt(rows, 0).toString()) && cell2.getNumericCellValue() == 1403.0) {
                        //System.out.println("found");
                        int rowNumber = row.getRowNum(); 
                        Row row2 = sheet.getRow(rowNumber);
                        row2.createCell(3).setCellValue(model.getValueAt(rows, 6).toString());
                    }
                    if (cell.getRichStringCellValue().getString().trim().equals(model.getValueAt(rows, 0).toString()) && cell2.getNumericCellValue() == 1404.0) {
                        //System.out.println("found");
                        int rowNumber = row.getRowNum(); 
                        Row row2 = sheet.getRow(rowNumber);
                        row2.createCell(3).setCellValue(model.getValueAt(rows, 7).toString());
                    }
                    }
                    }
                }
            }
        
        wb.write(new FileOutputStream(file));
        JOptionPane.showMessageDialog(null, "Données exportées ");
    }
    /**
                    }
                }
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton2.setText("Exporter");

        jLabel1.setText("fichier");

        jButton1.setText("Choisir un fichier");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel1)))
                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(66, 66, 66)
                .addComponent(jButton2)
                .addContainerGap(128, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Export2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Export2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Export2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Export2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Export2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
