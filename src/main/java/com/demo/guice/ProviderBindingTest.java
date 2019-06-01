package com.demo.guice;

import com.google.inject.*;

import java.sql.*;

/**
 * @author make
 * @creare 14/10/2018
 */
public class ProviderBindingTest extends AbstractModule {
    public static void main(String[] args) throws SQLException {

        Injector injector = Guice.createInjector(new ProviderBindingTest());

//        Connection con = injector.getInstance(Connection.class);

        Provider<Connection> p = injector.getProvider(Connection.class);
        Connection con = p.get();

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {

            System.out.println(rs.getString(1));
        }

        rs.close();
        con.close();



    }

    @Override
    protected void configure() {
        bind(String.class).toInstance("jdbc:mysql://localhost:3306/ambari?user=root&password=root");
        bind(Connection.class).toProvider(ProviderBindTestClass.class);
    }
}

class ProviderBindTestClass implements Provider<Connection> {

    private String url;

    @Inject
    public ProviderBindTestClass(String url) {
        this.url = url;
    }

    @Override
    public Connection get() {
        try {
            Connection connection = DriverManager.getConnection(url);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}