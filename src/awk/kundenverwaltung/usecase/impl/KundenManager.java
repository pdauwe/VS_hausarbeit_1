package awk.kundenverwaltung.usecase.impl;

import java.util.ArrayList;
import java.util.Collection;

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

		
	public boolean istKundevorhanden(Kunde kunde) throws AnwendungskernException {
		try {
			if (!this.einDatenverwalter.kundendatenSuchenByAttribute(kunde.toKundeTO()).isEmpty())
				return true;
			else
				return false;
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
		
	public Collection<Kunde> kundeSuchenByName (
			String nachname,
			String vorname) throws AnwendungskernException {
		Collection<Kunde> ergebnisliste = new ArrayList<Kunde>();
		
		KundeTO suchPKundeTO = new PrivatkundeTO();
		suchPKundeTO.setNachname(nachname);
		suchPKundeTO.setVorname(vorname);
		
		try {
			for (KundeTO kundeTO:this.einDatenverwalter.kundendatenSuchenByAttribute(suchPKundeTO))
				ergebnisliste.add(kundeTO.toKunde());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		
		return ergebnisliste;
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

	
	public Collection<Kunde> kundenLiefern () throws AnwendungskernException  {
		Collection <Kunde> ergebnisliste = new ArrayList<Kunde>();
		
		KundeTO suchPKundeTO = new PrivatkundeTO();
		
		try {
			for (KundeTO kundeTO:this.einDatenverwalter.kundendatenSuchenByAttribute(suchPKundeTO))
				ergebnisliste.add(kundeTO.toKunde());
		} catch (DatenhaltungsException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
		}
		
		return ergebnisliste;
	}
	
		
}
