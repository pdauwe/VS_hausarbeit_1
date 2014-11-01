package awk.depotverwaltung.usecase;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;

public interface IWertpapiertransaktionBuchen {

	public DepotTO kontoSuchen (int kontoNr) throws AnwendungskernException;
	public boolean einzahlen (int kontoNr, double betrag) throws AnwendungskernException;
	public boolean abheben (int kontoNr, double betrag) throws AnwendungskernException;
			
}
