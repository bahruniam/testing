import java.util.Locale;
import java.util.Scanner;

public class metode numerik {
    // Fungsi yang dicari akarnya: ganti sesuai kebutuhan
    static double f(double x) {
        return x * x * x - x - 2; // contoh: x^3 - x - 2
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        input.useLocale(Locale.US); // biar bisa input angka dengan titik

        System.out.print("Masukkan a: ");
        double a = input.nextDouble();

        System.out.print("Masukkan b: ");
        double b = input.nextDouble();

        System.out.print("Masukkan toleransi: ");
        double eps = input.nextDouble();

        System.out.print("Masukkan iterasi maksimum: ");
        int maxIter = input.nextInt();

        double fa = f(a), fb = f(b);
        if (fa * fb > 0) {
            System.out.println("Tidak ada akar pada selang [" + a + ", " + b + "]");
            return;
        }

        double xr = 0;
        for (int i = 1; i <= maxIter; i++) {
            xr = (a + b) / 2;
            double fxr = f(xr);

            System.out.printf("Iterasi %d: xr=%.6f f(xr)=%.6f%n", i, xr, fxr);

            if (fxr * fa < 0) {
                b = xr;
                fb = fxr;
            } else {
                a = xr;
                fa = fxr;
            }

            if (Math.abs(b - a) < eps) break;
        }

        System.out.println("\nAkar hampiran ≈ " + xr);
        input.close();
    }
}