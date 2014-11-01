package awk.depotverwaltung.persistence;

import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;

public interface IDepotDatenzugriff {
	
	
	public void kontodatenAnlegen(DepotTO kontoTO)throws DatenhaltungsException;
	public void buchungsdatenAnlegen(int Kontonummer, WertpapiertransaktionTO kontobewegungTO)throws DatenhaltungsException;
	public DepotTO depotdatenLesenByKey(int kontonummer) throws DatenhaltungsException;
	public void kontoSaldoaendern (DepotTO kontoTO) throws DatenhaltungsException;
	public int maxKontonummer() throws DatenhaltungsException;

}
