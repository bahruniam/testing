import java.util.Scanner;

// Custom Exception untuk angka tidak valid
class InvalidNumberException extends Exception {
    public InvalidNumberException(String message) {
        super(message);
    }
}

public class main {
    public static void main(String[] args) { // Method utama
        Scanner scanner = new Scanner(System.in); // Membuat objek

        try {
            System.out.print("Masukkan angka positif: ");
            String input = scanner.nextLine(); // Baca input sebagai string

            // Cek jika input kosong atau hanya spasi
            if (input.trim().isEmpty()) {
                throw new InvalidNumberException("Error: Input tidak boleh kosong atau hanya spasi!");
            }

            // Coba konversi ke angka
            double angka = Double.parseDouble(input);

            if (angka <= 0) { // Mengecek apakah angka tidak positif
                throw new InvalidNumberException("Error: Angka harus positif!");
            }

            System.out.println("Angka " + angka + " valid!");
            // Jika input valid

        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage()); // Menampilkan pesan error khusus
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!"); // Jika input bukan angka
        } catch (Exception e) {
            System.out.println("Error: Terjadi kesalahan tak terduga.");
        }

        scanner.close(); // Menutup scanner
    }
}