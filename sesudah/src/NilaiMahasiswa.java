import java.util.Scanner;

/**
 * Program untuk menghitung nilai rata-rata mahasiswa
 * dari nilai tugas, UTS, dan UAS serta menentukan grade akhir.
 *
 * @author Nama_Kamu
 * @version 1.0
 */
public class NilaiMahasiswa {

    private static final int GRADE_A = 80;
    private static final int GRADE_B = 70;
    private static final int GRADE_C = 60;

    /**
     * Method utama untuk menjalankan program.
     * Menerima input nama dan nilai mahasiswa dari pengguna.
     *
     * @param args argument dari command line (tidak digunakan)
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama mahasiswa: ");
        String nama = input.nextLine();

        System.out.print("Masukkan nilai tugas: ");
        int tugas = input.nextInt();

        System.out.print("Masukkan nilai UTS: ");
        int nilaiUTS = input.nextInt();

        System.out.print("Masukkan nilai UAS: ");
        int uas = input.nextInt();

        double rata = hitungRata(tugas, nilaiUTS, uas);

        tampilkanHasil(nama, rata);
        tentukanGrade(rata);

        input.close();
    }

    /**
     * Menampilkan nama mahasiswa dan nilai rata-rata
     *
     * @param nama nama mahasiswa
     * @param rata nilai rata-rata mahasiswa
     */
    private static void tampilkanHasil(String nama, double rata) {
        System.out.println("Nama: " + nama);
        System.out.println("Nilai rata-rata: " + rata);
    }

    /**
     * Menentukan grade mahasiswa berdasarkan nilai rata-rata
     *
     * @param rata nilai rata-rata mahasiswa
     */
    private static void tentukanGrade(double rata) {
        if (rata >= GRADE_A) {
            System.out.println("Grade: A");
        } else if (rata >= GRADE_B) {
            System.out.println("Grade: B");
        } else if (rata >= GRADE_C) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: D");
        }
    }

    /**
     * Menghitung nilai rata-rata dari nilai tugas, UTS, dan UAS
     *
     * @param tugas nilai tugas
     * @param uts nilai UTS
     * @param uas nilai UAS
     * @return nilai rata-rata (double)
     */
    private static double hitungRata(int tugas, int uts, int uas) {
        int total = tugas + uts + uas;
        return total / 3.0;
    }
}