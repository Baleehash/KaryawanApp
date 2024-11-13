import config.ConfigLoader;
import database.DatabaseConnector;
import services.DataImporter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Properties config = null;
        Connection connection = null;

        while (true) {
            System.out.println("\nPRE-MENU\n---------------");
            System.out.println("A. Import Konfigurasi config.properties");
            System.out.println("B. Connect to DB using config.properties database");
            System.out.println("C. Import Data Karyawan.txt ke Database");
            System.out.println("MENU\n--------");
            System.out.println("1. Ganti Status Karyawan");
            System.out.println("2. Edit Data Karyawan");
            System.out.println("3. Kehadiran Karyawan");
            System.out.println("4. Cuti Karyawan");
            System.out.println("5. Hitung Tunjangan Karyawan");
            System.out.println("6. Hitung Total Gaji Karyawan");
            System.out.println("7. Tampilkan Laporan per Status");
            System.out.println("99. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "A":
                    config = ConfigLoader.loadConfig();
                    if (config != null) {
                        System.out.println("Configuration imported.");
                    } else {
                        System.out.println("Failed to load config.");
                    }
                    break;

                case "B":
                    if (config != null) {
                        try {
                            connection = DatabaseConnector.connectToDatabase(config);
                            System.out.println("Connected to database.");
                        } catch (Exception e) {
                            System.out.println("Failed to connect to database.");
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Please import configuration first.");
                    }
                    break;

                case "C":
                    if (connection != null) {
                        DataImporter.importData(connection);
                    } else {
                        System.out.println("Please connect to the database first.");
                    }
                    break;

                case "1":
                    changeEmployeeStatus(scanner, connection);
                    break;

                case "2":
                    editEmployeeData(scanner, connection);
                    break;

                case "3":
                    recordEmployeeAttendance(scanner, connection);
                    break;

                case "4":
                    recordEmployeeLeave(scanner, connection);
                    break;

                case "5":
                    calculateEmployeeAllowance(connection);
                    break;

                case "6":
                    calculateTotalEmployeeSalary(connection);
                    break;

                case "7":
                    displayReportByStatus(scanner, connection);
                    break;

                case "99":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void changeEmployeeStatus(Scanner scanner, Connection connection) {
        try {
            connection.setAutoCommit(false);
            System.out.print("Enter Employee ID to change status: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new status (Probation, Kontrak, Tetap, Keluar): ");
            String newStatus = scanner.nextLine();

            String sql = "UPDATE karyawan SET Status = ? WHERE ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, newStatus);
                pstmt.setInt(2, id);
                int rowsAffected = pstmt.executeUpdate();
                connection.commit();
                System.out.println(rowsAffected + " row(s) updated.");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error updating employee status. Changes rolled back.");
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void editEmployeeData(Scanner scanner, Connection connection) {
        try {
            connection.setAutoCommit(false);
            System.out.print("Enter Employee ID to edit: ");
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Enter new name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new status: ");
            String newStatus = scanner.nextLine();

            String sql = "UPDATE karyawan SET Nama = ?, Status = ? WHERE ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, newName);
                pstmt.setString(2, newStatus);
                pstmt.setInt(3, id);
                int rowsAffected = pstmt.executeUpdate();
                connection.commit();
                System.out.println(rowsAffected + " row(s) updated.");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error editing employee data. Changes rolled back.");
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void recordEmployeeAttendance(Scanner scanner, Connection connection) {
        try {
            connection.setAutoCommit(false);
            System.out.print("Enter Employee ID for attendance: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "UPDATE karyawan SET JmlAbsensi = JmlAbsensi + 1 WHERE ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                connection.commit();
                System.out.println("Attendance recorded successfully.");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error recording attendance. Changes rolled back.");
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void recordEmployeeLeave(Scanner scanner, Connection connection) {
        try {
            connection.setAutoCommit(false);
            System.out.print("Enter Employee ID for leave: ");
            int id = Integer.parseInt(scanner.nextLine());

            String sql = "UPDATE karyawan SET JmlCutiTerpakai = JmlCutiTerpakai + 1 WHERE ID = ?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                int rowsAffected = pstmt.executeUpdate();
                connection.commit();
                System.out.println("Leave recorded successfully.");
            } catch (SQLException e) {
                connection.rollback();
                System.out.println("Error recording leave. Changes rolled back.");
                e.printStackTrace();
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void calculateEmployeeAllowance(Connection connection) {
        String sql = "SELECT ID, Nama, (JmlAbsensi * 50000) AS Tunjangan FROM karyawan";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            var resultSet = pstmt.executeQuery();
            System.out.println("Tunjangan Karyawan:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Nama");
                int allowance = resultSet.getInt("Tunjangan");
                System.out.printf("ID: %d, Nama: %s, Tunjangan: Rp%d%n", id, name, allowance);
            }
        } catch (SQLException e) {
            System.out.println("Error calculating employee allowance.");
            e.printStackTrace();
        }
    }

    private static void calculateTotalEmployeeSalary(Connection connection) {
        String sql = "SELECT ID, Nama, GajiPokok + (JmlAbsensi * 50000) AS TotalGaji FROM karyawan";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            var resultSet = pstmt.executeQuery();
            System.out.println("Total Gaji Karyawan:");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Nama");
                int totalSalary = resultSet.getInt("TotalGaji");
                System.out.printf("ID: %d, Nama: %s, Total Gaji: Rp%d%n", id, name, totalSalary);
            }
        } catch (SQLException e) {
            System.out.println("Error calculating total employee salary.");
            e.printStackTrace();
        }
    }

    private static void displayReportByStatus(Scanner scanner, Connection connection) {
        System.out.print("Masukkan Status (Probation, Kontrak, Tetap, Keluar): ");
        String status = scanner.nextLine();

        String sqlSelect = "SELECT ID, Nama, GajiPokok, JmlAbsensi, JmlCutiTerpakai, Status " +
                "FROM karyawan WHERE Status = ?";

        try (PreparedStatement pstmtSelect = connection.prepareStatement(sqlSelect)) {
            pstmtSelect.setString(1, status);
            var resultSet = pstmtSelect.executeQuery();

            System.out.println("\nLaporan Karyawan Berdasarkan Status: " + status);
            System.out.println("ID | Nama | Absensi | Cuti Terpakai | Tanpa Kabar | Total Gaji | Status");
            System.out.println("----------------------------------------------------------------------------");

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Nama");
                int baseSalary = resultSet.getInt("GajiPokok");
                int attendanceDays = resultSet.getInt("JmlAbsensi");
                int leaveTaken = resultSet.getInt("JmlCutiTerpakai");
                String employeeStatus = resultSet.getString("Status");

                // Hitung hari tanpa kabar
                int absentWithoutNotice = 22 - attendanceDays - leaveTaken;

                // Hitung total gaji (tidak disimpan di database)
                int mealAllowance = 10000; // Asumsi contoh tunjangan makan per hari
                int transportAllowance = 8000; // Asumsi contoh tunjangan transportasi per hari
                int totalSalary = (baseSalary + (mealAllowance + transportAllowance) * attendanceDays) / 22;

                // Tampilkan laporan karyawan
                System.out.printf("%d | %s | %d | %d | %d | Rp%d | %s%n",
                        id, name, attendanceDays, leaveTaken, absentWithoutNotice, totalSalary, employeeStatus);
            }

        } catch (SQLException e) {
            System.out.println("Error displaying report by status.");
            e.printStackTrace();
        }
    }
}
