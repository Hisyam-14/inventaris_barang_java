/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;
import model.Barang;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 *
 * @author Fadhiil
 */
public class BarangCRUD {
    // Static ArrayList untuk menampung daftar barang
    private static final ArrayList<Barang> daftarBarang = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    
    private int totalBarang; // Menyimpan total barang
    private String lastAction; // Menyimpan aksi terakhir
    private boolean isUpdated; // Menyimpan status update

    // Constructor
    public BarangCRUD() {
        this.totalBarang = 0;
        this.lastAction = "";
        this.isUpdated = false; // Menginisialisasi status update
    }

    public BarangCRUD(String adminName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Method untuk menambah barang
    public void tambahBarang() {
        try {
            System.out.print("Masukkan ID Barang: ");
            int id = scanner.nextInt();
            scanner.nextLine(); // Konsumsi newline
            
            // Cek apakah ID sudah ada dalam daftar
            if (idSudahAda(id)) {
                System.out.println("Error: ID Barang sudah ada. Gunakan ID lain.");
                return;
            }
            
            System.out.print("Masukkan Nama Barang: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan Stok Barang: ");
            int stok = scanner.nextInt();

            if (id <= 0 || stok < 0) {
                System.out.println("Error: ID harus lebih dari 0 dan stok tidak boleh negatif.");
                return;
            }

            Barang barangBaru = new Barang(id, nama, stok);
            daftarBarang.add(barangBaru);
            totalBarang++; // Update total barang
            lastAction = "Menambahkan barang ID: " + id; // Update aksi terakhir
            isUpdated = true; // Update status
            System.out.println("Barang berhasil ditambahkan.");
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid. Pastikan ID dan stok adalah angka.");
            scanner.nextLine(); // Bersihkan input yang salah
        }
    }

    // Method untuk mengecek apakah ID sudah ada
    private boolean idSudahAda(int id) {
        for (Barang barang : daftarBarang) {
            if (barang.getId() == id) {
                return true; // ID sudah ada
            }
        }
        return false; // ID belum ada
    }

    // Method untuk mengupdate stok barang
    public void updateBarang() {
        try {
            System.out.print("Masukkan ID Barang yang ingin diupdate: ");
            int id = scanner.nextInt();

            System.out.print("Masukkan Stok Baru: ");
            int stokBaru = scanner.nextInt();

            if (stokBaru < 0) {
                System.out.println("Error: Stok tidak boleh negatif.");
                return;
            }

            boolean barangDitemukan = false;
            for (Barang barang : daftarBarang) {
                if (barang.getId() == id) {
                    barang.setStok(stokBaru);
                    System.out.println("Stok barang berhasil diupdate.");
                    barangDitemukan = true;
                    lastAction = "Mengupdate barang ID: " + id; // Update aksi terakhir
                    isUpdated = true; // Update status
                    break;
                }
            }

            if (!barangDitemukan) {
                System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid. Pastikan ID dan stok adalah angka.");
            scanner.nextLine(); // Bersihkan input yang salah
        }
    }

    // Method untuk menghapus barang
    public void hapusBarang() {
        try {
            System.out.print("Masukkan ID Barang yang ingin dihapus: ");
            int id = scanner.nextInt();

            boolean barangDitemukan = daftarBarang.removeIf(barang -> barang.getId() == id);

            if (barangDitemukan) {
                totalBarang--; // Update total barang
                lastAction = "Menghapus barang ID: " + id; // Update aksi terakhir
                isUpdated = true; // Update status
                System.out.println("Barang berhasil dihapus.");
            } else {
                System.out.println("Barang dengan ID " + id + " tidak ditemukan.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Input tidak valid. Pastikan ID adalah angka.");
            scanner.nextLine(); // Bersihkan input yang salah
        }
    }

    // Method untuk menampilkan seluruh barang
    public void tampilkanSemuaBarang() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Tidak ada barang dalam inventaris.");
        } else {
            System.out.println("Daftar Barang dalam Inventaris:");
            daftarBarang.forEach(Barang::tampilkanBarang);
        }
    }

    // Metode untuk mendapatkan total barang
    public int getTotalBarang() {
        return totalBarang;
    }

    // Metode untuk mengecek apakah ada pembaruan
    public boolean isUpdated() {
        return isUpdated;
    }
}
