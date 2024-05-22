/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author DELL
 */
//Model Người dùng của hệ thống
public class ModelUser {
    private int userID; //ID tài khoản
    private String HoTen; // Họ và tên
    private String matKhau; //Mật khẩu
    private String email; //Email
    private String SDT; // Số điện thoại
    private String vaiTro; //Vai trò: 'Quản lý', 'Nhân viên' 

    //Constructor
    public ModelUser(){
    }
    
    public ModelUser(int userID, String HoTen, String matKhau, String email, String SDT, String vaiTro) {
        this.userID = userID;
        this.HoTen = HoTen;
        this.matKhau = matKhau;
        this.email = email;
        this.SDT = SDT;
        this.vaiTro = vaiTro;
    }
    
    //Get and Set

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public String getVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(String vaiTro) {
        this.vaiTro = vaiTro;
    }
    
}
