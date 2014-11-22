package awk.kundenverwaltung.factory.impl;

import awk.kundenverwaltung.factory.IKundenverwaltungFactory;
import awk.kundenverwaltung.usecase.IKundenPflegen;
import awk.kundenverwaltung.usecase.IKundenSuchen;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;
import awk.kundenverwaltung.usecase.IKundenlisteErstellen;
import awk.kundenverwaltung.usecase.impl.KundenPflegen;
import awk.kundenverwaltung.usecase.impl.KundenSuchen;
import awk.kundenverwaltung.usecase.impl.KundeninformationFuerNr;
import awk.kundenverwaltung.usecase.impl.KundenlisteErstellen;


/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenverwaltungFactory implements IKundenverwaltungFactory{
	
	@Override
	public IKundenPflegen getKundenPflegen() {
		return new KundenPflegen();
	}
	
	@Override
	public IKundeninformationFuerNr getKundeninformationFuerNr(){	
		return new KundeninformationFuerNr();
	}
	
	@Override
	public IKundenSuchen getKundenSuchen(){
		return new KundenSuchen();
	}
	
	@Override
	public IKundenlisteErstellen getKundenlisteErstellen(){
		return new KundenlisteErstellen();
	}
	

}
