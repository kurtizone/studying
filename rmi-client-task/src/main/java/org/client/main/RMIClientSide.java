package org.client.main;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.client.gui.ClientFrame;
import org.server.controller.PropertiesDataController;

public class RMIClientSide {
	
	private static final int RMI_PORT = 1099;
	private static final String SERVICE_NAME = "dataexec";
	private static final String RMI_IP = "192.168.110.147";
	
	public static void main(String[] args) {
		try {
			Registry reg = LocateRegistry.getRegistry("192.168.110.147", RMI_PORT);
			PropertiesDataController controller = (PropertiesDataController) reg.lookup(SERVICE_NAME);
			new ClientFrame(controller);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
