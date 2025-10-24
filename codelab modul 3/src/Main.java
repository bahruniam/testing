class KaraterGame{

    private String nama;
    private int kesehatan;

    public KaraterGame(String nama, int kesehatan){
        this.nama = nama;
        this.kesehatan = kesehatan;
    }

    public String getNama(){
        return nama;
    }

    public void setNama(String nama){
        this.nama = nama;
    }

    public int getKesehatan(){
        return kesehatan;
    }

    public void setKesehatan(int kesehatan){
        this.kesehatan = kesehatan;
    }
    public void serang(KaraterGame target){
        System.out.println(nama + "menyerang" + target.getNama() + "!");
    }

}
class Pahlawan extends KaraterGame{
    public Pahlawan(String nama, int kesehatan){
        super(nama, kesehatan);
    }
    @Override
    public void serang(KaraterGame target){
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan orbital strike!");
        target.setKesehatan(target.getKesehatan() - 20);
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }

}
class Musuh extends KaraterGame{
    public Musuh(String nama, int kesehatan){
        super(nama, kesehatan);
    }
    @Override
    public void serang(KaraterGame target){
        System.out.println(getNama() + " menyerang " + target.getNama() + " menggunakan Snake Bite!");
        target.setKesehatan(target.getKesehatan() - 15);
        System.out.println(target.getNama() + " sekarang memiliki kesehatan " + target.getKesehatan());
    }
}
public class Main {
    public static void main(String[]args){
        KaraterGame umum = new KaraterGame(" Karakter umum ", 100);
        Pahlawan hero  = new Pahlawan("Brimstone", 150);
        Musuh pahlawan = new Musuh("Viper", 200);

        System.out.println("Status awal: ");
        System.out.println(hero.getNama() + " Memiliki kesehatan : " + hero.getKesehatan());
        System.out.println(pahlawan.getNama() + " Memiliki kesehatan : " + pahlawan.getKesehatan());
        System.out.println();

        hero.serang(pahlawan);
        pahlawan.serang(hero);
    }
}
