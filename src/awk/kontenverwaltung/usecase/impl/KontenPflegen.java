package awk.kontenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.internal.Depot;
import awk.kontenverwaltung.usecase.IKontenPflegen;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;


public class KontenPflegen implements IKontenPflegen{
	
	public KontenPflegen( ) {
	}

	public int kontoAnlegen(int kundenNr) throws AnwendungskernException {
		
		KontenManager einKontenManager = KontenManager.getKontenManager();
		
		IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
		IKundeninformationFuerNr kundeninformation = kvf.getKundeninformationFuerNr();
		KundeTO einKundenTO = kundeninformation.kundeSuchenByNr(kundenNr);
		if ( einKundenTO == null )
			return 0;
		else {
			int max = einKontenManager.naechsteKontonummerErmitteln();
			Depot einKonto = new Depot(max+1,kundenNr);
			einKontenManager.kontoHinzufuegen(einKonto);
			
			return einKonto.getKontoNr();
		}
	}	
}
