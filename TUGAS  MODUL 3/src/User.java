class User{
    private String nama, nim;

    public User(String nama, String nim) {
        this.nama = nama;
        this.nim = nim;
    }

    public void setNama(String nama) {  //method setter
        this.nama = nama;
    }
    public String getNama() {
        return nama;
    }

    public void setNim(String nim){ //getter
        this.nim = nim;
    }
    public String getNim() {
        return nim;
    }

            }public void login(String nama, String nim) {  //method login
                if (nama.equals("Admin277")) {
                    if (nim.equals("Password277")) {
                        System.out.println("Login Admin Berhasil");
        }else{
            System.out.println("Login gagal! username atau password salah");
        }
    }

    public void displayinfo() {  //method displayinfo
        System.out.println("Login Admin Berhasil!!");
        System.out.println("Nama: "+ nama);
        System.out.println("NIM: "+ nim);
    }
}