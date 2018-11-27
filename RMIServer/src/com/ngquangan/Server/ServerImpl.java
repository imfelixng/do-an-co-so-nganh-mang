package com.ngquangan.Server;

import com.ngquangan.bean.CanBo;
import com.ngquangan.interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

    public ServerImpl() throws RemoteException {
    }

    @Override
    public boolean login(String username, String password, boolean role) {
        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            cnn = ConnectDB.connectDB();

            if( cnn == null) {
                return false;
            }

            System.out.println("Connect db success");

            String sql = "SELECT * FROM user WHERE username = ? AND password = ? AND role = ? ";

            preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setBoolean(3, role);

            rs = preparedStatement.executeQuery();

            if(rs.next()) {
                System.out.println("Chính xác");
                return true;
            } else {
                return false;
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                ConnectDB.closeConnection(cnn, preparedStatement, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return false;
    }

    @Override
    public boolean logout(String s) {
        return false;
    }

    @Override
    public boolean updateInfo(CanBo canBo) {
        return false;
    }

    @Override
    public CanBo getInfo(String s) {
        return null;
    }
}
