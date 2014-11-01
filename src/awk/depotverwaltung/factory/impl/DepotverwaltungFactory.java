package awk.depotverwaltung.factory.impl;

import awk.depotverwaltung.factory.IDepotverwaltungFactory;
import awk.depotverwaltung.usecase.IDepotPflegen;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchen;
import awk.depotverwaltung.usecase.impl.DepotPflegen;
import awk.depotverwaltung.usecase.impl.WertpapiertransaktionBuchen;

public class DepotverwaltungFactory implements IDepotverwaltungFactory{
	
	public IDepotPflegen getDepotPflegen() {
		return new DepotPflegen();
	}

	public IWertpapiertransaktionBuchen getKontobewegungBuchen() {	
		return new WertpapiertransaktionBuchen();
	}

	
}
