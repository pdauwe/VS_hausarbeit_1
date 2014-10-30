package awk.kundenverwaltung.factory.impl;

import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKontoRegistrieren;
import awk.kundenverwaltung.usecase.IKundenPflegen;
import awk.kundenverwaltung.usecase.IKundenSuchen;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;
import awk.kundenverwaltung.usecase.IKundenlisteErstellen;
import awk.kundenverwaltung.usecase.impl.KontoRegistrieren;
import awk.kundenverwaltung.usecase.impl.KundenPflegen;
import awk.kundenverwaltung.usecase.impl.KundenSuchen;
import awk.kundenverwaltung.usecase.impl.KundeninformationFuerNr;
import awk.kundenverwaltung.usecase.impl.KundenlisteErstellen;

public class KundenverwaltungFactory implements IKundenverwaltungFactory{
	
	public IKundenPflegen getKundenPflegen() {
		return new KundenPflegen();
	}
	
	public IKundenSuchen getKundenSuchen(){
		return new KundenSuchen();
	}
	
	public IKundenlisteErstellen getKundenlisteErstellen(){	
		return new KundenlisteErstellen();
	}
	
	public IKontoRegistrieren getKontoRegistrieren(){	
		return new KontoRegistrieren();
	}
	
	public IKundeninformationFuerNr getKundeninformationFuerNr(){	
		return new KundeninformationFuerNr();
	}
	
	

}
