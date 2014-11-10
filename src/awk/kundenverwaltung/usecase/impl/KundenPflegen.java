package awk.kundenverwaltung.usecase.impl;



import awk.AnwendungskernException;
import awk.DatenhaltungsException;
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
		
		return true;
	}

	public boolean privatkundeAnlegen(String vorname, String nachname, String str,
			String nr, String plz, String ort, String geschlecht, String benutzername, String passwort, String geburtsdatum) throws AnwendungskernException {
		
		KundenManager einKundenManager =  KundenManager.getKundenManager();
		System.out.println("name:"+nachname+" "+vorname+" "+str+" "+nr+" "+plz+" "+ort+" "+geschlecht);
		System.out.println(this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort));
		
		if (this.adressePruefen (einKundenManager, nachname,vorname,str,nr,plz,ort)) {
			int max = 0;
			
			try {
				max = einKundenManager.getMaxKundennummer();
			} catch (DatenhaltungsException e) {
				e.printStackTrace();
			}
			
			einKundenManager.kundeHinzufuegen(new Privatkunde( max, nachname,vorname,str,nr,plz,ort,geschlecht, benutzername, passwort, geburtsdatum));
			
			return true;
			
		}
		else{ 
			return false;
		}
	}
}
