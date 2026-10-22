
import java.sql.*;
import java.util.Scanner;

public class JDBCDemo {

    private static final String URL =
            "jdbc:mysql://localhost:3400/StudentsDB";
    private static final String USER = "root";

    // Replace with your MySQL password locally.
    private static final String PASSWORD = "Boonaniin34!";

    public static void main(String[] args) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection =
                         DriverManager.getConnection(URL, USER, PASSWORD);
                 Scanner scanner = new Scanner(System.in)) {

                System.out.println("Database connected successfully!");

                createTable(connection);

                while (true) {
                    System.out.println("\n===== STUDENT CRUD MENU =====");
                    System.out.println("1. Create student");
                    System.out.println("2. Read all students");
                    System.out.println("3. Update student");
                    System.out.println("4. Delete student");
                    System.out.println("5. Exit");
                    System.out.print("Choose an option: ");

                    String choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            createStudent(connection, scanner);
                            break;
                        case "2":
                            readStudents(connection);
                            break;
                        case "3":
                            updateStudent(connection, scanner);
                            break;
                        case "4":
                            deleteStudent(connection, scanner);
                            break;
                        case "5":
                            System.out.println("Program closed.");
                            return;
                        default:
                            System.out.println("Invalid choice. Try again.");
                    }
                }

            }

        } catch (Exception e) {
            System.out.println("Database operation failed:");
            e.printStackTrace();
        }
    }

    // Create the table if it does not exist
    private static void createTable(Connection connection)
            throws SQLException {

        String sql = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT PRIMARY KEY,
                    firstname VARCHAR(100) NOT NULL,
                    lastname VARCHAR(100) NOT NULL,
                    grade DOUBLE NOT NULL
                )
                """;

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }

        System.out.println("Students table is ready.");
    }

    // CREATE: add a new student
    private static void createStudent(
            Connection connection, Scanner scanner)
            throws SQLException {

        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter first name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter grade: ");
        double grade = Double.parseDouble(scanner.nextLine());

        String sql = """
                INSERT INTO students (id, firstname, lastname, grade)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, firstname);
            ps.setString(3, lastname);
            ps.setDouble(4, grade);

            int rows = ps.executeUpdate();
            System.out.println(rows + " student added successfully.");
        }
    }

    // READ: display all students
    private static void readStudents(Connection connection)
            throws SQLException {

        String sql = "SELECT id, firstname, lastname, grade " +
                "FROM students ORDER BY id";

        try (Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            System.out.println("\nID | First name | Last name | Grade");
            System.out.println("-----------------------------------");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.printf("%d | %s | %s | %.2f%n",
                        rs.getInt("id"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getDouble("grade"));
            }

            if (!found) {
                System.out.println("No students found.");
            }
        }
    }

    // UPDATE: change a student's name and grade
    private static void updateStudent(
            Connection connection, Scanner scanner)
            throws SQLException {

        System.out.print("Enter the ID of the student to update: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter new last name: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter new grade: ");
        double grade = Double.parseDouble(scanner.nextLine());

        String sql = """
                UPDATE students
                SET firstname = ?, lastname = ?, grade = ?
                WHERE id = ?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, firstname);
            ps.setString(2, lastname);
            ps.setDouble(3, grade);
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student updated successfully.");
            } else {
                System.out.println("No student found with ID " + id);
            }
        }
    }

    // DELETE: remove a student by ID
    private static void deleteStudent(
            Connection connection, Scanner scanner)
            throws SQLException {

        System.out.print("Enter the ID of the student to delete: ");
        int id = Integer.parseInt(scanner.nextLine());

        String sql = "DELETE FROM students WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Student deleted successfully.");
            } else {
                System.out.println("No student found with ID " + id);
            }
        }
    }
}
