package lab2_rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Reg extends Remote {           // a remote interface

    public int toTest(String s)           // a remote method for registration operation
            throws RemoteException;


}