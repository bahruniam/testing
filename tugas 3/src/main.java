import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input nama mahasiswa dengan validasi
        String nama = "";
        boolean namaValid = false;

        while (!namaValid) {
            System.out.print("Masukkan nama mahasiswa: ");
            nama = scanner.nextLine();

            // Validasi nama harus berisi huruf saja (dan spasi)
            if (nama.matches("[a-zA-Z\\s]+") && !nama.trim().isEmpty()) { 
                namaValid = true;
            } else {
                System.out.println("Error: Nama harus berupa huruf (tidak boleh angka atau karakter khusus). Silakan coba lagi.");
            }
        }

        // Input nilai ujian akhir dengan validasi
        double nilaiUjianAkhir = 0;
        boolean inputValid = false;

        while (!inputValid) {
            System.out.print("Masukkan nilai ujian akhir: ");

            if (scanner.hasNextDouble()) {
                nilaiUjianAkhir = scanner.nextDouble();

                // Validasi rentang nilai (0-100)
                if (nilaiUjianAkhir >= 0 && nilaiUjianAkhir <= 100) {
                    inputValid = true;
                } else {
                    System.out.println("Error: Nilai harus antara 0-100. Silakan coba lagi.");
                }
            } else {
                System.out.println("Error: Input harus berupa angka. Silakan coba lagi.");
                scanner.next(); // Membersihkan input yang salah
            }
        }

        // Proses penentuan status kelulusan
        String status;
        if (nilaiUjianAkhir >= 60) {
            status = "Lulus";
        } else {
            status = "Tidak Lulus";
        }

        // Output hasil
        System.out.println("\n=== HASIL KELULUSAN ===");
        System.out.println("Nama Mahasiswa: " + nama);
        System.out.println("Nilai Ujian Akhir: " + nilaiUjianAkhir);
        System.out.println("Status: " + status);

        scanner.close();
    }
}