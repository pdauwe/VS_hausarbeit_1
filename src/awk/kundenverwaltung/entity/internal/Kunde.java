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
	private Collection<Integer> depots;
	
	public Kunde(int kundennummer, String nachname, String vorname, String str, String nr,
			String plz, String ort) {
		super();
		this.kundennummer = kundennummer;
		this.nachname = nachname;
		this.vorname = vorname;
		this.adresse = new Adresse (str,nr, plz, ort);
		this.depots = new ArrayList<Integer>();
	}
	
	public Kunde (KundeTO einKundenTO) {
		this.kundennummer = einKundenTO.getKundennummer();
		this.nachname = einKundenTO.getNachname();
		this.vorname = einKundenTO.getVorname();
		this.adresse = new Adresse (
				einKundenTO.getStr(),einKundenTO.getNr(), einKundenTO.getPlz(), einKundenTO.getOrt());
		this.depots = new ArrayList<Integer>();
		for (Integer kontonr:einKundenTO.getDepots()) {
			this.depots.add(kontonr);
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
	public void addDepot(int depotnummer) {
		this.depots.add(Integer.valueOf(depotnummer));
	}
	public Collection<Integer> getDepots() {
		return this.depots;
	}
	
	public boolean equals (Object o) {
		Kunde k = (Kunde) o; 
		return this.nachname.equalsIgnoreCase(k.nachname) 
		&& this.vorname.equalsIgnoreCase(k.vorname) && this.adresse.equals(k.adresse);
	}

}
