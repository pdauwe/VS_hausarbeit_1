package awk.kundenverwaltung.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import awk.kundenverwaltung.entity.internal.Kunde;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public abstract class KundeTO implements Serializable{
	
	private static final long serialVersionUID = -7558883996516598760L;
	
	int kundennummer;
	String nachname;
	String vorname;
	String str;
	String nr;
	String plz;
	String ort;
	Collection<Integer> depots;
	
	public KundeTO () {
		this.depots = new ArrayList<Integer>();
	}
	
	public abstract Kunde toKunde();
	
	public int getKundennummer() {
		return kundennummer;
	}
	public void setKundennummer(int kundennummer) {
		this.kundennummer = kundennummer;
	}
	public String getNachname() {
		return nachname;
	}
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}
	public String getVorname() {
		return vorname;
	}
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public String getNr() {
		return nr;
	}
	public String getPlz() {
		return plz;
	}
	public void setPlz(String plz) {
		this.plz = plz;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	public String getOrt() {
		return ort;
	}
	public void setOrt(String ort) {
		this.ort = ort;
	}
	public Collection<Integer> getDepots() {
		return this.depots;
	}
	public void addDepot(int depotnummer){
		this.depots.add(Integer.valueOf(depotnummer));
	}

	
}
