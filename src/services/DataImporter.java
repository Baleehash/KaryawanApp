package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DataImporter {
    public static void importData(Connection connection) {
        String sql = "INSERT INTO karyawan (ID, Nama, GajiPokok, JmlAbsensi, JmlCutiTerpakai, Status) VALUES (?, ?, ?, ?, ?, ?)";

        try (InputStream inputStream = DataImporter.class.getClassLoader().getResourceAsStream("Karyawan.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            if (inputStream == null) {
                System.out.println("File Karyawan.txt tidak ditemukan di resources.");
                return;
            }

            // Skip the header line
            String line = reader.readLine();
            if (line != null) {
                // process the rest of the lines
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");

                    if (data.length == 6) { // memastikan ada 6 kolom yang sesuai
                        try (PreparedStatement statement = connection.prepareStatement(sql)) {
                            // Parsing dan menetapkan parameter SQL
                            statement.setInt(1, Integer.parseInt(data[0]));  // ID
                            statement.setString(2, data[1]);  // Nama
                            statement.setDouble(3, Double.parseDouble(data[2]));  // GajiPokok
                            statement.setInt(4, Integer.parseInt(data[3]));  // JmlAbsensi
                            statement.setInt(5, Integer.parseInt(data[4]));  // JmlCutiTerpakai
                            statement.setString(6, data[5]);  // Status

                            // Menjalankan update SQL
                            statement.executeUpdate();
                        } catch (NumberFormatException e) {
                            System.out.println("Format data salah pada baris: " + line);
                        }
                    } else {
                        System.out.println("Data tidak lengkap pada baris: " + line);
                    }
                }
            }

            System.out.println("Data berhasil diimpor ke database.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
