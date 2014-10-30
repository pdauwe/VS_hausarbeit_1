package awk.kundenverwaltung.factory.impl;

import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKontoRegistrieren;
import awk.kundenverwaltung.usecase.IKundenPflegen;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;
import awk.kundenverwaltung.usecase.impl.KontoRegistrieren;
import awk.kundenverwaltung.usecase.impl.KundenPflegen;
import awk.kundenverwaltung.usecase.impl.KundeninformationFuerNr;


public class KundenverwaltungFactory implements IKundenverwaltungFactory{
	
	public IKundenPflegen getKundenPflegen() {
		return new KundenPflegen();
	}
	
	public IKontoRegistrieren getKontoRegistrieren(){	
		return new KontoRegistrieren();
	}
	
	public IKundeninformationFuerNr getKundeninformationFuerNr(){	
		return new KundeninformationFuerNr();
	}
	

}
