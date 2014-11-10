package awk.kundenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;


import awk.AnwendungskernException;

public interface IKundenPflegenRemote extends Remote{
	
	public boolean privatkundeAnlegenR(
			String vorname, String nachname, String str, String nr, String plz, 
			String ort, String geschlecht, String benutzerkennung, String passwort, String geburtsdatum) 
			throws AnwendungskernException, RemoteException;	
}
