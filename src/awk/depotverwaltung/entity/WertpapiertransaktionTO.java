package awk.depotverwaltung.entity;

import java.io.Serializable;
import java.util.Date;

public class WertpapiertransaktionTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128637892633830197L;
	int vorgangsnummer;
	char typ;
	double preis;
	int menge;
	Date date;
	DepotTO depotTO;
	WertpapierTO wertpapierTO;
	
	
	/*Konstruktor zur Erzeugung eines TO-Geflechts (DepotTO / Wertpapiertransaktion TO)
	 * aus dem Entitaetsgeflecht Depot / Wertpapiertransaktion */
	
	public WertpapiertransaktionTO(DepotTO depotTO, char typ, double preis, int menge, int vorgangsnummer, Date date, WertpapierTO wertpapierTO) {
		this.typ = typ;
		this.preis = preis;
		this.depotTO = depotTO;
		this.menge = menge;
		this.vorgangsnummer = vorgangsnummer;
		this.date = date;
		this.wertpapierTO = wertpapierTO;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public WertpapierTO getWertpapierTO() {
		return wertpapierTO;
	}

	public void setWertpapierTO(WertpapierTO wertpapierTO) {
		this.wertpapierTO = wertpapierTO;
	}
}
