package awk.depotverwaltung.usecase;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.internal.Wertpapier;

public interface IWertpapiertransaktionBuchen {

	public DepotTO kontoSuchen (int kontoNr) throws AnwendungskernException;
	public boolean wertpapierkaufen(int depotnr, Wertpapier wertpapier, double preis, char art, int menge) throws AnwendungskernException;
			
}
