// Membuat custom exception untuk validasi usia
class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) { // constructor
        super(message); // kirim pesan error ke constructor Exception
    }
}