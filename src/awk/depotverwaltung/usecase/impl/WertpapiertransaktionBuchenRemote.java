package awk.depotverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;


public class WertpapiertransaktionBuchenRemote implements IWertpapiertransaktionBuchenRemote {


	public DepotTO depotSuchenR (int depotNr) throws AnwendungskernException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.depotSuchen(depotNr);
	}

	public boolean wertpapierBuchenR(int depotnr, WertpapierTO wertpapier,
			double preis, char art, int menge, int boersenplatz, String datum) throws AnwendungskernException,
			RemoteException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.wertpapierBuchen(depotnr, wertpapier.toWertpapier(), preis, art, menge, boersenplatz,datum);
	}

	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException, RemoteException {
		WertpapiertransaktionBuchen wpBuchen = new WertpapiertransaktionBuchen();
		return wpBuchen.getWertpapiere();
	}
	
	

}
