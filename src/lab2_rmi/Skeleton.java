package lab2_rmi;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class Skeleton extends Thread {
    public RemoteObjectRef ror;
    public Object robj;

    public Skeleton(RemoteObjectRef ror, Object robj){
        super();
        this.ror = ror;
        this.robj = robj;
    }
//#############################################
    @Override
    public void run(){
        try {
            ServerSocket svsoc = new ServerSocket(ror.Port);

            while(true){
                Socket newsoc = svsoc.accept();

                String Remote_Interface_Name = "null";
                String method = "null";
                Object[] args;
                Class<?>[] argTypes;
                String target = "null";

                Object result;

                System.out.println("new request received in skeleton");
//                BufferedReader in = new BufferedReader(new InputStreamReader(newsoc.getInputStream()));
//                PrintWriter out = new PrintWriter(newsoc.getOutputStream(), true);
                ObjectInputStream in = new ObjectInputStream(newsoc.getInputStream());


//                System.out.println(in.readUTF());


                if(in.readUTF().equals("new RORInvocationHandler")) {

                    System.out.println("new RORInvocationHandler reveived");

                    target = in.readUTF();

                    Remote_Interface_Name = in.readUTF();

                    method = in.readUTF();
                    args = (Object[]) in.readObject();

                    argTypes = (Class<?>[]) in.readObject();





                    ObjectOutputStream out = new ObjectOutputStream(newsoc.getOutputStream());
                    try {


                        Class c = this.robj.getClass();
                        assert Class.forName(Remote_Interface_Name).isAssignableFrom(c);
                        result = c.getDeclaredMethod(method, argTypes).invoke(this.robj, args);


//                        Class c = Class.forName(target);
//                        Object i = c.getDeclaredMethod(method, argTypes).invoke(argTypes, args);

                        out.writeUTF("Here is the output");
                        out.writeObject(result);

                    } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }



            }

        }catch(IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }


}
