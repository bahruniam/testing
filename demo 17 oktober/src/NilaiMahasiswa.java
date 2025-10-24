import java.util.Scanner;

public class NilaiMahasiswa {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama mahasiswa: ");
        String nama = input.nextLine();

        System.out.print("Masukkan nilai tugas: ");
        int tugas = input.nextInt();

        System.out.print("Masukkan nilai UTS: ");
        int uts = input.nextInt();

        System.out.print("Masukkan nilai UAS: ");
        int uas = input.nextInt();

        double rata = hitungRata(tugas, uts, uas);

        System.out.println("Nama: " + nama);
        System.out.println("Nilai rata-rata:%.2f/ns " + rata);

        tentukanGrade(rata);

        input.close();
    }

    private static void tentukanGrade(double rata) {
        if (rata >= 80) {
            System.out.println("Grade: A");
        } else if (rata >= 70) {
            System.out.println("Grade: B");
        } else if (rata >= 60) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: D");
        }
    }

    private static double hitungRata(int tugas, int uts, int uas) {
        double rata = (tugas + uts + uas) / 3.0;
        return rata;
    }
}