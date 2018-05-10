package lab1_data;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Reg_Log extends Remote {           // a remote interface

    public int registration(String user, String pasw)           // a remote method for registration operation
            throws RemoteException;

    public int login(String user, String pasw)          // a remote method for login operation
            throws RemoteException;

}