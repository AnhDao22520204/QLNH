/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JDBC;

import Restaurant.Model.MONAN;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Admin
 */
public class MONANDAO {
      public static void save(MONAN monan) {
        String query = "INSERT INTO monan (MAMONAN, SOLUONG, TENMONAN, DVT, LOAI, GIA) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            statement.setInt(1, monan.getMAMONAN());
            statement.setInt(2, monan.getSOLUONG());
            statement.setString(3, monan.getTENMONAN());
            statement.setString(4, monan.getDVT());
            statement.setString(5, monan.getLOAI());
            statement.setInt(6, monan.getGIA());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm món ăn thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static ArrayList<MONAN> getAllRecords() {
        ArrayList<MONAN> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM MONAN");
            while (rs.next()) {
                MONAN monan = new MONAN();
                monan.setMAMONAN(rs.getInt("MAMONAN"));
                monan.setSOLUONG(rs.getInt("SOLUONG"));
                monan.setTENMONAN(rs.getString("TENMONAN"));
                monan.setDVT(rs.getString("DVT"));
                monan.setLOAI(rs.getString("LOAI"));
                monan.setGIA(rs.getInt("GIA"));
                arrayList.add(monan);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static void update(MONAN monan) {
        String query = "UPDATE MONAN SET soluong = ?, tenmonan = ?, dvt = ?, loai = ?, gia = ? WHERE mamonan = ?";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            
            statement.setInt(1, monan.getSOLUONG());
            statement.setString(2, monan.getTENMONAN());
            statement.setString(3, monan.getDVT());
            statement.setString(4, monan.getLOAI());
            statement.setInt(5, monan.getGIA());
            statement.setInt(6, monan.getMAMONAN());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật món ăn thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void delete(String mamonan) {
        String query = "DELETE FROM MONAN WHERE MAMONAN = ?";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            statement.setString(1, mamonan);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa món ăn thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static boolean isMAMONANTonTai(int MAMONAN) {
    try {
        String query = "SELECT * FROM MONAN WHERE MAMONAN = ?";
        PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
        statement.setInt(1, MAMONAN);
        ResultSet rs = statement.executeQuery();
        return rs.next(); // Nếu có kết quả trả về, tức là nhân viên tồn tại
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false; // Trả về false nếu có lỗi xảy ra
    }
}
}