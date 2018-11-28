package com.ngquangan.UI;

import com.ngquangan.Funtional.ReadExel;
import com.ngquangan.Server.ConnectDB;
import com.ngquangan.bean.CanBo;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UIAddFile extends JFrame {

    JButton btnChonFile;
    JButton btnThem;
    JButton btnTroVe;
    JTextField txtTenFile;
    File file;

    public UIAddFile(String title) {

        super(title);

        addControls();
        addEvents();
    }

    public void showWindow() {
        setSize(400, 250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void addControls() {

        Container con = getContentPane();

        JPanel pn = new JPanel();
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        con.add(pn);

        JPanel pnChonFile = new JPanel();
        pnChonFile.setLayout(new FlowLayout());
        pn.add(pnChonFile);

        txtTenFile = new JTextField(20);
        txtTenFile.setEnabled(false);
        txtTenFile.setBackground(Color.LIGHT_GRAY);
        pnChonFile.add(txtTenFile);

        btnChonFile = new JButton("Chọn File");
        pnChonFile.add(btnChonFile);

        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new FlowLayout());
        pn.add(pnChucNang);

        btnThem = new JButton("Thêm cán bộ");
        pnChucNang.add(btnThem);

        btnTroVe = new JButton("Trở về");
        pnChucNang.add(btnTroVe);



    }

    public void addEvents() {


        btnChonFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.setFileFilter(new FileFilter() {
                    @Override
                    public boolean accept(File f) {
                        return f.getAbsolutePath().endsWith(".xls") || f.getAbsolutePath().endsWith(".xlsx");
                    }

                    @Override
                    public String getDescription() {
                        return "Tập tin xls, xlsx";
                    }
                });
                if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                    String nameFile = file.getPath();
                    txtTenFile.setText(nameFile);
                }

            }
        });


        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(file.getAbsolutePath().endsWith(".xls")) {

                    System.out.println("Bạn chọn file .xls");
                    ArrayList<CanBo> canBos = ReadExel.getDataFromXLS(file);

                    for(CanBo cb: canBos) {

                        Connection cnn = null;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultSet = null;
                        try {
                            cnn = ConnectDB.connectDB();
                            if (cnn == null) {
                                JOptionPane.showMessageDialog(null, "Đã xãy ra lỗi ở hệ thống, vui lòng thử lại sau!");
                            }


                            long dateInLong = cb.getNgaySinh().getTime();
                            java.sql.Date dateDB = new java.sql.Date(dateInLong);

                            String sql = "INSERT INTO nhanvien VALUES (?,?,?,?,?,?,?,?,?,?)";

                            preparedStatement = cnn.prepareStatement(sql);
                            preparedStatement.setString(1, cb.getMaNV());
                            preparedStatement.setString(2, cb.getTeNV());
                            preparedStatement.setDate(3, dateDB);
                            preparedStatement.setBoolean(4, cb.isGioiTinh());
                            preparedStatement.setString(5, cb.getSoDT());
                            preparedStatement.setString(6, cb.getEmail());
                            preparedStatement.setString(7, cb.getPhongBan());
                            preparedStatement.setString(8, cb.getChucVu());
                            preparedStatement.setString(9, cb.getUsername());
                            preparedStatement.setBoolean(10, cb.isOnline());

                            int executeUpdate = preparedStatement.executeUpdate();

                            if (executeUpdate > 0) {
                                preparedStatement = null;
                                String sql_user = "INSERT INTO user values(?,?,?)";
                                preparedStatement = cnn.prepareStatement(sql_user);
                                preparedStatement.setString(1, cb.getUsername());
                                preparedStatement.setString(2, cb.getUsername());
                                preparedStatement.setInt(3, 0);

                                int executeUpdateUser = preparedStatement.executeUpdate();

                                if (executeUpdateUser > 0) {

                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Thêm cán bộ thất bại, vui lòng thử lại sau!");
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }

                    }

                    UIManage uiManage = new UIManage("Quản lý cán bộ");
                    uiManage.showWindow();
                    dispose();

                } else if(file.getAbsolutePath().endsWith(".xlsx")){

                    System.out.println("Bạn chọn file .xlsx");

                    ArrayList<CanBo> canBos = ReadExel.getDataFromXLSX(file);


                    for(CanBo cb: canBos) {

                        Connection cnn = null;
                        PreparedStatement preparedStatement = null;
                        ResultSet resultSet = null;
                        try {
                            cnn = ConnectDB.connectDB();
                            if (cnn == null) {
                                JOptionPane.showMessageDialog(null, "Đã xãy ra lỗi ở hệ thống, vui lòng thử lại sau!");
                            }


                            long dateInLong = cb.getNgaySinh().getTime();
                            java.sql.Date dateDB = new java.sql.Date(dateInLong);

                            String sql = "INSERT INTO nhanvien VALUES (?,?,?,?,?,?,?,?,?,?)";

                            preparedStatement = cnn.prepareStatement(sql);
                            preparedStatement.setString(1, cb.getMaNV());
                            preparedStatement.setString(2, cb.getTeNV());
                            preparedStatement.setDate(3, dateDB);
                            preparedStatement.setBoolean(4, cb.isGioiTinh());
                            preparedStatement.setString(5, cb.getSoDT());
                            preparedStatement.setString(6, cb.getEmail());
                            preparedStatement.setString(7, cb.getPhongBan());
                            preparedStatement.setString(8, cb.getChucVu());
                            preparedStatement.setString(9, cb.getUsername());
                            preparedStatement.setBoolean(10, cb.isOnline());

                            int executeUpdate = preparedStatement.executeUpdate();

                            if (executeUpdate > 0) {
                                preparedStatement = null;
                                String sql_user = "INSERT INTO user values(?,?,?)";
                                preparedStatement = cnn.prepareStatement(sql_user);
                                preparedStatement.setString(1, cb.getUsername());
                                preparedStatement.setString(2, cb.getUsername());
                                preparedStatement.setInt(3, 0);

                                int executeUpdateUser = preparedStatement.executeUpdate();

                                if (executeUpdateUser > 0) {

                                }

                            } else {
                                JOptionPane.showMessageDialog(null, "Thêm cán bộ thất bại, vui lòng thử lại sau!");
                            }

                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }

                    }


                    UIManage uiManage = new UIManage("Quản lý cán bộ");
                    uiManage.showWindow();
                    dispose();
                } else{
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn file exel với đuôi .xls hoặc .xlsx!");
                }


            }
        });

        btnTroVe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIAdd uiAdd = new UIAdd("Thêm cán bộ");
                uiAdd.showWindow();
                dispose();
            }
        });

    }

}
