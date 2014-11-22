package awk.kundenverwaltung.entity.internal;

import java.io.Serializable;
import java.util.Date;

import awk.kundenverwaltung.entity.PrivatkundeTO;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class Privatkunde extends Kunde implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8537200194668564835L;
	private String geschlecht;
	private String benutzerkennung;
	private String passwort;
	private Date geburtsdatum;
	
	public Privatkunde(int kundennummer, String nachname, String vorname, String str, String nr,
			String plz, String ort, String geschlecht) {
		super(kundennummer, nachname, vorname, str, nr, plz, ort);
		this.geschlecht = geschlecht;
	}
	
	public Privatkunde(int kundennummer, String nachname, String vorname, String str, String nr,
			String plz, String ort, String geschlecht, String benutzerkennung, String passwort, Date geburtsdatum) {
		super(kundennummer, nachname, vorname, str, nr, plz, ort);
		this.geschlecht = geschlecht;
		this.benutzerkennung = benutzerkennung;
		this.passwort = passwort;
		this.geburtsdatum = geburtsdatum;
	}
	
	public Privatkunde (PrivatkundeTO einPKundeTO) {
		super (einPKundeTO);
		this.geschlecht = einPKundeTO.getGeschlecht();
		this.benutzerkennung = einPKundeTO.getBenutzerkennung();
		this.passwort = einPKundeTO.getPasswort();
		this.geburtsdatum = einPKundeTO.getGeburtsdatum();
		
	}
	
	@Override
	public PrivatkundeTO toKundeTO() {
		
		PrivatkundeTO privatKundenTO = new PrivatkundeTO();
		privatKundenTO.setKundennummer(this.getKundenummer());
		privatKundenTO.setNachname(this.getNachname());
		privatKundenTO.setVorname(this.getVorname());
		privatKundenTO.setStr(this.getAdresse().getStr());
		privatKundenTO.setNr(this.getAdresse().getNr());
		privatKundenTO.setPlz(this.getAdresse().getPlz());
		privatKundenTO.setOrt(this.getAdresse().getOrt());
		for (Integer depotnummer:this.getDepots()) {
			privatKundenTO.addDepot(depotnummer);
			
		}
		privatKundenTO.setGeschlecht(this.geschlecht);
		privatKundenTO.setBenutzerkennung(this.benutzerkennung);
		privatKundenTO.setPasswort(this.passwort);
		privatKundenTO.setGeburtsdatum(this.geburtsdatum);
		
		
		return privatKundenTO;
		
	}
	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	
	public String getBenutzerkennung(){
		return benutzerkennung;
	}
	
	public void setBenutzerkennung(String benutzerkennung){
		this.benutzerkennung = benutzerkennung;
	}

	public Date getGeburtsdatum(){
		return this.geburtsdatum;
	}
	
	public void setGeburtsdatum(Date geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}


	
}
