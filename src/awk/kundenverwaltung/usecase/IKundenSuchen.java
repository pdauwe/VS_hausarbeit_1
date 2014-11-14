package awk.kundenverwaltung.usecase;

import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenSuchen {
	
	public Collection<KundeTO> kundenSuchenByName (String vorname, String nachname) 
	throws AnwendungskernException;
	
	public KundeTO kundenSuchenByBenutzerkennungUndPasswort (String benutzerkennung, String passwort) throws AnwendungskernException;

}

