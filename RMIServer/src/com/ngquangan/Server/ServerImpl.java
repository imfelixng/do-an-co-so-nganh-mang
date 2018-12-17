package com.ngquangan.Server;

import com.ngquangan.bean.CanBo;
import com.ngquangan.interfaces.ServerInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
                preparedStatement = null;
                String sql_update = "UPDATE nhanvien SET Online = ? WHERE Username = ?";
                preparedStatement = cnn.prepareStatement(sql_update);
                preparedStatement.setBoolean(1, true);
                preparedStatement.setString(2, username);

                int updatedRow = preparedStatement.executeUpdate();

                if(updatedRow > 0) {
                    System.out.println("Update thành công");
                }

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
    public boolean logout(String username) {
        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            cnn = ConnectDB.connectDB();

            if( cnn == null) {
                return false;
            }

            System.out.println("Connect db success");

            String sql_update = "UPDATE nhanvien SET Online = ? WHERE Username = ?";
            preparedStatement = cnn.prepareStatement(sql_update);
            preparedStatement.setBoolean(1, false);
            preparedStatement.setString(2, username);

            int updatedRow = preparedStatement.executeUpdate();

            if(updatedRow > 0) {
                System.out.println("Update thành công");
                return true;
            }
            return false;

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
    public boolean updateInfo(CanBo canBo) {
        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        boolean status = false;

        try {
            cnn = ConnectDB.connectDB();

            if(cnn == null) return false;

            long dateInLong = canBo.getNgaySinh().getTime();

            java.sql.Date dateDB = new java.sql.Date(dateInLong);

            String sql = "UPDATE nhanvien SET TenNV = ?, NgaySinh = ?, GioiTinh = ?, SoDT = ?, Email = ?, PhongBan = ?, ChucVu = ? WHERE Username = ?";
            preparedStatement = cnn.prepareStatement(sql);
            preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setString(1, canBo.getTeNV());
            preparedStatement.setDate(2, dateDB);
            preparedStatement.setBoolean(3, canBo.isGioiTinh());
            preparedStatement.setString(4, canBo.getSoDT());
            preparedStatement.setString(5, canBo.getEmail());
            preparedStatement.setString(6, canBo.getPhongBan());
            preparedStatement.setString(7, canBo.getChucVu());
            preparedStatement.setString(8, canBo.getUsername());

            int updatedRow = preparedStatement.executeUpdate();

            if(updatedRow > 0) {
                System.out.println("Update thanh cong!");
                status = true;
            } else {
                status = false;
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
            return status;
        }

    }

    @Override
    public CanBo getInfo(String s) {

        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        CanBo canBo = null;

        try {
            cnn = ConnectDB.connectDB();

            if(cnn == null) return null;

            String sql = "SELECT * FROM nhanvien WHERE Username = ?";
            preparedStatement = cnn.prepareStatement(sql);
            preparedStatement.setString(1, s);

            rs = preparedStatement.executeQuery();

            if(rs.next()) {
                canBo = new CanBo();

                canBo.setMaNV(rs.getString("MaNV"));
                canBo.setTeNV(rs.getString("TenNV"));
                canBo.setNgaySinh(rs.getDate("NgaySinh"));
                canBo.setGioiTinh(rs.getBoolean("GioiTinh"));
                canBo.setSoDT(rs.getString("SoDT"));
                canBo.setEmail(rs.getString("Email"));
                canBo.setPhongBan(rs.getString("PhongBan"));
                canBo.setChucVu(rs.getString("ChucVu"));
                canBo.setUsername(rs.getString("Username"));
                canBo.setOnline(rs.getBoolean("Online"));

                return canBo;
            }
            return null;

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
            return canBo;
        }

    }

    public static ArrayList<String> getUsernames () {

        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        ArrayList<String> usernames = new ArrayList<>();

        try {
            cnn = ConnectDB.connectDB();
            if(cnn == null) return usernames;

            String sql = "SELECT Username from nhanvien";

            preparedStatement = cnn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                usernames.add(rs.getString("Username"));
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
            return usernames;
        }

    }

}
