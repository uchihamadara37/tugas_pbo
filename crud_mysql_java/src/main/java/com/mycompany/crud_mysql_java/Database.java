/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_mysql_java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class Database {
    
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/movie_db";
    static final String USER = "root";
    static final String PASS = "";
    
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;  // untuk menampung hasil SELECT *
    
    static ArrayList<DataMovie> dataList = new ArrayList<DataMovie>();
    int hasil;
    
    public Database(){
        
    }
    
    public ArrayList<DataMovie> getAllDataMovie(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "SELECT * FROM movie";
            rs = stmt.executeQuery(sql);
            
            
            dataList.clear();
            
            while(rs.next()){
                
                dataList.add(
                    new DataMovie(
                        rs.getString("judul"), 
                        rs.getDouble("alur"),
                        rs.getDouble("penokohan"),
                        rs.getDouble("akting")
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
    // query Insert
    public int insertDataMovie(DataMovie movie){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "INSERT INTO `movie` (`judul`, `alur`, `penokohan`, `akting`, `nilai`) VALUES ('"+movie.getJudul()+"', '"+movie.getAlur()+"', '"+movie.getPenokohan()+"', '"+movie.getAkting()+"', '"+movie.getNilai()+"');";
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
    public int updateDataMovie(String judulBefore, DataMovie movieBaru){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "UPDATE `movie` SET `judul` = '"+movieBaru.getJudul()+"', `alur` = '"+movieBaru.getAlur()+"', `penokohan` = '"+movieBaru.getPenokohan()+"', `akting` = '"+movieBaru.getAkting()+"', `nilai` = '"+movieBaru.getNilai()+"' WHERE `movie`.`judul` = '"+judulBefore+"';";
            hasil = stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
            return hasil;
            
        } catch (Exception e) {
            e.printStackTrace();
            return hasil;
        }
    }
    // query Delete
    public int deleteDataMovie(String judul){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql = "DELETE FROM movie WHERE `movie`.`judul` = '"+judul+"'";
            hasil = stmt.executeUpdate(sql);
            
            stmt.close();
            conn.close();
            return hasil;
            
        } catch (Exception e) {
            e.printStackTrace();
            return hasil;
        }
    }
    
    
}
