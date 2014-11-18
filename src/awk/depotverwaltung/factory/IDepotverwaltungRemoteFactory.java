package awk.depotverwaltung.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;


public interface IDepotverwaltungRemoteFactory extends Remote{
	
	IDepotPflegenRemote getDepotPflegenRemote() throws RemoteException;
	IWertpapiertransaktionBuchenRemote getWertpapiertransaktionBuchen() throws RemoteException;
}
