# DOKUMENTASI PROGRAM

Berikut adalah dokumentasi program inventaris barang dimana memiliki 3 package yaitu:
1. crud
   - BarangCRUD
     ```
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
     ```
2. inventarisbarang
   - Inventarisbarang
     ```
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
          
          public void tampilkanMenu() {
              System.out.println("\n=== Menu Inventaris Barang ===");
              System.out.println("Admin: " + adminName); // Menampilkan nama admin
              System.out.println("Total Barang: " + totalBarang); // Menampilkan total barang
              System.out.println("1. Tambah Barang");
              System.out.println("2. Update Stok Barang");
              System.out.println("3. Hapus Barang");
              System.out.println("4. Tampilkan Semua Barang");
              System.out.println("5. Keluar");
              System.out.print("Pilih opsi: ");
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
      
              while (inventaris.isRunning) {
                  // Panggil method tampilkanMenu
                  inventaris.tampilkanMenu();
                  
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
     ```
3. model
   - Barang
     ```
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
     ```
