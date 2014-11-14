package awk.depotverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;

public interface IWertpapiertransaktionBuchenRemote extends Remote {

	public DepotTO depotSuchenR (int depotNr) throws AnwendungskernException, RemoteException;
	public boolean wertpapierBuchenR(int depotnr, WertpapierTO wertpapier, double preis, char art, int menge, int boersenplatz, String datum) throws AnwendungskernException, RemoteException;	
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException, RemoteException;
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot (int depotnummer) throws AnwendungskernException, RemoteException;
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws AnwendungskernException, RemoteException;
}
