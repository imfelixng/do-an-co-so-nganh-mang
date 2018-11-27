package com.ngquangan.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

public class UIManage extends JFrame {

    DefaultTableModel defaultTableModel;
    JTable tblCanBo;
    JTextField txtOnline;
    JTextField txtAll;
    JButton btnXem;
    JButton btnXoa;
    JButton btnAdd;
    JButton btnLogout;

    public UIManage(String title) {
        super(title);

        addControls();
        addEvents();
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
        defaultTableModel.addColumn("Số ĐT");
        defaultTableModel.addColumn("Email");
        defaultTableModel.addColumn("Phòng Ban");
        defaultTableModel.addColumn("Chức Vụ");
        defaultTableModel.addColumn("Online");

        tblCanBo = new JTable(defaultTableModel);
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
                UIInfo uiInfo = new UIInfo("Thông tin cán bộ");
                uiInfo.showWindow();
                dispose();
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
