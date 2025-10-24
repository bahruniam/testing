import java.util.Scanner;
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        RekeningBank rekening1 = new RekeningBank();
        rekening1.nomorRekening = "202410370110277";
        rekening1.namaPemilik = "Bahru Ni'am";
        rekening1.saldo = 700000.0;

        RekeningBank rekening2 = new RekeningBank();
        rekening2.nomorRekening = "303420370220600";
        rekening2.namaPemilik = "KKJ";
        rekening2.saldo = 1000000.0;

        rekening1.tampilkaninfo();
        rekening2.tampilkaninfo();

        System.out.println("Masukkan junmlah setor tunai untuk Bahru Ni'am: ");
        double setorBahru = scanner.nextDouble();
        rekening1.setorUang(setorBahru);

        System.out.println("Masukkan junmlah setor tunai untuk KKJ: ");
        double setorKKJ = scanner.nextDouble();
        rekening2.setorUang(setorKKJ);

        System.out.println("Masukkan jumlah tarik tunai untuk Bahru Ni'am: ");
        double tarikBahru = scanner.nextDouble();
        rekening1.tarikSaldo(tarikBahru);

        System.out.println("Masukkan jumlah tarik tunai untuk KKJ : ");
        double tarikKKJ = scanner.nextDouble();
        rekening2.tarikSaldo(tarikKKJ);

        rekening1.tampilkaninfo();
        rekening2.tampilkaninfo();

        scanner.close();
    }
}