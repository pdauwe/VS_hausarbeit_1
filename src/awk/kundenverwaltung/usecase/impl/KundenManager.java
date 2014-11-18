package awk.kundenverwaltung.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.DatenhaltungsException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.persistence.IKundenDatenzugriff;
import awk.kundenverwaltung.persistence.impl.KundenDatenzugriff_DAO_Db_Einzelsatz;


public class KundenManager {
	
	/* Hier wird festgelegt, dass zur Speicherung eine Datei verwendet wird!*/
	//private IKundenDatenzugriff einDatenverwalter= new KundenDatenzugriff_DAO_Datei();
	private IKundenDatenzugriff einDatenverwalter= new KundenDatenzugriff_DAO_Db_Einzelsatz();
	
	private static KundenManager kundenManager;
	
	public static KundenManager getKundenManager() throws AnwendungskernException {
		if (kundenManager == null) {
			kundenManager = new KundenManager();
		}
		
		return kundenManager;
	}
	
	public void kundeHinzufuegen (Kunde kunde) throws AnwendungskernException {
		try {
			this.einDatenverwalter.kundendatenAnlegen(kunde.toKundeTO());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
	public int getMaxKundennummer () throws DatenhaltungsException {
		int max = 0;
		try{
			max = this.einDatenverwalter.getMaxKundennummer();
		}catch (DatenhaltungsException e){
			e.printStackTrace();
			throw new DatenhaltungsException();
		}
		return max;
	}
	
	public Kunde kundeSuchenByNr (int kundennummer) throws AnwendungskernException {
		KundeTO kundeTO;
		try {
			kundeTO = this.einDatenverwalter.kundendatenSuchenByKey(kundennummer);
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		if (kundeTO == null)
			return null;
		else
			return kundeTO.toKunde();
	}
	
	public Kunde kundeSuchenByBenutzerkennungUndPasswort (String benutzerkennung, String passwort) throws AnwendungskernException{
		KundeTO suchKunde = new PrivatkundeTO();
		
		try{
			suchKunde = this.einDatenverwalter.kundeSuchenFuerLoginUndPasswort(benutzerkennung, passwort);
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		
		return suchKunde.toKunde();
	}
	
	public ArrayList<PrivatkundeTO> kundenlisteErstellen() throws AnwendungskernException{
		try{
			return this.einDatenverwalter.getKundenListe();
		}catch(DatenhaltungsException e){
			e.printStackTrace();
			throw new AnwendungskernException();
		}
	}
	
		
}
