package awk.depotverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.internal.Depot;
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
	
	public boolean abheben(int kontoNr, double betrag) throws AnwendungskernException {
		DepotManager einKontoManager = DepotManager.getDepotManager();
		Depot einKonto = einKontoManager.depotSuchenByNr(kontoNr);
		if (einKonto == null)
			return false;
		else {
			Wertpapiertransaktion kontobewegung = new Wertpapiertransaktion(einKonto,'A',betrag);
			einKonto.addKontobewegung(kontobewegung);
			try {
				einKontoManager.getDatenverwalter().buchungsdatenAnlegen(
						einKonto.getDepotNr(), kontobewegung.toKontobewegungTO());
				einKontoManager.getDatenverwalter().kontoSaldoaendern(einKonto.toKontoTO());
			} catch (DatenhaltungsException e) {
				e.printStackTrace();
				throw new AnwendungskernException();
			}
				
			return true;
		}
	}

	
	public boolean einzahlen(int kontoNr, double betrag) throws AnwendungskernException {
		DepotManager einKontoManager = DepotManager.getDepotManager();
		Depot einKonto = einKontoManager.depotSuchenByNr(kontoNr);
		if (einKonto == null)
			return false;
		else {
			Wertpapiertransaktion kontobewegung = new Wertpapiertransaktion(einKonto,'E',betrag);
			einKonto.addKontobewegung(kontobewegung);
			try {
				einKontoManager.getDatenverwalter().buchungsdatenAnlegen(
						einKonto.getDepotNr(), kontobewegung.toKontobewegungTO());
				einKontoManager.getDatenverwalter().kontoSaldoaendern(einKonto.toKontoTO());
			} catch (DatenhaltungsException e) {
				e.printStackTrace();
				throw new AnwendungskernException();
			}
				
			return true;
		}
	}

}
