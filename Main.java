import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        koneksi dbKoneksi = new koneksi();
        Connection conn = dbKoneksi.Connect();
        
        if (conn != null) {
            System.out.println("Database connection is established.");
        } else {
            System.out.println("Failed to establish database connection.");
        }

        // Close the connection
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to close the database connection " + e);
        }
    }
}
