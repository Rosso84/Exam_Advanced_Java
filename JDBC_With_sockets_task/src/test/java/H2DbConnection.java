import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2DbConnection extends DbConnection implements ConnectionProvider{


    /**
     * Connects to H2's embedded database for testing methods
     *
     * @throws Exception
     */


    public H2DbConnection()throws Exception {
    }

    /**
     *
     * @return a connection to the embedded Database system for testing methods.
     * @throws IOException if input of 'user' data and 'password' is wrong
     * @throws SQLException if sqql error occurs
     * @throws ClassNotFoundException if H2's driver fails.
     */
    @Override
    public Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager.getConnection("jdbc:h2:~", "user", "non");
        return connection;
    }
}
