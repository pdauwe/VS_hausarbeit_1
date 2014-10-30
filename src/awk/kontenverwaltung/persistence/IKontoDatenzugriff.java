package awk.kontenverwaltung.persistence;

import awk.DatenhaltungsException;
import awk.kontenverwaltung.entity.KontoTO;
import awk.kontenverwaltung.entity.KontobewegungTO;

public interface IKontoDatenzugriff {
	
	
	public void kontodatenAnlegen(KontoTO kontoTO)throws DatenhaltungsException;
	public void buchungsdatenAnlegen(int Kontonummer, KontobewegungTO kontobewegungTO)throws DatenhaltungsException;
	public KontoTO kontendatenLesenByKey(int kontonummer) throws DatenhaltungsException;
	public void kontoSaldoaendern (KontoTO kontoTO) throws DatenhaltungsException;
	public int maxKontonummer() throws DatenhaltungsException;

}
