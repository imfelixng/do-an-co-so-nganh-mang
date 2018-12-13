package com.ngquangan.UI;

import com.ngquangan.Server.ConnectDB;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class UIAddTay extends JFrame {

    JTextField txtHoTen;

    JTextField txtSoDT;
    JTextField txtEmail;
    JTextField txtPhongBan;
    JTextField txtChucVu;
    JTextField txtUsername;
    JTextField txtPassword;

    JRadioButton radNam;
    JRadioButton radNu;

    JDatePickerImpl datePicker;

    JButton btnThem;
    JButton btnHuyBo;

    public UIAddTay(String title) {

        super(title);

        addControls();
        addEvents();
    }

    public void showWindow() {
        setSize(600, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void addControls() {

        Container con = getContentPane();

        JPanel pn = new JPanel();
        pn.setLayout(new BorderLayout());
        con.add(pn);

        JPanel pnCenter = new JPanel();
        pn.add(pnCenter, BorderLayout.CENTER);

        JPanel pnMaSo = new JPanel();
        pnMaSo.setLayout(new FlowLayout());
        pnCenter.add(pnMaSo);

        JPanel pnHoTen = new JPanel();
        pnHoTen.setLayout(new FlowLayout());
        pnCenter.add(pnHoTen);

        JLabel lblHoTen = new JLabel("Họ Tên: ");
        pnHoTen.add(lblHoTen);

        txtHoTen = new JTextField(30);
        pnHoTen.add(txtHoTen);

        JPanel pnNgaySinh = new JPanel();
        pnNgaySinh.setLayout(new FlowLayout());
        pnCenter.add(pnNgaySinh);

        JLabel lblNgaySinh = new JLabel("Ngày Sinh: ");
        pnNgaySinh.add(lblNgaySinh);

        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        pnNgaySinh.add(datePicker);

        JPanel pnGioiTinh = new JPanel();
        pnGioiTinh.setLayout(new BoxLayout(pnGioiTinh, BoxLayout.X_AXIS));
        pnCenter.add(pnGioiTinh);

        JLabel lblGioiTinh = new JLabel("Giới Tính: ");
        pnGioiTinh.add(lblGioiTinh);

        radNam = new JRadioButton("Nam");
        radNu = new JRadioButton("Nữ");

        ButtonGroup groupGioiTinh = new ButtonGroup();
        groupGioiTinh.add(radNam);
        groupGioiTinh.add(radNu);

        pnGioiTinh.add(radNam);
        pnGioiTinh.add(radNu);

        JPanel pnSoDT = new JPanel();
        pnSoDT.setLayout(new FlowLayout());
        pnCenter.add(pnSoDT);

        JLabel lblSoDT = new JLabel("Số ĐT: ");
        pnSoDT.add(lblSoDT);

        txtSoDT = new JTextField(30);
        pnSoDT.add(txtSoDT);

        JPanel pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout());
        pnCenter.add(pnEmail);

        JLabel lblEmail = new JLabel("Email: ");
        pnEmail.add(lblEmail);

        txtEmail = new JTextField(30);
        pnEmail.add(txtEmail);

        JPanel pnPhongBan = new JPanel();
        pnPhongBan.setLayout(new FlowLayout());
        pnCenter.add(pnPhongBan);

        JLabel lblPhongBan = new JLabel("Phòng Ban: ");
        pnPhongBan.add(lblPhongBan);

        txtPhongBan = new JTextField(30);
        pnPhongBan.add(txtPhongBan);

        JPanel pnChucVu = new JPanel();
        pnChucVu.setLayout(new FlowLayout());
        pnCenter.add(pnChucVu);

        JLabel lblChucVu = new JLabel("Chức Vụ: ");
        pnChucVu.add(lblChucVu);

        txtChucVu = new JTextField(30);
        pnChucVu.add(txtChucVu);


        JPanel pnUsername = new JPanel();
        pnUsername.setLayout(new FlowLayout());
        pnCenter.add(pnUsername);

        JLabel lblUsername = new JLabel("Username: ");
        pnUsername.add(lblUsername);

        txtUsername = new JTextField(30);
        pnUsername.add(txtUsername);

        JPanel pnPassword = new JPanel();
        pnPassword.setLayout(new FlowLayout());
        pnCenter.add(pnPassword);

        JLabel lblPassword = new JLabel("Password: ");
        pnPassword.add(lblPassword);

        txtPassword = new JTextField(30);
        pnPassword.add(txtPassword);

        JPanel pnBottom = new JPanel();
        pn.add(pnBottom, BorderLayout.SOUTH);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        pnBottom.add(pnButton);

        btnThem = new JButton("Thêm");
        pnButton.add(btnThem);

        btnHuyBo = new JButton("Trở về");
        pnButton.add(btnHuyBo);

        //set data for giới tính

        radNam.setSelected(true);

    }

    public void addEvents() {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String hoten = txtHoTen.getText().trim();
                String sodt = txtSoDT.getText().trim();
                String email = txtEmail.getText().trim();
                String phongban = txtPhongBan.getText().trim();
                String chucvu = txtChucVu.getText().trim();
                String username = txtUsername.getText().trim();
                String password = txtPassword.getText().trim();
                boolean online  = false;
                String macanbo = "cb_" + System.currentTimeMillis();

                if(
                        !hoten.equals("") && !sodt.equals("")
                        && !email.equals("") && !phongban.equals("") && !chucvu.equals("") && !username.equals("")
                        && !password.equals("")
                ) {

                    Connection cnn = null;
                    PreparedStatement preparedStatement = null;
                    ResultSet resultSet = null;
                    try {
                        cnn = ConnectDB.connectDB();
                        if(cnn == null) {
                            JOptionPane.showMessageDialog(null, "Đã xãy ra lỗi ở hệ thống, vui lòng thử lại sau!");
                        }

                        boolean gt = false;
                        if(radNam.isSelected()) {
                            gt = true;
                        }

                        if(radNu.isSelected()) {
                            gt = false;
                        }

                        java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                        java.sql.Date date = new java.sql.Date(selectedDate.getTime());

                        String sql = "INSERT INTO nhanvien VALUES (?,?,?,?,?,?,?,?,?,?)";

                        preparedStatement = cnn.prepareStatement(sql);
                        preparedStatement.setString(1, macanbo);
                        preparedStatement.setString(2, hoten);
                        preparedStatement.setDate(3, date);
                        preparedStatement.setBoolean(4, gt);
                        preparedStatement.setString(5, sodt);
                        preparedStatement.setString(6, email);
                        preparedStatement.setString(7, phongban);
                        preparedStatement.setString(8, chucvu);
                        preparedStatement.setString(9, username);
                        preparedStatement.setBoolean(10, online);

                        int executeUpdate = preparedStatement.executeUpdate();

                        if(executeUpdate > 0) {
                            preparedStatement = null;
                            String sql_user = "INSERT INTO user values(?,?,?)";
                            preparedStatement = cnn.prepareStatement(sql_user);
                            preparedStatement.setString(1, username);
                            preparedStatement.setString(2, password);
                            preparedStatement.setInt(3, 0);

                            int executeUpdateUser = preparedStatement.executeUpdate();

                            if(executeUpdateUser > 0) {
                                UIManage uiManage = new UIManage("Quản lý cán bộ");
                                uiManage.showWindow();
                                dispose();
                            }

                        } else {
                            JOptionPane.showMessageDialog(null, "Thêm cán bộ thất bại, vui lòng thử lại sau!");
                        }


                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } finally {
                        try {
                            ConnectDB.closeConnection(cnn, preparedStatement, resultSet);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }

                } else {

                    JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
                }

            }
        });

        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIAdd uiAdd = new UIAdd("Thêm cán bộ");
                uiAdd.showWindow();
                dispose();
            }
        });
    }

}
