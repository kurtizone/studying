package org.server.controller;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

public interface PropertiesDataController extends Remote {

	Map<String, String> getAllProperties() throws RemoteException;

	boolean updateValue(String entryKey, String entryValue) throws RemoteException;

	boolean commitChanges() throws RemoteException;
}
