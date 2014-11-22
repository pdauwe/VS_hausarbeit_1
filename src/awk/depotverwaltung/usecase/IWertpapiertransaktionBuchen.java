package awk.depotverwaltung.usecase;

import java.util.ArrayList;
import java.util.Date;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.depotverwaltung.entity.internal.Wertpapier;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IWertpapiertransaktionBuchen {

	public DepotTO depotSuchen (int depotNr) throws AnwendungskernException;
	public boolean wertpapierBuchen(int depotnr, Wertpapier wertpapier, double preis, char art, int menge, int boersenplatz, Date datum) throws AnwendungskernException;
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException;	
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepot (int depotnummer) throws AnwendungskernException;
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws AnwendungskernException;
	public double depotWert(int depotnummer) throws AnwendungskernException;
}
