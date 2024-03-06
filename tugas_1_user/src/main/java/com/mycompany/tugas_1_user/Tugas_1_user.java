/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tugas_1_user;

import java.util.InputMismatchException;
import java.util.Scanner;

class Link{
    private Link next = null;
    private Link prev = null;
    private String val = "";
    public void setValue(String isi){
        this.val = isi;
    }
    public String getVal(){
        return this.val;
    }
    public void setNext(Link data){
        next = data;
    }
    public Link getNext(){
        return next;
    }
    public void setPrev(Link data){
        prev = data;
    }
    public Link getPrev(){
        return prev;
    }
}
class History{
    private Link head = null;
    private Link tail = null;
    public void setHead(Link data){
        head = data;
    }
    public Link getHead(){
        return head;
    }
    public void setTail(Link data){
        tail = data;
    }
    public Link getTail(){
        return tail;
    }
    public boolean isEmpty(){
        return (head == null && tail == null);
    }
    public void addLast(String isi){
        Link data = new Link();
        data.setValue(isi);
        if (this.isEmpty()){
            this.setHead(data);
        }else{
            tail.setNext(data);
        }
        this.setTail(data);
    }
    public Link removeFirst(){
        if (isEmpty()){
            return null;
        }else{
            Link temp = head;
            head = temp.getNext();
            temp.setNext(null);
            return temp;
        }
    }
}

class User{
    private String username = "";
    private String password = "";
    protected History activity = new History();
    private final Scanner input = new Scanner(System.in);
    
    void register(){
        System.out.println("silakan masukan username : ");
        String uname = input.nextLine();
        System.out.println("silakan masukan password : ");
        String passw = input.nextLine();
        this.username = uname;
        this.password = passw;
        System.out.println("username : "+ this.username);
    }

    protected String getUsername(){
        return username;
    }

    protected String getPassword(){
        return password;
    }

    void notification(){
        System.out.println("User "+this.getUsername()+" created by User!\n");
    }


}

class Admin extends User{
    protected String[] access = {"create","read", "update"};
    @Override void notification(){
        System.out.println("User "+this.getUsername()+" created by Admin!\n");
    }
}

class Guest extends User{

    @Override void notification(){
        System.out.println("User "+this.getUsername()+" created by Guest!\n");
    }
}

public class Tugas_1_user {
    static Scanner input = new Scanner(System.in);

    public static int checkInputNumber(String perintah, int rangeBawah, int rangeAtas, String warn_range){
        boolean isNumeric = false;
        int pilihan = 0;
        while(!isNumeric){
            try{
                System.out.println(perintah);
                pilihan = input.nextInt();
                input.nextLine();       // maju satu langkah
                isNumeric = true;       // break the loop onces done
                if (pilihan > rangeAtas || pilihan < rangeBawah){
                    isNumeric = false;
                    System.out.println(warn_range);
                }
            }catch (InputMismatchException ime){
                System.out.println("Jangan masukan huruf!");
                input.nextLine();
            }
        }
        return pilihan;
    }
    
    public static void main(String[] args) {
        
        System.out.println("Laman Registrasi : ");
        System.out.println("1. user biasa");
        System.out.println("2. user admin");
        System.out.println("3. user guest");

        int pilihan = checkInputNumber("Pilih salah satu : ", 1, 3, "pilih 1-3 saja!");

        switch (pilihan) {
            case 1 -> {
                // user
                User user1 = new User();
                user1.register();
                user1.notification();

                // menambahkan aktivitas
                int jum = checkInputNumber("Jumlah activity baru : ", 1, 5, "Masukan 1-5 saja!" );
                for (int i = 0; i < jum; i++) {
                    System.out.println("Tambahkan nama aktivitas : ");
                    String act = input.nextLine();
                    user1.activity.addLast(act);
                }

                System.out.println("\nDaftar activity = ");
                for (Link node = user1.activity.getHead(); node != null ; node = node.getNext()) {
                    System.out.println("<>. "+node.getVal());
                }
                break;
            }
            case 2 -> {
                // admin
                User user2 = new Admin();
                user2.register();
                user2.notification();
                // menambahkan aktivitas
                int jum = checkInputNumber("Jumlah activity baru : ", 1, 5, "Masukan 1-5 saja!" );
                for (int i = 0; i < jum; i++) {
                    System.out.println("Tambahkan nama aktivitas : ");
                    String act = input.nextLine();
                    user2.activity.addLast(act);
                }

                System.out.println("\nDaftar activity = ");
                for (Link node = user2.activity.getHead(); node != null ; node = node.getNext()) {
                    System.out.println("<>. "+node.getVal());
                }
                break;
            }
            case 3 -> {
                // guest
                User user3 = new Guest();
                user3.register();
                user3.notification();

                // menambahkan aktivitas
                int jum = checkInputNumber("Jumlah activity baru : ", 1, 5, "Masukan 1-5 saja!" );
                for (int i = 0; i < jum; i++) {
                    System.out.println("Tambahkan nama aktivitas : ");
                    String act = input.nextLine();
                    user3.activity.addLast(act);
                }

                System.out.println("\nDaftar activity = ");
                for (Link node = user3.activity.getHead(); node != null ; node = node.getNext()) {
                    System.out.println("<>. "+node.getVal());
                }

                break;
            }
        }
    }
}
