package awk.kundenverwaltung.usecase;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenSuchenRemote extends Remote{
	
	public Collection<KundeTO> kundenSuchenByNameR (String vorname, String nachname) throws AnwendungskernException, RemoteException;
	public KundeTO kundenSuchenByBenutzerkennungUndPasswort (String benutzerkennung, String passwort) throws AnwendungskernException, RemoteException;
}
