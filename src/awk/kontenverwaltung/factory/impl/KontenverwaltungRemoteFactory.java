package awk.kontenverwaltung.factory.impl;


import awk.kontenverwaltung.factory.IKontenverwaltungRemoteFactory;
import awk.kontenverwaltung.usecase.IKontenPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;
import awk.kontenverwaltung.usecase.impl.KontenPflegenRemote;
import awk.kontenverwaltung.usecase.impl.KontobewegungBuchenRemote;

public class KontenverwaltungRemoteFactory implements IKontenverwaltungRemoteFactory{
	
	public IKontenPflegenRemote getKontenPflegenRemote() {
		return new KontenPflegenRemote();
	}

	public IKontobewegungBuchenRemote getKontobewegungBuchenRemote() {	
		return new KontobewegungBuchenRemote();
	}

	
	
}
