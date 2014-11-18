package awk.kundenverwaltung.usecase;


import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenSuchenRemote extends Remote{

	public KundeTO kundenSuchenByBenutzerkennungUndPasswort (String benutzerkennung, String passwort) throws AnwendungskernException, RemoteException;
}
