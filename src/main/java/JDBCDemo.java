
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBCDemo {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3400/StudentsDB";
        String username = "root";
        String password = "Boonaniin34!";

        try {
            // Step 1: Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Step 2: Connect Java to MySQL
            try (Connection connection =
                         DriverManager.getConnection(url, username, password)) {

                System.out.println("Established Connection");

                // Task 1: Create the students table
                createTable(connection);

                // Task 2: Insert one example and ten more students
                insertStudents(connection);

                // Task 3: Retrieve and display five students
                retrieveStudents(connection);

                // Task 4: Update a student's firstname
                updateStudentName(connection, 1, "UpdatedFirstName");

                // Task 5: Delete the student whose ID is 11
                deleteStudent(connection, 11);

                // Task 6: Calculate the average grade
                calculateAverageGrade(connection);

            } // Connection closes automatically here

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Task 1: Create the table
    public static void createTable(Connection connection) throws Exception {

        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INT PRIMARY KEY, " +
                "firstname VARCHAR(100), " +
                "lastname VARCHAR(100), " +
                "grade INT)";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }

        System.out.println("Students table created or already exists.");
    }

    // Task 2: Insert 11 students
    public static void insertStudents(Connection connection) throws Exception {

        String sql = "INSERT IGNORE INTO students " +
                "(id, firstname, lastname, grade) VALUES (?, ?, ?, ?)";

        String[][] students = {
                {"1", "John", "Doe", "90"},
                {"2", "Sara", "Ali", "85"},
                {"3", "Abebe", "Kebede", "78"},
                {"4", "Hana", "Bekele", "92"},
                {"5", "Dawit", "Mekonnen", "80"},
                {"6", "Marta", "Tesfaye", "88"},
                {"7", "Samuel", "Tadesse", "75"},
                {"8", "Rahel", "Getachew", "95"},
                {"9", "Daniel", "Alemu", "82"},
                {"10", "Liya", "Solomon", "89"},
                {"11", "Meron", "Abate", "84"}
        };

        try (PreparedStatement ps = connection.prepareStatement(sql)) {

            for (String[] student : students) {
                ps.setInt(1, Integer.parseInt(student[0]));
                ps.setString(2, student[1]);
                ps.setString(3, student[2]);
                ps.setInt(4, Integer.parseInt(student[3]));

                ps.executeUpdate();
            }
        }

        System.out.println("Student insertion completed.");
    }

    // Task 3: Retrieve five students
    public static void retrieveStudents(Connection connection) throws Exception {

        String sql = "SELECT * FROM students ORDER BY id LIMIT 5";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            System.out.println("\nFirst five students:");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Name: " + rs.getString("firstname") +
                                " " + rs.getString("lastname") +
                                ", Grade: " + rs.getInt("grade")
                );
            }
        }
    }

    // Task 4: Update a student's first name
    public static void updateStudentName(
            Connection connection, int id, String newFirstName)
            throws Exception {

        String sql = "UPDATE students SET firstname = ? WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newFirstName);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();
            System.out.println("\nUpdated student rows: " + rows);
        }
    }

    // Task 5: Delete a student
    public static void deleteStudent(Connection connection, int id)
            throws Exception {

        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rows = ps.executeUpdate();
            System.out.println("Deleted student rows: " + rows);
        }
    }

    // Task 6: Calculate the average grade
    public static void calculateAverageGrade(Connection connection)
            throws Exception {

        String sql = "SELECT AVG(grade) AS averageGrade FROM students";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            if (rs.next()) {
                System.out.printf(
                        "Average grade: %.2f%n",
                        rs.getDouble("averageGrade")
                );
            }
        }
    }
}
