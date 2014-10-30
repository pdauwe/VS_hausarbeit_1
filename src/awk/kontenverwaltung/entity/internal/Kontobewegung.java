package awk.kontenverwaltung.entity.internal;

import java.io.Serializable;

import awk.kontenverwaltung.entity.KontobewegungTO;

public class Kontobewegung implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3130735335614620677L;
	char typ;
	double betrag;
	Konto konto;
	
	public Kontobewegung(Konto konto, char typ, double betrag) {
		super();
		this.typ = typ;
		this.betrag = betrag;
		this.konto = konto;
	}
	public KontobewegungTO toKontobewegungTO() {
		return new KontobewegungTO(null, this.getTyp(), this.getBetrag());
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
