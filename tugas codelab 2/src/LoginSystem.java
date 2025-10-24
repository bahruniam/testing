import java.util.Scanner;

public class LoginSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Admin admin = new Admin();
        Mahasiswa mahasiswa = new Mahasiswa();

        System.out.println("=== Sistem Login ===");
        while (true) {
            System.out.print("\nPilih login sebagai (Admin/Mahasiswa), atau ketik 'q' untuk keluar: ");
            String pilihan = scanner.nextLine();

            if (pilihan.equalsIgnoreCase("q")) {
                System.out.println("Program selesai. Bye!");
                break;
            }

            if (pilihan.equalsIgnoreCase("Admin")) {
                while (true) {
                    System.out.print("Masukkan username: ");
                    String username = scanner.nextLine();
                    System.out.print("Masukkan password: ");
                    String password = scanner.nextLine();

                    if (admin.login(username, password)) {
                        System.out.println("Login Admin berhasil!");
                        break; // keluar dari loop login admin
                    } else {
                        System.out.println("Username atau password salah. Coba lagi.\n");
                    }
                }

            } else if (pilihan.equalsIgnoreCase("Mahasiswa")) {
                while (true) {
                    System.out.print("Masukkan nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();

                    if (mahasiswa.login(nama, nim)) {
                        System.out.println("Login Mahasiswa berhasil!");
                        mahasiswa.displayInfo();
                        break; // keluar dari loop login mahasiswa
                    } else {
                        System.out.println("Nama atau NIM salah. Coba lagi.\n");
                    }
                }

            } else {
                System.out.println("Pilihan tidak valid. Silakan pilih lagi.");
            }
        }

        scanner.close();
    }
}