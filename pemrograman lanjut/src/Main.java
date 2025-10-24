import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String[] nama = {"ciprek", "Budion", "Cahyo", "Diana", "Eva"}; //gawe array isi jenengmu
        ArrayList<String> namaTerpanjang = cariNamaTerpanjang(nama); // Panggil method cariNamaTerpanjang untuk mencari nama terpanjang
        System.out.println("Nama terpanjang adalah: " + namaTerpanjang);
    }


    public static ArrayList<String> cariNamaTerpanjang(String[] array) {
        ArrayList<String> hasil = new ArrayList<>(); // ArrayList untuk menyimpan nama terpanjang


        int maxLength = array[0].length(); // Ambil panjang nama pertama buat nilai awal
        for (String nama : array) { // Loop untuk setiap nama di dalam array
            if (nama.length() > maxLength) {
                maxLength = nama.length();
            }
        }

        for (String nama : array) { // Loop lagi untuk mengecek semua nama
            if (nama.length() == maxLength) {
                hasil.add(nama); // Tambahkan nama ke ArrayList hasil
            }
        }

        return hasil;
    }
}