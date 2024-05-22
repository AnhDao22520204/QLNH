/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author DELL
 */
public class ModelLogin {
    private String email; //email
    private String password; // Mật khẩu

    // Constructor
    public ModelLogin() {
    }
    
    public ModelLogin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    // Get/Set
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
