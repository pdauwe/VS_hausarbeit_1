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
	private Collection<WertpapiertransaktionTO> kontobewegungen;
	
	public DepotTO(){
		this.kontobewegungen = new HashSet<WertpapiertransaktionTO>();
	}
	
	/*
	 * wandelt Geflecht aus Konto/Kontobewegungen in Geflecht KontoTO/Kontobewegung TO
	 * Achtung: inhaberNachname und inhaberVorname werden hier nicht gesetzt!
	 */
	
	public Depot toDepot()  throws AnwendungskernException {
		
		Depot depot = new Depot(this.depotNr, this.inhaberNr);
		
		if (this.kontobewegungen!=null){
			for (WertpapiertransaktionTO kontobewegungTO:this.kontobewegungen)
				depot.getKontobewegungen().add(
					new Wertpapiertransaktion(depot, kontobewegungTO.getTyp(),kontobewegungTO.getPreis()));
		}
		return depot;
		
	}
	
	public int getDepotNr() {
		return depotNr;
	}

	public int getInhaberNr() {
		return inhaberNr;
	}
	public Collection<WertpapiertransaktionTO> getKontobewegungen () {
		return this.kontobewegungen;
	}
	public void setDepotNr(int depotNr) {
		this.depotNr = depotNr;
	}
	public void setInhaberNr(int inhaberNr) {
		this.inhaberNr = inhaberNr;
	}
	public void setKontobewegungen(Collection<WertpapiertransaktionTO> kontobewegungen) {
		this.kontobewegungen = kontobewegungen;
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
