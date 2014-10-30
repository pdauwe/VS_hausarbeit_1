package awk.kundenverwaltung.factory.impl;

import awk.kundenverwaltung.factory.IKundenverwaltungRemoteFactory;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;
import awk.kundenverwaltung.usecase.impl.KundenPflegenRemote;

public class KundenverwaltungRemoteFactory implements IKundenverwaltungRemoteFactory{
	
	public IKundenPflegenRemote getKundenPflegenRemote() {
		return new KundenPflegenRemote();
	}
	
	
}
