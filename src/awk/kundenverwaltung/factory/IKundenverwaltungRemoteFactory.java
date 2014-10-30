package awk.kundenverwaltung.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.kundenverwaltung.usecase.IKundenPflegenRemote;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;


public interface IKundenverwaltungRemoteFactory extends Remote {
	
	IKundenPflegenRemote getKundenPflegenRemote() throws RemoteException;
	IKundenSuchenRemote getKundenSuchenRemote() throws RemoteException;
	IKundenlisteErstellenRemote getKundenlisteErstellenRemote() throws RemoteException;
	
}
