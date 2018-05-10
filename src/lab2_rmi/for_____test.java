package lab2_rmi;

public class for_____test {

    public for_____test(){};

    public static void main(String args[]){
        try{
            RegImpl test____robj = new RegImpl();
            RemoteObjectRef stub = (RemoteObjectRef)UnicastRemoteObject.exportObject(test____robj, 1103);

            SimpleRegistry registry = LocateSimpleRegistry.getRegistry("localhost", 1099);
            registry.rebind("thisisfortest", stub);
            System.out.println("remotelink lab1_data.Server is ready to listen...");



        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
