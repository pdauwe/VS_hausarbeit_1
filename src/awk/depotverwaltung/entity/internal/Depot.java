package awk.depotverwaltung.entity.internal;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.entity.WertpapiertransaktionTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKontoRegistrieren;

public class Depot{
	
	/**
	 * 
	 */
	private final int depotNr;
	private String eroeffnungsdatum;
	
	private Collection<Wertpapiertransaktion> wertpapiertransaktionen;
	
	private int inhaberNr;
	
	public Depot(int depotnummer, int kundennummer) throws AnwendungskernException {
		this.depotNr = depotnummer;
		this.inhaberNr = kundennummer;
		Date now = new Date();
		this.eroeffnungsdatum = now.toString();
		wertpapiertransaktionen = new ArrayList<Wertpapiertransaktion>();
		IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
		IKontoRegistrieren kontoRegistrieren = kvf.getKontoRegistrieren();
		kontoRegistrieren.kontoHinzufuegen(this.inhaberNr, this.depotNr);
	}
	
	/* Konstruktor zu Erzeugung eines Entitaetsgeflechts aus dem TO-Geflecht */
	
	public DepotTO toDepotTO() {
		DepotTO einDepotTO = new DepotTO();
		
		einDepotTO.setInhaberNr(this.inhaberNr);
		einDepotTO.setDepotNr(this.depotNr);
		einDepotTO.setEroeffnungsdatum(this.eroeffnungsdatum);
		einDepotTO.setWertpapiertransaktionen(new ArrayList<WertpapiertransaktionTO>());
		
		for (Wertpapiertransaktion eineWertpapiertransaktion:this.getWertpapiertransaktionen()) 
			einDepotTO.getWertpapiertransaktionen().add(
					new WertpapiertransaktionTO(einDepotTO, 
							eineWertpapiertransaktion.getTyp(), 
							eineWertpapiertransaktion.getPreis(), 
							eineWertpapiertransaktion.getMenge(), 
							eineWertpapiertransaktion.getVorgangsnummer(), 
							eineWertpapiertransaktion.getDate(), 
							eineWertpapiertransaktion.getWertpapier().toWertpapierTO(),
							eineWertpapiertransaktion.getBoersenplatz())
					);
		return einDepotTO;
	}	
	
	public void addWertpapiertransaktion (Wertpapiertransaktion eineWertpapiertransaktion) {
		this.wertpapiertransaktionen.add(eineWertpapiertransaktion);
	}
	public Collection <Wertpapiertransaktion> getWertpapiertransaktionen () {
		return this.wertpapiertransaktionen;
	}
	public int getInhaberNr(){
		return this.inhaberNr;
	}
	public int getDepotNr(){
		return this.depotNr;
	}

	public String getEroeffnungsdatum() {
		return eroeffnungsdatum;
	}

	public void setEroeffnungsdatum(String eroeffnungsdatum) {
		this.eroeffnungsdatum = eroeffnungsdatum;
	}

}
