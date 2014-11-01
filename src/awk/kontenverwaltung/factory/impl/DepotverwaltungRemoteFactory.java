package awk.kontenverwaltung.factory.impl;


import awk.kontenverwaltung.factory.IDepotverwaltungRemoteFactory;
import awk.kontenverwaltung.usecase.IDepotPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;
import awk.kontenverwaltung.usecase.impl.DepotPflegenRemote;
import awk.kontenverwaltung.usecase.impl.KontobewegungBuchenRemote;

public class DepotverwaltungRemoteFactory implements IDepotverwaltungRemoteFactory{
	
	public IDepotPflegenRemote getDepotPflegenRemote() {
		return new DepotPflegenRemote();
	}

	public IKontobewegungBuchenRemote getKontobewegungBuchenRemote() {	
		return new KontobewegungBuchenRemote();
	}

	
	
}
