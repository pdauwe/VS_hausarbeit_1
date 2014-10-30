package awk.kundenverwaltung.usecase.impl;

import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;

public class KundenSuchenRemote implements IKundenSuchenRemote{
	
	public Collection<KundeTO> kundenSuchenByNameR(String vorname, String nachname) throws AnwendungskernException {
		
		KundenSuchen kundenSuchen = new KundenSuchen();
		return kundenSuchen.kundenSuchenByName(vorname, nachname);
	}

}
