package konfiguration;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dlg.menue.MitarbeiterMenue;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

public class StartClient {

	
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1");
		
		IKundenPflegenRemote kundenPflegenRemote = 
			(IKundenPflegenRemote) registry.lookup("kundenPflegen");
		
		IDepotPflegenRemote kontenPflegenRemote = 
			(IDepotPflegenRemote) registry.lookup("kontenPflegen");
		IWertpapiertransaktionBuchenRemote kontobewegungBuchenRemote = 
			(IWertpapiertransaktionBuchenRemote) registry.lookup("kontobewegungBuchen");
				
//		Hauptmenue.main(
//				kundenPflegenRemote, kontenPflegenRemote, kontobewegungBuchenRemote);
		MitarbeiterMenue.main(kundenPflegenRemote, kontenPflegenRemote, kontobewegungBuchenRemote);
	}

}
