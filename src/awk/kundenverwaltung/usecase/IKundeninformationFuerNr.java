package awk.kundenverwaltung.usecase;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundeninformationFuerNr {
	
	public KundeTO kundeSuchenByNr (int nummer) 
	throws AnwendungskernException;

}
