/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nh.p.m.n.cnpm;

import Bean.NhanKhauBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.NhanKhauModel;
import models.HoKhauModel;
/**
 *
 * @author DTV
 */
public class TreEmService {
    public List<NhankhauBean> getListTreEm() {
    List<NhankhauBean> list = new ArrayList<>();

    try{
    Connection connection = MysqlConnection.getMysqlConnection();
    String query = "SELECT ID,hoTen,gioiTinh, YEAR(CURRENT_TIMESTAMP) - YEAR(namSinh) as Tuoi "
    + "FROM nhan_khau WHERE YEAR(CURRENT_TIMESTAMP) - YEAR(namSinh) <= 18";
    PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
    ResultSet rs = preparedStatement.executeQuery();
    while (rs.next()){
                NhanKhauBean temp = new NhanKhauBean();
                NhanKhauModel hoKhauModel = temp.getNhanKhauModel();
                NhanKhauModel.setID(rs.getInt("ID"));
                NhanKhauModel.setHoTen(rs.getString("hoTen"));
                NhanKhauModel.setGioiTinh(rs.getString("gioiTinh"));
                }
            preparedStatement.close();
            connection.close();
} catch (Exception e) {
    System.out.println(e.getMessage());
}
return list;
}
}