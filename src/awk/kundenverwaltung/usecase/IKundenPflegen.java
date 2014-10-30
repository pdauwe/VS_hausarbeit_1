package awk.kundenverwaltung.usecase;

import awk.AnwendungskernException;

public interface IKundenPflegen {
	
	public boolean geschaeftskundeAnlegen(
			String vorname, String nachname, String str, String nr, String plz, 
			String ort, String ustId) 
			throws AnwendungskernException;
	public boolean privatkundeAnlegen(
			String vorname, String nachname, String str, String nr, String plz, 
			String ort, String geschlecht) 
			throws AnwendungskernException;
	public boolean kundenLoeschen (int nummer) throws AnwendungskernException;

	
}

