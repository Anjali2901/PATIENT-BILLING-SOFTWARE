/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication23;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;

/**
 *
 * @author iiita
 */
public class Transfer extends javax.swing.JFrame {
    
    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    /**
     * Creates new form Transfer
     */
    public Transfer() {
        initComponents();
    }
    
    public String CurrentDate() {

        Calendar cal = new GregorianCalendar();
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return (day + "/" + (month + 1) + "/" + year);
        /*System.out.println("Current date : "
                + day + "/" + (month + 1) + "/" + year);*/
    }
    
    public int getIntDate(String str){
        int date=0, a=0,b=0,c=0;
        
        a=Integer.parseInt(str.substring(0,2));
        b=Integer.parseInt(str.substring(3,5));
        c=Integer.parseInt(str.substring(6,10));
        
        date = c*10000 + b *100 + a;
        
        return date;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        regNo = new javax.swing.JLabel();
        txtRegNo = new javax.swing.JTextField();
        btnTransfer = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        regNo.setText("Reg. No.");

        txtRegNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRegNoActionPerformed(evt);
            }
        });

        btnTransfer.setText("Transfer");
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });

        jLabel1.setText("Transfer Patient");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(regNo)
                        .addGap(90, 90, 90)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTransfer)
                            .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regNo)
                    .addComponent(txtRegNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(btnTransfer)
                .addContainerGap(170, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtRegNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRegNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRegNoActionPerformed

    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        // TODO add your handling code here:
        String last_date = "",current_Ward="", currentDate=CurrentDate();
        int days=0, transfers=0, General_days=0, ICU_days=0;
        
        Connect_db connect = new Connect_db();
        conn = connect.ConnecrDb();
        
        
        try{
            String sql = "select * from PatientDetails where RegNo = ?";
            pst = conn.prepareStatement(sql);
            pst.setString(1,txtRegNo.getText());
            
            pst.executeQuery();
            
            while(rs.next()){
                last_date = rs.getString("lastChangeDate");
                current_Ward = rs.getString("WardType");
                transfers = rs.getInt("NoofWardTrns");
                General_days = rs.getInt("daysGeneral");
                ICU_days = rs.getInt("daysICU");   
            }
            
            if(last_date.equals(currentDate)){
                days=1;
            }
            else{
                days = getIntDate(currentDate)-getIntDate(last_date);
            }
            
            if(current_Ward.equals("General")){
                sql = "update PatientDetails set WardType = ?, NoofWardTrns = ?, lastChangeDate = ?, daysGeneral = ? where RegNo = ?";
                
                pst = conn.prepareStatement(sql);
                
                pst.setString(1,"ICU");
                pst.setInt(2,transfers+1);
                pst.setString(3,currentDate);
                pst.setInt(4,days+General_days);
                pst.setString(5,txtRegNo.getText());
            }
            else{
                sql = "update PatientDetails set WardType = ?, NoofWardTrns = ?, lastChangeDate = ?, daysICU = ? where RegNo = ?";
                
                pst = conn.prepareStatement(sql);
                
                pst.setString(1,"General");
                pst.setInt(2,transfers+1);
                pst.setString(3,currentDate);
                pst.setInt(4,days+ICU_days);
                pst.setString(5,txtRegNo.getText());
            }
            
            
            
            
        }catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
       
        
    }//GEN-LAST:event_btnTransferActionPerformed

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
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transfer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTransfer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel regNo;
    private javax.swing.JTextField txtRegNo;
    // End of variables declaration//GEN-END:variables
}
