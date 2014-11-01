package awk.kontenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.DepotTO;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;


public class KontobewegungBuchenRemote implements IKontobewegungBuchenRemote {


	public DepotTO kontoSuchenR (int kontoNr) throws AnwendungskernException {
		KontobewegungBuchen kontobewegungBuchen = new KontobewegungBuchen();
		return kontobewegungBuchen.kontoSuchen(kontoNr);
	}
	
	public boolean abhebenR(int kontoNr, double betrag) throws AnwendungskernException {
		KontobewegungBuchen kontobewegungBuchen = new KontobewegungBuchen();
		return kontobewegungBuchen.abheben(kontoNr, betrag);
	}

	
	public boolean einzahlenR(int kontoNr, double betrag) throws AnwendungskernException {
		KontobewegungBuchen kontobewegungBuchen = new KontobewegungBuchen();
		return kontobewegungBuchen.einzahlen(kontoNr, betrag);
		
		
	}

}
