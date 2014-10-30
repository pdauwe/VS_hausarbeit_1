package awk.kontenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.KontoTO;

public interface IKontobewegungBuchenRemote extends Remote {

	public KontoTO kontoSuchenR (int kontoNr) throws AnwendungskernException, RemoteException;
	public boolean einzahlenR (int kontoNr, double betrag) throws AnwendungskernException, RemoteException;
	public boolean abhebenR (int kontoNr, double betrag) throws AnwendungskernException, RemoteException;
			
}
