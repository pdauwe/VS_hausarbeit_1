package awk.kundenverwaltung.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.kundenverwaltung.usecase.IKundenPflegenRemote;


public interface IKundenverwaltungRemoteFactory extends Remote {
	
	IKundenPflegenRemote getKundenPflegenRemote() throws RemoteException;
}
