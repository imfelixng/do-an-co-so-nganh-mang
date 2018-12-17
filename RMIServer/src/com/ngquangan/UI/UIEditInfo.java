package com.ngquangan.UI;

import com.ngquangan.Funtional.ValidateData;
import com.ngquangan.Server.ConnectDB;
import com.ngquangan.Server.ServerImpl;
import com.ngquangan.bean.CanBo;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class UIEditInfo extends JFrame {

    JTextField txtMaSo;
    JTextField txtHoTen;
    JTextField txtSoDT;
    JTextField txtEmail;
    JTextField txtPhongBan;
    JTextField txtChucVu;
    JTextField txtPassword;

    JRadioButton radNam;
    JRadioButton radNu;

    JDatePickerImpl datePicker;

    JButton btnCapNhat;
    JButton btnHuyBo;
    String username;
    ServerImpl server;
    CanBo cb = null;


    public UIEditInfo(String title, String username) {

        super(title);
        this.username = username;

        try {
            server = new ServerImpl();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        cb = server.getInfo(username);

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

        JLabel lblMaSo = new JLabel("Mã Số: ");
        pnMaSo.add(lblMaSo);

        txtMaSo = new JTextField(30);
        txtMaSo.setEditable(false);
        txtMaSo.setBackground(Color.LIGHT_GRAY);
        pnMaSo.add(txtMaSo);

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

        btnCapNhat = new JButton("Lưu lại");
        pnButton.add(btnCapNhat);

        btnHuyBo = new JButton("Hủy bỏ");
        pnButton.add(btnHuyBo);

        //set Data

        txtMaSo.setText(cb.getMaNV());
        txtHoTen.setText(cb.getTeNV());
        txtSoDT.setText(cb.getSoDT());
        txtEmail.setText(cb.getEmail());
        txtPhongBan.setText(cb.getPhongBan());
        txtChucVu.setText(cb.getChucVu());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(cb.getNgaySinh());

        model.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        model.setSelected(true);

        if(cb.isGioiTinh()) {
            radNam.setSelected(true);
        } else {
            radNu.setSelected(true);
        }

    }

    public void addEvents() {
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String hoten = txtHoTen.getText().trim();
                String sodt = txtSoDT.getText().trim();
                String email = txtEmail.getText().trim();
                String phongban = txtPhongBan.getText().trim();
                String chucvu = txtChucVu.getText().trim();
                String password = txtPassword.getText().trim();

                if(
                        !hoten.equals("") && !sodt.equals("")
                                && !email.equals("") && !phongban.equals("") && !chucvu.equals("")
                ) {

                    boolean check = false;
                    String error = "";

                    if(!ValidateData.checkTenCanBo(hoten).equals("")) {
                        error += ValidateData.checkTenCanBo(hoten) + "\n";
                        check = true;
                    }

                    if(!ValidateData.checkSoDT(sodt).equals("")) {
                        error += ValidateData.checkSoDT(sodt) + "\n";
                        check = true;
                    }

                    if(!ValidateData.checkEmail(email).equals("")) {
                        error += ValidateData.checkEmail(email) + "\n";
                        check = true;
                    }

                    if(!ValidateData.checkPhongBan(phongban).equals("")) {
                        error += ValidateData.checkPhongBan(phongban) + "\n";
                        check = true;
                    }

                    if(!ValidateData.checkChucVu(chucvu).equals("")) {
                        error += ValidateData.checkChucVu(chucvu) + "\n";
                        check = true;
                    }


                    if(!check) {

                        boolean gt = false;
                        if(radNam.isSelected()) {
                            gt = true;
                        }

                        if(radNu.isSelected()) {
                            gt = false;
                        }

                        Date selectedDate = (Date) datePicker.getModel().getValue();
                        java.sql.Date date = new java.sql.Date(selectedDate.getTime());

                        CanBo canBo = new CanBo();

                        canBo.setTeNV(hoten);
                        canBo.setNgaySinh(date);
                        canBo.setGioiTinh(gt);
                        canBo.setSoDT(sodt);
                        canBo.setEmail(email);
                        canBo.setPhongBan(phongban);
                        canBo.setChucVu(chucvu);
                        canBo.setUsername(username);

                        boolean checkUpdate = server.updateInfo(canBo);

                        if(checkUpdate) {
                            System.out.println("Update thông tin can bo thanh cong");
                            if(!password.equals("")) {

                                Connection cnn = null;
                                PreparedStatement preparedStatement = null;
                                ResultSet rs = null;

                                try {
                                    cnn = ConnectDB.connectDB();

                                    if(cnn == null) {
                                        JOptionPane.showMessageDialog(null, "Có lỗi xãy ra khi đổi mật khẩu, vui lòng thử lại sau!");
                                        return;
                                    }

                                    String sql = "UPDATE user SET Password = ? WHERE Username = ?";
                                    preparedStatement = cnn.prepareStatement(sql);
                                    preparedStatement.setString(1, password);
                                    preparedStatement.setString(2, username);

                                    int updatedRow = preparedStatement.executeUpdate();

                                    if(updatedRow > 0) {
                                        System.out.println("Thay đổi mật khẩu thành công!");
                                    }

                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                }

                            }

                            UIInfo uiInfo = new UIInfo("Thông tin cá nhân", username);
                            uiInfo.showWindow();
                            dispose();
                        }

                        UIInfo uiInfo = new UIInfo("Thông tin cán bộ", username);
                        uiInfo.showWindow();
                        dispose();

                    } else {
                        JOptionPane.showMessageDialog(null, error);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin");
                }

            }
        });

        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIInfo uiInfo = new UIInfo("Thông tin cán bộ", username);
                uiInfo.showWindow();
                dispose();
            }
        });
    }

}
