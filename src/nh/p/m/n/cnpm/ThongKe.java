/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nh.p.m.n.cnpm;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ThongKe extends javax.swing.JFrame {

    /**
     * Creates new form ThongKe
     */
    
    private String header[] = {"Tổng phần quà", "Tổng giá trị"};
    private DefaultTableModel tblModel = new DefaultTableModel(header, 0);
    private String suKien;
    private String nam;
    
    public ThongKe() {
        
        initComponents();
        setLocationRelativeTo(null);
        
    }
    
    public void getData(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/b2_db";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            // Câu lệnh xem dữ liệu
            String sql = "select count(maQua) 'Tổng phần quà', sum(giaTri) 'Tổng giá trị' "
                    + "from 'phatthuong' inner join 'sukien' "
                    + "on 'phatthuong'.'masukien' = 'sukien'.'masukien'"
                    + "where 'sukien' = " +suKien+ " and year('thoigian') = "+nam
                    + "group by masukien";
            // Tạo đối tượng thực thi câu lệnh Select
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery(sql);
            Vector data = null;
            tblModel.setRowCount(0);
            // Nếu sách không tồn tại
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Chưa có thông tin!");
                return;
            }
            // Trong khi chưa hết dữ liệu
            while (rs.next()) {
                data = new Vector();
                data.add(rs.getInt("Tổng phần quà"));
                data.add(rs.getInt("Tổng giá trị"));

                // Thêm một dòng vào table model
                tblModel.addRow(data);
            }
            jTable1.setModel(tblModel); // Thêm dữ liệu vào table
       } catch (Exception e) {
            e.printStackTrace();
       }
    }
    
    public void getDataCuoiNam(){
        String title[] = {"Loại quà", "Tổng phần quà", "Tổng giá trị"};
        DefaultTableModel CuoiNam = new DefaultTableModel(title, 0);
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            String dbURL = "jdbc:mysql://localhost:3306/b2_db";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, username, password);
            if (conn != null) {
                System.out.println("Kết nối thành công");
            }
            // Câu lệnh xem dữ liệu
            String sql = "select tenQua 'Loại quà', count(soHoKhau) 'Tổng phần quà', sum(giaTri) 'Tổng giá trị' "
                    + "from 'phatthuong' inner join 'sukien' "
                    + "on 'phatthuong'.'masukien' = 'sukien'.'masukien', Qua"
                    + "where 'sukien' = " +suKien+ " and year('thoigian') = "+nam
                    + " and 'phatthuong'.'maqua' = Qua.'maqua'"
                    + "group by masukien, tenQua";
            // Tạo đối tượng thực thi câu lệnh Select
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery(sql);
            Vector data = null;
            CuoiNam.setRowCount(0);
            // Nếu sách không tồn tại
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Chưa có thông tin!");
                return;
            }
            // Trong khi chưa hết dữ liệu
            while (rs.next()) {
                data = new Vector();
                data.add(rs.getString("Loại quà"));
                data.add(rs.getInt("Tổng phần quà"));
                data.add(rs.getInt("Tổng giá trị"));

                // Thêm một dòng vào table model
                CuoiNam.addRow(data);
            }
            jTable1.setModel(CuoiNam); // Thêm dữ liệu vào table
            
            //in tong gia tri cac phan qua
            jLabel4.setOpaque(true);
            jLabel3.setText("Tổng giá trị:");
            String tong = "select sum(giaTri) 'Tổng giá trị' "
                    + "from 'phatthuong' inner join 'sukien' "
                    + "on 'phatthuong'.'masukien' = 'sukien'.'masukien', Qua"
                    + "where 'sukien' = " +suKien+ " and year('thoigian') = "+nam
                    + "group by masukien";
            // Tạo đối tượng thực thi câu lệnh Select
            st = conn.createStatement();
            // Thực thi
            rs = st.executeQuery(tong);
            rs.next();
            jLabel4.setText(rs.getString("Tổng giá trị"));
            
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thông kê phần quà");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));

        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(240, 240, 240));

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "   "
            }
        ));
        jTable1.setAlignmentX(1.0F);
        jTable1.setAlignmentY(1.0F);
        jTable1.setGridColor(new java.awt.Color(0, 51, 51));
        jTable1.setPreferredSize(new java.awt.Dimension(225, 164));
        jTable1.setRequestFocusEnabled(false);
        jTable1.setRowHeight(25);
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox1.setMaximumRowCount(3);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trung thu", "Tết thiếu nhi 1-6", "Cuối năm", " " }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jComboBox3.setMaximumRowCount(5);
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", " " }));
        jComboBox3.setSelectedIndex(20);
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText(" Sự kiện:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jLabel2.setText(" Năm");

        jButton1.setFont(new java.awt.Font("Times New Roman", 1, 17)); // NOI18N
        jButton1.setText("Lọc");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(29, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jButton1)
                        .addGap(44, 44, 44))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        int index = jComboBox1.getSelectedIndex();
        this.suKien = jComboBox1.getItemAt(index);
        //JOptionPane.showMessageDialog(null,suKien);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
        int index = jComboBox3.getSelectedIndex();
        this.nam = jComboBox3.getItemAt(index);
        //JOptionPane.showMessageDialog(null,nam);
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(suKien == "Cuối năm"){
            getDataCuoiNam();
        }
        else{
            getData();
        }
            
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThongKe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
