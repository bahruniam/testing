package tokoku;

public abstract class Produk {
    protected String nama;
    protected double harga;
    protected int jumlah;

    public Produk(String nama, double harga, int jumlah) {
        this.nama = nama;
        this.harga = harga;
        this.jumlah = jumlah;
    }

    // getter-setter

    public abstract double hitungTotalHarga(); // method abstract
}
