/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inventarisbarang;
import crud.BarangCRUD;
import java.util.Scanner;
/**
 *
 * @author Fadhiil
 */
public class Inventarisbarang {

    //properti
    private final String adminName;
    private int totalBarang;
    private boolean isRunning;

    // Constructor untuk menginisialisasi properti
    public Inventarisbarang(String adminName) {
        this.adminName = adminName;
        this.totalBarang = 0;  // Awalnya, tidak ada barang
        this.isRunning = true; // Program dalam status berjalan
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String adminName;
        do {
            System.out.print("Masukkan nama admin: ");
            adminName = scanner.nextLine().trim();

            if (adminName.isEmpty()) {
                System.out.println("Nama admin tidak boleh kosong. Silakan coba lagi.");
            }
        } while (adminName.isEmpty());

        // Membuat objek dari Inventarisbarang
        Inventarisbarang inventaris = new Inventarisbarang(adminName);

        // Menginisialisasi BarangCRUD tanpa nama admin
        BarangCRUD barangCRUD = new BarangCRUD();

        while (inventaris.isRunning) { // Menggunakan properti isRunning
            System.out.println("\n=== Menu Inventaris Barang ===");
            System.out.println("Admin: " + inventaris.adminName); // Menampilkan nama admin
            System.out.println("Total Barang: " + inventaris.totalBarang); // Menampilkan total barang
            System.out.println("1. Tambah Barang");
            System.out.println("2. Update Stok Barang");
            System.out.println("3. Hapus Barang");
            System.out.println("4. Tampilkan Semua Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            
            // Mengambil input sebagai string
            String input = scanner.nextLine().trim();

            // Cek jika input kosong
            if (input.isEmpty()) {
                System.out.println("Error: Tidak boleh kosong. Silakan coba lagi.");
                continue; // Kembali ke loop awal untuk meminta input lagi
            }

            try {
                // Parsing input menjadi integer
                int opsi = Integer.parseInt(input);

                switch (opsi) {
                    case 1:
                        barangCRUD.tambahBarang();
                        inventaris.totalBarang++; // Menambah total barang
                        break;
                    case 2:
                        barangCRUD.updateBarang();
                        break;
                    case 3:
                        barangCRUD.hapusBarang();
                        if (inventaris.totalBarang > 0) {
                            inventaris.totalBarang--; // Mengurangi total barang
                        }
                        break;
                    case 4:
                        barangCRUD.tampilkanSemuaBarang();
                        break;
                    case 5:
                        System.out.println("Keluar dari program.");
                        inventaris.isRunning = false; // Mengubah status isRunning menjadi false
                        break;
                    default:
                        System.out.println("Opsi tidak valid, coba lagi.");
                        break;
                }
            } catch (NumberFormatException e) {
                // Menangani kesalahan jika input bukan angka
                System.out.println("Error: Input harus berupa angka. Silakan coba lagi.");
            }
        }

        scanner.close(); // Menutup Scanner
    }
}
