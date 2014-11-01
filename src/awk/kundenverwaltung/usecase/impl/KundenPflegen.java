package awk.kundenverwaltung.usecase.impl;

import java.util.Date;

import awk.AnwendungskernException;
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
		
		if ( kundenmanager.istKundevorhanden(new Privatkunde(0,nachname, vorname, str, nr, plz, ort, "0"))){
			System.out.println("Adresse schon vorhanden");
			return false;
		}
		
		return true;
	}

	public boolean privatkundeAnlegen(String vorname, String nachname, String str,
			String nr, String plz, String ort, String geschlecht, String benutzername, String passwort, Date geburtsdatum) throws AnwendungskernException {
		
		KundenManager einKundenManager =  KundenManager.getKundenManager();
		System.out.println("name:"+nachname+" "+vorname+" "+str+" "+nr+" "+plz+" "+ort+" "+geschlecht);
		System.out.println(this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort));
		if (this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort)) {
			int max = einKundenManager.naechsteKundennummerErmitteln();
			System.out.println("max: "+max);
			einKundenManager.kundeHinzufuegen(new Privatkunde( max+1, nachname,vorname,str,nr,plz,ort,geschlecht, benutzername, passwort, geburtsdatum));
			return true;
			
		}
		else 
			return false;
		
	}
}
