/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ThiTracNghiem;

import static BTL4.ServerUDP.tranfersData;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random; 

/**
 *
 * @author lamqu
 */
public class ConnectDtb {
    public static int getCountUser(String str){
        int count=0;
        Connection conn = getConnect();
        String sql = "select count(*) from BANGDIEM where MASV='"+str+"'";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDtb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return count;
    }
    
    public static String getIdMaSV(String str){
        String idMaSv="";
        Connection conn = getConnect();
        String sql = "select * from SINHVIEN where UserName='"+str+"'";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                idMaSv = rs.getString(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDtb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idMaSv;
    }
    public static String getAllCauHoi(){
        List<CauHoi> listCH = new ArrayList<CauHoi>();
        int[] socau = new int[1000];
        Arrays.fill(socau, 0);
        Connection conn = getConnect();
        String sql = "select * from BODE";
        Random rand = new Random(); 
        int dem=0;
        while(dem<10){
            int k = rand.nextInt(220);
            if(socau[k]!=1){
                socau[k] = 1;
                dem++;
            }
        }
        dem=-1;
        String str="";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            while(rs.next()){
                dem++;
                if(socau[dem]>0){
                    str+=rs.getString("CAUHOI");
                    str+="///";
                    str+=rs.getString("TRINHDO");
                    str+="///";
                    str+=rs.getString("NOIDUNG");
                    str+="///";
                    str+=rs.getString("A");
                    str+="///";
                    str+=rs.getString("B");
                    str+="///";
                    str+=rs.getString("C");
                    str+="///";
                    str+=rs.getString("D");
                    str+="///";
                    str+=rs.getString("DAP_AN");
                    str+="///";
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDtb.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return str;
    }
    
    public static boolean getUser(String str){
        boolean check = false;
        Connection conn = getConnect();
        String[] arrStr = str.split("///");
        System.out.println(arrStr[0]+arrStr[1]);
        String sql = "select * from sinhvien where UserName='"+arrStr[0]+"' and PassWord='"+arrStr[1]+"'";
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ResultSet rs = ptsm.executeQuery();
            if(rs.next()){
                check= true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDtb.class.getName()).log(Level.SEVERE, null, ex);
        }
        return check;
    }

    public static void insertData(String masv, int core){
        Connection conn = getConnect();
        String time;
        LocalDateTime now = LocalDateTime.now(); 
        String  idMasv = getIdMaSV(masv);
        int count = getCountUser(idMasv)+1;
        time = now.toString();
        String sql = "insert into BANGDIEM(MASV,LAN,NGAYTHI,DIEM) values ('"+idMasv+"',"+count+",'"+time+"',"+core+")";
        System.out.println(sql);
        try {
            PreparedStatement ptsm = conn.prepareStatement(sql);
            ptsm.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConnectDtb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Connection getConnect() {
        Connection conn = null;
        String name = "lamquangvinh";
        String pass = "A1t2o3";
        String url = "jdbc:sqlserver://localhost:1433;databaseName=KIEMTRALTM";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(url, name, pass);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    public static void main(String[] args) throws IOException {
        System.out.println(getConnect());
    }
}
