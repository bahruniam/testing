import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        try {
            System.out.print("Masukkan usia Anda: ");//input umur
            int usia = input.nextInt();

            periksaUsia(usia); // validasi

            System.out.println("Usia valid: " + usia);

        } catch (InvalidAgeException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (Exception e) {
            System.out.println("Input tidak valid, harus berupa angka!");
        }

        input.close();
    }

    public static void periksaUsia(int usia) throws InvalidAgeException {
        if (usia <= 0 || usia >= 120) {
            throw new InvalidAgeException("Usia harus lebih dari 0 dan kurang dari 120!");
        }
    }
}