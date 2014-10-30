package awk.kontenverwaltung.entity;

import java.io.Serializable;

public class KontobewegungTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7128637892633830197L;
	char typ;
	double betrag;
	KontoTO kontoTO;
	
	
	/*Konstruktor zur Erzeugung eines TO-Geflechts (KontoTO / Kontobewegung TO)
	 * aus dem Entitätsgeflecht Konto / Kontobewegung */
	
	public KontobewegungTO(KontoTO kontoTO, char typ, double betrag) {
		this.typ = typ;
		this.betrag = betrag;
		this.kontoTO = kontoTO;
	}
	
	public char getTyp() {
		return typ;
	}
	public void setTyp(char typ) {
		this.typ = typ;
	}
	public double getBetrag() {
		return betrag;
	}
	public void setBetrag(double betrag) {
		this.betrag = betrag;
	}
}
