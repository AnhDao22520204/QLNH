/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;



public class tkModeltwo implements Comparable<tkModeltwo> {
    private int thoiGian;
    private double value;
    public tkModeltwo(int thoiGian, double value) {
        this.thoiGian = thoiGian;
        this.value = value;
    }
    public tkModeltwo() {
    }
    public int getThoiGian() {
        return thoiGian;
    }
    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }
    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
    public String toString() {
        return "Thonke{x="+thoiGian
        +", y=" +value+ '}';
    }

    public int compareTo(tkModeltwo other) {
        return Integer.compare(this.thoiGian, other.thoiGian); 
    }
    
}
