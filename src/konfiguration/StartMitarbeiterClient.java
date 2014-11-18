
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
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

public class StartMitarbeiterClient {

	
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1");
		
		IKundenPflegenRemote kundenPflegenRemote = 
			(IKundenPflegenRemote) registry.lookup("kundenPflegen");
		
		IKundenlisteErstellenRemote kundenlisteErstellen = (IKundenlisteErstellenRemote) registry.lookup("kundenlisteErstellen");
		
		IDepotPflegenRemote kontenPflegenRemote = 
			(IDepotPflegenRemote) registry.lookup("depotPflegen");
		IWertpapiertransaktionBuchenRemote wertpapiertransaktionBuchenRemote = 
			(IWertpapiertransaktionBuchenRemote) registry.lookup("wpBuchen");
				
		MitarbeiterMenue.main(kundenPflegenRemote, kontenPflegenRemote, wertpapiertransaktionBuchenRemote, kundenlisteErstellen);
	}

}
