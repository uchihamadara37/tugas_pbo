/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.responsi_pbo_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author andre
 */
public class ModelBuku {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/perpustakaan";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;  // untuk menampung hasil SELECT *
    
    static ArrayList<Buku> dataList = new ArrayList<Buku>();
    int hasil;
    Buku myBook = new Buku(0,0, Double.NaN, "", "");
    
    public ModelBuku(){
        
    }
    
    public ArrayList<Buku> getAllData(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM buku";
            rs = stmt.executeQuery(sql);
            
            
            dataList.clear();
            
            while(rs.next()){
                
                dataList.add(
                    new Buku(
                        rs.getInt("id"),
                        rs.getInt("harga"),
                        rs.getDouble("rating"),
                        rs.getString("judul"),
                        rs.getString("penulis")
                    )   
                );
                
            }
            System.out.println("okokokoko");
            
            stmt.close();
            conn.close();
            
            return dataList;
        } catch (Exception e) {
            e.printStackTrace();
            dataList.clear();
            return dataList;
        }
    }
    public Buku getOneData(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM buku WHERE id ='"+id+"'";
            rs = stmt.executeQuery(sql);
            
            
            dataList.clear();
            
            while(rs.next()){
                
                dataList.add(
                    new Buku(
                         rs.getInt("id"),
                        rs.getInt("harga"),
                        rs.getDouble("rating"),
                        rs.getString("judul"),
                        rs.getString("penulis")
                    )   
                );
                
            }
//            myBook = new Buku(
//                    rs.getInt("harga"),
//                        rs.getDouble("rating"),
//                        rs.getString("judul"),
//                        rs.getString("penulis")
//            );
            System.out.println("ambil satu data");
//            System.out.println(myBook);
            stmt.close();
            conn.close();
            
            return dataList.getFirst();
        } catch (Exception e) {
            e.printStackTrace();
            return myBook;
        }
    }
    // query Insert
    public int insertData(Buku buku){
        try {
            float rating = Float.parseFloat(buku.rating.toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO `buku` (`judul`, `penulis`, `rating`, `harga`) VALUES ('"+buku.judul+"', '"+buku.penulis+"', '"+rating+"', '"+buku.harga+"');";
            hasil = stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
            
            return hasil;
            
        } catch (Exception e) {
            e.printStackTrace();
            return hasil;
        }
    }
    // wuery update
    public int updateData(int idBefore, Buku buku){
        try {
            float rating = Float.parseFloat(buku.rating.toString());
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "UPDATE `buku` SET `judul` = '"+buku.judul+"', `penulis` = '"+buku.penulis+"', `rating` = '"+rating+"' WHERE `buku`.`id` = "+idBefore+";";
//            String sql = "UPDATE `buku` SET `judul` = '"+buku.judul+"', `penulis` = '"+buku.penulis+"', `rating` = '"+rating+"', `harga` = '"+buku.harga+"' WHERE `buku`.`id` = '"+idBefore+"';";
            hasil = stmt.executeUpdate(sql);
            
            System.out.println(buku);
            System.out.println("hasil update"+ hasil);
            stmt.close();
            conn.close();
            return hasil;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error update");
            return hasil;
        }
    }
    // query Delete
    public int deleteData(int id){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "DELETE FROM buku WHERE `buku`.`id` = "+id+";";
            hasil = stmt.executeUpdate(sql);
            try {
                stmt.executeUpdate(sql);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Berhasil menghapus data"+hasil);
            stmt.close();
            conn.close();
            return hasil;
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("gagal delete");
            return hasil;
        }
    }
}
