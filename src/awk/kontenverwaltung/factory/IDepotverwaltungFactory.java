package awk.kontenverwaltung.factory;

import awk.kontenverwaltung.usecase.IDepotPflegen;
import awk.kontenverwaltung.usecase.IKontobewegungBuchen;


public interface IDepotverwaltungFactory {
	
	IDepotPflegen getDepotPflegen();
	IKontobewegungBuchen getKontobewegungBuchen();
}
