package awk.kundenverwaltung.type;

import java.io.Serializable;

public class Adresse implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5668483573431584941L;
	private String str;
	private String nr;
	private String plz;
	private String ort;
	
	public Adresse(String str, String nr, String plz, String ort) {
		super();
		this.str = str;
		this.nr = nr;
		this.plz = plz;
		this.ort = ort;
	}
	
	public String toString () {
		return this.str + " " + this.nr + ", " +this.plz + " " + this.ort;
	}
	
	public boolean equals (Object o) {
		Adresse a = (Adresse) o;
		return 
			this.str.equalsIgnoreCase(a.str)
		&& 	this.plz.equalsIgnoreCase(a.plz)
		&& 	this.ort.equalsIgnoreCase(a.ort);
	}

	public static boolean adressePruefen (
			String nachname,
			String vorname,
			String str,
			String nr,
			String plz,
			String ort) {
		
		/* Test auf sytantaktische Korrektheit */
		
		/* Name, Vorname, Strasse und Ort bestehen nur aus Buchstaben, Leerzeichen und -*/
				
		if(!nachname.matches("[a-zA-Z -]*"))
			return false;
//		System.out.println("nachname true");
		
		if(!vorname.matches("[a-zA-Z -]*"))
			return false;
//		System.out.println("vorname true");
		
		if(!str.matches("[a-zA-Z -]*"))
			return false;
//		System.out.println("strasse true");
		
		if(!ort.matches("[a-zA-Z -]*"))
			return false;
//		System.out.println("ort true");
		
		/* PLZ ist 5 Zeichen lang - und au�erdem eine Zahl */
		
		if (plz.length()!=5)
			return false;
//		System.out.println("plz-Laenge true");
		
		try {
			Integer.parseInt(plz);
		} catch (NumberFormatException e) {
			return false;
		}
//		System.out.println("plz int true");
		
		/* Die Hausnummer beginnt mit einer Zahl */
		if (!nr.matches("\\d.*"))
			return false;
//		System.out.println("HNr true");
		
		return true;
	}
	public String getStr() {
		return str;
	}

	public String getNr() {
		return nr;
	}

	public String getPlz() {
		return plz;
	}

	public String getOrt() {
		return ort;
	}
	
	
}

