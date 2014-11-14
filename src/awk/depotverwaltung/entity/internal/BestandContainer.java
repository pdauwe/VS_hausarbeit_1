package awk.depotverwaltung.entity.internal;

/*
 * Hilfsklasse, um den Bestand eines Wertpapiers in einem Depot abzubilden
 */
public class BestandContainer {

	private int nummer;
	private String bezeichnung;
	private int menge;
	private double summe;
	
	public BestandContainer(int nummer, String bezeichnung, int menge, int summe){
		this.nummer = nummer;
		this.bezeichnung = bezeichnung;
		this.menge = menge;
		this.summe = summe;
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
