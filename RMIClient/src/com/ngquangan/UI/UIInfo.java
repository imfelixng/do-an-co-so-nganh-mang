package com.ngquangan.UI;

import com.ngquangan.bean.CanBo;
import com.ngquangan.interfaces.ServerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class UIInfo extends JFrame {

    JLabel lblXinChao;
    JTextField txtMaSo;
    JTextField txtHoTen;
    JTextField txtNgaySinh;
    JTextField txtGioiTinh;
    JTextField txtSoDT;
    JTextField txtEmail;
    JTextField txtPhongBan;
    JTextField txtChucVu;

    JButton btnCapNhat;
    JButton btnLogout;

    Remote lookup;
    ServerInterface serverInterface;
    CanBo cb = null;
    String username;

    public  UIInfo(String title, String username) {
        super(title);
        this.username = username;
        try {
            lookup = Naming.lookup("rmi://localhost/quanlycanbo");
            serverInterface = (ServerInterface) lookup;

            //get Data

            UIClient uiClient = new UIClient("");
            if(!username.equals("")) {
                try {
                    cb = serverInterface.getInfo(username);



                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

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

        JPanel pnTop = new JPanel();
        pn.add(pnTop, BorderLayout.NORTH);

        lblXinChao = new JLabel("Xin Chào: ");
        pnTop.add(lblXinChao);

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
        txtHoTen.setEditable(false);
        txtHoTen.setBackground(Color.LIGHT_GRAY);
        pnHoTen.add(txtHoTen);

        JPanel pnNgaySinh = new JPanel();
        pnNgaySinh.setLayout(new FlowLayout());
        pnCenter.add(pnNgaySinh);

        JLabel lblNgaySinh = new JLabel("Ngày Sinh: ");
        pnNgaySinh.add(lblNgaySinh);

        txtNgaySinh = new JTextField(30);
        txtNgaySinh.setEditable(false);
        txtNgaySinh.setBackground(Color.LIGHT_GRAY);
        pnNgaySinh.add(txtNgaySinh);

        JPanel pnGioiTinh = new JPanel();
        pnGioiTinh.setLayout(new FlowLayout());
        pnCenter.add(pnGioiTinh);

        JLabel lblGioiTinh = new JLabel("Giới Tính: ");
        pnGioiTinh.add(lblGioiTinh);

        txtGioiTinh = new JTextField(30);
        txtGioiTinh.setEditable(false);
        txtGioiTinh.setBackground(Color.LIGHT_GRAY);
        pnGioiTinh.add(txtGioiTinh);

        JPanel pnSoDT = new JPanel();
        pnSoDT.setLayout(new FlowLayout());
        pnCenter.add(pnSoDT);

        JLabel lblSoDT = new JLabel("Số ĐT: ");
        pnSoDT.add(lblSoDT);

        txtSoDT = new JTextField(30);
        txtSoDT.setEditable(false);
        txtSoDT.setBackground(Color.LIGHT_GRAY);
        pnSoDT.add(txtSoDT);

        JPanel pnEmail = new JPanel();
        pnEmail.setLayout(new FlowLayout());
        pnCenter.add(pnEmail);

        JLabel lblEmail = new JLabel("Email: ");
        pnEmail.add(lblEmail);

        txtEmail = new JTextField(30);
        txtEmail.setEditable(false);
        txtEmail.setBackground(Color.LIGHT_GRAY);
        pnEmail.add(txtEmail);

        JPanel pnPhongBan = new JPanel();
        pnPhongBan.setLayout(new FlowLayout());
        pnCenter.add(pnPhongBan);

        JLabel lblPhongBan = new JLabel("Phòng Ban: ");
        pnPhongBan.add(lblPhongBan);

        txtPhongBan = new JTextField(30);
        txtPhongBan.setEditable(false);
        txtPhongBan.setBackground(Color.LIGHT_GRAY);
        pnPhongBan.add(txtPhongBan);

        JPanel pnChucVu = new JPanel();
        pnChucVu.setLayout(new FlowLayout());
        pnCenter.add(pnChucVu);

        JLabel lblChucVu = new JLabel("Chức Vụ: ");
        pnChucVu.add(lblChucVu);

        txtChucVu = new JTextField(30);
        txtChucVu.setEditable(false);
        txtChucVu.setBackground(Color.LIGHT_GRAY);
        pnChucVu.add(txtChucVu);


        JPanel pnBottom = new JPanel();
        pn.add(pnBottom, BorderLayout.SOUTH);

        JPanel pnButton = new JPanel();
        pnButton.setLayout(new FlowLayout());
        pnBottom.add(pnButton);

        btnCapNhat = new JButton("Cập nhật");
        pnButton.add(btnCapNhat);

        btnLogout = new JButton("Log out");
        pnButton.add(btnLogout);


        //setData

        lblXinChao.setText(lblXinChao.getText() + cb.getTeNV() + "");
        txtMaSo.setText(cb.getMaNV());
        txtHoTen.setText(cb.getTeNV());
        txtNgaySinh.setText(cb.getNgaySinh().toString());
        txtGioiTinh.setText(cb.isGioiTinh() ? "Nam" : "Nữ");
        txtSoDT.setText(cb.getSoDT());
        txtEmail.setText(cb.getEmail());
        txtPhongBan.setText(cb.getPhongBan());
        txtChucVu.setText(cb.getChucVu());

    }

    public void addEvents() {

        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIEditInfo uiEditInfo = new UIEditInfo("Cập nhật thông tin", username);
                uiEditInfo.showWindow();
                dispose();
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean checkLogout = serverInterface.logout(username);
                    if(checkLogout) {
                        UIClient uiClient = new UIClient("Đăng nhập");
                        uiClient.showWindow();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi, vui lòng thử lại sau!");
                    }
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {


            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    serverInterface.logout(username);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }


        });

    }

}
