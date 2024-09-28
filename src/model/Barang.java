/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Fadhiil
 */
public class Barang {
    private final int id;
    private final String nama;
    private int stok;

    // Constructor
    public Barang(int id, String nama, int stok) {
        this.id = id;
        this.nama = nama;
        this.stok = stok;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    // Method untuk menampilkan detail barang
    public void tampilkanBarang() {
        System.out.println("ID: " + id + ", Nama: " + nama + ", Stok: " + stok);
    }
}
