package awk.kontenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;

public interface IKontenPflegenRemote extends Remote {
	
	int kontoAnlegenR (int kundenNr) throws AnwendungskernException, RemoteException;
 
}
