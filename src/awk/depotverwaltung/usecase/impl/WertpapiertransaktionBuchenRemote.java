package awk.depotverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;


/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class WertpapiertransaktionBuchenRemote implements IWertpapiertransaktionBuchenRemote {


	@Override
	public DepotTO depotSuchenR (int depotNr) throws AnwendungskernException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.depotSuchen(depotNr);
	}

	@Override
	public boolean wertpapierBuchenR(int depotnr, WertpapierTO wertpapier,
			double preis, char art, int menge, int boersenplatz, Date datum) throws AnwendungskernException,
			RemoteException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.wertpapierBuchen(depotnr, wertpapier.toWertpapier(), preis, art, menge, boersenplatz,datum);
	}

	@Override
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException, RemoteException {
		WertpapiertransaktionBuchen wpBuchen = new WertpapiertransaktionBuchen();
		return wpBuchen.getWertpapiere();
	}

	@Override
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot(int depotnummer)
			throws AnwendungskernException, RemoteException {
		WertpapiertransaktionBuchen wertpapiertransaktionBuchen = new WertpapiertransaktionBuchen();
		return wertpapiertransaktionBuchen.wertpapierBestandFuerDepot(depotnummer);
	}
	
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws AnwendungskernException, RemoteException{
		WertpapiertransaktionBuchen wpbuchen = new WertpapiertransaktionBuchen();
		return wpbuchen.wertpapierHistorieFuerDepot(depotnummer);
	}
	
	public double depotWert(int depotnummer) throws AnwendungskernException, RemoteException{
		WertpapiertransaktionBuchen wpbuchen = new WertpapiertransaktionBuchen();
		return wpbuchen.depotWert(depotnummer);
	}
	
	

}
