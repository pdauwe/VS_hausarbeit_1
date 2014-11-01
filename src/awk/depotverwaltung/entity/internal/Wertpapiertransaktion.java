package awk.depotverwaltung.entity.internal;

import java.io.Serializable;

import awk.depotverwaltung.entity.WertpapiertransaktionTO;

public class Wertpapiertransaktion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3130735335614620677L;
	char typ;
	double preis;
	Depot depot;
	
	public Wertpapiertransaktion(Depot depot, char typ, double preis) {
		super();
		this.typ = typ;
		this.preis = preis;
		this.depot = depot;
	}
	public WertpapiertransaktionTO toKontobewegungTO() {
		return new WertpapiertransaktionTO(null, this.getTyp(), this.getPreis());
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

}
