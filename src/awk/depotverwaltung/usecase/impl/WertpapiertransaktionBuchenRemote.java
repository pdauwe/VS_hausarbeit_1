package awk.depotverwaltung.usecase.impl;

import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.internal.Wertpapier;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;


public class WertpapiertransaktionBuchenRemote implements IWertpapiertransaktionBuchenRemote {


	public DepotTO depotSuchenR (int depotNr) throws AnwendungskernException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.depotSuchen(depotNr);
	}

	public boolean wertpapierkaufen(int depotnr, Wertpapier wertpapier,
			double preis, char art, int menge) throws AnwendungskernException,
			RemoteException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.wertpapierkaufen(depotnr, wertpapier, preis, art, menge);
	}

}
