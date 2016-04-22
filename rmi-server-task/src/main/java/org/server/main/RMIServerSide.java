package org.server.main;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.server.controller.PropertiesDataController;
import org.server.controller.PropertiesDataControllerImp;
import org.server.prop.ConfigProp;
import org.server.service.ConnectPool;
import org.server.service.PropTableAct;
import org.server.service.PropTableActImpl;

public class RMIServerSide {
	
	private static final int RMI_PORT = 1099;
	private static final String SERVICE_NAME = "dataexec";
	public static final boolean LOAD_ON_START = false;
	public static final ConnectPool POOL = new ConnectPool();
	
	public static void main(String[] args) {
		try {
			System.out.println("Start!!");
			ConfigProp prop = new ConfigProp();
			PropTableAct service = new PropTableActImpl();
			PropertiesDataController controller = new PropertiesDataControllerImp(service);
			createRegistry(controller);
			System.out.println("OK");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void createRegistry(PropertiesDataController controller) {
		try {
			Registry reg = LocateRegistry.createRegistry(RMI_PORT);
			reg.rebind(SERVICE_NAME, controller);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
	}
}
