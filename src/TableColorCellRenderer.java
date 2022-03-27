
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author M TRAORE
 */
public class TableColorCellRenderer implements TableCellRenderer{
         private static final TableCellRenderer RENDERER = new DefaultTableCellRenderer();
         ArrayList<Employee> list;
         public TableColorCellRenderer (ArrayList<Employee> list){
             this.list= list;
         }
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component c = RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus,row,column);
            if(column == 0){
                Color color = null;
                Object registration_number = table.getModel().getValueAt(row, column);
                Employee employee = null;
                for (int i = 0; i < list.size(); i++){
                    if(list.get(i).getRegistration_number() == registration_number.toString())
                        employee  = list.get(i);
                }
                int j = list.indexOf(employee);
                if(list.get(j).getEdited()== 1)
                    color = Color.RED;
                    //color = table.getForegnd();
                else 
                    //color = Color.RED;
                    color = table.getForeground();
                c.setForeground(color);
            } else
                c.setForeground(table.getForeground());
            return c;
        }
         
     }
