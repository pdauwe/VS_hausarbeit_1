package awk.kundenverwaltung.persistence;

import java.util.Collection;

import awk.DatenhaltungsException;
import awk.kundenverwaltung.entity.KundeTO;

public interface IKundenDatenzugriff {
	
	public void kundendatenAnlegen(KundeTO kundeTO) throws DatenhaltungsException;
	public KundeTO kundendatenSuchenByKey (int nummer)throws DatenhaltungsException;
	public Collection<KundeTO> kundendatenSuchenByAttribute (KundeTO kundeTO)throws DatenhaltungsException;
	public void kundendatenLoeschen (KundeTO kundeTO) throws DatenhaltungsException;
	public void kundendatenaendern (KundeTO kundeTO) throws DatenhaltungsException;

}
