package lab2_rmi;

import java.net.ServerSocket;

public class UnicastRemoteObject {


    public static Object exportObject(Object robj, int port) throws Exception{

        String riname = robj.getClass().getInterfaces()[0].getName();
        RemoteObjectRef ror1 = new RemoteObjectRef("127.0.0.1", port, 0, riname);//ip之后要改为Server实际ip，obj_key在之后序列化时要照顾
        Object stub = ror1.localise();

//        ServerSocket serverSoc = new ServerSocket(port);
//        System.out.println("server socket of Unicast created.\n");


        Skeleton skeleton = new Skeleton(ror1, robj);
        skeleton.start();


        return ror1;


    }
}
