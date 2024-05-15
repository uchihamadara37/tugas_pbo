/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.crud_mysql_java;

/**
 *
 * @author andre
 */
public class DataMovie implements InterfaceMovie{
    private String judul; 
    private Double alur; 
    private Double penokohan; 
    private Double akting; 
    private Double nilai;
    
    public DataMovie(String judul, Double alur, Double penokohan, Double akting) {
        this.judul = judul;
        this.alur = alur;
        this.penokohan = penokohan;
        this.akting = akting;
        this.nilai = (alur + penokohan + akting) / 3; 
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Double getAlur() {
        return alur;
    }

    public void setAlur(Double alur) {
        this.alur = alur;
        updateNilai(); 
    }

    public Double getPenokohan() {
        return penokohan;
    }

    public void setPenokohan(Double penokohan) {
        this.penokohan = penokohan;
        updateNilai(); 
    }

    public Double getAkting() {
        return akting;
    }

    public void setAkting(Double akting) {
        this.akting = akting;
        updateNilai(); 
    }

    public Double getNilai() {
        return nilai;
    }

    private void updateNilai() {
        this.nilai = (alur + penokohan + akting) / 3;
    }
}
