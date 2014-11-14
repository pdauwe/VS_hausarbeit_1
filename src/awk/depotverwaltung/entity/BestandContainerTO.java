package awk.depotverwaltung.entity;

import java.io.Serializable;

public class BestandContainerTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int nummer;
	private String bezeichung;
	private int menge;
	private double summe;
	
	public BestandContainerTO(){
		
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public String getBezeichung() {
		return bezeichung;
	}

	public void setBezeichung(String bezeichung) {
		this.bezeichung = bezeichung;
	}

	public int getMenge() {
		return menge;
	}

	public void setMenge(int menge) {
		this.menge = menge;
	}

	public double getSumme() {
		return summe;
	}

	public void setSumme(double summe) {
		this.summe = summe;
	}
	
	
}
