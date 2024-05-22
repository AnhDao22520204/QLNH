/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Controller.Service;

import JDBC.DbOperations;
import Restaurant.Model.NhanVien;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;

/**
 *
 * @author Admin
 */
public class NhanVienDAO {

    public static void save(NhanVien nhanvien) {
        String query = "INSERT INTO nhanvien(manv, hoten, diachi, ngvl, ngsinh, sodt) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            statement.setInt(1, nhanvien.getMaNV());
            statement.setString(2, nhanvien.getHoTen());
            statement.setString(3, nhanvien.getDiaChi());
            statement.setDate(4, nhanvien.getNgVaoLam()); // Đảm bảo ngày vào làm đã được định dạng đúng trong đối tượng NhanVien
            statement.setDate(5, nhanvien.getNgSinh()); // Đảm bảo ngày sinh đã được định dạng đúng trong đối tượng NhanVien
            statement.setString(6, nhanvien.getSDT());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static ArrayList<NhanVien> getAllRecords() {
        ArrayList<NhanVien> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM nhanvien");
            while (rs.next()) {
                NhanVien nhanvien = new NhanVien();
                nhanvien.setMaNV(rs.getInt("manv"));
                nhanvien.setHoTen(rs.getString("hoten"));
                nhanvien.setDiaChi(rs.getString("diachi"));
                nhanvien.setNgVaoLam(rs.getDate("ngvl"));
                nhanvien.setNgSinh(rs.getDate("ngsinh"));
                nhanvien.setSDT(rs.getString("sodt"));
                arrayList.add(nhanvien);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }

    public static void update(NhanVien nhanvien) {
        String query = "UPDATE nhanvien SET hoten = ?, ngsinh = ?, ngvl = ?, sodt = ?, diachi = ? WHERE manv = ?";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            statement.setString(1, nhanvien.getHoTen());
            statement.setDate(2, nhanvien.getNgSinh());
            statement.setDate(3, nhanvien.getNgVaoLam());
            statement.setString(4, nhanvien.getSDT());
            statement.setString(5, nhanvien.getDiaChi());
            statement.setInt(6, nhanvien.getMaNV());

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật nhân viên thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void delete(String manv) {
        String query = "DELETE FROM nhanvien WHERE manv = ?";
        try {
            PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
            statement.setString(1, manv);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public static boolean isMaNVTonTai(int maNV) {
    try {
        String query = "SELECT * FROM nhanvien WHERE manv = ?";
        PreparedStatement statement = DbOperations.getConnection().prepareStatement(query);
        statement.setInt(1, maNV);
        ResultSet rs = statement.executeQuery();
        return rs.next(); // Nếu có kết quả trả về, tức là nhân viên tồn tại
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false; // Trả về false nếu có lỗi xảy ra
    }
}
}

