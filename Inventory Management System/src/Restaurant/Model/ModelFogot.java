/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author DELL
 */
public class ModelFogot {
    private String email;
    private String NewPassword;
    
    public ModelFogot(){
    }
    public ModelFogot(String email, String NewPassword) {
        this.email = email;
        this.NewPassword = NewPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }
}
