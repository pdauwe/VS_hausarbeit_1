package awk.kontenverwaltung.factory.impl;

import awk.kontenverwaltung.factory.IDepotverwaltungFactory;
import awk.kontenverwaltung.usecase.IDepotPflegen;
import awk.kontenverwaltung.usecase.IKontobewegungBuchen;
import awk.kontenverwaltung.usecase.impl.KontobewegungBuchen;
import awk.kontenverwaltung.usecase.impl.DepotPflegen;

public class DepotverwaltungFactory implements IDepotverwaltungFactory{
	
	public IDepotPflegen getDepotPflegen() {
		return new DepotPflegen();
	}

	public IKontobewegungBuchen getKontobewegungBuchen() {	
		return new KontobewegungBuchen();
	}

	
}
