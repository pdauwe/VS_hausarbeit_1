package awk.depotverwaltung.entity.internal;

import awk.depotverwaltung.entity.WertpapierTO;

public class Wertpapier {

	private int nummer;
	private String bezeichnung;
	private char art;
	
	public Wertpapier(int nummer, String bezeichnung, char art){
		this.nummer = nummer;
		this.bezeichnung = bezeichnung;
		this.art = art;
	}
	
	public WertpapierTO toWertpapierTO(){
		WertpapierTO wertpapierTO = new WertpapierTO();
		wertpapierTO.setNummer(this.nummer);
		wertpapierTO.setBezeichnung(this.bezeichnung);
		wertpapierTO.setArt(this.art);
		
		return wertpapierTO;
	}
	
	public int getNummer() {
		return nummer;
	}
	public void setNummer(int nummer) {
		this.nummer = nummer;
	}
	public String getBezeichnung() {
		return bezeichnung;
	}
	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}
	public char getArt() {
		return art;
	}
	public void setArt(char art) {
		this.art = art;
	}
	
	
}
