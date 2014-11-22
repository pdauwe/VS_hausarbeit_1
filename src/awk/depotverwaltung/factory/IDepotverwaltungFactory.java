package awk.depotverwaltung.factory;

import awk.depotverwaltung.usecase.IDepotPflegen;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchen;


/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IDepotverwaltungFactory {
	
	IDepotPflegen getDepotPflegen();
	IWertpapiertransaktionBuchen getWertpapiertransaktionBuchen();
}
