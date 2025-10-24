package app;
import perpustakaan.*;
public class Main {public static void main(String[] args) {
    // Data buku
    Buku buku1 = new NonFiksi("Madilog", "Tan Malaka", "Sejarah & Ilmu Pengetahuan");
    Buku buku2 = new Fiksi("Hainuwele: Sang Putri Kelapa", "Lilis Hu", "Dongeng");

    buku1.displayInfo();
    buku2.displayInfo();
    System.out.println();

    // Data anggota
    Anggota anggota1 = new Anggota("bahru niam", "ID: 277");
    Anggota anggota2 = new Anggota("jess no limit", "ID: 2ok");
    System.out.println();

    anggota1.pinjamBuku("Madilog");
    anggota2.pinjamBuku("Hainuwele: Sang Putri Kelapa", 7);
    System.out.println();

    anggota1.kembalikanBuku("Madilog");
    anggota2.kembalikanBuku("Hainuwele: Sang Putri Kelapa");
    System.out.println();

    System.out.println("Process finished with exit code 0");
}
}
