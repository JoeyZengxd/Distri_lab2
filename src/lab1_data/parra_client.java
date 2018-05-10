package lab1_data;

import lab2_rmi.LocateSimpleRegistry;
import lab2_rmi.SimpleRegistry;
import lab2_rmi.RemoteObjectRef;
import java.util.Random;
public class parra_client extends Thread {

    public parra_client() {
    }

    private static RemoteObjectRef ror = null;

    private static Reg_Log stub = null;

    private int total_op;
    private int total_success;
    private boolean stop_signal = false;

    public int getTotal_op() {
        return total_op;
    }

    public int getTotal_success() {
        return total_success;
    }

    public void setStop_signal() {
        stop_signal = true;
    }

    @Override
    public void run() {
        while (!stop_signal == true) {
            total_op++;
            String username = "";
            String password = "";
            Random rand = new Random();
            for (int i = 0; i < 10; i++) {

                //System.out.printf("&&%d&&\n", rand.nextInt(10));
                password = password + String.valueOf(rand.nextInt(10));
                username = username + String.valueOf(rand.nextInt(10));
            }


            System.out.println("****************************** new op ********************************");


            try {
                SimpleRegistry reg = LocateSimpleRegistry.getRegistry("localhost", 1100);         // locate the host
                ror = reg.lookup("remotelink");          // look up a remote object by its name
                stub = (Reg_Log) ror.localise();

            } catch (Exception e) {
                System.err.println("lab1_data.Client exception thrown: " + e.toString());
                e.printStackTrace();
            }


            try {
                int t = stub.registration(username, password);          // invoke the remote method
                if (t == 1) {         // the return value of 1 represents registration success
                    System.out.println("Acount registration success");
                    total_success++;
                } else {           // other return value represents failure
                    System.out.println("Acount registration failed");
                }
            } catch (Exception e) {
                System.out.println("Remote method exception thrown: " + e.getMessage());
            }

        }


    }

}