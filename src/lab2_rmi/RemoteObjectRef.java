package lab2_rmi;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemoteObjectRef {
	String IP_adr;
	int Port;
	int Obj_Key;
	String Remote_Interface_Name;

	public RemoteObjectRef(String ip, int port, int obj_key, String riname) {
		IP_adr = ip;
		Port = port;
		Obj_Key = obj_key;
		Remote_Interface_Name = riname;
	}

	// this method is important, since it is a stub creator.
	//
	public Object localise() {
		Class c;
		try {
			c = Class.forName(Remote_Interface_Name+"Impl");
			Object o = c.newInstance();
			RORtbl.table.put(this, o);

			InvocationHandler handler = new RORInvocationHandler((Remote) o, IP_adr, Port, Remote_Interface_Name);
			Remote proxy = (Remote)Proxy.newProxyInstance(o.getClass().getClassLoader(), o.getClass().getInterfaces(), handler);
			return proxy;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String []args)throws RemoteException{
		RemoteObjectRef tr = new RemoteObjectRef("localhost", 3636, 10, "Reg");
		Reg stub = null;
		stub = (Reg)tr.localise();
		stub.toTest("haha");
	}
}