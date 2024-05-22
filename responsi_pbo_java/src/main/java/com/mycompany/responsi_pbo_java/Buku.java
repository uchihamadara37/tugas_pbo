/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.responsi_pbo_java;

/**
 *
 * @author andre
 */
public class Buku {
    public  int id;
    public  Integer harga;
    public  Double rating;
    public  String judul;
    public  String penulis;
    
    public Buku(int id, int harga, Double rating, String judul, String penulis){
        this.id = id;
        this.harga = harga;
        this.rating = rating;
        this.judul = judul;
        this.penulis = penulis;
    }
    
    public void setHarga(int hargaLuar){
        this.harga = (int )(rating * 100) + hargaLuar + 500;
    }
    public void setRating(Double rating){
        this.rating = rating;
    }
    public void setJudul(String judul){
        this.judul = judul;
    }
    public void setPenulis(String penulis){
        this.penulis = penulis;
    }
    
    
    
}
