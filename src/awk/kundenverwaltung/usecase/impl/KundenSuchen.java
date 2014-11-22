package awk.kundenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.usecase.IKundenSuchen;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenSuchen implements IKundenSuchen{

	@Override
	public KundeTO kundenSuchenByBenutzerkennungUndPasswort(
			String benutzerkennung, String passwort)
			throws AnwendungskernException {
		
		KundenManager kundenManager = KundenManager.getKundenManager();
		Kunde k = kundenManager.kundeSuchenByBenutzerkennungUndPasswort(benutzerkennung, passwort);
		return k.toKundeTO();
	}

}
