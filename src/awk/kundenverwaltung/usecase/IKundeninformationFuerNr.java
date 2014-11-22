package awk.kundenverwaltung.usecase;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IKundeninformationFuerNr {
	
	public KundeTO kundeSuchenByNr (int nummer) 
	throws AnwendungskernException;

}
