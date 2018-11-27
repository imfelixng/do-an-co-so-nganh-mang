package com.ngquangan.Server;

import com.ngquangan.UI.UIServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] s) throws RemoteException, MalformedURLException {

        ServerImpl server = new ServerImpl();

        LocateRegistry.createRegistry(1099);

        Naming.rebind("rmi://localhost/quanlycanbo", server);

        UIServer uiServer = new UIServer("Đăng nhập");
        uiServer.showWindow();

    }

}
