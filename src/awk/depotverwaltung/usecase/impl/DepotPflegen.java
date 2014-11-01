package awk.depotverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.usecase.IDepotPflegen;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;


public class DepotPflegen implements IDepotPflegen{
	
	public DepotPflegen( ) {
	}

	public int depotAnlegen(int kundenNr) throws AnwendungskernException {
		
		DepotManager einDepotManager = DepotManager.getDepotManager();
		
		IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
		IKundeninformationFuerNr kundeninformation = kvf.getKundeninformationFuerNr();
		KundeTO einKundenTO = kundeninformation.kundeSuchenByNr(kundenNr);
		if ( einKundenTO == null )
			return 0;
		else {
			int max = einDepotManager.naechsteDepotnummerErmitteln();
			Depot einDepot = new Depot(max+1,kundenNr);
			einDepotManager.depotHinzufuegen(einDepot);
			
			return einDepot.getDepotNr();
		}
	}	
}
