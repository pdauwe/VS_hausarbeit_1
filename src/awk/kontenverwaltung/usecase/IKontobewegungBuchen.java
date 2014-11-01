package awk.kontenverwaltung.usecase;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.DepotTO;

public interface IKontobewegungBuchen {

	public DepotTO kontoSuchen (int kontoNr) throws AnwendungskernException;
	public boolean einzahlen (int kontoNr, double betrag) throws AnwendungskernException;
	public boolean abheben (int kontoNr, double betrag) throws AnwendungskernException;
			
}
