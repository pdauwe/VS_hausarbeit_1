package awk.kundenverwaltung.entity.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.type.Adresse;

public abstract class Kunde implements Serializable{
		
	private static final long serialVersionUID = 5322225081307882380L;
	private int kundennummer;
	private String vorname;
	private String nachname;
	private Adresse adresse;
	private Collection<Integer> konten;
	
	public Kunde(int kundennummer, String nachname, String vorname, String str, String nr,
			String plz, String ort) {
		super();
		this.kundennummer = kundennummer;
		this.nachname = nachname;
		this.vorname = vorname;
		this.adresse = new Adresse (str,nr, plz, ort);
		this.konten = new ArrayList<Integer>();
	}
	
	public Kunde (KundeTO einKundenTO) {
		this.kundennummer = einKundenTO.getKundennummer();
		this.nachname = einKundenTO.getNachname();
		this.vorname = einKundenTO.getVorname();
		this.adresse = new Adresse (
				einKundenTO.getStr(),einKundenTO.getNr(), einKundenTO.getPlz(), einKundenTO.getOrt());
		this.konten = new ArrayList<Integer>();
		for (Integer kontonr:einKundenTO.getKonten()) {
			this.konten.add(kontonr);
		}
		
	}
	
	public abstract KundeTO toKundeTO();
	
	public int getKundenummer() {
		return kundennummer;
	}
	public String getNachname() {
		return nachname;
	}
	public String getVorname() {
		return vorname;
	}
	public Adresse getAdresse() {
		return adresse;
	}
	public void addKonto (int kontonummer) {
		this.konten.add(Integer.valueOf(kontonummer));
	}
	public Collection<Integer> getKonten() {
		return this.konten;
	}
	
	public boolean equals (Object o) {
		Kunde k = (Kunde) o; 
		return this.nachname.equalsIgnoreCase(k.nachname) 
		&& this.vorname.equalsIgnoreCase(k.vorname) && this.adresse.equals(k.adresse);
	}

}
