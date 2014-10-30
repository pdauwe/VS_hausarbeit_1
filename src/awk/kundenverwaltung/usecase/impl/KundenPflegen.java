package awk.kundenverwaltung.usecase.impl;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.internal.Geschaeftskunde;
import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.entity.internal.Privatkunde;
import awk.kundenverwaltung.type.Adresse;
import awk.kundenverwaltung.usecase.IKundenPflegen;

public class KundenPflegen implements IKundenPflegen{
	
	
	public KundenPflegen( ) {
	}
	
	public boolean adressePruefen (
			KundenManager kundenmanager,
			String nachname,
			String vorname,
			String str,
			String nr,
			String plz,
			String ort) throws AnwendungskernException {
		
		/* Test auf syntaktische Korrektheit */
		
		if (!Adresse.adressePruefen(nachname, vorname, str, nr, plz, ort)){
			System.out.println("Syntaktisches Problem");
			return false;
		}
			
		
		/* Pruefen, ob identische Adresse existiert */
		
		if ( kundenmanager.istKundevorhanden(new Geschaeftskunde(0,nachname, vorname, str, nr, plz, ort, "0"))){
			System.out.println("Adresse schon vorhanden");
			return false;
		}
		
		return true;
	}

	public boolean privatkundeAnlegen(String vorname, String nachname, String str,
			String nr, String plz, String ort, String geschlecht) throws AnwendungskernException {
		
		KundenManager einKundenManager =  KundenManager.getKundenManager();
		System.out.println("name:"+nachname+" "+vorname+" "+str+" "+nr+" "+plz+" "+ort+" "+geschlecht);
		System.out.println(this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort));
		if (this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort)) {
			int max = einKundenManager.naechsteKundennummerErmitteln();
			System.out.println("max: "+max);
			einKundenManager.kundeHinzufuegen(new Privatkunde( max+1, nachname,vorname,str,nr,plz,ort,geschlecht));
			return true;
			
		}
		else 
			return false;
		
	}

	public boolean geschaeftskundeAnlegen(String vorname, String nachname, String str,
			String nr, String plz, String ort, String ustId) throws AnwendungskernException {		
		KundenManager einKundenManager = KundenManager.getKundenManager();
		if (this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort)) {
			int max = einKundenManager.naechsteKundennummerErmitteln();
			einKundenManager.kundeHinzufuegen(new Geschaeftskunde(max+1,nachname,vorname,str,nr,plz,ort,ustId));
			return true;
			
		}
		else 
			return false;
		
	}
	
	public boolean kundenLoeschen (int nummer) throws AnwendungskernException {
		KundenManager kundenmanager = KundenManager.getKundenManager();
		Kunde kunde = kundenmanager.kundeSuchenByNr(nummer);
		if (kunde == null)
			return false;
		else if ( !kunde.getKonten().isEmpty() )
			return false;
		else {
			kundenmanager.kundeEntfernen(kunde);
			return true;
		}
	}





}
