public class Mahasiswa extends User{ //mewarisi class user (mendapatmua atribut,method
    public Mahasiswa (String nama, String nim){
        super(nama, nim);
    }

    @Override
    public void login(String nama, String nim){ //override method login,mengoverride dri class user
        if (nama.equals(getNama())) { //cek nama,nim dengn atribut
            if (nim.equals(getNim())) {
                displayinfo();
            }
        }else{
            System.out.println("Login gagal! Nama atau NIM salah");
        }
    }

    public void displayinfo(){
        System.out.println("Login Admin Berhasil!!");
        System.out.println("Nama: "+ getNama());
        System.out.println("NIM: "+ getNama());
    }

}
