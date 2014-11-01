package awk.depotverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;


public class WertpapiertransaktionBuchenRemote implements IWertpapiertransaktionBuchenRemote {


	public DepotTO kontoSuchenR (int kontoNr) throws AnwendungskernException {
		WertpapiertransaktionBuchen kontobewegungBuchen = new WertpapiertransaktionBuchen();
		return kontobewegungBuchen.kontoSuchen(kontoNr);
	}
	
	public boolean abhebenR(int kontoNr, double betrag) throws AnwendungskernException {
		WertpapiertransaktionBuchen kontobewegungBuchen = new WertpapiertransaktionBuchen();
		return kontobewegungBuchen.abheben(kontoNr, betrag);
	}

	
	public boolean einzahlenR(int kontoNr, double betrag) throws AnwendungskernException {
		WertpapiertransaktionBuchen kontobewegungBuchen = new WertpapiertransaktionBuchen();
		return kontobewegungBuchen.einzahlen(kontoNr, betrag);
		
		
	}

}
