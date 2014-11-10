package awk.kundenverwaltung.usecase;



import awk.AnwendungskernException;

public interface IKundenPflegen {
	
	public boolean privatkundeAnlegen(
			String vorname, String nachname, String str, String nr, String plz, 
			String ort, String geschlecht, String benutzerkennung, String passwort, String geburtsdatum) 
			throws AnwendungskernException;
}

