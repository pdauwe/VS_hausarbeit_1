package awk.kundenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenlisteErstellenRemote extends Remote{
	
	public Collection<KundeTO> kundenListeAusgebenR() throws AnwendungskernException, RemoteException;

}
