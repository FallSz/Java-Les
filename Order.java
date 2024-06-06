public class Order {
    private int id;
    private String username;
    private String tanggal;
    private String kursus;
    private int harga;

    public Order(int id, String username, String tanggal, String kursus, int harga) {
        this.id = id;
        this.username = username;
        this.tanggal = tanggal;
        this.kursus = kursus;
        this.harga = harga;
    }

    // Getter methods
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getKursus() {
        return kursus;
    }

    public int getHarga() {
        return harga;
    }
}
