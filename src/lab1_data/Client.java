package lab1_data;

import lab2_rmi.LocateSimpleRegistry;
import lab2_rmi.SimpleRegistry;
import java.util.Scanner;
import lab2_rmi.RemoteObjectRef;

public class Client {
    private static RemoteObjectRef ror = null;
    private static Reg_Log stub = null;
    private Client() {}

    public static void main(String[] args) {
        String hasAcount = "";
        String username = "";
        String password = "";
        try {
            SimpleRegistry reg = LocateSimpleRegistry.getRegistry("localhost",1100);         // locate the host
            ror = reg.lookup("remotelink");          // look up a remote object by its name
            stub = (Reg_Log) ror.localise();

        } catch (Exception e) {
            System.err.println("lab1_data.Client exception thrown: " + e.toString());
            e.printStackTrace();
        }

        Scanner scan = new Scanner(System.in);
        System.out.println("Do have an acount? (y/n)");         // to decide whether to registration or login
        if(scan.hasNext()){
            hasAcount = scan.next();
        }


        if(hasAcount.equals("n")) {         // "n" represent not having an account
            System.out.println("Here we come into Registration Layer\n Please encounter your expected username: ");
            if(scan.hasNext()){
                username = scan.next();
            }
            System.out.println(" Please encounter your password: ");
            if(scan.hasNext()){
                password = scan.next();
            }

            try {
                int t = stub.registration(username, password);          // invoke the remote method
                if(t == 1){         // the return value of 1 represents registration success
                    System.out.println("Acount registration success");
                }
                else{           // other return value represents failure
                    System.out.println("Acount registration failed");
                }
            } catch (Exception e) {
                System.out.println("Remote method exception thrown: " + e.getMessage());
            }
        }

        else if(hasAcount.equals("y")) {            // "y" represent having an account
            System.out.println(" Here we come into the Login Layer\n Please encouter your username: ");
            if(scan.hasNext()){
                username = scan.next();
            }
            System.out.println("Please encounter your password: ");
            if(scan.hasNext()){
                password = scan.next();
            }

            try {
                int t = stub.login(username, password);         // invoke the remote method
                if(t == 1){         // the return value of 1 represents the username and password are both correct
                    System.out.println("Login successful");
                }
                else if(t == -1){           // the return value of 1 represents the username exists but password is wrong
                    System.out.println("Login failed, wrong pasw");
                }
                else{           // other return value(which is 0) represents that there is no such acount
                    System.out.println("Login failed, no such acount");
                }
            } catch (Exception e) {
                System.out.println("Remote method exception thrown: " + e.getMessage());
            }
        }

        scan.close();
    }

}