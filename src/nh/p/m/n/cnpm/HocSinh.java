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
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author hp
 */
public class HocSinh extends javax.swing.JFrame {

    private String header[] = {"Số hộ khẩu", "Họ tên","Ngày Sinh", "Địa chỉ", "Trường", "Lớp", "Thành tích"};
//    private String header[] = {"Số hộ khẩu", "Họ tên","Ngày Sinh", "Địa chỉ"};
    private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
    
    public HocSinh() throws ClassNotFoundException, SQLException {
        initComponents();
        Statement st = null;
        ResultSet rs = null;
        ResultSet dropRs = null;
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        String drop = "DELETE FROM hocsinh;";
        String sql = "SELECT * from nguoi where NgheNghiep like n'%Học Sinh%';";
        st = conn.createStatement();
//        dropSt = conn.createStatement();
        st.executeUpdate(drop);
        rs = st.executeQuery(sql);
        Vector data = null;
        tblModel.setRowCount(0);
        if(rs.isBeforeFirst() == false) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin yêu cầu");
            return;
        }
        while(rs.next()) {
            data = new Vector();
            data.add(rs.getString("SoHoKhau"));
            data.add(rs.getString("HoTen"));
            data.add(rs.getDate("NgaySinh"));
            data.add(rs.getString("DiaChi"));
            tblModel.addRow(data);
        }
        jTable1.setModel(tblModel);
        
    }
    
    public void save() throws ClassNotFoundException, SQLException {
        Connector connector = new Connector();
        Connection conn = connector.getConnection();
        PreparedStatement ps = null;
        conn.setAutoCommit(false);
        String query = "INSERT INTO hocsinh(SoHoKhau, HoTen, NgaySinh, DiaChi, Truong, Lop, ThanhTich) VALUES (?,?,?,?,?,?,?)";
        ps = conn.prepareStatement(query);
        int rows = tblModel.getRowCount();
        for(int i = 0; i<rows; i++) {
            String houseHoldId = (String) tblModel.getValueAt(i, 0);
            String name = (String) tblModel.getValueAt(i, 1);
            Date dob = (Date) tblModel.getValueAt(i, 2);
            String address = (String) tblModel.getValueAt(i, 3);
            String school = (String) tblModel.getValueAt(i, 4);
            String grade = (String) tblModel.getValueAt(i, 5);
            String achievement = (String) tblModel.getValueAt(i, 6);
            System.out.println(houseHoldId + " " + name + " " + dob + " " + address + " " + school + " " + grade + " " + achievement);
            try {
                
                ps.setString(1, houseHoldId);
                ps.setString(2, name);
                ps.setDate(3, dob);
                ps.setString(4, address);
                ps.setString(5, school);
                ps.setString(6, grade);
                ps.setString(7, achievement);
                
                ps.addBatch();
                
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        ps.executeBatch();
        conn.commit();
        conn.close();
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jFrame2 = new javax.swing.JFrame();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jFrame2Layout = new javax.swing.GroupLayout(jFrame2.getContentPane());
        jFrame2.getContentPane().setLayout(jFrame2Layout);
        jFrame2Layout.setHorizontalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame2Layout.setVerticalGroup(
            jFrame2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh sách học sinh");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Số hộ khẩu", "Họ tên", "Ngày sinh", "Địa chỉ", "Trường", "Lớp", "Danh hiệu"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {
            save();
            JOptionPane.showMessageDialog(null, "Đã lưu");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HocSinh.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HocSinh.class.getName()).log(Level.SEVERE, null, ex);
        }
    }                                        

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
            java.util.logging.Logger.getLogger(HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HocSinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new HocSinh().setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(HocSinh.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(HocSinh.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JFrame jFrame2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration                   
}

