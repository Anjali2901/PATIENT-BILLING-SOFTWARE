/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication23;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.time.Period;
/**
 *
 * @author iiita
 */
public class Dummy {
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    
    Dummy(){
        /*Connect_db connect = new Connect_db();
        conn = connect.ConnecrDb();
        
        
        String sql = "INSERT INTO BedDetail (BedNo, General, ICU)" + "VALUES (?, ?, ?)";
        
        try{
        for(int i=0; i<200 ; i++){
            pst = conn.prepareStatement(sql);
            pst.setInt(1, i+1);
            pst.setString(2, "Available");
            pst.setString(3, "Available");
            
            pst.executeUpdate();
            
        }
        conn.close();
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);

        }*/
    }
    
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
            java.util.logging.Logger.getLogger(Login_PROMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login_PROMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login_PROMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login_PROMA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        Dummy dm = new Dummy();
        
        String str1, str2;
        str1 = "11/10/2017";
        str2 = "10/10/2017";
        
        
        
        //LocalDate localDate1 = LocalDate.parse(str1);
        ///LocalDate localDate2 = LocalDate.parse(str2);
        
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy/MM/dd"); 
        
        Date date_old = (Date) formatter1.parse(str1);
        Date date_new = formatter2.parse(str2);
        
        Period intervalPeriod = Period.between(localDate1, localDate2);

        System.out.println("Difference of days: " + intervalPeriod.getDays());
    }
}
