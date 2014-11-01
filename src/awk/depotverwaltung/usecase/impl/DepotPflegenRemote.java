package awk.depotverwaltung.usecase.impl;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;


public class DepotPflegenRemote implements IDepotPflegenRemote{
	

	public int depotAnlegenR(int kundenNr) throws AnwendungskernException, RemoteException {
		
		DepotPflegen depotPflegen = new DepotPflegen();
		return depotPflegen.depotAnlegen(kundenNr);
	}	
}
