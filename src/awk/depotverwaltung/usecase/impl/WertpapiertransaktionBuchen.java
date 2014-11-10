package awk.depotverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.Date;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.entity.internal.Wertpapier;
import awk.depotverwaltung.entity.internal.Wertpapiertransaktion;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchen;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;

public class WertpapiertransaktionBuchen implements IWertpapiertransaktionBuchen {


	public WertpapiertransaktionBuchen( ) {
	}
	
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
	
	public boolean wertpapierBuchen (int depotnr, Wertpapier wertpapier, double preis, char art, int menge, int boersenplatz, String datum) throws AnwendungskernException {
		
		DepotManager einDepotManager = DepotManager.getDepotManager();
		Depot einDepot = einDepotManager.depotSuchenByNr(depotnr);
		if(einDepot == null){
			return false; 
		}else{
			String today = "";
			// Wenn kein Datum angegeben wurde, wird das heute Datum verwendet.
			if(datum == null || datum.isEmpty()){
				today = new Date().toString();
			}else{
				today = datum;
			}
			
			try{
				int vorgangsnummer = einDepotManager.getDatenverwalter().generiereVorgangsnummer();
				Wertpapiertransaktion wpt = new Wertpapiertransaktion(einDepot, art, preis, menge, vorgangsnummer, today, wertpapier, boersenplatz);
				einDepotManager.getDatenverwalter().wertpapiertransaktionAnlegen(depotnr, wpt.toWertpapiertransaktionTO());
			}catch (DatenhaltungsException e){
				e.printStackTrace();
				throw new AnwendungskernException();
			}
			
			return true;
			
			
		}
	}
	
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException{
		try {
			DepotManager depotmanager = DepotManager.getDepotManager();
			return depotmanager.getWertpapiere();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
