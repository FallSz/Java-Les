import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class koneksi {
    private Connection connection;

    public koneksi() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Koneksi Driver Berhasil");
        } catch (ClassNotFoundException e) {
            System.out.println("Koneksi Driver Gagal: " + e);
        }

        // Database URL
        String url = "jdbc:mysql://localhost:3306/db_les";
        try {
            // Establish the connection
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Koneksi database berhasil");
        } catch (SQLException e) {
            System.out.println("Koneksi database gagal: " + e);
        }
    }
    
    public static Connection mycon(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_perpustakaan","root","");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        
        return con;
    }
    
    public static int execute(String SQL) {
        int status = 0;
        Connection koneksi = mycon();
        try {
            Statement st = koneksi.createStatement();
            status = st.executeUpdate(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }
    public static ResultSet executeQuery(String SQL) {
        ResultSet rs = null;
        Connection koneksi = mycon();
        try {
            Statement st = koneksi.createStatement();
            rs = st.executeQuery(SQL);
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean validateLogin(String username, String password) {
        String query = "SELECT * FROM user WHERE nama = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            System.out.println("Login validation failed: " + e);
            return false;
        }
    }
    
    public List<Kursus> getKursus() {
        List<Kursus> kursusList = new ArrayList<>();
        String query = "SELECT * FROM kursus";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idKursus = resultSet.getInt("id_kursus");
                String namaPengajar = resultSet.getString("nama_pengajar");
                int harga = resultSet.getInt("harga");
                String subjek = resultSet.getString("subjek");
                kursusList.add(new Kursus(idKursus, namaPengajar, harga, subjek));
            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve kursus data: " + e);
        }
        return kursusList;
    }
    
    public void addOrder(String username, String tanggal, String kursus, String harga) {
        try {
            // Prepare the SQL statement with placeholders
            String sql = "INSERT INTO `order` (nama, tanggal, kursus, harga) VALUES (?, ?, ?, ?)";
            
            // Create a PreparedStatement
            PreparedStatement statement = connection.prepareStatement(sql);
            
            // Set values for the placeholders
            statement.setString(1, username);
            statement.setString(2, tanggal);
            statement.setString(3, kursus);
            statement.setString(4, harga);
            
            // Execute the query
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Order added successfully!");
            } else {
                System.out.println("Failed to add order.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding order: " + e.getMessage());
        }
    }
    
    public List<Order> getOrdersByUsername(String username) {
    List<Order> orderList = new ArrayList<>();
    String query = "SELECT * FROM `order` WHERE nama = ?";
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, username);
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String tanggal = resultSet.getString("tanggal");
                String kursus = resultSet.getString("kursus");
                int harga = resultSet.getInt("harga");
                orderList.add(new Order(id, username, tanggal, kursus, harga));
            }
        }
    } catch (SQLException e) {
        System.out.println("Failed to retrieve orders: " + e.getMessage());
    }
    return orderList;
}


    Connection Connect() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
