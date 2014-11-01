package awk.kontenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.kontenverwaltung.entity.DepotTO;
import awk.kontenverwaltung.entity.internal.Depot;
import awk.kontenverwaltung.persistence.IKontoDatenzugriff;
import awk.kontenverwaltung.persistence.impl.KontoDatenzugriff_DAO_Db_Einzelsatz;

public class KontenManager {
	
	/* Hier wird festgelegt, dass zur Speicherung eine Datei verwendet wird!*/
	private IKontoDatenzugriff einDatenverwalter = new KontoDatenzugriff_DAO_Db_Einzelsatz();

	private static KontenManager kontenManager;

	public static KontenManager getKontenManager() throws AnwendungskernException {
		if (kontenManager == null)
			kontenManager = new KontenManager();
		
		return kontenManager;
	}
	private  KontenManager () throws AnwendungskernException {
	}
	
	public void kontoHinzufuegen (Depot einKonto) throws AnwendungskernException {
		try {
			this.einDatenverwalter.kontodatenAnlegen(einKonto.toKontoTO());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	
	public int naechsteKontonummerErmitteln () throws AnwendungskernException {
		try {
			return this.einDatenverwalter.maxKontonummer() + 1;
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}	
	}
	
	public Depot kontoSuchenByNr(int kontoNr) throws AnwendungskernException {
		DepotTO kontoTO;
		try {
			kontoTO = this.einDatenverwalter.kontendatenLesenByKey(kontoNr);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		if (kontoTO == null)
			return null;
		else
			return kontoTO.toDepot();
	}
	
	public IKontoDatenzugriff getDatenverwalter () {
		return this.einDatenverwalter;
	}
}
