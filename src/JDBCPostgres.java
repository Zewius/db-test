import java.math.BigDecimal;
import java.sql.*;

public class JDBCPostgres {

    private final String DB_URL;
    private final String USER;
    private final String PASS;
    private Connection connection;

    public JDBCPostgres() {
        DB_URL = ""; //URL Address;
        USER = ""; //Username
        PASS = ""; //Password
    }

    public JDBCPostgres(String dbUrl, String user, String pass) {
        DB_URL = dbUrl;
        USER = user;
        PASS = pass;
    }

    public void createConnection() {
        if (driverIsFound()) {
            try {
                this.connection = DriverManager
                        .getConnection(DB_URL, USER, PASS);
            } catch (SQLException e) {
                System.out.println("Connection Failed");
                e.printStackTrace();
            }
        }
    }

    private boolean driverIsFound() {
        try {
            Class.forName("org.postgresql.Driver");
            return true;
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            return false;
        }
    }

    public void getEmployeeList() {
        String SQL_SELECT = "SELECT * FROM java_task.employee";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong("ID");
                String name = resultSet.getString("NAME");
                BigDecimal salary = resultSet.getBigDecimal("SALARY");
                Timestamp createdDate = resultSet.getTimestamp("CREATED_DATE");
                System.out.println(id + ", " + name + ", " + salary + ", " + createdDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}