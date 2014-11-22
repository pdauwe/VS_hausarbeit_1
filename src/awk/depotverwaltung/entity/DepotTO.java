package awk.depotverwaltung.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.entity.internal.Wertpapiertransaktion;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class DepotTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1965005798780312432L;
	private int depotNr;
	private int inhaberNr;
	private String inhaberNachname;
	private String inhaberVorname;
	private Date eroeffnungsdatum;
	private Collection<WertpapiertransaktionTO> wertpapiertransaktionen;
	
	public DepotTO(){
		this.wertpapiertransaktionen = new HashSet<WertpapiertransaktionTO>();
	}
	
	public Depot toDepot()  throws AnwendungskernException {
		
		Depot depot = new Depot(this.depotNr, this.inhaberNr);
		depot.setEroeffnungsdatum(this.getEroeffnungsdatum());
		if (this.wertpapiertransaktionen!=null){
			
			for (WertpapiertransaktionTO wertpapiertransaktionTO:this.wertpapiertransaktionen)
				depot.getWertpapiertransaktionen().add(
						new Wertpapiertransaktion(depot,
								wertpapiertransaktionTO.getTyp(),
								wertpapiertransaktionTO.getPreis(),
								wertpapiertransaktionTO.getMenge(),
								wertpapiertransaktionTO.getVorgangsnummer(),
								wertpapiertransaktionTO.getDate(),
								wertpapiertransaktionTO.getWertpapierTO().toWertpapier(),
								wertpapiertransaktionTO.getBoersenplatz()
								)
						);								
		}
		return depot;
		
	}
	
	public int getDepotNr() {
		return depotNr;
	}

	public int getInhaberNr() {
		return inhaberNr;
	}
	public Collection<WertpapiertransaktionTO> getWertpapiertransaktionen () {
		return this.wertpapiertransaktionen;
	}
	public void setDepotNr(int depotNr) {
		this.depotNr = depotNr;
	}
	public void setInhaberNr(int inhaberNr) {
		this.inhaberNr = inhaberNr;
	}
	public void setWertpapiertransaktionen(Collection<WertpapiertransaktionTO> wertpapiertransaktionen) {
		this.wertpapiertransaktionen = wertpapiertransaktionen;
	}

	public String getInhaberNachname() {
		return inhaberNachname;
	}

	public void setInhaberNachname(String inhaberNachname) {
		this.inhaberNachname = inhaberNachname;
	}

	public String getInhaberVorname() {
		return inhaberVorname;
	}

	public void setInhaberVorname(String inhaberVorname) {
		this.inhaberVorname = inhaberVorname;
	}

	public Date getEroeffnungsdatum() {
		return eroeffnungsdatum;
	}

	public void setEroeffnungsdatum(Date eroeffnungsdatum) {
		this.eroeffnungsdatum = eroeffnungsdatum;
	}
	
}
