package awk.kundenverwaltung.usecase;

import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenlisteErstellen {
	
	public Collection<KundeTO> kundenListeAusgeben() 
	throws AnwendungskernException;

}
