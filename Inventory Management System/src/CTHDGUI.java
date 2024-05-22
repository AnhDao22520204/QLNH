
import Restaurant.Controller.Service.CTHDDAO;
import Restaurant.Model.CTHD;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class CTHDGUI extends JFrame {

    private JTable cthdTable;
    private DefaultTableModel cthdTableModel;
    private int soHD;

    public CTHDGUI(int soHD) {
        this.soHD = soHD;
        setTitle("Chi Tiết Hóa Đơn - Số Hóa Đơn: " + soHD);
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadCTHDs();
    }

    private void initComponents() {
        cthdTableModel = new DefaultTableModel();
        cthdTableModel.addColumn("Mã Món Ăn");
        cthdTableModel.addColumn("Số Hóa Đơn");
        cthdTableModel.addColumn("Số Lượng");
        cthdTableModel.addColumn("Thành Tiền");

        cthdTable = new JTable(cthdTableModel);
        JScrollPane scrollPane = new JScrollPane(cthdTable);

        JButton updateButton = new JButton("Update");
        JButton addButton = new JButton("Add");
                JButton deleteButton = new JButton("Delete"); // Thêm button Xóa


        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCTHD();
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addCTHD();
            }
        });
      deleteButton.addActionListener(new ActionListener() { // Xử lý sự kiện khi button Xóa được nhấn
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCTHD();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(updateButton);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void loadCTHDs() {
        cthdTableModel.setRowCount(0);
        List<CTHD> cthds = CTHDDAO.getCTHDsBySoHD(soHD);
        for (CTHD cthd : cthds) {
            cthdTableModel.addRow(new Object[]{
                cthd.getMaMonAn(),
                cthd.getSoHD(),
                cthd.getSoLuong(),
                cthd.getThanhTien()
            });
        }
    }

    private void updateCTHD() {
        int selectedRow = cthdTable.getSelectedRow();
        if (selectedRow != -1) {
            int maMonAn = (int) cthdTable.getValueAt(selectedRow, 0);
            int soLuong = Integer.parseInt(JOptionPane.showInputDialog("Nhập số lượng mới:"));
            int thanhTien = Integer.parseInt(JOptionPane.showInputDialog("Nhập thành tiền mới:"));

            CTHD cthd = new CTHD();
            cthd.setMaMonAn(maMonAn);
            cthd.setSoHD(soHD);
            cthd.setSoLuong(soLuong);
            cthd.setThanhTien(thanhTien);

            CTHDDAO.update(cthd);
            loadCTHDs();
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một chi tiết hóa đơn để cập nhật.");
        }
    }

private void addCTHD() {
    JTextField maMonAnField = new JTextField(10);
    JTextField soLuongField = new JTextField(10);

    JPanel inputPanel = new JPanel(new GridLayout(2, 2));
    inputPanel.add(new JLabel("Mã Món Ăn:"));
    inputPanel.add(maMonAnField);
    inputPanel.add(new JLabel("Số Lượng:"));
    inputPanel.add(soLuongField);

    int result = JOptionPane.showConfirmDialog(null, inputPanel, "Thêm Chi Tiết Hóa Đơn", JOptionPane.OK_CANCEL_OPTION);
    if (result == JOptionPane.OK_OPTION) {
        try {
            int maMonAn = Integer.parseInt(maMonAnField.getText());
            int soLuong = Integer.parseInt(soLuongField.getText());

            // Kiểm tra sự tồn tại của mã món ăn
            if (!CTHDDAO.isMaMonAnExist(maMonAn)) {
                JOptionPane.showMessageDialog(null, "Mã món ăn không tồn tại.");
                return;
            }

            // Lấy giá của món ăn từ cơ sở dữ liệu
            int giaMonAn = CTHDDAO.getGiaMonAn(maMonAn);
            
            // Tính toán thành tiền
            int thanhTien = giaMonAn * soLuong;

            // Tạo chi tiết hóa đơn mới và lưu vào cơ sở dữ liệu
            CTHD newCTHD = new CTHD();
            newCTHD.setMaMonAn(maMonAn);
            newCTHD.setSoHD(soHD);
            newCTHD.setSoLuong(soLuong);
            newCTHD.setThanhTien(thanhTien);

            CTHDDAO.createNewCTHD(maMonAn, soHD, soLuong, thanhTien);

            // Hiển thị thông báo thành công với tổng tiền
            JOptionPane.showMessageDialog(null, "Thêm chi tiết hóa đơn thành công.\nThành Tiền: " + thanhTien);

            // Thêm chi tiết hóa đơn mới vào bảng
            cthdTableModel.addRow(new Object[]{
                newCTHD.getMaMonAn(),
                newCTHD.getSoHD(),
                newCTHD.getSoLuong(),
                newCTHD.getThanhTien()
            });

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số.");
        }
    }
}
 private void deleteCTHD() { // Phương thức xử lý khi button Xóa được nhấn
        int selectedRow = cthdTable.getSelectedRow();
        if (selectedRow != -1) {
            int maMonAn = (int) cthdTable.getValueAt(selectedRow, 0);
            int soHD = (int) cthdTable.getValueAt(selectedRow, 1);

            CTHDDAO.delete(maMonAn, soHD);
            loadCTHDs(); // Load lại danh sách CTHD sau khi xóa
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một chi tiết hóa đơn để xóa.");
        }
    }

}
