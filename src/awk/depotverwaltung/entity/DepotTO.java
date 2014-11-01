package awk.depotverwaltung.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.internal.Depot;
import awk.depotverwaltung.entity.internal.Wertpapiertransaktion;

public class DepotTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1965005798780312432L;
	private int depotNr;
	private int inhaberNr;
	private String inhaberNachname;
	private String inhaberVorname;
	private Collection<WertpapiertransaktionTO> wertpapiertransaktionen;
	
	public DepotTO(){
		this.wertpapiertransaktionen = new HashSet<WertpapiertransaktionTO>();
	}
	
	/*
	 * wandelt Geflecht aus Konto/Kontobewegungen in Geflecht KontoTO/Kontobewegung TO
	 * Achtung: inhaberNachname und inhaberVorname werden hier nicht gesetzt!
	 */
	
	public Depot toDepot()  throws AnwendungskernException {
		
		Depot depot = new Depot(this.depotNr, this.inhaberNr);
		
		if (this.wertpapiertransaktionen!=null){
			
			for (WertpapiertransaktionTO wertpapiertransaktionTO:this.wertpapiertransaktionen)

				depot.getWertpapiertransaktionen().add(
					new Wertpapiertransaktion(depot,
							wertpapiertransaktionTO.getTyp(),
							wertpapiertransaktionTO.getPreis(),
							wertpapiertransaktionTO.getMenge(),
							wertpapiertransaktionTO.getVorgangsnummer(),
							wertpapiertransaktionTO.getDate(),
							wertpapiertransaktionTO.getWertpapierTO().toWertpapier()));
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
	
}
