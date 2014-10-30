package awk.kundenverwaltung.entity.internal;

import java.io.Serializable;

import awk.kundenverwaltung.entity.PrivatkundeTO;

public class Privatkunde extends Kunde implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8537200194668564835L;
	private String geschlecht;
	
	public Privatkunde(int kundennummer, String nachname, String vorname, String str, String nr,
			String plz, String ort, String geschlecht) {
		super(kundennummer, nachname, vorname, str, nr, plz, ort);
		this.geschlecht = geschlecht;
	}
	
	public Privatkunde (PrivatkundeTO einPKundeTO) {
		super (einPKundeTO);
		this.geschlecht = einPKundeTO.getGeschlecht();
	}
	
	public PrivatkundeTO toKundeTO() {
		
		PrivatkundeTO privatKundenTO = new PrivatkundeTO();
		privatKundenTO.setKundennummer(this.getKundenummer());
		privatKundenTO.setNachname(this.getNachname());
		privatKundenTO.setVorname(this.getVorname());
		privatKundenTO.setStr(this.getAdresse().getStr());
		privatKundenTO.setNr(this.getAdresse().getNr());
		privatKundenTO.setPlz(this.getAdresse().getPlz());
		privatKundenTO.setOrt(this.getAdresse().getOrt());
		for (Integer kontonummer:this.getKonten()) {
			privatKundenTO.addKonto(kontonummer);
			
		}
		privatKundenTO.setGeschlecht(this.geschlecht);
		
		return privatKundenTO;
		
	}
	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}


	
}
