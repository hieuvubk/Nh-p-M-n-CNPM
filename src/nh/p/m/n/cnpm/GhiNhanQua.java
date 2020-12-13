/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nh.p.m.n.cnpm;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;
import java.util.Random;
import javax.swing.JOptionPane;
/**
 *
 * @author admin
 */
public class GhiNhanQua extends javax.swing.JFrame {

    /**
     * Creates new form GhiNhanQua
     */
    
    private String tensukien;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    PreparedStatement ps = null;
    
    public GhiNhanQua() {
        initComponents();
        setLocationRelativeTo(null);
    }
    
    public void GhiNhanQuaChoHS(){
        
        try {
            String dbURL = "jdbc:mysql://localhost:3306/nmcnpm";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            
            // lay thong tin hs
            
            String sql = "SELECT SoHoKhau, HoTen, ThanhTich FROM hocsinh ";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Vector data = null;
            Vector input = new Vector();
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(null, "Chưa có thông tin!");
                return;
            }
            while (rs.next()){
                data = new Vector();
                data.add(rs.getString("SoHoKhau"));
                data.add(rs.getString("HoTen"));
                data.add(rs.getString("ThanhTich"));
                input.add(data);
            }
            
            //lay thong tin phan qua
            
            String maqua[] = new String[3];
            rs = st.executeQuery("SELECT MaQua From qua WHERE Ten = 'Phần thưởng học sinh giỏi'");
            rs.next();
            maqua[0] = rs.getString("MaQua");
            rs = st.executeQuery("SELECT MaQua From qua WHERE Ten = 'Phần thưởng học sinh tiên tiến'");
            rs.next();
            maqua[1] = rs.getString("MaQua");
            rs = st.executeQuery("SELECT MaQua From qua WHERE Ten = 'Phần thưởng học sinh khá'");
            rs.next();
            maqua[2] = rs.getString("MaQua");
            
            // them ma su kien
            
            Date thoigian = new java.sql.Date(System.currentTimeMillis());
            String masukien = "CN" + java.util.Calendar.getInstance().get(Calendar.YEAR);;
            try{
                String query = "INSERT INTO sukien(MaSuKien, TenSuKien, ThoiGian) VALUES (?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(0, masukien);
                ps.setString(1, tensukien);
                ps.setDate(2, thoigian);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Đã ghi nhận!");
                return;
            }
            // them vao bang phat qua
            
            String ghinhan = "INSERT INTO phatqua(MaSuKien, HoTen, SoHoKhau, MaQua) VALUES (?,?,?,?)";
            
            while(!input.isEmpty()){
                Vector hocsinh = (Vector)input.remove(0);
                ps = conn.prepareStatement(ghinhan);
                ps.setString(0, masukien);
                ps.setString(1,(String)hocsinh.get(1));
                ps.setString(2,(String)hocsinh.get(0));
                if((String)hocsinh.get(2) == "Giỏi")
                    ps.setString(3,maqua[0]);
                else if((String)hocsinh.get(2) == "Tiên tiến")
                    ps.setString(3,maqua[1]);
                else if((String)hocsinh.get(2) == "Khá")
                    ps.setString(3,maqua[2]);
            }
            
            conn.close();
       } catch (Exception e) {
            e.printStackTrace();
       }
        
    }
    
    public void GhiNhanQuaChoTreEm(){
        
        try {
            String dbURL = "jdbc:mysql://localhost:3306/nmcnpm";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            
            // lay thong tin tre em
            
            String sql = "select SoHoKhau,HoTen  from nguoi "
                    + "where year(Now()) - year(NgaySinh) <= 18";
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            Vector data = null;
            Vector input = new Vector();
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(null, "Chưa có thông tin!");
                return;
            }
            while (rs.next()){
                data = new Vector();
                data.add(rs.getString("SoHoKhau"));
                data.add(rs.getString("HoTen"));
                input.add(data);
            }
            
            //lay thong tin phan qua
            
            Vector maqua = new Vector();
            rs = st.executeQuery("select MaQua from qua where Ten not in('Phần thưởng học sinh giỏi', "
                    + "'Phần thưởng học sinh tiên tiến', 'Phần thưởng học sinh khá')");
            while (rs.next()){
                maqua.add(rs.getString("MaQua"));
            }
            int n = maqua.size();
            Random index = new Random();
            // them ma su kien
            
            Date thoigian = new java.sql.Date(System.currentTimeMillis());
            String masukien = null;
            try{
                if(tensukien == "Trung thu"){
                    masukien = "TT" + java.util.Calendar.getInstance().get(Calendar.YEAR);
                }else if(tensukien == "Tết thiếu nhi"){
                    masukien = "TN" + java.util.Calendar.getInstance().get(Calendar.YEAR);
                }
                String query = "INSERT INTO sukien(MaSuKien, TenSuKien, ThoiGian) VALUES (?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(0, masukien);
                ps.setString(1, tensukien);
                ps.setDate(2, thoigian);
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Đã ghi nhận!");
                return;
            }
            
            // them vao bang phat qua
            
            String ghinhan = "INSERT INTO phatqua(MaSuKien, HoTen, SoHoKhau, MaQua) VALUES (?,?,?,?)";
            
            while(!input.isEmpty()){
                Vector hocsinh = (Vector)input.remove(0);
                ps = conn.prepareStatement(ghinhan);
                ps.setString(0, masukien);
                ps.setString(1,(String)hocsinh.get(1));
                ps.setString(2,(String)hocsinh.get(0));
                ps.setString(3,(String)maqua.get(index.nextInt(n)));
  
            }
            
            conn.close();
       } catch (Exception e) {
            e.printStackTrace();
       }
          
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ghi nhận phần quà");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Chọn sự kiện:");

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(3);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trung thu", "Tết thiếu nhi 1-6", "Cuối năm", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton1.setText("Cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jButton2.setText("Ghi nhận");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(59, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int index = jComboBox1.getSelectedIndex();
        this.tensukien = jComboBox1.getItemAt(index);
        //JOptionPane.showMessageDialog(null,suKien);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(this.tensukien == "Cuối năm")
            GhiNhanQuaChoHS();
        else
            GhiNhanQuaChoTreEm();
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
            java.util.logging.Logger.getLogger(GhiNhanQua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GhiNhanQua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GhiNhanQua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GhiNhanQua.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GhiNhanQua().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
