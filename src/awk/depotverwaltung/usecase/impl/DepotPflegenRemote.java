package awk.depotverwaltung.usecase.impl;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;


/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class DepotPflegenRemote implements IDepotPflegenRemote{
	

	@Override
	public int depotAnlegenR(int kundenNr) throws AnwendungskernException, RemoteException {
		
		DepotPflegen depotPflegen = new DepotPflegen();
		return depotPflegen.depotAnlegen(kundenNr);
	}	
}
