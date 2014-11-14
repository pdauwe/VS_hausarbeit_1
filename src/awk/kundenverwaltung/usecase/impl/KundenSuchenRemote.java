package awk.kundenverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;

public class KundenSuchenRemote implements IKundenSuchenRemote{
	
	@Override
	public Collection<KundeTO> kundenSuchenByNameR(String vorname, String nachname) throws AnwendungskernException {
		
		KundenSuchen kundenSuchen = new KundenSuchen();
		return kundenSuchen.kundenSuchenByName(vorname, nachname);
	}

	@Override
	public KundeTO kundenSuchenByBenutzerkennungUndPasswort(
			String benutzerkennung, String passwort)
			throws AnwendungskernException, RemoteException {

		KundenSuchen kundenSuchen = new KundenSuchen();
		return kundenSuchen.kundenSuchenByBenutzerkennungUndPasswort(benutzerkennung, passwort);
	}

}
