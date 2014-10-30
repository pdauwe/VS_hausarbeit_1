package awk.kontenverwaltung.factory;

import awk.kontenverwaltung.usecase.IKontenPflegen;
import awk.kontenverwaltung.usecase.IKontobewegungBuchen;


public interface IKontenverwaltungFactory {
	
	IKontenPflegen getKontenPflegen();
	IKontobewegungBuchen getKontobewegungBuchen();
}
