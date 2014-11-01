package awk.depotverwaltung.entity.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
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
	
	private Collection<Wertpapiertransaktion> kontobewegungen;
	
	private int inhaberNr;
	
	public Depot(int depotnummer, int kundennummer) throws AnwendungskernException {
		this.depotNr = depotnummer;
		this.inhaberNr = kundennummer;
		kontobewegungen = new ArrayList<Wertpapiertransaktion>();
		IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
		IKontoRegistrieren kontoRegistrieren = kvf.getKontoRegistrieren();
		kontoRegistrieren.kontoHinzufuegen(this.inhaberNr, this.depotNr);
	}
	
	/* Konstruktor zu Erzeugung eines Entitaetsgeflechts aus dem TO-Geflecht */
	
	public DepotTO toKontoTO() {
		DepotTO einKontoTO = new DepotTO();
		
		einKontoTO.setInhaberNr(this.inhaberNr);
		einKontoTO.setDepotNr(this.depotNr);
		einKontoTO.setKontobewegungen(new ArrayList<WertpapiertransaktionTO>());
		for (Wertpapiertransaktion eineKontobewegung:this.getKontobewegungen()) 
			einKontoTO.getKontobewegungen().add(
				new WertpapiertransaktionTO(einKontoTO, eineKontobewegung.getTyp(), eineKontobewegung.getPreis()));
		
		return einKontoTO;
	}	
	
	public void addKontobewegung (Wertpapiertransaktion eineKontobewegung) {
		this.kontobewegungen.add(eineKontobewegung);
	}
	public Collection <Wertpapiertransaktion> getKontobewegungen () {
		return this.kontobewegungen;
	}
	public int getInhaberNr(){
		return this.inhaberNr;
	}
	public int getDepotNr(){
		return this.depotNr;
	}

	public Date getEroeffnungsdatum() {
		return eroeffnungsdatum;
	}

	public void setEroeffnungsdatum(Date eroeffnungsdatum) {
		this.eroeffnungsdatum = eroeffnungsdatum;
	}

}
