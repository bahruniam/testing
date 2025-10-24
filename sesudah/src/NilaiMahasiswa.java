import java.util.Scanner;

public class NilaiMahasiswa {

    private static final int GRADE_A = 80;
    private static final int GRADE_B = 70;
    private static final int GRADE_C = 60;

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

    private static void tampilkanHasil(String nama, double rata) {
        System.out.println("Nama: " + nama);
        System.out.println("Nilai rata-rata: " + rata);
    }

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

    private static double hitungRata(int tugas, int uts, int uas) {
        int rata = tugas + uts + uas;
        return rata / 3.0;
    }
}