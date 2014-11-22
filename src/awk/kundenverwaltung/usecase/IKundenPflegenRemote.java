package awk.kundenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

import awk.AnwendungskernException;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IKundenPflegenRemote extends Remote{
	
	public boolean privatkundeAnlegenR(
			String vorname, String nachname, String str, String nr, String plz, 
			String ort, String geschlecht, String benutzerkennung, String passwort, Date geburtsdatum) 
			throws AnwendungskernException, RemoteException;	
}
