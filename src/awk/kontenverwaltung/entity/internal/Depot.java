package awk.kontenverwaltung.entity.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.DepotTO;
import awk.kontenverwaltung.entity.KontobewegungTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKontoRegistrieren;

public class Depot implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1791762923927701475L;
	private final int depotNr;
	private Date eroeffnungsdatum;
	
	private Collection<Kontobewegung> kontobewegungen;
	
	private int inhaberNr;
	
	public Depot(int depotnummer, int kundennummer) throws AnwendungskernException {
		this.depotNr = depotnummer;
		this.inhaberNr = kundennummer;
		kontobewegungen = new ArrayList<Kontobewegung>();
		IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
		IKontoRegistrieren kontoRegistrieren = kvf.getKontoRegistrieren();
		kontoRegistrieren.kontoHinzufuegen(this.inhaberNr, this.depotNr);
	}
	
	/* Konstruktor zu Erzeugung eines Entitaetsgeflechts aus dem TO-Geflecht */
	
	public DepotTO toKontoTO() {
		DepotTO einKontoTO = new DepotTO();
		
		einKontoTO.setInhaberNr(this.inhaberNr);
		einKontoTO.setDepotNr(this.depotNr);
		einKontoTO.setKontobewegungen(new ArrayList<KontobewegungTO>());
		for (Kontobewegung eineKontobewegung:this.getKontobewegungen()) 
			einKontoTO.getKontobewegungen().add(
				new KontobewegungTO(einKontoTO, eineKontobewegung.getTyp(), eineKontobewegung.getBetrag()));
		
		return einKontoTO;
	}	
	
	public void addKontobewegung (Kontobewegung eineKontobewegung) {
		this.kontobewegungen.add(eineKontobewegung);
	}
	public Collection <Kontobewegung> getKontobewegungen () {
		return this.kontobewegungen;
	}
	public int getInhaberNr(){
		return this.inhaberNr;
	}
	public int getKontoNr(){
		return this.depotNr;
	}

	public Date getEroeffnungsdatum() {
		return eroeffnungsdatum;
	}

	public void setEroeffnungsdatum(Date eroeffnungsdatum) {
		this.eroeffnungsdatum = eroeffnungsdatum;
	}

}
