package awk.depotverwaltung.entity;

import java.io.Serializable;

public class WertpapiertransaktionTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128637892633830197L;
	char typ;
	double preis;
	DepotTO depotTO;
	
	
	/*Konstruktor zur Erzeugung eines TO-Geflechts (KontoTO / Kontobewegung TO)
	 * aus dem Entit�tsgeflecht Konto / Kontobewegung */
	
	public WertpapiertransaktionTO(DepotTO depotTO, char typ, double preis) {
		this.typ = typ;
		this.preis = preis;
		this.depotTO = depotTO;
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
