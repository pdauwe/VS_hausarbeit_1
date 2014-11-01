package awk.depotverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;

public interface IWertpapiertransaktionBuchenRemote extends Remote {

	public DepotTO kontoSuchenR (int kontoNr) throws AnwendungskernException, RemoteException;
	public boolean einzahlenR (int kontoNr, double betrag) throws AnwendungskernException, RemoteException;
	public boolean abhebenR (int kontoNr, double betrag) throws AnwendungskernException, RemoteException;
			
}
