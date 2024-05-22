/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class MONAN {
   private int MAMONAN;
   private int SOLUONG;
   private String TENMONAN;
   private String DVT;
   private String LOAI;
   private int GIA;

    /*public MONAN(int MAMONAN, String TENMONAN, String DVT, String LOAI, int GIA) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public MONAN() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
   
    public int getMAMONAN() {
        return MAMONAN;
    }

    public void setMAMONAN(int MAMONAN) {
        this.MAMONAN = MAMONAN;
    }

    public int getSOLUONG() {
        return SOLUONG;
    }

    public void setSOLUONG(int SOLUONG) {
        this.SOLUONG = SOLUONG;
    }

    public String getTENMONAN() {
        return TENMONAN;
    }

    public void setTENMONAN(String TENMONAN) {
        this.TENMONAN = TENMONAN;
    }

    public String getDVT() {
        return DVT;
    }

    public void setDVT(String DVT) {
        this.DVT = DVT;
    }

    public String getLOAI() {
        return LOAI;
    }

    public void setLOAI(String LOAI) {
        this.LOAI = LOAI;
    }

    public int getGIA() {
        return GIA;
    }

    public void setGIA(int GIA) {
        this.GIA = GIA;
    }      
    
}
