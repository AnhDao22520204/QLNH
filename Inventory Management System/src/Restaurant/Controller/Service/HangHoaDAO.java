/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import JDBC.DbOperations;
import Restaurant.Model.HangHoa;
import java.util.ArrayList;
import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class HangHoaDAO {
      public static void save(HangHoa hanghoa)
    {  
        String query = "INSERT INTO hanghoa(tenhh, soluong, gia, nhacungcap, hsd) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection connection = DbOperations.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, hanghoa.getTenHH());
            statement.setInt(2, hanghoa.getSoLuong());
            statement.setInt(3, hanghoa.getGia());
            statement.setString(4, hanghoa.getNhaCungCap());
            statement.setDate(5, hanghoa.getHSD());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm hàng hóa thành công");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static   ArrayList<HangHoa>  getAllRecords()
    {   ArrayList<HangHoa>  arrayList = new ArrayList<>();
        try{    ResultSet rs = DbOperations.getData("select * from hanghoa");
            while(rs.next())
            {   HangHoa hanghoa = new HangHoa();
                hanghoa.setMaHH(rs.getInt("mahh"));
                hanghoa.setTenHH(rs.getString("tenhh"));
                 hanghoa.setSoLuong(rs.getInt("soluong"));
               hanghoa.setGia(rs.getInt("gia"));
               hanghoa.setNhaCungCap(rs.getString("nhacungcap"));
               hanghoa.setHSD(rs.getDate("hsd"));
               arrayList.add(hanghoa);
                  
                
            
            }
        }
        catch(Exception e)
        {   JOptionPane.showMessageDialog(null, e);
            }
        return arrayList;
    }
    public static void delete(String maHH)
    {   String query = "DELETE FROM hanghoa WHERE mahh = ?";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            statement.setString(1, maHH);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    
    
}
      public static void update(HangHoa hanghoa) {
    String query = "UPDATE hanghoa SET mahh = ?, tenhh = ?, soluong = ?, gia = ?, nhacungcap = ?, hsd =? WHERE mahh = ?";
    try {
        PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
        statement.setInt(1, hanghoa.getMaHH());
        statement.setString(2, hanghoa.getTenHH());
        statement.setInt(3, hanghoa.getSoLuong());
        statement.setInt(4, hanghoa.getGia());
        statement.setString(5, hanghoa.getNhaCungCap());
        statement.setDate(6, hanghoa.getHSD());
        statement.setInt(7, hanghoa.getMaHH());

        statement.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cập nhật hàng hóa thành công");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
    }
}

}