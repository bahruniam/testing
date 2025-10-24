class MinFinder {
    public static int findMin(int a, int b, int c) {
        if (a <= b && a <= c) {
            return a;
        } else if (b <= a && b <= c) {
            return b;
        } else {
            return c;
        }
    }
}

public class main {
    public static void main(String[] args) {
        testScenario1();
        testScenario2();
        testScenario3();
    }

    public static boolean testScenario1() {
        int expected = 1;
        int actual = MinFinder.findMin(1, 2, 3);
        if (actual == expected) {
            System.out.println("Skenario 1: LULUS");
            return true;
        } else {
            System.out.println("Skenario 1: GAGAL");
            return false;
        }
    }

    public static boolean testScenario2() {
        int expected = -3;
        int actual = MinFinder.findMin(-1, -2, -3);
        if (actual == expected) {
            System.out.println("Skenario 2: LULUS");
            return true;
        } else {
            System.out.println("Skenario 2: GAGAL");
            return false;
        }
    }

    public static boolean testScenario3() {
        int expected = 0;
        int actual = MinFinder.findMin(0, 0, 1);
        if (actual == expected) {
            System.out.println("Skenario 3: LULUS");
            return true;
        } else {
            System.out.println("Skenario 3: GAGAL");
            return false;
        }
    }
}