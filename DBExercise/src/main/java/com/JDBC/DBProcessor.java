package com.JDBC;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Анна on 03.08.2016.
 */


public class DBProcessor {
    private Connection connection;

    public DBProcessor() throws SQLException {
        DriverManager.registerDriver(new FabricMySQLDriver());
    }

    public Connection getConnection(String url, String username, String password) throws SQLException {
        if(connection!=null){
            return connection;
        }else {
            connection=DriverManager.getConnection(url,username,password);
            return connection;
        }

    }
}


