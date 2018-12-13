package com.ngquangan.interfaces;

import com.ngquangan.bean.CanBo;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInterface extends Remote {

    public boolean login(String username, String password, boolean role) throws RemoteException;

    public boolean logout(String username) throws RemoteException;

    public boolean updateInfo(CanBo canBo) throws RemoteException;

    public CanBo getInfo(String username) throws RemoteException;

    public ServerInterface getClient() throws RemoteException;


}
