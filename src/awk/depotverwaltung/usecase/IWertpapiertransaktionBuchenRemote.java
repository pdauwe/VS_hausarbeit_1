package awk.depotverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.internal.Wertpapier;

public interface IWertpapiertransaktionBuchenRemote extends Remote {

	public DepotTO depotSuchenR (int depotNr) throws AnwendungskernException, RemoteException;
	public boolean wertpapierkaufen(int depotnr, Wertpapier wertpapier, double preis, char art, int menge) throws AnwendungskernException, RemoteException;	
}
