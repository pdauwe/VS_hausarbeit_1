package awk.kontenverwaltung.usecase.impl;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.kontenverwaltung.usecase.IKontenPflegenRemote;


public class KontenPflegenRemote implements IKontenPflegenRemote{
	

	public int kontoAnlegenR(int kundenNr) throws AnwendungskernException, RemoteException {
		
		KontenPflegen kontenPflegen = new KontenPflegen();
		return kontenPflegen.kontoAnlegen(kundenNr);
	}	
}
