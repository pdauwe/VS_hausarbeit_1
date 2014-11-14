package awk.kundenverwaltung.factory;

import awk.kundenverwaltung.usecase.IKontoRegistrieren;
import awk.kundenverwaltung.usecase.IKundenPflegen;
import awk.kundenverwaltung.usecase.IKundenSuchen;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;
import awk.kundenverwaltung.usecase.IKundenlisteErstellen;


public interface IKundenverwaltungFactory {
	
	IKundenPflegen getKundenPflegen();
	IKundeninformationFuerNr getKundeninformationFuerNr();
	IKontoRegistrieren getKontoRegistrieren();
	IKundenSuchen getKundenSuchen();
	IKundenlisteErstellen getKundenlisteErstellen();
}
