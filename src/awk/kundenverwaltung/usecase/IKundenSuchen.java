package awk.kundenverwaltung.usecase;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IKundenSuchen {
	
	public KundeTO kundenSuchenByBenutzerkennungUndPasswort (String benutzerkennung, String passwort) throws AnwendungskernException;

}

