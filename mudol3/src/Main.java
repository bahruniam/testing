import java.util.ArrayList;
import java.util.Scanner;

// Class model menu makanan
class MenuMakanan {
    String nama;
    double harga;

    public MenuMakanan(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }
}

// Class nota pemesanan
class NotaPemesanan {
    ArrayList<MenuMakanan> pesanan = new ArrayList<>();
    ArrayList<Integer> jumlah = new ArrayList<>();

    // Menambah pesanan
    public void tambahPesanan(MenuMakanan menu, int qty) {
        pesanan.add(menu);
        jumlah.add(qty);
    }

    // Menghitung total harga
    public double hitungTotal() {

double total = 0;
for (int i = 0; i < pesanan.size(); i++) {
    total += pesanan.get(i).getHarga() * jumlah.get(i);
}
return total;


    // Menampilkan nota
    public void tampilkanNota() {
        System.out.println("===== NOTA PEMESANAN =====");
        for (int i = 0; i < pesanan.size(); i++) {
            System.out.println(pesanan.get(i).getNama() + " x " + jumlah.get(i) +
                    " = Rp" + (pesanan.get(i).getHarga() * jumlah.get(i)));
        }
        System.out.println("--------------------------");
        System.out.println("Total Bayar: Rp" + hitungTotal());
        System.out.println("==========================");
    }
}

// Class utama
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Daftar menu
        ArrayList<MenuMakanan> daftarMenu = new ArrayList<>();
        daftarMenu.add(new MenuMakanan("Nasi Goreng", 15000));
        daftarMenu.add(new MenuMakanan("Mie Ayam", 12000));
        daftarMenu.add(new MenuMakanan("Ayam Geprek", 18000));
        daftarMenu.add(new MenuMakanan("Es Teh", 5000));

        NotaPemesanan nota = new NotaPemesanan();

        System.out.println("=== MENU RESTORAN ===");
        for (int i = 0; i < daftarMenu.size(); i++) {
            System.out.println((i + 1) + ". " + daftarMenu.get(i).getNama() + " - Rp" + daftarMenu.get(i).getHarga());
        }

        char lanjut;
        do {
            System.out.print("Pilih nomor menu: ");
            int pilihan = input.nextInt();
            System.out.print("Masukkan jumlah: ");
            int qty = input.nextInt();

            nota.tambahPesanan(daftarMenu.get(pilihan - 1), qty);

            System.out.print("Tambah pesanan lagi? (y/n): ");
            lanjut = input.next().charAt(0);
        } while (lanjut == 'y' || lanjut == 'Y');

        nota.tampilkanNota();
    }
}

    private void tampilkanNota() {

    }
