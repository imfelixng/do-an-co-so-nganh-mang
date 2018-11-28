package com.ngquangan.Client;

import com.ngquangan.UI.UIClient;
import com.ngquangan.interfaces.ServerInterface;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] s) {

        UIClient ui = new UIClient("Đăng nhập");
        ui.showWindow();

    }



}
