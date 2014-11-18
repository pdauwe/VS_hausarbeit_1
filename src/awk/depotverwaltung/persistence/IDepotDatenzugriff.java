package awk.depotverwaltung.persistence;

import java.util.ArrayList;

import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;

public interface IDepotDatenzugriff {
	
	
	public void depotAnlegen(DepotTO depotTO)throws DatenhaltungsException;
	public void wertpapiertransaktionAnlegen(int depotnummer, WertpapiertransaktionTO wertpapiertransaktionTO)throws DatenhaltungsException;
	public DepotTO depotdatenLesenByKey(int depotnummer) throws DatenhaltungsException;
	public int maxDepotnummer() throws DatenhaltungsException;
	public int generiereVorgangsnummer() throws DatenhaltungsException;
	public ArrayList<WertpapierTO> getWertpapiere() throws DatenhaltungsException;
	public String getWertpapierBezeichnungFuerNuemmer(int nummer) throws DatenhaltungsException;
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot(int depotnummer) throws DatenhaltungsException;
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws DatenhaltungsException;
}
