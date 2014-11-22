package awk.kundenverwaltung.usecase;

import java.util.ArrayList;
import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.PrivatkundeTO;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public interface IKundenlisteErstellen {

	public ArrayList<PrivatkundeTO> erstelleKundenListe() throws AnwendungskernException;
}
