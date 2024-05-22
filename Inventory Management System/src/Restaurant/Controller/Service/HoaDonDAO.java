        /*
         * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
         * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
         */
        package Restaurant.Controller.Service;
import JDBC.DbOperations;
        import Restaurant.Model.HoaDon;
        import javax.swing.*;
        import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;
        /**
         *
         * @author Admin
         */
        public class HoaDonDAO {
            public static void createNewHoaDon(int soHD, int maNV, int maBan, Date ngHD, int tongTien, String trangThai) {
        String query = "INSERT INTO hoadon(sohd, manv, maban, nghd, tongtien, trangthai) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = DbOperations.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, soHD);
            statement.setInt(2, maNV);

            statement.setInt(3, maBan);
            statement.setDate(4, ngHD);
            statement.setInt(5, tongTien);
            statement.setString(6, trangThai);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Thêm hóa đơn thành công");
            connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
 

             public static List<HoaDon> getAllHoaDons() {
                List<HoaDon> hoaDons = new ArrayList<>();
                String query = "SELECT * FROM hoadon ORDER BY sohd";
                try {
                    Connection connection = DbOperations.getConnection();
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(query);
                    while (rs.next()) {
                        HoaDon hoadon = new HoaDon();
                        hoadon.setSoHD(rs.getInt("sohd"));
                        hoadon.setMaNV(rs.getInt("manv"));

                        hoadon.setMaBan(rs.getInt("maban"));
                        hoadon.setNgHD(rs.getDate("nghd"));
                        hoadon.setTongTien(rs.getInt("tongtien"));
                        hoadon.setTrangThai(rs.getString("trangthai"));
                        hoaDons.add(hoadon);
                    }
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                return hoaDons;
            }

            public static HoaDon getHoaDonById(int soHD) {
                HoaDon hoadon = null;
                String query = "SELECT * FROM hoadon WHERE sohd = ?";
                try {
                    Connection connection = DbOperations.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, soHD);
                    ResultSet rs = statement.executeQuery();
                    if (rs.next()) {
                        hoadon = new HoaDon();
                        hoadon.setSoHD(rs.getInt("sohd"));
                        hoadon.setMaNV(rs.getInt("manv"));

                        hoadon.setMaBan(rs.getInt("maban"));
                        hoadon.setNgHD(rs.getDate("nghd"));
                        hoadon.setTongTien(rs.getInt("tongtien"));
                        hoadon.setTrangThai(rs.getString("trangthai"));
                    }
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                return hoadon;
            }

            public static List<HoaDon> searchHoaDons(String keyword) {
                List<HoaDon> hoaDons = new ArrayList<>();
                String query = "SELECT * FROM hoadon WHERE trangthai LIKE ?";
                try {
                    Connection connection = DbOperations.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, "%" + keyword + "%");
                    ResultSet rs = statement.executeQuery();
                    while (rs.next()) {
                        HoaDon hoadon = new HoaDon();
                        hoadon.setSoHD(rs.getInt("sohd"));
                        hoadon.setMaNV(rs.getInt("manv"));

                        hoadon.setMaBan(rs.getInt("maban"));
                        hoadon.setNgHD(rs.getDate("nghd"));
                        hoadon.setTongTien(rs.getInt("tongtien"));
                        hoadon.setTrangThai(rs.getString("trangthai"));
                        hoaDons.add(hoadon);
                    }
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
                return hoaDons;
            }

            public static void update(HoaDon hoadon) {
                String query = "UPDATE hoadon SET manv = ?, maban = ?, nghd = ?, tongtien = ?, trangthai = ? WHERE sohd = ?";
                try {
                    Connection connection = DbOperations.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, hoadon.getMaNV());

                    statement.setInt(2, hoadon.getMaBan());
                    statement.setDate(3, hoadon.getNgHD());
                    statement.setInt(4, hoadon.getTongTien());
                    statement.setString(5, hoadon.getTrangThai());
                    statement.setInt(6, hoadon.getSoHD());
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thành công");
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            public static void delete(int soHD) {
                String query = "DELETE FROM hoadon WHERE sohd = ?";
                try {
                    Connection connection = DbOperations.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, soHD);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công");
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            public static void payBill(int soHD) {
                String query = "UPDATE hoadon SET trangthai = 'Đã thanh toán' WHERE sohd = ?";
                try {
                    Connection connection = DbOperations.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setInt(1, soHD);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Thanh toán hóa đơn thành công");
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }

            public static void printBill(int soHD) {
                // Dummy print function. You need to implement actual printing logic.
                HoaDon hoadon = getHoaDonById(soHD);
                if (hoadon != null) {
                    System.out.println("Hóa Đơn ID: " + hoadon.getSoHD());
                    System.out.println("Mã Nhân Viên: " + hoadon.getMaNV());

                    System.out.println("Mã Bàn: " + hoadon.getMaBan());
                    System.out.println("Ngày Hóa Đơn: " + hoadon.getNgHD());
                    System.out.println("Tổng Tiền: " + hoadon.getTongTien());
                    System.out.println("Trạng Thái: " + hoadon.getTrangThai());
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn");
                }
            }

            public static void updateStatus(int soHD, String trangThai) {
                String query = "UPDATE hoadon SET trangthai = ? WHERE sohd = ?";
                try {
                    Connection connection = DbOperations.getConnection();
                    PreparedStatement statement = connection.prepareStatement(query);
                    statement.setString(1, trangThai);
                    statement.setInt(2, soHD);
                    statement.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Cập nhật trạng thái thành công");
                    connection.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            public static void updateTongTien(int soHD) {
    String query = "UPDATE hoadon SET tongtien = (SELECT SUM(thanhtien) FROM cthd WHERE sohd = ?) WHERE sohd = ?";
    try (Connection con = DbOperations.getConnection();
         PreparedStatement stmt = con.prepareStatement(query)) {
        stmt.setInt(1, soHD);
        stmt.setInt(2, soHD);
        stmt.executeUpdate();
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
    }
}
            

  


        }


