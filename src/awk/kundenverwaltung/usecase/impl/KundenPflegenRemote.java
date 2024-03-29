package awk.kundenverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.Date;

import awk.AnwendungskernException;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenPflegenRemote implements IKundenPflegenRemote{
	
	
	public KundenPflegenRemote( ) {
	}

	@Override
	public boolean privatkundeAnlegenR(String vorname, String nachname, String str,
			String nr, String plz, String ort, String geschlecht, String benutzerkennung, String passwort, Date geburtsdatum) throws AnwendungskernException, RemoteException {
		KundenPflegen kundenPflegen = new KundenPflegen();
		return kundenPflegen.privatkundeAnlegen(vorname, nachname, str, nr, plz, ort, geschlecht, benutzerkennung, passwort, geburtsdatum);
	}
}
