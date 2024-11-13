# Employee Management System

This is a Java-based Employee Management System that allows you to manage employee data, track attendance, calculate allowances and salaries, and generate reports by employee status. The system uses a MySQL database to store employee information.

## Features

1. **Import Configuration**: Load configurations from a `config.properties` file.
2. **Database Connection**: Connect to a MySQL database using imported configurations.
3. **Data Import**: Import employee data from a `Karyawan.txt` file to the database.
4. **Employee Status Update**: Change employee status to Probation, Contract, Permanent, or Terminated.
5. **Edit Employee Data**: Edit employee name and status.
6. **Track Attendance**: Record employee attendance.
7. **Track Leave**: Record employee leave.
8. **Calculate Allowances**: Calculate allowances for permanent employees.
9. **Calculate Total Salary**: Calculate monthly salary for all employees (excluding those with "Terminated" status).
10. **Generate Report by Status**: Display a report for employees based on their status, showing ID, name, attendance, leave, total salary, and status.

## Technologies Used

- **Java**: Core language for building the application.
- **MySQL**: Database for storing employee data.
- **JDBC**: Java Database Connectivity for interacting with the database.

## Prerequisites

- **Java JDK** 11 or later
- **MySQL** installed and running
- **MySQL JDBC Driver** (added to the project)
- **Maven** (optional, if using dependencies management)

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/your-repo-name.git
   cd your-repo-name
   ```
2. **Set up MySQL database**:
   - Create a database named employee_management.
   - Use the SQL file provided (e.g., schema.sql) to create necessary tables.
     
3. **Configure database connection**:
   - Create a config.properties file in the config directory.
   - Add the following configuration:
      ```bash
      db.url=jdbc:mysql://localhost:3306/employee_management
      db.username=your-username
      db.password=your-password
      ```
4. **Import Employee Data (Optional)**:
   Place Karyawan.txt file in the project root, then run the import data option in the app's pre-menu.

## Usage
Run the Main class. The application provides a menu where you can choose various options to manage employee data.

Main Menu Options
A: Import Configuration
B: Connect to Database
C: Import Employee Data
1: Change Employee Status
2: Edit Employee Data
3: Record Attendance
4: Record Leave
5: Calculate Allowances
6: Calculate Total Salary
7: Generate Report by Status
99: Exit

## Example
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

## License
This project is licensed under the MIT License - see the LICENSE file for details.
