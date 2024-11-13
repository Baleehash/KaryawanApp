# Aplikasi Manajemen Karyawan

Aplikasi Manajemen Karyawan ini adalah aplikasi berbasis Java yang memungkinkan pengelolaan data karyawan, pelacakan kehadiran, perhitungan tunjangan dan gaji, serta pembuatan laporan berdasarkan status karyawan. Sistem ini menggunakan MySQL sebagai database untuk menyimpan informasi karyawan.

## Fitur

1. **Import Konfigurasi**: Memuat konfigurasi dari file `config.properties`.
2. **Koneksi Database**: Terhubung ke database MySQL menggunakan konfigurasi yang telah diimpor.
3. **Impor Data**: Mengimpor data karyawan dari file `Karyawan.txt` ke database.
4. **Perbarui Status Karyawan**: Mengubah status karyawan menjadi Probation, Contract, Permanent, atau Terminated.
5. **Edit Data Karyawan**: Mengedit nama dan status karyawan.
6. **Pelacakan Kehadiran**: Mencatat kehadiran karyawan.
7. **Pelacakan Cuti**: Mencatat cuti karyawan.
8. **Perhitungan Tunjangan**: Menghitung tunjangan untuk karyawan berstatus permanent.
9. **Perhitungan Total Gaji**: Menghitung gaji bulanan semua karyawan (kecuali yang berstatus "Terminated").
10. **Laporan Berdasarkan Status**: Menampilkan laporan karyawan berdasarkan status, termasuk ID, nama, kehadiran, cuti, total gaji, dan status.

## Teknologi yang Digunakan

- **Java**: Bahasa pemrograman utama untuk aplikasi ini.
- **MySQL**: Database untuk menyimpan data karyawan.
- **JDBC**: Java Database Connectivity untuk interaksi dengan database.

## Persyaratan

- **Java JDK** versi 11 atau lebih baru
- **MySQL** yang terinstal dan berjalan
- **MySQL JDBC Driver** (ditambahkan ke proyek)
- **Maven** (opsional, jika menggunakan manajemen dependensi)

## Installation

1. **Klon repositori**:
   ```bash
   git clone https://github.com/Baleehas/KaryawanApp.git
   cd KaryawanApp
   ```
2. **Siapkan database MySQL**:
   - Buat database bernama employee_management.
   - Gunakan file SQL yang disediakan (misalnya, schema.sql) untuk membuat tabel yang diperlukan.
     
3. **Konfigurasi koneksi database**:
   - Buat file ``config.properties`` di direktori config.
   - Tambahkan konfigurasi berikut:
      ```bash
      db.url=jdbc:mysql://localhost:3306/perusahaan
      db.username=root
      db.password=password
      ```
4. **Impor Data Karyawan (Opsional)**:
     Letakkan file ```Karyawan.txt``` di root proyek, lalu jalankan opsi impor data pada menu awal aplikasi.

## Penggunaan
   Jalankan kelas ```Main.``` Aplikasi ini menyediakan menu pilihan untuk mengelola data karyawan.

   Pilihan Menu Utama
   - A: Import Configuration
   - B: Connect to Database
   - C: Import Employee Data
   - 1: Change Employee Status
   - 2: Edit Employee Data
   - 3: Record Attendance
   - 4: Record Leave
   - 5: Calculate Allowances
   - 6: Calculate Total Salary
   - 7: Generate Report by Status
   - 99: Exit

## Contoh Tampilan Menu
```bash
PRE-MENU
---------------
A. Import Konfigurasi config.properties
B. Connect to DB using config.properties database
C. Import Data Karyawan.txt ke Database
MENU
--------
1. Ganti Status Karyawan
2. Edit Data Karyawan
3. Kehadiran Karyawan
4. Cuti Karyawan
5. Hitung Tunjangan Karyawan
6. Hitung Total Gaji Karyawan
7. Tampilkan Laporan per Status
99. Exit
Choose an option: 
```

## Lisensi

Proyek ini dilisensikan di bawah [MIT License](LICENSE) - lihat file LICENSE untuk informasi lebih lanjut.

