package model;

public abstract class Worker {
    protected int idKaryawan;
    protected String nama;
    protected double gajiPokok;
    protected int absensiHari = 0;
    protected int jumlahCuti;

    public Worker(int idKaryawan, String nama, double gajiPokok, int jumlahCuti) {
        this.idKaryawan = idKaryawan;
        this.nama = nama;
        this.gajiPokok = gajiPokok;
        this.jumlahCuti = jumlahCuti;
    }

    public void tambahKehadiran() {
        absensiHari++;
    }

    public int hitungKehadiran() {
        return absensiHari;
    }

    public abstract double hitungGajiPokok();
}

