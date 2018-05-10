package lab2_rmi;

public class for_____test______client {
    private static RemoteObjectRef rere = null;
    private for_____test______client(){};

    public static void main(String args[]){
        try{
            SimpleRegistry registry = LocateSimpleRegistry.getRegistry("localhost", 1099);
            RemoteObjectRef ror = registry.lookup("thisisfortest");

            System.out.println("**************************Here we start*********************************");
            Reg stub = (Reg)ror.localise();
            stub.toTest("ssss");



        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
