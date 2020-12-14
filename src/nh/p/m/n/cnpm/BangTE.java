/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nh.p.m.n.cnpm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author DTV
 */
public class BangTE extends javax.swing.JFrame {

private String header[] = {"Họ tên", "Số hộ khẩu", "Ngày sinh"};
private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
    /**
     * Creates new form BangTE
     */
    public BangTE() {
        initComponents();
        setLocationRelativeTo(null);
        data();
    }
    public void data(){
            Connection conn = null;
            Statement st = null;
            ResultSet rs = null;
            try {
            String dbURL = "jdbc:mysql://localhost:3306/nmcnpm";
            String username = "root";
            String password = "";
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
            System.out.println("Kết nối thành công");
            }
            // Câu lệnh xem dữ liệu
            String sql = "select SoHoKhau, HoTen, NgaySinh"
                    + " from nguoi "
                    + "where year(Now()) - year(NgaySinh) <= 18";
            // Tạo đối tượng thực thi câu lệnh Select
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery(sql);
            Vector data = null;
            tblModel.setRowCount(0);
            // Nếu sách không tồn tại
           
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Chưa có thông tin");
                return;
           }
           // Trong khi chưa hết dữ liệu
           while (rs.next()) {
            data = new Vector();
            data.add(rs.getString("HoTen"));
            data.add(rs.getString("SoHoKhau"));
            data.add(rs.getDate("NgaySinh"));
            // Thêm một dòng vào table model
            tblModel.addRow(data);
           }
           jTable1.setModel(tblModel); // Thêm dữ liệu vào table
           conn.close();
          } catch (Exception e) {
            e.printStackTrace();
          }
    }
    
            
            
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh sách trẻ em dưới 18 tuổi");
        setBounds(new java.awt.Rectangle(4, 3, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Times New Roman", 0, 10)); // NOI18N
        setName("hoten"); // NOI18N
        setType(java.awt.Window.Type.UTILITY);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Họ tên", "Số hộ khẩu", "Ngày Sinh"
            }
        ));
        jTable1.setPreferredSize(new java.awt.Dimension(225, 317));
        jTable1.setRowHeight(25);
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(BangTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(BangTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(BangTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(BangTE.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new BangTE().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
