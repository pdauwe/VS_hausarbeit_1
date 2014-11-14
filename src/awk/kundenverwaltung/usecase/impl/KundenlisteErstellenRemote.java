package awk.kundenverwaltung.usecase.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.usecase.IKundenlisteErstellen;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

public class KundenlisteErstellenRemote implements IKundenlisteErstellenRemote{

	@Override
	public ArrayList<PrivatkundeTO> erstelleKundenListe()
			throws AnwendungskernException, RemoteException {
		IKundenlisteErstellen klErstellen = new KundenlisteErstellen();
		return klErstellen.erstelleKundenListe();
	}

}
