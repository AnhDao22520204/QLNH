/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author DELL
 */
public class ModelBan {
    private int ID; //Mã bàn
    private String name; //Tên bàn
    private String status; //Loại bàn
    
    public ModelBan(){
    }

    public ModelBan(int ID, String name, String status) {
        this.ID = ID;
        this.name = name;
        this.status = status;
    }
    

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
