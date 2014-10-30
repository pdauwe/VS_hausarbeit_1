package awk.kundenverwaltung.factory.impl;

import awk.kundenverwaltung.factory.IKundenverwaltungRemoteFactory;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;
import awk.kundenverwaltung.usecase.impl.KundenPflegenRemote;
import awk.kundenverwaltung.usecase.impl.KundenSuchenRemote;
import awk.kundenverwaltung.usecase.impl.KundenlisteErstellenRemote;

public class KundenverwaltungRemoteFactory implements IKundenverwaltungRemoteFactory{
	
	public IKundenPflegenRemote getKundenPflegenRemote() {
		return new KundenPflegenRemote();
	}
	
	public IKundenSuchenRemote getKundenSuchenRemote(){
		return new KundenSuchenRemote();
	}
	
	public IKundenlisteErstellenRemote getKundenlisteErstellenRemote(){	
		return new KundenlisteErstellenRemote();
	}


}
