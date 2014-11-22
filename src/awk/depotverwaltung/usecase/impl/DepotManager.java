package awk.depotverwaltung.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.BestandContainerTO;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.persistence.IDepotDatenzugriff;
import awk.depotverwaltung.persistence.impl.DepotDatenzugriff_DAO_Db_Einzelsatz;
import awk.depotverwaltung.entity.WertpapierTO;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class DepotManager {
	
	/* Hier wird festgelegt, dass zur Speicherung eine Datenbank verwendet wird!*/
	private IDepotDatenzugriff einDatenverwalter = new DepotDatenzugriff_DAO_Db_Einzelsatz();

	private static DepotManager depotManager;

	public static DepotManager getDepotManager() throws AnwendungskernException {
		if (depotManager == null)
			depotManager = new DepotManager();
		
		return depotManager;
	}
	private  DepotManager () throws AnwendungskernException {
	}
	
	public void depotHinzufuegen (Depot einDepot) throws AnwendungskernException {
		try {
			this.einDatenverwalter.depotAnlegen(einDepot.toDepotTO());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	
	public int naechsteDepotnummerErmitteln () throws AnwendungskernException {
		try {
			return this.einDatenverwalter.maxDepotnummer() + 1;
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}	
	}
	
	public Depot depotSuchenByNr(int depotNr) throws AnwendungskernException {
		DepotTO depotTO;
		try {
			depotTO = this.einDatenverwalter.depotdatenLesenByKey(depotNr);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		if (depotTO == null)
			return null;
		else
			return depotTO.toDepot();
	}
	
	public IDepotDatenzugriff getDatenverwalter () {
		return this.einDatenverwalter;
	}
	
	public ArrayList<WertpapierTO> getWertpapiere() throws AnwendungskernException{
		ArrayList<WertpapierTO> wps;
		try{
			wps = this.einDatenverwalter.getWertpapiere();
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		
		if(wps == null){
			return null;
		}else{
			return wps;
		}
	}
	
	public ArrayList<BestandContainerTO> wertpapierBestandFuerDepotnummer (int depotnummer) throws AnwendungskernException{
		
		try{
			return this.einDatenverwalter.wertpapierBestandFuerDepot(depotnummer);
		}catch (DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	public ArrayList<WertpapiertransaktionTO> wertpapierHistorieFuerDepot(int depotnummer) throws AnwendungskernException {
		try{
			return this.einDatenverwalter.wertpapierHistorieFuerDepot(depotnummer);
		}catch (DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	public double depotWert(int depotnummer) throws AnwendungskernException{
		try{ 
			return this.einDatenverwalter.depotWert(depotnummer);
		}catch (DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
}
