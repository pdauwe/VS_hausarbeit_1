package awk.kundenverwaltung.factory;

import awk.kundenverwaltung.usecase.IKontoRegistrieren;
import awk.kundenverwaltung.usecase.IKundenPflegen;
import awk.kundenverwaltung.usecase.IKundeninformationFuerNr;


public interface IKundenverwaltungFactory {
	
	IKundenPflegen getKundenPflegen();
	IKundeninformationFuerNr getKundeninformationFuerNr();
	IKontoRegistrieren getKontoRegistrieren();
}
