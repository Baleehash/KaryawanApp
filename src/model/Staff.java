package model;

public class Staff extends Worker {
    private double tunjanganMakan = 220000;
    private double tunjanganTransport = 440000;

    public Staff(int idKaryawan, String nama, double gajiPokok, int jumlahCuti) {
        super(idKaryawan, nama, gajiPokok, jumlahCuti);
    }

    @Override
    public double hitungGajiPokok() {
        return (gajiPokok / 22) * hitungKehadiran();
    }

    public double hitungTunjanganMakan() {
        if (jumlahCuti > 1) {
            tunjanganMakan -= (tunjanganMakan / 22) * (jumlahCuti - 1);
        }
        return tunjanganMakan;
    }

    public double hitungTunjanganTransport() {
        if (jumlahCuti > 1) {
            tunjanganTransport -= (tunjanganTransport / 22) * (jumlahCuti - 1);
        }
        return tunjanganTransport;
    }

    public double hitungTotalGaji() {
        return hitungGajiPokok() + hitungTunjanganMakan() + hitungTunjanganTransport();
    }
}

