package konfiguration;

import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;

import awk.depotverwaltung.factory.IDepotverwaltungRemoteFactory;
import awk.depotverwaltung.factory.impl.DepotverwaltungRemoteFactory;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.factory.IKundenverwaltungRemoteFactory;
import awk.kundenverwaltung.factory.impl.KundenverwaltungRemoteFactory;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;


/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class StartServer {
		
	public static void main (String args[]) throws AccessException, RemoteException {
		/* 1. Implementierung der Komponenten des Anwendungskerns aus Factory beziehen */
		IKundenverwaltungRemoteFactory kundenvf = new KundenverwaltungRemoteFactory();
		IDepotverwaltungRemoteFactory depotvf = new DepotverwaltungRemoteFactory();
		
		/* 2. Implementierung der benoetigten Use Cases beziehen */
		
		IKundenPflegenRemote kundenPflegenRemote = kundenvf.getKundenPflegenRemote();
		IKundenlisteErstellenRemote kundenlisteErstellenRemote = kundenvf.getKundenlisteErstellenRemote();
		IDepotPflegenRemote depotPflegenRemote = depotvf.getDepotPflegenRemote();
		IWertpapiertransaktionBuchenRemote wertpapiertransaktionBuchenRemote = depotvf.getWertpapiertransaktionBuchen();
	
		IKundenSuchenRemote kundenSuchenRemote = kundenvf.getKundenSuchenRemote();
		
		/* 3. Implementierungen extern verfuebar machen */
		IKundenPflegenRemote stubKundenPflegenRemote =
				(IKundenPflegenRemote) UnicastRemoteObject.exportObject(kundenPflegenRemote,0);
		
		IKundenlisteErstellenRemote stubKundenlisteErstellenRemote = 
				(IKundenlisteErstellenRemote) UnicastRemoteObject.exportObject(kundenlisteErstellenRemote, 0);
		
		IDepotPflegenRemote stubDepotPflegenRemote =
				(IDepotPflegenRemote) UnicastRemoteObject.exportObject(depotPflegenRemote,0);
		IWertpapiertransaktionBuchenRemote stubWertpapiertransaktionBuchenRemote =
				(IWertpapiertransaktionBuchenRemote) UnicastRemoteObject.exportObject(wertpapiertransaktionBuchenRemote,0);
		
		IKundenSuchenRemote stubKundenSuchenRemote = (IKundenSuchenRemote) UnicastRemoteObject.exportObject(kundenSuchenRemote,0);
		
		/* Namensdienst starten und Remote Objekte dort anmelden */
		
		RemoteServer.setLog(System.out);
		LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
		Registry registry = LocateRegistry.getRegistry();
		
		registry.rebind("kundenPflegen", stubKundenPflegenRemote);
		registry.rebind("kundenlisteErstellen", stubKundenlisteErstellenRemote);

		registry.rebind("depotPflegen", stubDepotPflegenRemote);
		registry.rebind("wpBuchen", stubWertpapiertransaktionBuchenRemote);
		
		registry.rebind("kundenSuchen", stubKundenSuchenRemote);
	}
}

