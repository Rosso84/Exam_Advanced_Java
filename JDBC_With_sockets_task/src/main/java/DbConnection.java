/**
 * Date: 02.10.2017
 * Subject: Exam part1 in PGR200 Advanced Java
 * Agenda: JDBC , Maven, CRUD, Server-ClientTest operations
 * Name: Roosbeh Morad
 * java version 1.8
 * Mysql version: 1.5.7
 */


import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Roozbeh Morandi
 *
 * This class will connect to the database with data
 * given from Properties file
 */


public class DbConnection implements ConnectionProvider {


    private MysqlDataSource datasource = new MysqlDataSource();


    /**
     * @throws Exception if connection fails due to incorrect password, userName or hostName
     */
    public DbConnection() throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/java/ConnectionSource.properties"));
        String password = properties.getProperty("password");
        String serverName = properties.getProperty("host");
        String user = properties.getProperty("user");
        datasource.setPassword(password);
        datasource.setUser(user);
        datasource.setServerName(serverName);
    }


    /**
     *
     * @throws IOException if Connection fails du to wrong password, User or hostName
     */
    @Override
    public Connection getConnection() throws IOException, SQLException, ClassNotFoundException {
        return datasource.getConnection();
    }
}