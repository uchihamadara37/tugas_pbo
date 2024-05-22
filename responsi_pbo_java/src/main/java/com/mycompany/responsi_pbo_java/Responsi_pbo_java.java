/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.responsi_pbo_java;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class Responsi_pbo_java {

    ModelBuku model = new ModelBuku();
    int idBefore = 0;
    
    public static void main(String[] args) {
        System.out.println("Hello World!");
        
        new ViewTampilan();
    }
    
    public void setModeTambahData(ViewTampilan view){
        
        view.btnCancel.setVisible(false);
        view.btnHapus.setVisible(false);
        clearInputan(view);
        view.btnTambah.setText("Tambahkan Data");
        idBefore = 0;
    }
    public void setModeUpdateData(ViewTampilan view){
        
        view.btnCancel.setVisible(true);
        view.btnHapus.setVisible(true);
        view.btnTambah.setText("Update Data");
    }
    public void reloadIsiTable(ViewTampilan view){
        DefaultTableModel tabel = (DefaultTableModel) view.table.getModel();
        tabel.setRowCount(0);
        int i = 1;
        for(Buku a : model.getAllData()){
            tabel.addRow(new Object[] {a.id, a.judul, a.penulis, a.rating, a.harga});
            i++;
        }
    }
    public void clearInputan(ViewTampilan view){
        view.inputJudul.setText("");
        view.inputPenulis.setText("");
        view.inputRating.setText("");
        view.inputHarga.setText("");
    }
    public void inputOrUpdateData(ViewTampilan view){
        if (view.btnTambah.getText() == "Tambahkan Data"){
            // insert data
            int hasil = model.insertData(
                new Buku(
                    0,
                    Integer.parseInt(view.inputHarga.getText()),
                    Double.parseDouble(view.inputRating.getText()),
                    view.inputJudul.getText(),
                    view.inputPenulis.getText()
                )
            );
            if (hasil > 0){
                JOptionPane.showMessageDialog(view, "Data berhasil dimasukan", "Info!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal dimasukan", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            int hasil = model.updateData(idBefore,
                new Buku(
                        idBefore,
                    Integer.parseInt(view.inputHarga.getText()),
                    Double.parseDouble(view.inputRating.getText()),
                    view.inputJudul.getText(),
                    view.inputPenulis.getText()
                )
            );
            if (hasil > 0){
                JOptionPane.showMessageDialog(view, "Data berhasil diupdate", "Info!", JOptionPane.INFORMATION_MESSAGE);
            }else{
                JOptionPane.showMessageDialog(view, "Data gagal diupdate", "try again!", JOptionPane.INFORMATION_MESSAGE);
            }
            
        }
        reloadIsiTable(view);
        clearInputan(view);
        setModeTambahData(view);
        view.table.clearSelection();
    }
    public void eventKlikRowTable(ViewTampilan view, java.awt.event.MouseEvent evt){
        JTable table = (JTable) evt.getSource();
        int row = table.rowAtPoint(evt.getPoint());
        
        Buku data = new Buku(0, 0, Double.NaN, "", "");
        if (row >= 0) {
            // Ambil data dari row yang diklik
            data.harga = (int) table.getValueAt(row, 4);
            data.judul = (String) table.getValueAt(row, 1);
            data.penulis = (String) table.getValueAt(row, 2);
            data.rating = (Double) table.getValueAt(row, 3);
            
            idBefore = (int) table.getValueAt(row, 0);
            
        }
        view.inputJudul.setText(data.judul);
        view.inputPenulis.setText(data.penulis);
        view.inputRating.setText(data.rating.toString());
        view.inputHarga.setText(data.harga.toString());
        
        setModeUpdateData(view);
    }
    public void deleteData(ViewTampilan view){
//        Buku buku = model.getOneData(idBefore);
        int y = JOptionPane.showConfirmDialog(view, "Apakah anda yakin ingin menghapus buku ini ", "Butuh Konfirmasi!", JOptionPane.YES_NO_OPTION);
        if (y == JOptionPane.YES_OPTION){
            System.out.println("id sebelum "+idBefore);
            model.deleteData(idBefore);
        }
        reloadIsiTable(view);
        clearInputan(view);
    }
    
}
