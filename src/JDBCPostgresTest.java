//STEP 1. Import required packages
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCPostgresTest {

    public static void main(String[] args) {
        JDBCPostgres db = new JDBCPostgres();
        db.createConnection();
        db.getEmployeeList();
        db.getDistinctName();
    }
}