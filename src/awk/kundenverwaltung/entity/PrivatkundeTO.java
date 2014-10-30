package awk.kundenverwaltung.entity;

import awk.kundenverwaltung.entity.internal.Kunde;
import awk.kundenverwaltung.entity.internal.Privatkunde;

public class PrivatkundeTO extends KundeTO {

	private static final long serialVersionUID = -6690944127375207095L;

	private String geschlecht;
	
	public Kunde toKunde() {
		Kunde kunde = new Privatkunde(
				this.getKundennummer(),
				this.getNachname(),
				this.getVorname(),
				this.getStr(),
				this.getNr(),
				this.getPlz(),
				this.getOrt(),
				this.getGeschlecht());
		
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
	
}
