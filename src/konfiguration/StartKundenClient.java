package konfiguration;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dlg.menue.KundenAnmeldenMenue;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;

public class StartKundenClient {

	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1");
		
		IKundenSuchenRemote kundenSuchen = (IKundenSuchenRemote) registry.lookup("kundenSuchen");
		IWertpapiertransaktionBuchenRemote wpbuchen = (IWertpapiertransaktionBuchenRemote) registry.lookup("wpbuchen");
		
		KundenAnmeldenMenue.main(kundenSuchen, wpbuchen);
	}

}
