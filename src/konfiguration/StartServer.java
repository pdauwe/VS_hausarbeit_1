package konfiguration;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

import awk.kontenverwaltung.factory.IKontenverwaltungRemoteFactory;
import awk.kundenverwaltung.factory.IKundenverwaltungRemoteFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungRemoteFactory;
import awk.kontenverwaltung.factory.impl.KontenverwaltungRemoteFactory;

import awk.kontenverwaltung.usecase.IKontenPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;


public class StartServer {
		
	public static void main (String args[]) throws AccessException, RemoteException {
		/* 1. Implementierung der Komponenten des Anwendungskerns aus Factory beziehen */
		IKundenverwaltungRemoteFactory kundenvf = new KundenverwaltungRemoteFactory();
		IKontenverwaltungRemoteFactory kontenvf = new KontenverwaltungRemoteFactory();
		
		/* 2. Implementierung der benoetigten Use Cases beziehen */
		
		IKundenPflegenRemote kundenPflegenRemote = kundenvf.getKundenPflegenRemote();
		
		IKontenPflegenRemote kontenPflegenRemote = kontenvf.getKontenPflegenRemote();
		IKontobewegungBuchenRemote kontobewegungBuchenRemote = kontenvf.getKontobewegungBuchenRemote();
	
		/* 3. Implementierungen extern verfuebar machen */
		IKundenPflegenRemote stubKundenPflegenRemote =
				(IKundenPflegenRemote) UnicastRemoteObject.exportObject(kundenPflegenRemote,0);
		
		IKontenPflegenRemote stubKontenPflegenRemote =
				(IKontenPflegenRemote) UnicastRemoteObject.exportObject(kontenPflegenRemote,0);
		IKontobewegungBuchenRemote stubKontobewegungBuchenRemote =
				(IKontobewegungBuchenRemote) UnicastRemoteObject.exportObject(kontobewegungBuchenRemote,0);
		
		/* Namensdienst starten und Remote Objekte dort anmelden */
		
		RemoteServer.setLog(System.out);
		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		Registry registry = LocateRegistry.getRegistry();
		
		registry.rebind("kundenPflegen", stubKundenPflegenRemote);

		registry.rebind("kontenPflegen", stubKontenPflegenRemote);
		registry.rebind("kontobewegungBuchen", stubKontobewegungBuchenRemote);
	}
}

