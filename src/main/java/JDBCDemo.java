import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3400/StudentsDB";
        String username = "root";
        String password = "Boonaniin34!";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection =
                    DriverManager.getConnection(url, username, password);

            System.out.println("Established Connection");

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}