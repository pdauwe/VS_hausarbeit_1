package awk.kontenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;

public interface IDepotPflegenRemote extends Remote {
	
	int depotAnlegenR (int kundenNr) throws AnwendungskernException, RemoteException;
 
}
