/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Restaurant.Model;

/**
 *
 * @author 84889
 */
import JDBC.ConnectionProvider;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author 84889
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;


public class tkDAO { 
    private int Ngay;
    private double doanhthu;
    private double chitieu;
    
    public int getNgay() {
        return Ngay;
    }
    public void setNgay(int ngay) {
        Ngay = ngay;
    }
    public double getDoanhthu() {
        return doanhthu;
    }
    public void setDoanhthu(double doanhthu) {
        this.doanhthu = doanhthu;
    }
    public double getChitieu() {
        return chitieu;
    }
    public void setChitieu(double chitieu) {
        this.chitieu = chitieu;
    }
    public tkDAO() {
    }
    public tkDAO(int ngay, double doanhthu, double chitieu) {
        Ngay = ngay;
        this.doanhthu = doanhthu;
        this.chitieu = chitieu;
    }
public ArrayList<tkDAO> listTKNam(int nam)
{
    ArrayList<tkModeltwo> listdt =new ArrayList<>();
    listdt= listDT(nam);
    ArrayList<tkModeltwo> listct =new ArrayList<>();
    listct= listCT(nam);

    ArrayList<tkDAO> list=new ArrayList<>();
    tkDAO thongke =new tkDAO();
    int x=0;
    int y=0;
    int x1=listdt.size();
    int y1=listct.size();

    
    while (x<x1||y<y1) 
    {
        if(x<x1&&y<y1){
                if(listct.get(y).getThoiGian()==listdt.get(x).getThoiGian())
                {
                    thongke =new tkDAO(listct.get(y).getThoiGian(),listdt.get(x).getValue(),listct.get(y).getValue());
                    x++;
                    y++;
                }else if(listct.get(y).getThoiGian()<listdt.get(x).getThoiGian())
                {
                    thongke =new tkDAO(listct.get(y).getThoiGian(),0,listct.get(y).getValue());
                    y++;
        
                } else if(listct.get(y).getThoiGian()>listdt.get(x).getThoiGian())
                {
                    thongke =new tkDAO(listdt.get(x).getThoiGian(),listdt.get(x).getValue(),0);
                    x++;
        
                }
    }else if(x<x1)
    {
        thongke =new tkDAO(listdt.get(x).getThoiGian(),listdt.get(x).getValue(),0);
            x++;            
    }else if(y<y1){
        thongke =new tkDAO(listct.get(y).getThoiGian(),0,listct.get(y).getValue());
        y++;
    }
        list.add(thongke);
    }



    

return list;
}
    public ArrayList<tkModeltwo> listDT(int nam)
    {
        ArrayList<tkModeltwo> arrayList=new ArrayList<>();
        tkModeltwo thongket=new tkModeltwo();
        try {
        Connection con=ConnectionProvider.getCon();
            String sql="SELECT EXTRACT(MONTH FROM NGHD) AS THANG, SUM(TONGTIEN) AS TONG_TIEN "
     +" FROM HOADON WHERE EXTRACT(YEAR FROM NGHD) = ? GROUP BY EXTRACT(MONTH FROM NGHD) ORDER BY THANG ";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, nam);
            ResultSet rs =st.executeQuery();
            while(rs.next())
            {
                thongket=new tkModeltwo(rs.getInt(1),rs.getInt(2));
                arrayList.add(thongket);
            }
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public ArrayList<tkModeltwo> listCT(int nam)
    {
        ArrayList<tkModeltwo> arrayList=new ArrayList<>();
        tkModeltwo thongket=new tkModeltwo();
        try {
        Connection con=ConnectionProvider.getCon();
            String sql=" SELECT EXTRACT(MONTH FROM NGAYNHAP) AS THANG, SUM(SOLUONGNHAP * GIANHAP) AS TONGTIEN "
+" FROM   PHIEUNHAPKHO WHERE  EXTRACT(YEAR FROM NGAYNHAP) = ? GROUP BY  EXTRACT(MONTH FROM NGAYNHAP) ORDER BY THANG ";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1, nam);
            ResultSet rs =st.executeQuery();
            while(rs.next())
            {
                thongket=new tkModeltwo(rs.getInt(1),rs.getInt(2));
                arrayList.add(thongket);
            }
            con.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        
        Collections.sort(arrayList);
        return arrayList;
    }


///-------------thang------------------------


    public ArrayList<tkDAO> listTKThang(int thang,int nam)
    {
        ArrayList<tkModeltwo> listdt =new ArrayList<>();
        listdt= listDTT(thang,nam);
        ArrayList<tkModeltwo> listct =new ArrayList<>();
        listct= listCTT(thang,nam);
    
        ArrayList<tkDAO> list=new ArrayList<>();
        tkDAO thongke =new tkDAO();
        int x=0;
        int y=0;
        int x1=listdt.size();
        int y1= listct.size();
        while (x<x1||y<y1) 
        {
            if(x<x1&&y<y1){
                    if(listct.get(y).getThoiGian()==listdt.get(x).getThoiGian())
                    {
                        thongke =new tkDAO(listct.get(y).getThoiGian(),listdt.get(x).getValue(),listct.get(y).getValue());
                        x++;
                        y++;
                    }else if(listct.get(y).getThoiGian()<listdt.get(x).getThoiGian())
                    {
                        thongke =new tkDAO(listct.get(y).getThoiGian(),0,listct.get(y).getValue());
                        y++;
            
                    } else if(listct.get(y).getThoiGian()>listdt.get(x).getThoiGian())
                    {
                        thongke =new tkDAO(listdt.get(x).getThoiGian(),listdt.get(x).getValue(),0);
                        x++;
            
                    }
        }else if(x<x1)
        {
            thongke =new tkDAO(listdt.get(x).getThoiGian(),listdt.get(x).getValue(),0);
                x++;            
        }else if(y<y1){
            thongke =new tkDAO(listct.get(y).getThoiGian(),0,listct.get(y).getValue());
            y++;
        }
            list.add(thongke);
        }
    return list;
    }
      
    
    
    
    
    /*"SELECT EXTRACT(MONTH FROM NGHD) AS THANG, SUM(TONGTIEN) AS TONG_TIEN"
         +"FROM HOADON WHERE EXTRACT(YEAR FROM NGHD) = ? GROUP BY EXTRACT(MONTH FROM NGHD) ORDER BY THANG";*/
    
    
    
    
    public ArrayList<tkModeltwo> listDTT(int thang, int nam)
        {
            ArrayList<tkModeltwo> arrayList=new ArrayList<>();
            tkModeltwo thongket=new tkModeltwo();
            try {
            Connection con=ConnectionProvider.getCon();
                String sql="SELECT EXTRACT(DAY FROM NGHD) AS NGAY, SUM(TONGTIEN) AS TONG_TIEN_THEO_NGAY"
 +" FROM HOADON WHERE EXTRACT(MONTH FROM NGHD) = ? AND EXTRACT(YEAR FROM NGHD) = ? "
 +" GROUP BY EXTRACT(DAY FROM NGHD)ORDER BY NGAY ";
        
        
        
         PreparedStatement st=con.prepareStatement(sql);
                st.setInt(1, thang);
                st.setInt(2, nam);
                ResultSet rs =st.executeQuery();
                while(rs.next())
                {
                    thongket=new tkModeltwo(rs.getInt(1),rs.getInt(2));
                    arrayList.add(thongket);
                }
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            Collections.sort(arrayList);
            return arrayList;
        }
    
  
  
  
  
  
  
  
  
  
  /*"SELECT  TO_CHAR(NGAYNHAP, 'DD') AS NGAY,  SUM(SOLUONGNHAP * GIANHAP) AS TONGTIEN FROM"
                +"PHIEUNHAPKHO WHERE TO_CHAR(NGAYNHAP, 'MM') = ? ANDTO_CHAR(NGAYNHAP, 'YYYY') = TO_CHAR(CURRENT_DATE, 'YYYY')  GROUP BY"
                +"TO_CHAR(NGAYNHAP, 'DD')ORDER BY   NGAY;";*/
  
  
  
        public ArrayList<tkModeltwo> listCTT(int thang,int nam)
        {
            ArrayList<tkModeltwo> arrayList=new ArrayList<>();
            tkModeltwo thongket=new tkModeltwo();
            try {
            Connection con=ConnectionProvider.getCon();
                String sql=  "SELECT EXTRACT(DAY FROM NGAYNHAP) AS NGAY,SUM(SOLUONGNHAP * GIANHAP) AS TONG_GIATRI_NHAP"
                +" FROM PHIEUNHAPKHO WHERE EXTRACT(MONTH FROM NGAYNHAP) = ? AND EXTRACT(YEAR FROM NGAYNHAP) = ? "
                 +"GROUP BY EXTRACT(DAY FROM NGAYNHAP) ORDER BY NGAY";

                PreparedStatement st=con.prepareStatement(sql);
                st.setInt(1, thang);
                st.setInt(2, nam);
                ResultSet rs =st.executeQuery();
                while(rs.next())
                {
                    thongket=new tkModeltwo(rs.getInt(1),rs.getInt(2));
                    arrayList.add(thongket);
                }
                con.close();
            } catch (Exception e) {
                // TODO: handle exception
            }
            
            Collections.sort(arrayList);
            return arrayList;
        }
}
