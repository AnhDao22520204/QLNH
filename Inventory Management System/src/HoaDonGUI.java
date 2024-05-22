
import Restaurant.Controller.Service.CTHDDAO;
import Restaurant.Controller.Service.HoaDonDAO;
import Restaurant.Model.CTHD;
import Restaurant.Model.HoaDon;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class HoaDonGUI extends JFrame {
    
    private JTable hoaDonTable;
    private DefaultTableModel hoaDonTableModel;
    private DefaultListModel<HoaDon> hoaDonListModel;
    private JList<HoaDon> hoaDonJList;
    private JButton refreshButton;
    private JButton viewDetailsButton;
    private JButton createButton;
    private JButton editButton;
    private JButton payButton;
    private JButton printButton;
    private JButton updateStatusButton;
    private JTextField searchField;
    private JButton searchButton;
    private JButton deleteButton;

    public HoaDonGUI() {
        setTitle("Quản lý hóa đơn");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
        loadHoaDons();
    }

    private void initComponents() {
        hoaDonListModel = new DefaultListModel<>();
        hoaDonJList = new JList<>(hoaDonListModel);
        JScrollPane scrollPane = new JScrollPane(hoaDonJList);
       
        refreshButton = new JButton("Refresh");
        viewDetailsButton = new JButton("View Details");
        createButton = new JButton("Create New");
        editButton = new JButton("Edit");
        payButton = new JButton("Pay");
        printButton = new JButton("Print");
        updateStatusButton = new JButton("Update Status");
        searchButton = new JButton("Search");
        deleteButton= new JButton("Delete");

        searchField = new JTextField(20);
        searchField.setToolTipText("Search");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(viewDetailsButton);
        buttonPanel.add(createButton);
        buttonPanel.add(editButton);
        buttonPanel.add(payButton);
        buttonPanel.add(printButton);
        buttonPanel.add(updateStatusButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchField);
        

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        // Khởi tạo model cho JTable
        hoaDonTableModel = new DefaultTableModel();
        hoaDonTableModel.addColumn("Số Hóa Đơn");
        hoaDonTableModel.addColumn("Mã Nhân Viên");
        hoaDonTableModel.addColumn("Mã Bàn");
        hoaDonTableModel.addColumn("Ngày Hóa Đơn");
        hoaDonTableModel.addColumn("Tổng Tiền");
        hoaDonTableModel.addColumn("Trạng Thái");

        // Khởi tạo JTable với model
        hoaDonTable = new JTable(hoaDonTableModel);

        // Tạo thanh cuộn cho JTable nếu cần
        JScrollPane tableScrollPane = new JScrollPane(hoaDonTable);

        // Thêm JTable vào giao diện
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadHoaDons();
            }
        });

       viewDetailsButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = hoaDonTable.getSelectedRow();
        if (selectedRow != -1) {
            int soHD = (int) hoaDonTable.getValueAt(selectedRow, 0);
            // Mở cửa sổ chi tiết hóa đơn
            CTHDGUI cthdGUI = new CTHDGUI(soHD);
            cthdGUI.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để xem chi tiết.");
        }
    }
});
    createButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextField soHDField = new JTextField(10);
        JTextField maNVField = new JTextField(10);
        JTextField maBanField = new JTextField(10);
        JTextField ngHDField = new JTextField(10);
        JTextField tongTienField = new JTextField(10);
        JTextField trangThaiField = new JTextField(10);

        JPanel inputPanel = new JPanel(new GridLayout(7, 2));
        inputPanel.add(new JLabel("Số Hóa Đơn:"));
        inputPanel.add(soHDField);
        inputPanel.add(new JLabel("Mã Nhân Viên:"));
        inputPanel.add(maNVField);
        inputPanel.add(new JLabel("Mã Bàn:"));
        inputPanel.add(maBanField);
        inputPanel.add(new JLabel("Ngày Hóa Đơn (yyyy-mm-dd):"));
        inputPanel.add(ngHDField);
        inputPanel.add(new JLabel("Tổng Tiền:"));
        inputPanel.add(tongTienField);
        inputPanel.add(new JLabel("Trạng Thái:"));
        inputPanel.add(trangThaiField);

        int result = JOptionPane.showConfirmDialog(null, inputPanel,
                "Thêm Hóa Đơn Mới", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                int soHD = Integer.parseInt(soHDField.getText());
                int maNV = Integer.parseInt(maNVField.getText());
                int maBan = Integer.parseInt(maBanField.getText());
                Date ngHD = Date.valueOf(ngHDField.getText());
                int tongTien = Integer.parseInt(tongTienField.getText());
                String trangThai = trangThaiField.getText();

                HoaDonDAO.createNewHoaDon(soHD, maNV, maBan, ngHD, tongTien, trangThai);
                
                // Load lại danh sách hóa đơn
                loadHoaDons();
                
           
            
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số.");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày (yyyy-mm-dd).");
            }
        }
    }
});
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = hoaDonTable.getSelectedRow();
                if (selectedRow != -1) {
                    int soHD = (int) hoaDonTable.getValueAt(selectedRow, 0);
                    // Truy vấn cơ sở dữ liệu để lấy thông tin chi tiết của hóa đơn dựa trên số hóa đơn
                    HoaDon hoaDon = HoaDonDAO.getHoaDonById(soHD);
                    if (hoaDon != null) {
                        // Hiển thị thông tin chi tiết trong các trường nhập liệu để chỉnh sửa
                        JTextField maNVField = new JTextField(String.valueOf(hoaDon.getMaNV()), 10);
                        JTextField maBanField = new JTextField(String.valueOf(hoaDon.getMaBan()), 10);
                        JTextField ngHDField = new JTextField(hoaDon.getNgHD().toString(), 10);
                        JTextField tongTienField = new JTextField(String.valueOf(hoaDon.getTongTien()), 10);
                        JTextField trangThaiField = new JTextField(hoaDon.getTrangThai(), 10);

                        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
                        inputPanel.add(new JLabel("Mã Nhân Viên:"));
                        inputPanel.add(maNVField);
                        inputPanel.add(new JLabel("Mã Bàn:"));
                        inputPanel.add(maBanField);
                        inputPanel.add(new JLabel("Ngày Hóa Đơn (yyyy-mm-dd):"));
                        inputPanel.add(ngHDField);
                        inputPanel.add(new JLabel("Tổng Tiền:"));
                        inputPanel.add(tongTienField);
                        inputPanel.add(new JLabel("Trạng Thái:"));
                        inputPanel.add(trangThaiField);

                        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Chỉnh Sửa Hóa Đơn", JOptionPane.OK_CANCEL_OPTION);
                        if (result == JOptionPane.OK_OPTION) {
                            try {
                                int newMaNV = Integer.parseInt(maNVField.getText());
                                int newMaBan = Integer.parseInt(maBanField.getText());
                                Date newNgHD = Date.valueOf(ngHDField.getText());
                                int newTongTien = Integer.parseInt(tongTienField.getText());
                                String newTrangThai = trangThaiField.getText();

                                // Cập nhật thông tin của hóa đơn trong cơ sở dữ liệu
                                HoaDon updatedHoaDon = new HoaDon();
                                updatedHoaDon.setSoHD(soHD);
                                updatedHoaDon.setMaNV(newMaNV);
                                updatedHoaDon.setMaBan(newMaBan);
                                updatedHoaDon.setNgHD(newNgHD);
                                updatedHoaDon.setTongTien(newTongTien);
                                updatedHoaDon.setTrangThai(newTrangThai);
                                // Gọi phương thức cập nhật của HoaDonDAO
                                HoaDonDAO.update(updatedHoaDon);

                                // Cập nhật thông tin của hóa đơn trong JTable
                                hoaDonTable.setValueAt(newMaNV, selectedRow, 1);
                                hoaDonTable.setValueAt(newMaBan, selectedRow, 2);
                                hoaDonTable.setValueAt(newNgHD, selectedRow, 3);
                                hoaDonTable.setValueAt(newTongTien, selectedRow, 4);
                                hoaDonTable.setValueAt(newTrangThai, selectedRow, 5);

                                JOptionPane.showMessageDialog(null, "Cập nhật hóa đơn thành công.");
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng số.");
                            } catch (IllegalArgumentException ex) {
                                JOptionPane.showMessageDialog(null, "Vui lòng nhập đúng định dạng ngày (yyyy-mm-dd).");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Không thể tìm thấy hóa đơn trong cơ sở dữ liệu.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để chỉnh sửa.");
                }
            }
        });
   searchButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Hiển thị giao diện nhập mã hóa đơn
        String input = JOptionPane.showInputDialog(null, "Nhập mã hóa đơn:");

        // Kiểm tra nếu người dùng đã nhập mã hóa đơn và không hủy bỏ
        if (input != null && !input.isEmpty()) {
            try {
                // Chuyển đổi mã hóa đơn thành kiểu số nguyên
                int soHD = Integer.parseInt(input);
                
                // Tìm kiếm hóa đơn trong cơ sở dữ liệu
                HoaDon hoaDon = HoaDonDAO.getHoaDonById(soHD);
                
                // Kiểm tra xem hóa đơn có tồn tại không
                if (hoaDon != null) {
                    // Xóa dữ liệu hiện có trong JTable
                    hoaDonTableModel.setRowCount(0);
                    
                    // Thêm hóa đơn tìm thấy vào JTable
                    hoaDonTableModel.addRow(new Object[]{
                        hoaDon.getSoHD(),
                        hoaDon.getMaNV(),
                        hoaDon.getMaBan(),
                        hoaDon.getNgHD(),
                        hoaDon.getTongTien(),
                        hoaDon.getTrangThai()
                    });
                    
                    // Hiển thị thông báo
                    JOptionPane.showMessageDialog(null, "Đã tìm thấy hóa đơn.");
                } else {
                    // Hiển thị thông báo nếu không tìm thấy hóa đơn
                    JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn với mã " + soHD);
                }
            } catch (NumberFormatException ex) {
                // Hiển thị thông báo nếu người dùng nhập không đúng định dạng
                JOptionPane.showMessageDialog(null, "Vui lòng nhập mã hóa đơn dưới dạng số.");
            }
        }
    }
});
   updateStatusButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRow = hoaDonTable.getSelectedRow();
        if (selectedRow != -1) {
            int soHD = (int) hoaDonTable.getValueAt(selectedRow, 0);
            String currentStatus = (String) hoaDonTable.getValueAt(selectedRow, 5);
            if (!"Đã thanh toán".equals(currentStatus)) {
                int confirmation = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn cập nhật trạng thái thành 'Đã thanh toán'?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Cập nhật trạng thái trong cơ sở dữ liệu
                    HoaDonDAO.updateStatus(soHD, "da thanh toan");
                    // Cập nhật trạng thái trong JTable
                    hoaDonTable.setValueAt("da thanh toan", selectedRow, 5);
                    JOptionPane.showMessageDialog(null, "Cập nhật trạng thái thành công.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã ở trạng thái 'Đã thanh toán'.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để cập nhật trạng thái.");
        }
    }
});
   deleteButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = hoaDonTable.getSelectedRow();
            if (selectedRow != -1) {
                int soHD = (int) hoaDonTable.getValueAt(selectedRow, 0);
                int confirmation = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa hóa đơn này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Xóa hóa đơn trong cơ sở dữ liệu
                    HoaDonDAO.delete(soHD);
                    // Xóa hóa đơn trong JTable
                    hoaDonTableModel.removeRow(selectedRow);
                    JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để xóa.");
            }
        }
    });
   printButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lấy số hàng đã chọn trong bảng
        int selectedRow = hoaDonTable.getSelectedRow();
        if (selectedRow != -1) {
            // Lấy số hóa đơn từ hàng đã chọn
            int soHD = (int) hoaDonTable.getValueAt(selectedRow, 0);

            // Lấy thông tin chi tiết hóa đơn từ CSDL
            List<CTHD> cthds = CTHDDAO.getCTHDsBySoHD(soHD);

            // Tạo cửa sổ in
            JFrame printFrame = new JFrame("Print Invoice");
            printFrame.setSize(400, 300);
            printFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Tạo panel cho thông tin in
            JPanel printPanel = new JPanel(new BorderLayout());

            // Tạo model cho table in
            DefaultTableModel printTableModel = new DefaultTableModel();
            printTableModel.addColumn("Mã Món Ăn");
            printTableModel.addColumn("Số Lượng");
            printTableModel.addColumn("Thành Tiền");

            // Thêm thông tin từ danh sách chi tiết hóa đơn vào table in
            int totalCost = 0;
            for (CTHD cthd : cthds) {
                printTableModel.addRow(new Object[]{
                    cthd.getMaMonAn(),
                    cthd.getSoLuong(),
                    cthd.getThanhTien()
                });
                totalCost += cthd.getThanhTien();
            }

            // Thêm table in vào panel
            JTable printTable = new JTable(printTableModel);
            JScrollPane printScrollPane = new JScrollPane(printTable);
            printPanel.add(printScrollPane, BorderLayout.CENTER);

            // Hiển thị tổng thành tiền
            JLabel totalLabel = new JLabel("Tổng Tiền: " + totalCost);
            printPanel.add(totalLabel, BorderLayout.SOUTH);

            // Thêm panel in vào cửa sổ in
            printFrame.add(printPanel);
            printFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn một hóa đơn để in.");
        }
    }
});
   

        // Thêm các sự kiện xử lý cho các nút khác tương tự
    }

    private void loadHoaDons() {
        // Xóa dữ liệu cũ trong model
        hoaDonTableModel.setRowCount(0);

        // Lấy danh sách hóa đơn từ cơ sở dữ liệu
        List<HoaDon> hoaDons = HoaDonDAO.getAllHoaDons();

        // Thêm hóa đơn vào model của JTable
        for (HoaDon hoaDon : hoaDons) {
            hoaDonTableModel.addRow(new Object[]{
                hoaDon.getSoHD(),
                hoaDon.getMaNV(),
                hoaDon.getMaBan(),
                hoaDon.getNgHD(),
                hoaDon.getTongTien(),
                hoaDon.getTrangThai()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HoaDonGUI().setVisible(true);
            }
        });
    }

}
