package de.wevsvirus.configuration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

@Configuration
@Profile({"prod"})

//Vermutlich muss das hier von was AWS spezifischem erben extends...
public class CloudConfiguration  {

    private static final Log LOG = LogFactory.getLog(CloudConfiguration.class);

    @Bean
    public DataSource dataSource(Environment environment, DataSourceProperties dataSourceProperties) {

      return new DataSource() {
          @Override
          public Connection getConnection() throws SQLException {
              return null;
          }

          @Override
          public Connection getConnection(String username, String password) throws SQLException {
              return null;
          }

          @Override
          public PrintWriter getLogWriter() throws SQLException {
              return null;
          }

          @Override
          public void setLogWriter(PrintWriter out) throws SQLException {

          }

          @Override
          public void setLoginTimeout(int seconds) throws SQLException {

          }

          @Override
          public int getLoginTimeout() throws SQLException {
              return 0;
          }

          @Override
          public <T> T unwrap(Class<T> iface) throws SQLException {
              return null;
          }

          @Override
          public boolean isWrapperFor(Class<?> iface) throws SQLException {
              return false;
          }

          @Override
          public Logger getParentLogger() throws SQLFeatureNotSupportedException {
              return null;
          }
      };
    }

}
