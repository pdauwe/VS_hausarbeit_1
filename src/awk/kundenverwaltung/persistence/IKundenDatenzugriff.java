package awk.kundenverwaltung.persistence;

import java.util.Collection;

import awk.DatenhaltungsException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenDatenzugriff {
	
	public void kundendatenAnlegen(KundeTO kundeTO) throws DatenhaltungsException;
	public KundeTO kundendatenSuchenByKey (int nummer)throws DatenhaltungsException;
	public Collection<KundeTO> kundendatenSuchenByAttribute (KundeTO kundeTO)throws DatenhaltungsException;
	public KundeTO kundeSuchenFuerLoginUndPasswort(String benutzerkennung, String passwort) throws DatenhaltungsException;
	public int getMaxKundennummer() throws DatenhaltungsException;

}
