package awk.depotverwaltung.usecase.impl;

import java.util.Date;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.DepotTO;
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
	
	public DepotTO kontoSuchen (int kontoNr) throws AnwendungskernException {
		DepotManager einKontoManager = DepotManager.getDepotManager();
		Depot einKonto = einKontoManager.depotSuchenByNr(kontoNr);
		DepotTO einKontoTO;
		if ( einKonto != null) {
			einKontoTO = einKonto.toKontoTO();
			IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
			IKundeninformationFuerNr kundeninfo = kvf.getKundeninformationFuerNr();
			KundeTO einKundeTO = kundeninfo.kundeSuchenByNr(einKonto.getInhaberNr());
			einKontoTO.setInhaberNachname(einKundeTO.getNachname());
			einKontoTO.setInhaberVorname(einKundeTO.getVorname());
			System.out.println("Nachname: "+einKundeTO.getNachname());
		}
		else
			einKontoTO = null;
		
		return einKontoTO;
	}
	
	public boolean wertpapierkaufen (int depotnr, Wertpapier wertpapier, double preis, char art, int menge) throws AnwendungskernException {
		
		DepotManager einDepotManager = DepotManager.getDepotManager();
		Depot einDepot = einDepotManager.depotSuchenByNr(depotnr);
		if(einDepot == null){
			return false; 
		}else{
			Date today = new Date();
			try{
				int vorgangsnummer = einDepotManager.getDatenverwalter().generiereVorgangsnummer();
				Wertpapiertransaktion wpt = new Wertpapiertransaktion(einDepot, art, preis, menge, vorgangsnummer, today, wertpapier);
				einDepotManager.getDatenverwalter().wertpapierBuchen(depotnr, wpt.toWertpapiertransaktionTO());
			}catch (DatenhaltungsException e){
				e.printStackTrace();
				throw new AnwendungskernException();
			}
			
			return true;
			
			
		}
	}
	/*
	public boolean einzahlen(int kontoNr, double betrag) throws AnwendungskernException {
		DepotManager einKontoManager = DepotManager.getDepotManager();
		Depot einKonto = einKontoManager.depotSuchenByNr(kontoNr);
		if (einKonto == null)
			return false;
		else {
			Wertpapiertransaktion kontobewegung = new Wertpapiertransaktion(einKonto,'E',betrag);
			einKonto.addWertpapiertransaktion(kontobewegung);
			try {
				einKontoManager.getDatenverwalter().buchungsdatenAnlegen(
						einKonto.getDepotNr(), kontobewegung.toWertpapiertransaktionTO());
				einKontoManager.getDatenverwalter().kontoSaldoaendern(einKonto.toKontoTO());
			} catch (DatenhaltungsException e) {
				e.printStackTrace();
				throw new AnwendungskernException();
			}
				
			return true;
		}
	}
	*/
}
