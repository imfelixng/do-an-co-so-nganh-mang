package com.ngquangan.UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIAdd extends JFrame {


    JButton btnAddFile;
    JButton btnAddTay;
    JButton btnHuyBo;

    public UIAdd(String title) {

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
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));
        con.add(pn);

        JPanel pnChucNang = new JPanel();
        pnChucNang.setLayout(new FlowLayout());
        pn.add(pnChucNang);

        btnAddFile = new JButton("Thêm bằng file");
        pnChucNang.add(btnAddFile);

        btnAddTay = new JButton("Thêm thủ công");
        pnChucNang.add(btnAddTay);

        btnHuyBo = new JButton("Hủy bỏ");
        pn.add(btnHuyBo);

    }

    public void addEvents() {


        btnAddFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIAddFile uiAddFile = new UIAddFile("Thêm bằng file");
                uiAddFile.showWindow();
                dispose();
            }
        });

        btnAddTay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIAddTay uiAddTay = new UIAddTay("Thêm thủ công");
                uiAddTay.showWindow();
                dispose();
            }
        });

        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UIManage uiManage = new UIManage("Quản lý cán bộ");
                uiManage.showWindow();
                dispose();
            }
        });

    }

}
