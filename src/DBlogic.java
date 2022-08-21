import java.sql.*;

public class DBlogic {
    String DB = "jdbc:mysql://localhost:3306/2108";
    String USER = "root";
    String PASS = "biskviits";

    // register
    public void register(String username, String password) {
        try {

            Connection conn = DriverManager.getConnection(DB, USER, PASS);

            String insert = "INSERT INTO users (username, password) VALUES (?, ?)";

            PreparedStatement ps = conn.prepareStatement(insert);

            ps.setString(1, username);
            ps.setString(2, password);

            ps.executeUpdate();

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // get user
    public String select(String username) {
        try {
            Connection conn = DriverManager.getConnection(DB, USER, PASS);

            String select = "SELECT username FROM users WHERE username = ?";

            PreparedStatement ps = conn.prepareStatement(select);

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                System.out.println("User already exists");
            }

            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    // login
    public boolean login(String username, String password) {
        boolean isLoggedIn = false;

        try {
            Connection conn = DriverManager.getConnection(DB, USER, PASS);

            String select = "SELECT username, password FROM users WHERE username = ? AND password = ?";

            PreparedStatement ps = conn.prepareStatement(select);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                isLoggedIn = true;
            } else {
                System.out.println("User not faund");
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isLoggedIn;
    }

}
