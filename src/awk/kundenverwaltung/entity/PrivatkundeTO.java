package awk.kundenverwaltung.entity;

import java.util.Date;

import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.entity.internal.Privatkunde;

public class PrivatkundeTO extends KundeTO {

	private static final long serialVersionUID = -6690944127375207095L;

	private String geschlecht;
	private String benutzerkennung;
	private String passwort;
	private Date geburtsdatum;
	
	public Kunde toKunde() {
		Kunde kunde = new Privatkunde(
				this.getKundennummer(),
				this.getNachname(),
				this.getVorname(),
				this.getStr(),
				this.getNr(),
				this.getPlz(),
				this.getOrt(),
				this.getGeschlecht(),
				this.getBenutzerkennung(),
				this.getPasswort(),
				this.getGeburtsdatum());
			
		
		for (Integer kontonr:this.getKonten())
			kunde.getKonten().add(kontonr);
		
		return kunde;
	}
	
	public String getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(String geschlecht) {
		this.geschlecht = geschlecht;
	}
	
	public String getBenutzerkennung() {
		return benutzerkennung;
	}

	public void setBenutzerkennung(String benutzerkennung) {
		this.benutzerkennung = benutzerkennung;
	}
	
	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}
	
	public Date getGeburtsdatum(){
		return this.geburtsdatum;
	}
	
	public void setGeburtsdatum(Date geburtsdatum){
		this.geburtsdatum = geburtsdatum;
	}

}
