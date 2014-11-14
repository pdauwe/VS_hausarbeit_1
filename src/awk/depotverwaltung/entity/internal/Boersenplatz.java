package awk.depotverwaltung.entity.internal;


/*
 * Diese Klasse wird nur für den Dialog zur Erstellung einer Wertpapiertransaktion genutzt.
 */
public class Boersenplatz {

	int nummer;
	String bezeichnung;
	
	public Boersenplatz(int nummer, String bezeichnung){
		this.nummer = nummer;
		this.bezeichnung = bezeichnung;
	}

	public int getNummer() {
		return nummer;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}
	
	@Override
	public String toString(){
		return this.bezeichnung;
	}
}
