package org.server.controller;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.Map.Entry;

import org.server.service.PropTableAct;

public class PropertiesDataControllerImp extends UnicastRemoteObject implements PropertiesDataController {
	
	PropTableAct propTableAct;
	
	public PropertiesDataControllerImp(PropTableAct propTableAct) throws RemoteException{
		this.propTableAct = propTableAct;
	}
	
	public Map<String, String> getAllProperties() {
		return propTableAct.getAllProperties();
	}

	public boolean updateValue(String entryKey, String entryValue) {
		return propTableAct.updateValue(entryKey, entryValue);
	}

	public boolean commitChanges() {
		return propTableAct.commitChanges();
	}

}
