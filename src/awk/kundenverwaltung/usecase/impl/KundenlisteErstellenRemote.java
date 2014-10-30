package awk.kundenverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

public class KundenlisteErstellenRemote implements IKundenlisteErstellenRemote {
		
	public Collection<KundeTO> kundenListeAusgebenR() throws AnwendungskernException, RemoteException {
		System.out.println("Kunde remote erstellen");
		KundenlisteErstellen kundenlisteErstellen = new KundenlisteErstellen();
		return kundenlisteErstellen.kundenListeAusgeben();
	}

}

