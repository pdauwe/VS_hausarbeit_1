package awk.kundenverwaltung.factory;

import awk.kundenverwaltung.usecase.IKontoRegistrieren;
import awk.kundenverwaltung.usecase.IKundenPflegen;
import awk.kundenverwaltung.usecase.IKundenSuchen;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;
import awk.kundenverwaltung.usecase.IKundenlisteErstellen;


public interface IKundenverwaltungFactory {
	
	IKundenPflegen getKundenPflegen();
	IKundenSuchen getKundenSuchen();
	IKundenlisteErstellen getKundenlisteErstellen();
	
	IKontoRegistrieren getKontoRegistrieren();
	IKundeninformationFuerNr getKundeninformationFuerNr();
}
