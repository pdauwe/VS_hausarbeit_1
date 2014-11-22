package awk.depotverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IDepotPflegenRemote extends Remote {
	
	int depotAnlegenR (int kundenNr) throws AnwendungskernException, RemoteException;
 
}
