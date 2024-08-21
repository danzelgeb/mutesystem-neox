package de.danzel34.mutesystem.database;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.*;

@RequiredArgsConstructor
@Data
public class MySQLConnector {

    private final String host;
    private final int port;
    private final String user;
    private final String database;

    @Getter(AccessLevel.PRIVATE)
    private final String password;

    private Connection connection;

    public void connect() {
        try {
            this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true",
                    this.user, this.password);
            System.out.println("Connected successfully to mysql-server");

            update("CREATE TABLE IF NOT EXISTS mutes(uuid VARCHAR(36), started VARCHAR(60), ending VARCHAR(60), UNIQUE (`uuid`))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(String qry) {
        ResultSet rs = null;
        try {
            Statement st = this.connection.createStatement();
            rs = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return rs;
    }

    public void update(String qry) {
        try {
            Statement st = this.connection.createStatement();
            st.executeUpdate(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
