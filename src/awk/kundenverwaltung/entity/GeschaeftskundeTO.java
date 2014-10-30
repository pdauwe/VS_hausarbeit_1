package awk.kundenverwaltung.entity;

import java.io.Serializable;

import awk.kundenverwaltung.entity.internal.Geschaeftskunde;
import awk.kundenverwaltung.entity.internal.Kunde;

public class GeschaeftskundeTO extends KundeTO implements Serializable {
	
	private static final long serialVersionUID = 806860765260848117L;
	private String ustId;
	
	
	public String getUstID() {
		return ustId;
	}
	public void setUstId(String ustId) {
		this.ustId = ustId;
	}
	
	public Kunde toKunde() {
		Kunde kunde = new Geschaeftskunde(
				this.getKundennummer(),
				this.getNachname(),
				this.getVorname(),
				this.getStr(),
				this.getNr(),
				this.getPlz(),
				this.getOrt(),
				this.getUstID());
		for (Integer kontonr:this.getKonten())
			kunde.getKonten().add(kontonr);
		
		return kunde;
	}
}
