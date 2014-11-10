package awk.depotverwaltung.usecase;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.internal.Wertpapier;

public interface IWertpapiertransaktionBuchen {

	public DepotTO depotSuchen (int depotNr) throws AnwendungskernException;
	public boolean wertpapierBuchen(int depotnr, Wertpapier wertpapier, double preis, char art, int menge, int boersenplatz, String datum) throws AnwendungskernException;
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException;	
}
