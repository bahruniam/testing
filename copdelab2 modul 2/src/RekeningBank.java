public class RekeningBank {
    String nomorRekening;
    String namaPemilik;
    double  saldo;

    void tampilkaninfo(){

        System.out.println("Nomor rekening: " + nomorRekening);
        System.out.println("Nama pemilik: " + namaPemilik);
        System.out.println("Saldo: Rp " + saldo);
    }
    void setorUang(double jumlah){
        saldo += jumlah;
        System.out.println(namaPemilik + "Menyetor Rp" + jumlah + "Saldo sekarang : Rp" + saldo);
    }

    void tarikSaldo(double jumlah){
        if (saldo >= jumlah){
            saldo -= jumlah;
            System.out.println(namaPemilik +"Menarik Rp" + jumlah + ". (Berhasil) Saldo sekarang : Rp" + saldo);

        }else {
            System.out.println(namaPemilik + "menarik Rp" + jumlah + ". (Gagal, Saldo tidak mencukupi) Saldo saat ini : Rp" + saldo);
        }
    }
}