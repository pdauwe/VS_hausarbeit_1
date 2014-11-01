package awk.kontenverwaltung.persistence;

import awk.DatenhaltungsException;
import awk.kontenverwaltung.entity.DepotTO;
import awk.kontenverwaltung.entity.KontobewegungTO;

public interface IDepotDatenzugriff {
	
	
	public void kontodatenAnlegen(DepotTO kontoTO)throws DatenhaltungsException;
	public void buchungsdatenAnlegen(int Kontonummer, KontobewegungTO kontobewegungTO)throws DatenhaltungsException;
	public DepotTO depotdatenLesenByKey(int kontonummer) throws DatenhaltungsException;
	public void kontoSaldoaendern (DepotTO kontoTO) throws DatenhaltungsException;
	public int maxKontonummer() throws DatenhaltungsException;

}
