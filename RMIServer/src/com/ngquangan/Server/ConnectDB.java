package com.ngquangan.Server;

import java.sql.*;

public class ConnectDB {



    public static Connection connectDB() throws ClassNotFoundException, SQLException {

        String url = "jdbc:mysql://localhost/quanlycanbo";
        String username = "root";
        String password = "Haimuoithang8@";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection cnn = DriverManager.getConnection(url, username, password);
        return cnn;

    }

    public static void closeConnection(Connection cnn, PreparedStatement preparedStatement, ResultSet rs) throws SQLException {

        if(rs != null) {
            rs.close();
        }

        if(preparedStatement != null) {
            preparedStatement.close();
        }

        if(cnn != null) {
            cnn.close();
        }

    }

}
