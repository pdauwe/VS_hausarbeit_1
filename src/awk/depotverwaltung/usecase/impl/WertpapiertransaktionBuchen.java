package awk.depotverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.Date;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.entity.internal.Wertpapier;
import awk.depotverwaltung.entity.internal.Wertpapiertransaktion;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchen;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class WertpapiertransaktionBuchen implements IWertpapiertransaktionBuchen {


	public WertpapiertransaktionBuchen( ) {
	}
	
	@Override
	public DepotTO depotSuchen (int depotNr) throws AnwendungskernException {
		DepotManager einDepotManager = DepotManager.getDepotManager();
		Depot einDepot = einDepotManager.depotSuchenByNr(depotNr);
		DepotTO einDepotTO;
		if ( einDepot != null) {
			einDepotTO = einDepot.toDepotTO();
			IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
			IKundeninformationFuerNr kundeninfo = kvf.getKundeninformationFuerNr();
			KundeTO einKundeTO = kundeninfo.kundeSuchenByNr(einDepot.getInhaberNr());
			einDepotTO.setInhaberNachname(einKundeTO.getNachname());
			einDepotTO.setInhaberVorname(einKundeTO.getVorname());
			System.out.println("Nachname: "+einKundeTO.getNachname());
		}
		else
			einDepotTO = null;
		
		return einDepotTO;
	}
	
	@Override
	public boolean wertpapierBuchen (int depotnr, Wertpapier wertpapier, double preis, char art, int menge, int boersenplatz, Date datum) throws AnwendungskernException {
		
		DepotManager einDepotManager = DepotManager.getDepotManager();
		Depot einDepot = einDepotManager.depotSuchenByNr(depotnr);
		if(einDepot == null){
			return false; 
		}else{
			try{
				int vorgangsnummer = einDepotManager.getDatenverwalter().generiereVorgangsnummer();
				Wertpapiertransaktion wpt = new Wertpapiertransaktion(einDepot, art, preis, menge, vorgangsnummer+1, datum, wertpapier, boersenplatz);
				boolean ok = einDepotManager.getDatenverwalter().wertpapiertransaktionAnlegen(depotnr, wpt.toWertpapiertransaktionTO());
				if(!ok){
					return false;
				}
			}catch (DatenhaltungsException e){
				e.printStackTrace();
				throw new AnwendungskernException();
			}
			
			return true;
			
			
		}
	}
	
	@Override
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException{
		try {
			DepotManager depotmanager = DepotManager.getDepotManager();
			return depotmanager.getWertpapiere();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot(int depotnummer)
			throws AnwendungskernException {
		try{
			DepotManager depotManager = DepotManager.getDepotManager();
			return depotManager.wertpapierBestandFuerDepotnummer(depotnummer);
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws AnwendungskernException{
		try{
			DepotManager depotmanager = DepotManager.getDepotManager();
			return depotmanager.wertpapierHistorieFuerDepot(depotnummer);
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public double depotWert(int depotnummer) throws AnwendungskernException{
		try{
			DepotManager dm = DepotManager.getDepotManager();
			return dm.depotWert(depotnummer);
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
}
