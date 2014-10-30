package awk.kontenverwaltung.entity.internal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import awk.AnwendungskernException;
import awk.kontenverwaltung.entity.KontoTO;
import awk.kontenverwaltung.entity.KontobewegungTO;
import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKontoRegistrieren;

public class Konto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1791762923927701475L;
	private final int kontoNr;
	private double saldo;
	private Collection<Kontobewegung> kontobewegungen;
	
	private int inhaberNr;
	
	public Konto(int kontonummer, int kundennummer) throws AnwendungskernException {
		this.kontoNr = kontonummer;
		this.inhaberNr = kundennummer;
		this.saldo = 0.0;
		kontobewegungen = new ArrayList<Kontobewegung>();
		IKundenverwaltungFactory kvf = new KundenverwaltungFactory();
		IKontoRegistrieren kontoRegistrieren = kvf.getKontoRegistrieren();
		kontoRegistrieren.kontoHinzufuegen(this.inhaberNr, this.kontoNr);
	}
	
	/* Konstruktor zu Erzeugung eines Entit�tsgeflechts aus dem TO-Geflecht */
	
	public KontoTO toKontoTO() {
		KontoTO einKontoTO = new KontoTO();
		
		einKontoTO.setInhaberNr(this.inhaberNr);
		einKontoTO.setKontoNr(this.kontoNr);
		einKontoTO.setSaldo(this.saldo);
		einKontoTO.setKontobewegungen(new ArrayList<KontobewegungTO>());
		for (Kontobewegung eineKontobewegung:this.getKontobewegungen()) 
			einKontoTO.getKontobewegungen().add(
				new KontobewegungTO(einKontoTO, eineKontobewegung.getTyp(), eineKontobewegung.getBetrag()));
		
		return einKontoTO;
	}	
	
	public void setSaldo (double amount){
		this.saldo = amount;
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
		return this.kontoNr;
	}
	public double getSaldo() {
		return this.saldo;
	}

	
	

}
