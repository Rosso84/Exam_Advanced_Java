package ConnectionProvider;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class Connection {

    /**
     *
     * @return connection to database
     * @throws SQLException
     */
    public ConnectionSource getConnection()  {
        ConnectionSource con = null;
        try {
            con = new JdbcConnectionSource("jdbc:mysql://localhost:3306/world","root"," ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
