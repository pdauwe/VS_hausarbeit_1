package awk.kundenverwaltung.usecase.impl;

import java.util.ArrayList;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.usecase.IKundenlisteErstellen;

public class KundenlisteErstellen implements IKundenlisteErstellen {

	@Override
	public ArrayList<PrivatkundeTO> erstelleKundenListe()
			throws AnwendungskernException {
	
		KundenManager km = KundenManager.getKundenManager();
		
		return km.kundenlisteErstellen();
	}

}
