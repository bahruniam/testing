public class Admin extends User { //subclass
    public Admin(String user, String password) {
        super(user, password);
    }

    @Override
    public void login(String user, String password) {
        if (user.equals(getNama())) {
            if (password.equals(getNim())) {
                displayinfo();
            }
        } else {
            System.out.println("Login gagal! username atau password salah");
        }
    }

    @Override
    public void displayinfo() {
        System.out.println("Login Admin Berhasil");
    }

}