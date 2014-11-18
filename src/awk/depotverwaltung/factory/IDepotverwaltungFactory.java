package awk.depotverwaltung.factory;

import awk.depotverwaltung.usecase.IDepotPflegen;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchen;


public interface IDepotverwaltungFactory {
	
	IDepotPflegen getDepotPflegen();
	IWertpapiertransaktionBuchen getWertpapiertransaktionBuchen();
}
