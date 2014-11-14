package awk.kundenverwaltung.usecase;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.PrivatkundeTO;

public interface IKundenlisteErstellenRemote extends Remote {

	public ArrayList<PrivatkundeTO> erstelleKundenListe() throws AnwendungskernException, RemoteException;
}
