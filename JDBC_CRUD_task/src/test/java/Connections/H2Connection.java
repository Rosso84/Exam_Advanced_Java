package Connections;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection implements ConnectionProvider {
    @Override
    public Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Connection connection = DriverManager
                .getConnection("jdbc:h2:~", "user", "non"
                );
        return connection;
    }
}
