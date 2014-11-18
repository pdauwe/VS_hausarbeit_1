package awk.kundenverwaltung.usecase;



import java.util.Date;

import awk.AnwendungskernException;

public interface IKundenPflegen {
	
	public boolean privatkundeAnlegen(
			String vorname, String nachname, String str, String nr, String plz, 
			String ort, String geschlecht, String benutzerkennung, String passwort, Date geburtsdatum) 
			throws AnwendungskernException;
}

