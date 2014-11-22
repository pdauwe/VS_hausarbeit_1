package awk.kundenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundeninformationFuerNr implements IKundeninformationFuerNr {

	@Override
	public KundeTO kundeSuchenByNr(int nummer) throws AnwendungskernException {
		KundenManager einKundenManager =  KundenManager.getKundenManager();
		Kunde einKunde = einKundenManager.kundeSuchenByNr(nummer); 
		if (einKunde == null) {
			return null;
		}
		else {
				return einKunde.toKundeTO();
		}

	}

}
