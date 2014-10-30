package awk.kontenverwaltung.factory;

import java.rmi.Remote;
import java.rmi.RemoteException;

import awk.kontenverwaltung.usecase.IKontenPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;


public interface IKontenverwaltungRemoteFactory extends Remote{
	
	IKontenPflegenRemote getKontenPflegenRemote() throws RemoteException;
	IKontobewegungBuchenRemote getKontobewegungBuchenRemote() throws RemoteException;
}
