package awk.kontenverwaltung.factory.impl;

import awk.kontenverwaltung.factory.IKontenverwaltungFactory;
import awk.kontenverwaltung.usecase.IKontenPflegen;
import awk.kontenverwaltung.usecase.IKontobewegungBuchen;
import awk.kontenverwaltung.usecase.impl.KontobewegungBuchen;
import awk.kontenverwaltung.usecase.impl.KontenPflegen;

public class KontenverwaltungFactory implements IKontenverwaltungFactory{
	
	public IKontenPflegen getKontenPflegen() {
		return new KontenPflegen();
	}

	public IKontobewegungBuchen getKontobewegungBuchen() {	
		return new KontobewegungBuchen();
	}

	
}
