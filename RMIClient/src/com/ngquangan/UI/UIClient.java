package com.ngquangan.UI;

import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UIClient extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;

    public UIClient(String title) {
        super(title);
        addControls();
        addEvents();
    }

    public void showWindow() {

        this.setSize(400, 250);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.setLocationRelativeTo(null);

        this.setResizable(false);

        this.setVisible(true);


    }

    public void addControls() {

        Container con = getContentPane();

        JPanel pn = new JPanel();
        pn.setLayout(new BoxLayout(pn, BoxLayout.Y_AXIS));

        JPanel pnUsername = new JPanel();
        pnUsername.setLayout(new FlowLayout());

        txtUsername = new JTextField(20);
        pnUsername.add(txtUsername);

        pn.add(pnUsername);

        JPanel pnPassword = new JPanel();
        pnPassword.setLayout(new FlowLayout());

        txtPassword = new JPasswordField(20);
        pnPassword.add(txtPassword);

        pn.add(pnPassword);

        JPanel pnLogin = new JPanel();
        pnLogin.setLayout(new FlowLayout());
        pn.add(pnLogin);

        btnLogin = new JButton("Login");
        pnLogin.add(btnLogin);

        con.add(pn);

    }

    public void addEvents() {

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = txtPassword.getText();

                if(!username.equals("") && !password.equals("")) {

                    UIInfo uiInfo = new UIInfo("Thông tin cá nhân");
                    uiInfo.showWindow();

                    dispose();


                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập username và mật khẩu!");
                }

            }
        });

    }
}
