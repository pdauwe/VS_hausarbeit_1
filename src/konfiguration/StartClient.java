package konfiguration;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dlg.menue.Hauptmenue;
import awk.kontenverwaltung.usecase.IKontenPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

public class StartClient {

	
	public static void main(String[] args) throws AccessException, RemoteException, NotBoundException {
		
		Registry registry = LocateRegistry.getRegistry("127.0.0.1");
		
		IKundenPflegenRemote kundenPflegenRemote = 
			(IKundenPflegenRemote) registry.lookup("kundenPflegen");
		
		IKontenPflegenRemote kontenPflegenRemote = 
			(IKontenPflegenRemote) registry.lookup("kontenPflegen");
		IKontobewegungBuchenRemote kontobewegungBuchenRemote = 
			(IKontobewegungBuchenRemote) registry.lookup("kontobewegungBuchen");
				
		Hauptmenue.main(
				kundenPflegenRemote, kontenPflegenRemote, kontobewegungBuchenRemote);
		
	}

}
