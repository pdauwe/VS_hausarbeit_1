package awk.depotverwaltung.persistence;

import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;

public interface IDepotDatenzugriff {
	
	
	public void depotAnlegen(DepotTO kontoTO)throws DatenhaltungsException;
	public void wertpapiertransaktionAnlegen(int depotnummer, WertpapiertransaktionTO wertpapiertransaktionTO)throws DatenhaltungsException;
	public DepotTO depotdatenLesenByKey(int kontonummer) throws DatenhaltungsException;
	public void kontoSaldoaendern (DepotTO kontoTO) throws DatenhaltungsException;
	public int maxKontonummer() throws DatenhaltungsException;
	public int generiereVorgangsnummer() throws DatenhaltungsException;
	public ArrayList<WertpapierTO> getWertpapiere() throws DatenhaltungsException;
	public String getWertpapierBezeichnungFuerNuemmer(int nummer) throws DatenhaltungsException;
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot(int depotnummer) throws DatenhaltungsException;
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws DatenhaltungsException;
}
