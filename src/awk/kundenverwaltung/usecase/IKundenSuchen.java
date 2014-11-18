package awk.kundenverwaltung.usecase;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenSuchen {
	
	public KundeTO kundenSuchenByBenutzerkennungUndPasswort (String benutzerkennung, String passwort) throws AnwendungskernException;

}

