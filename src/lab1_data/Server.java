package lab1_data;

import lab1_data.Reg_Log;
import lab1_data.Reg_LogImpl;

import lab2_rmi.SimpleRegistry;
import lab2_rmi.LocateSimpleRegistry;
import lab2_rmi.UnicastRemoteObject;
import lab2_rmi.RemoteObjectRef;

public class Server {

    public Server() {}

    public static void main(String args[]) {

        try {
            Reg_LogImpl robj = new Reg_LogImpl();           // robj is an instance of the remote object lab1_data.Reg_LogImpl
            RemoteObjectRef stub = (RemoteObjectRef) UnicastRemoteObject.exportObject(robj,1107);          // export the remote object

            SimpleRegistry registry = LocateSimpleRegistry.getRegistry("localhost", 1100);           // get a registry operating on the particular host and port
            registry.rebind("remotelink", stub);            // bind name "remoetelink" to the reference of the remote object
            System.out.println("remotelink lab1_data.Server is ready to listen...");

        } catch (Exception e) {
            System.err.println("lab1_data.Server exception thrown: " + e.toString());
            e.printStackTrace();
        }
    }
}