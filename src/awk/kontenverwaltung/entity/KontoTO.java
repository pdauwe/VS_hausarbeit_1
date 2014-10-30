package awk.kontenverwaltung.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.internal.Konto;
import awk.kontenverwaltung.entity.internal.Kontobewegung;

public class KontoTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1965005798780312432L;
	private int kontoNr;
	private double saldo;
	private int inhaberNr;
	private String inhaberNachname;
	private String inhaberVorname;
	private Collection<KontobewegungTO> kontobewegungen;
	
	public KontoTO(){
		this.kontobewegungen = new HashSet<KontobewegungTO>();
	}
	
	/*
	 * wandelt Geflecht aus Konto/Kontobewegungen in Geflecht KontoTO/Kontobewegung TO
	 * Achtung: inhaberNachname und inhaberVorname werden hier nicht gesetzt!
	 */
	
	public Konto toKonto()  throws AnwendungskernException {
		
		Konto konto = new Konto(this.kontoNr, this.inhaberNr);
		konto.setSaldo(this.saldo);
		if (this.kontobewegungen!=null){
			for (KontobewegungTO kontobewegungTO:this.kontobewegungen)
				konto.getKontobewegungen().add(
					new Kontobewegung(konto, kontobewegungTO.getTyp(),kontobewegungTO.getBetrag()));
		}
		return konto;
		
	}
	
	public int getKontoNr() {
		return kontoNr;
	}
	public double getSaldo() {
		return saldo;
	}
	public int getInhaberNr() {
		return inhaberNr;
	}
	public Collection<KontobewegungTO> getKontobewegungen () {
		return this.kontobewegungen;
	}
	public void setKontoNr(int kontoNr) {
		this.kontoNr = kontoNr;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public void setInhaberNr(int inhaberNr) {
		this.inhaberNr = inhaberNr;
	}
	public void setKontobewegungen(Collection<KontobewegungTO> kontobewegungen) {
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
