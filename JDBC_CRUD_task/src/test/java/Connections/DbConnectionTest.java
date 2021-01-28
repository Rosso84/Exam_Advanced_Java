package Connections;

import org.junit.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertNotNull;

public class DbConnectionTest {
    H2Connection h2 = new H2Connection();

    @Test
    public void tesConnectionWithoutUserInput() {
        DbConnection db = new DbConnection();
        try {
            assertNotNull(db.getConnection());
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


}
