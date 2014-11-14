package awk.depotverwaltung.entity.internal;

import java.io.Serializable;


import awk.depotverwaltung.entity.WertpapiertransaktionTO;

public class Wertpapiertransaktion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3130735335614620677L;
	int vorgangsnummer;
	char typ;
	double preis;
	int menge;
	String date;
	Depot depot;
	Wertpapier wertpapier;
	int boersenplatz;
	
	public Wertpapiertransaktion(Depot depot, char typ, double preis, int menge, int vorgangsnummer, String date, Wertpapier wertpapier, int boersenplatz) {
		super();
		this.typ = typ;
		this.preis = preis;
		this.depot = depot;
		this.menge = menge;
		this.vorgangsnummer = vorgangsnummer;
		this.date = date;
		this.wertpapier = wertpapier;
		this.boersenplatz = boersenplatz;
	}
	public WertpapiertransaktionTO toWertpapiertransaktionTO() {
		return new WertpapiertransaktionTO(null, this.getTyp(), this.getPreis(), this.getMenge(), this.getVorgangsnummer(), this.getDate(), this.wertpapier.toWertpapierTO(), this.getBoersenplatz());
	}
	public char getTyp() {
		return typ;
	}
	public void setTyp(char typ) {
		this.typ = typ;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getVorgangsnummer() {
		return vorgangsnummer;
	}
	public void setVorgangsnummer(int vorgangsnummer) {
		this.vorgangsnummer = vorgangsnummer;
	}
	public int getMenge() {
		return menge;
	}
	public void setMenge(int menge) {
		this.menge = menge;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Wertpapier getWertpapier() {
		return wertpapier;
	}
	public void setWertpapier(Wertpapier wertpapier) {
		this.wertpapier = wertpapier;
	}
	public int getBoersenplatz() {
		return boersenplatz;
	}
	public void setBoersenplatz(int boersenplatz) {
		this.boersenplatz = boersenplatz;
	}

}
