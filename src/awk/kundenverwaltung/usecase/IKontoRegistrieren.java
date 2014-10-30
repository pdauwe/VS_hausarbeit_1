package awk.kundenverwaltung.usecase;

import awk.AnwendungskernException;

public interface IKontoRegistrieren {
	
	public void kontoHinzufuegen(int kundennummer, int kontonummer) throws AnwendungskernException;

}
