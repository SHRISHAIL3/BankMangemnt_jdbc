package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.io.InputStream;

//connection
public class DBConnection {
    private static Connection connection = null;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Properties props = new Properties();
                InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("db.properties");

                if (input != null) {
                    props.load(input);
                    String url = props.getProperty("db.url");
                    String user = props.getProperty("db.username");
                    String pass = props.getProperty("db.password");
                    connection = DriverManager.getConnection(url, user, pass);
                } else {
                    // fallback
                    connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/bankdb",
                        "root",
                        "yourpassword"
                    );
                }
                System.out.println("✅ Database Connected Successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
