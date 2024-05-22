package Restaurant.Controller.Service;

import JDBC.DbOperations;
import Restaurant.Model.CTHD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class CTHDDAO {
    public static void createNewCTHD(int maMonAn, int soHD, int soLuong, int thanhTien) {
        String query = "INSERT INTO cthd(mamonan, sohd, soluong, thanhtien) VALUES (?, ?, ?, ?)";
        try {
            Connection connection = DbOperations.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, maMonAn);
            statement.setInt(2, soHD);
            statement.setInt(3, soLuong);
            statement.setInt(4, thanhTien);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thành công");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static List<CTHD> getAllCTHDs() {
        List<CTHD> cthds = new ArrayList<>();
        String query = "SELECT * FROM cthd";
        try {
            Connection connection = DbOperations.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                CTHD cthd = new CTHD();
                cthd.setMaMonAn(rs.getInt("mamonan"));
                cthd.setSoHD(rs.getInt("sohd"));
                cthd.setSoLuong(rs.getInt("soluong"));
                cthd.setThanhTien(rs.getInt("thanhtien"));
                cthds.add(cthd);
            }
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return cthds;
    }

    public static void update(CTHD cthd) {
        String query = "UPDATE cthd SET soluong = ?, thanhtien = ? WHERE mamonan = ? AND sohd = ?";
        try {
            Connection connection = DbOperations.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, cthd.getSoLuong());
            statement.setInt(2, cthd.getThanhTien());
            statement.setInt(3, cthd.getMaMonAn());
            statement.setInt(4, cthd.getSoHD());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật chi tiết hóa đơn thành công");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public static void delete(int maMonAn, int soHD) {
        String query = "DELETE FROM cthd WHERE mamonan = ? AND sohd = ?";
        try {
            Connection connection = DbOperations.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, maMonAn);
            statement.setInt(2, soHD);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Xóa chi tiết hóa đơn thành công");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    public static List<CTHD> getCTHDsBySoHD(int soHD) {
    List<CTHD> cthds = new ArrayList<>();
    String query = "SELECT * FROM cthd WHERE sohd = ?";
    try {
        Connection connection = DbOperations.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, soHD);
        ResultSet rs = statement.executeQuery();
        while (rs.next()) {
            CTHD cthd = new CTHD();
            cthd.setMaMonAn(rs.getInt("mamonan"));
            cthd.setSoHD(rs.getInt("sohd"));
            cthd.setSoLuong(rs.getInt("soluong"));
            cthd.setThanhTien(rs.getInt("thanhtien"));
            cthds.add(cthd);
        }
        connection.close();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
    return cthds;
}
     public static boolean isMaMonAnExist(int maMonAn) {
        boolean exists = false;
        String query = "SELECT COUNT(*) FROM MONAN WHERE MAMONAN = ?";
        try (Connection con = DbOperations.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setInt(1, maMonAn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    exists = rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }
     public static int getGiaMonAn(int maMonAn) {
    int giaMonAn = 0;
    String query = "SELECT GIA FROM MONAN WHERE MAMONAN = ?";
    try (Connection con = DbOperations.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, maMonAn);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                giaMonAn = rs.getInt("GIA");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return giaMonAn;
}
}