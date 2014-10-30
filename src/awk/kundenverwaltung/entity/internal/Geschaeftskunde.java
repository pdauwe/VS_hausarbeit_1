package awk.kundenverwaltung.entity.internal;

import java.io.Serializable;

import awk.kundenverwaltung.entity.GeschaeftskundeTO;

public class Geschaeftskunde extends Kunde implements Serializable{
	
	private static final long serialVersionUID = -5477236992104065493L;
	String ustId;
	
	public Geschaeftskunde(int kundennummer, String nachname, String vorname, String str, String nr,
			String plz, String ort, String ustId) {
		super(kundennummer, nachname, vorname, str, nr, plz, ort);
		this.ustId = ustId;
	}
	public Geschaeftskunde (GeschaeftskundeTO einGKundeTO) {
		super (einGKundeTO);
		this.ustId = einGKundeTO.getUstID();
	}
	
	public GeschaeftskundeTO toKundeTO() {
		GeschaeftskundeTO einGeschaeftskundenTO = new GeschaeftskundeTO();
		einGeschaeftskundenTO.setKundennummer(this.getKundenummer());
		einGeschaeftskundenTO.setNachname(this.getNachname());
		einGeschaeftskundenTO.setVorname(this.getVorname());
		einGeschaeftskundenTO.setStr(this.getAdresse().getStr());
		einGeschaeftskundenTO.setNr(this.getAdresse().getNr());
		einGeschaeftskundenTO.setPlz(this.getAdresse().getPlz());
		einGeschaeftskundenTO.setOrt(this.getAdresse().getOrt());
		einGeschaeftskundenTO.setUstId(this.ustId);
		for (Integer kontonummer:this.getKonten())
			einGeschaeftskundenTO.addKonto(kontonummer);
		return einGeschaeftskundenTO;
	}
	
	public String getUstId () {
		return ustId;
	}

	

}
