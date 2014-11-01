package awk.kontenverwaltung.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.kontenverwaltung.usecase.IDepotPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;


public interface IDepotverwaltungRemoteFactory extends Remote{
	
	IDepotPflegenRemote getDepotPflegenRemote() throws RemoteException;
	IKontobewegungBuchenRemote getKontobewegungBuchenRemote() throws RemoteException;
}
