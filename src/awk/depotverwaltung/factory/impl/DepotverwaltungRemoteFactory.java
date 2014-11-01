package awk.depotverwaltung.factory.impl;


import awk.depotverwaltung.factory.IDepotverwaltungRemoteFactory;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.depotverwaltung.usecase.impl.DepotPflegenRemote;
import awk.depotverwaltung.usecase.impl.WertpapiertransaktionBuchenRemote;

public class DepotverwaltungRemoteFactory implements IDepotverwaltungRemoteFactory{
	
	public IDepotPflegenRemote getDepotPflegenRemote() {
		return new DepotPflegenRemote();
	}

	public IWertpapiertransaktionBuchenRemote getKontobewegungBuchenRemote() {	
		return new WertpapiertransaktionBuchenRemote();
	}

	
	
}
