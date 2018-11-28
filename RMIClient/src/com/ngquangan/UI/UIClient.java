package com.ngquangan.UI;

import com.ngquangan.interfaces.ServerInterface;
import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class UIClient extends JFrame {

    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin;
    Remote lookup;
    ServerInterface serverInterface;

    public UIClient(String title) {
        super(title);
        addControls();
        addEvents();

        try {
            lookup = Naming.lookup("rmi://localhost/quanlycanbo");
            serverInterface = (ServerInterface) lookup;
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }

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

                    try {
                        boolean checkLogin = serverInterface.login(username, password, false);

                        if(checkLogin) {
                            UIInfo uiInfo = new UIInfo("Thông tin cá nhân", username);
                            uiInfo.showWindow();
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Sai username hoặc mật khẩu!");
                        }

                    } catch (RemoteException e1) {
                        e1.printStackTrace();
                    }




                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập username và mật khẩu!");
                }

            }
        });

    }


}
