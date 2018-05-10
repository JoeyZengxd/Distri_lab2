package lab2_rmi;

import java.io.*;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RORInvocationHandler implements InvocationHandler{
    private Object target;
    String ip;
    int port;
    String Remote_Interface_Name;
    public RORInvocationHandler(Object target, String ip, int port, String Remote_Interface_Name){
        this.target = target;
        this.ip = ip;
        this.port = port;
        this.Remote_Interface_Name = Remote_Interface_Name;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object output = null;
        //Object result = method.invoke(target,args);


        try {
            Socket socket = new Socket();
            socket.connect(new InetSocketAddress(ip, port));



            // get TCP streams and wrap them.


            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());



            // ask.
            out.writeUTF("new RORInvocationHandler");

//            System.out.println("new RORInvocationHandler sent");

            out.writeUTF(target.getClass().getName());
            out.writeUTF(Remote_Interface_Name);
            out.writeUTF(method.getName());
            out.writeObject(args);
            out.writeObject(method.getParameterTypes());



            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());



            // gets answer.
            if ((in.readUTF()).equals("Here is the output")) {
                output = in.readObject();

                System.out.println(output);
            } else {
                System.out.println("Something wrong just happened !");
            }





        } catch (Exception e) {
            e.printStackTrace();
        }
        return output;

    }



}
