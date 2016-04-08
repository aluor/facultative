package by.pvt.aliushkevich.dataSources;

/**
 * Created by Rabotnik on 03.04.2016.
 */

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {

  private static DataSource datasource;
  private ComboPooledDataSource cpds;

  private DataSource() throws IOException, SQLException, PropertyVetoException {
    cpds = new ComboPooledDataSource();
    cpds.setDriverClass("com.mysql.jdbc.Driver"); //loads the jdbc driver
    cpds.setJdbcUrl("jdbc:mysql://localhost:3306/facultative");
    cpds.setUser("root");
    cpds.setPassword("root");
    // the settings below are optional -- c3p0 can work with defaults
    cpds.setMinPoolSize(5);
    cpds.setAcquireIncrement(5);
    cpds.setMaxPoolSize(20);
    cpds.setMaxStatements(180);

  }

  public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
    if (datasource == null) {
      datasource = new DataSource();
      return datasource;
    } else {
      return datasource;
    }
  }

  public Connection getConnection() throws SQLException {
    return this.cpds.getConnection();
  }
}