import java.util.Scanner;

public class NilaiMahasiswa {


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan nama mahasiswa: ");
        String nama = input.nextLine();

      
        System.out.print("Masukkan nilai tugas: "); //3.rename variabel
        int tugas = input.nextInt();
        System.out.print("Masukkan nilai UTS: ");
        int uts = input.nextInt();

        System.out.print("Masukkan nilai UAS: ");
        int uas = input.nextInt();


        double rata = (tugas + uts + uas) / 3.0; //1.ekstrak method   5.ekstrak variabel


        if (rata >= 80) {//2. seluruh if= tentukan grade, ekstract method
            System.out.println("Grade: A");
        } else if (rata >= 70) {  //6.ekstrak konstan (80,70,60)=GRADE_A
            System.out.println("Grade: B");
        } else if (rata >= 60) {
            System.out.println("Grade: C");
        } else {
            System.out.println("Grade: D");
        }



        System.out.println("Nama: " + nama); //7.ekstrak method, tampilkan hasil
        System.out.println("Nilai rata-rata: " + rata);

        input.close();
    }
}