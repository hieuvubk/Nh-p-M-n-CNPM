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
 * @author phongdao
 */
public class GhiNhanQua {
    
    private String tensukien;
    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;
    
    public GhiNhanQua(String sukien){
        tensukien = sukien;
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
            String query = "INSERT INTO sukien(MaSuKien, TenSuKien, ThoiGian) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(0, masukien);
            ps.setString(1, tensukien);
            ps.setDate(2, thoigian);
            
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
            if(tensukien == "Trung thu"){
                masukien = "TT" + java.util.Calendar.getInstance().get(Calendar.YEAR);
            }else if(tensukien == "Tết thiếu nhi"){
                masukien = "TN" + java.util.Calendar.getInstance().get(Calendar.YEAR);
            }
            String query = "INSERT INTO sukien(MaSuKien, TenSuKien, ThoiGian) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(0, masukien);
            ps.setString(1, tensukien);
            ps.setDate(2, thoigian);
            
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

    
}
