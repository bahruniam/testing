import java.util.Scanner;
public class LoginSystem{
    public static void main(String[] args) {

        User user1 = new User("niam", "2024");
        Admin admi1 = new Admin("Admin277", "Password277");
        Mahasiswa mahasiswa1 = new Mahasiswa("Bahru Ni'am", "2024103701103277");
        bos bos1=new bos ("jack", "277");
        bos2 bos2=new bos2("jjk","277")

        int pilihan;
        Scanner masuk = new Scanner(System.in);
        System.out.print("Pilih Login:\n1. Admin\n2. Mahasiswa\n3.bos\n3");
        System.out.print("Masukkan Pilihan: ");
        pilihan = masuk.nextInt();
        masuk.nextLine();

        switch (pilihan) {
            case 1:
                System.out.print("Masukkan username: ");
                String userAdmin = masuk.nextLine();
                System.out.print("Masukkan password: ");
                String paswordAdmin = masuk.nextLine();
                admi1.login(userAdmin,paswordAdmin);
                break;
            case 2:
                System.out.print("Masukkan nama: ");
            String namaMaha = masuk.nextLine();
            System.out.print("Masukkan NIM: ");
            String nimMaha= masuk.nextLine();
            mahasiswa1.login(namaMaha,nimMaha );
            break;
            case 3:
                System.out.print("Masukkan nama: ");
                String namabos = masuk.nextLine();
                System.out.print("Masukkan NIM: ");
                String nimbos= masuk.nextLine();
                mahasiswa1.login(namabos,nimbos );
                break;

            default:
                System.out.println("Pilihan tidak valid!");
        }
        masuk.close();
    }
}