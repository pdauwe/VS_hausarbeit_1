package awk.depotverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.persistence.IDepotDatenzugriff;
import awk.depotverwaltung.persistence.impl.DepotDatenzugriff_DAO_Db_Einzelsatz;

public class DepotManager {
	
	/* Hier wird festgelegt, dass zur Speicherung eine Datei verwendet wird!*/
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
			this.einDatenverwalter.kontodatenAnlegen(einDepot.toDepotTO());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	
	public int naechsteDepotnummerErmitteln () throws AnwendungskernException {
		try {
			return this.einDatenverwalter.maxKontonummer() + 1;
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
}
