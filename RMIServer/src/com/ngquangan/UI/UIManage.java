package com.ngquangan.UI;

import com.ngquangan.Server.ConnectDB;
import com.ngquangan.bean.CanBo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UIManage extends JFrame {

    DefaultTableModel defaultTableModel;
    JTable tblCanBo;
    JTextField txtOnline;
    JTextField txtAll;
    JButton btnXem;
    JButton btnXoa;
    JButton btnAdd;
    JButton btnLogout;
    ArrayList<CanBo> canBos;


    public UIManage(String title) {
        super(title);

        getCanBos();

        addControls();
        addEvents();
    }

    private void getCanBos() {

        Connection cnn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        canBos = new ArrayList<>();

        try {
            cnn = ConnectDB.connectDB();

            if(cnn == null) {
                System.out.println("Connect DB failed");
                return;
            }

            String sql = "SELECT * FROM nhanvien";
            preparedStatement = cnn.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                CanBo canBo = new CanBo();

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

                canBos.add(canBo);
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

    }


    public void showWindow() {
        this.setSize(600, 500);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);

        this.setResizable(false);

        this.setVisible(true);
    }

    public void addControls() {

        Container con = getContentPane();

        JPanel pn = new JPanel();
        pn.setLayout(new BorderLayout());
        con.add(pn);


        defaultTableModel = new DefaultTableModel();
        defaultTableModel.addColumn("STT");
        defaultTableModel.addColumn("Mã Cán Bộ");
        defaultTableModel.addColumn("Họ Tên");
        defaultTableModel.addColumn("Ngày Sinh");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("Username");
        defaultTableModel.addColumn("Online");

        tblCanBo = new JTable(defaultTableModel);
        int i = 0;
        for(CanBo cb : canBos) {
            i++;
            String[] cbInfo = {
                    i + "",
                    cb.getMaNV() + "",
                    cb.getTeNV()+ "",
                    cb.getNgaySinh() + "",
                    cb.getEmail() + "",
                    cb.getUsername() + "",
                    cb.isOnline() ? "Có" : "Không"
            };

            defaultTableModel.addRow(cbInfo);
        }

        JScrollPane scTable = new JScrollPane(
            tblCanBo,
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS
        );

        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BoxLayout(pnCenter, BoxLayout.Y_AXIS));
        pnCenter.add(scTable);
        pn.add(pnCenter, BorderLayout.CENTER);

        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new FlowLayout());
        pnCenter.add(pnChucNang);

        btnXem = new JButton("Xem chi tiết");
        pnChucNang.add(btnXem);

        btnXoa = new JButton("Xóa");
        pnChucNang.add(btnXoa);

        JPanel pnBottom = new JPanel();
        pnBottom.setLayout(new BoxLayout(pnBottom, BoxLayout.Y_AXIS));
        pnBottom.setPreferredSize(new Dimension(0, 120));
        pn.add(pnBottom, BorderLayout.SOUTH);

        JPanel pnOnline = new JPanel();
        pnOnline.setLayout(new FlowLayout());
        pnBottom.add(pnOnline);

        JLabel lblOnline = new JLabel("Online");
        pnOnline.add(lblOnline);

        txtOnline = new JTextField(5);
        txtOnline.setEnabled(false);
        txtOnline.setBackground(Color.LIGHT_GRAY);
        pnOnline.add(txtOnline);

        JPanel pnAll = new JPanel();
        pnAll.setLayout(new FlowLayout());
        pnBottom.add(pnAll);

        JLabel lblAll = new JLabel("All");
        pnAll.add(lblAll);

        txtAll = new JTextField(5);
        txtAll.setEnabled(false);
        txtAll.setBackground(Color.LIGHT_GRAY);
        pnAll.add(txtAll);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        pnBottom.add(pnButton);

        btnAdd = new JButton("Thêm cán bộ");
        pnButton.add(btnAdd);

        btnLogout = new JButton("Log out");
        pnButton.add(btnLogout);

        //get Data

        int online = 0;

        for(CanBo cb: canBos) {

            if(cb.isOnline()) {
                online += 1;
            }

        }

        txtOnline.setText(online + "");

        txtAll.setText(defaultTableModel.getRowCount() + "");
    }

    private void addEvents() {

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIAdd uiAdd = new UIAdd("Thêm cán bộ");
                uiAdd.showWindow();
                dispose();
            }
        });

        btnXem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tblCanBo.getSelectedRow();
                if(i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn cán bộ!");
                    return;
                }
                UIInfo uiInfo = new UIInfo("Thông tin cán bộ", canBos.get(i).getUsername());
                uiInfo.showWindow();
                dispose();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = tblCanBo.getSelectedRow();
                if(i == -1) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn cán bộ!");
                    return;
                }
                int ret = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa cán bộ này ?", "Xóa cán bộ", JOptionPane.YES_NO_OPTION);
                if(ret == JOptionPane.YES_OPTION) {

                    String username = canBos.get(i).getUsername();
                    Connection cnn = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet rs = null;

                    try {
                        cnn = ConnectDB.connectDB();
                        if(cnn == null) return;

                        String sql = "DELETE FROM nhanvien WHERE username = ?";
                        preparedStatement = cnn.prepareStatement(sql);
                        preparedStatement.setString(1, username);

                        int deletedRow = preparedStatement.executeUpdate();

                        if(deletedRow > 0) {
                            System.out.println("Delete can bo thanh cong");
                            preparedStatement = null;
                            String sql_user = "DELETE FROM user WHERE username = ?";
                            preparedStatement = cnn.prepareStatement(sql_user);
                            preparedStatement.setString(1, username);

                            int deletedRowUser = preparedStatement.executeUpdate();

                            if(deletedRowUser > 0) {
                                System.out.println("Delete user thanh cong");
                                defaultTableModel.removeRow(i);
                                UIManage uiManage = new UIManage("Quản lý cán bộ");
                                uiManage.showWindow();
                                dispose();
                            }


                        }

                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }



            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UIServer uiServer = new UIServer("Đăng nhập");
                uiServer.showWindow();
                dispose();

            }
        });

    }

}
