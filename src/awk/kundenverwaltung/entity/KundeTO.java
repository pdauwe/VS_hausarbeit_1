package awk.kundenverwaltung.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import awk.kundenverwaltung.entity.internal.Kunde;

public abstract class KundeTO implements Serializable{
	
	private static final long serialVersionUID = -7558883996516598760L;
	
	int kundennummer;
	String nachname;
	String vorname;
	String str;
	String nr;
	String plz;
	String ort;
	Collection<Integer> konten;
	
	public KundeTO () {
		this.konten = new ArrayList<Integer>();
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
	public Collection<Integer> getKonten() {
		return this.konten;
	}
	public void addKonto(int kontonummer){
		this.konten.add(Integer.valueOf(kontonummer));
	}

	
}
