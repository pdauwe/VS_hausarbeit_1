package awk.depotverwaltung.entity;

import java.io.Serializable;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.internal.Wertpapier;

public class WertpapierTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1367117351536721L;
	private int nummer;
	private String bezeichnung;
	private char art;
	
	
	public WertpapierTO(){
		
	}
	
	public Wertpapier toWertpapier() throws AnwendungskernException{
		return new Wertpapier(this.nummer, this.bezeichnung, this.art);
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