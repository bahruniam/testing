public class MinFinder {

    // Fungsi untuk mencari nilai minimum dari tiga bilangan
    public static int findMin(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }

    // Method main hanya untuk coba jalankan manual (opsional)
    public static void main(String[] args) {
        System.out.println("Min dari (1, 2, 3) = " + findMin(1, 2, 3));
        System.out.println("Min dari (-1, -2, -3) = " + findMin(-1, -2, -3));
        System.out.println("Min dari (0, 0, 1) = " + findMin(0, 0, 1));
    }
}