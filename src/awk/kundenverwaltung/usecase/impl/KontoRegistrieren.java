package awk.kundenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.usecase.IKontoRegistrieren;

public class KontoRegistrieren implements IKontoRegistrieren {
	
	public void kontoHinzufuegen(int kundennummer, int kontonummer) throws AnwendungskernException {
		KundenManager kundenmanager = KundenManager.getKundenManager();
			Kunde einKunde = kundenmanager.kundeSuchenByNr(kundennummer);
			einKunde.addKonto(kontonummer);
	}

}
