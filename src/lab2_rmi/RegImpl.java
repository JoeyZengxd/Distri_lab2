package lab2_rmi;

import java.rmi.RemoteException;

public class RegImpl implements Reg{
    public int toTest(String s){
        System.out.println(s);
        return 15;
    }
}
