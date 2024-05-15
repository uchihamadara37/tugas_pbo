/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.crud_mysql_java;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class Crud_mysql_java {

    Database db = new Database();
    static int hasil;
    String judulBefore = new String();
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        new TampilanCRUD_mysql();
    }
    
    public void masukanData(TampilanCRUD_mysql view){
        if (view.btn_save.getText() == "Simpan"){
            // insert data
            int hasil = db.insertDataMovie(
                new DataMovie(
                    view.text_judul.getText(),
                    Double.parseDouble(view.text_alur.getText()),
                    Double.parseDouble(view.text_penokohan.getText()),
                    Double.parseDouble(view.text_akting.getText())
                )
            );
            if (hasil > 0){
                JOptionPane.showMessageDialog(view, "Data berhasil dimasukan", "Info!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal dimasukan", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            int hasil = db.updateDataMovie(judulBefore,
                new DataMovie(
                    view.text_judul.getText(),
                    Double.parseDouble(view.text_alur.getText()),
                    Double.parseDouble(view.text_penokohan.getText()),
                    Double.parseDouble(view.text_akting.getText())
                )
            );
            if (hasil > 0){
                JOptionPane.showMessageDialog(view, "Data berhasil diupdate", "Info!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal diupdate", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        reloadIsiTabel(view);
        clearForm(view);
        clearButtonDelete(view);
        
        view.tabelTampilan.clearSelection();
        
        
    }
    public void reloadIsiTabel(TampilanCRUD_mysql view){
        DefaultTableModel tabel = (DefaultTableModel) view.tabelTampilan.getModel();
        tabel.setRowCount(0);
        int i = 1;
        for(DataMovie a : db.getAllDataMovie()){
            tabel.addRow(new Object[] {i, a.getJudul(), a.getAlur(), a.getPenokohan(), a.getAkting(), a.getNilai()});
            i++;
        }
    }
    public static void clearForm(TampilanCRUD_mysql view){
        view.text_judul.setText("");
        view.text_alur.setText("");
        view.text_penokohan.setText("");
        view.text_akting.setText("");
    }
    public static void clearButtonDelete(TampilanCRUD_mysql view){
        view.text_judul.setText("");
        view.text_alur.setText("");
        view.text_penokohan.setText("");
        view.text_akting.setText("");
        
        view.tabelTampilan.clearSelection();
        view.btn_save.setText("Simpan");
        view.hapusData.setVisible(false);
        view.UpdateData.setVisible(false);
        view.btn_cancel.setVisible(false);
    }
    public static void bukaButtonDelete(TampilanCRUD_mysql view){
        view.btn_save.setText("Update");
        view.hapusData.setVisible(true);
        view.UpdateData.setVisible(true);
        view.btn_cancel.setVisible(true);
    }
    public void memilihRowTabel(TampilanCRUD_mysql view, java.awt.event.MouseEvent evt){
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        
        DataMovie data = new DataMovie("", Double.NaN, Double.NaN, Double.NaN);
        if (row >= 0) {
            // Ambil data dari row yang diklik
            data.setJudul((String) table.getValueAt(row, 1)); 
            data.setAlur((Double) table.getValueAt(row, 2)); 
            data.setPenokohan((Double) table.getValueAt(row, 2)); 
            data.setAkting((Double) table.getValueAt(row, 2)); 
            
            judulBefore = (String) table.getValueAt(row, 1);
            
            // Lakukan operasi yang diinginkan dengan data yang diklik
            // System.out.println("Row yang diklik: " + data);
        }
        view.text_judul.setText(data.getJudul());
        view.text_alur.setText(data.getAlur().toString());
        view.text_penokohan.setText(data.getPenokohan().toString());
        view.text_akting.setText(data.getAkting().toString());
        
        bukaButtonDelete(view);
    }
    public void hapusData(TampilanCRUD_mysql view){
        int y = JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus movie'"+judulBefore+"'", "Butuh Konfirmasi!", JOptionPane.YES_NO_OPTION);
        if (y == JOptionPane.YES_OPTION){
            db.deleteDataMovie(judulBefore);
        }
        reloadIsiTabel(view);
        clearForm(view);
    }
}
