package dlg.kundenverwaltung;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

public class KundenTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<KundeTO> kundenTOListe = new ArrayList<KundeTO>();

	public KundenTableModel(Collection<KundeTO> kundenListe) {
		for (KundeTO einKundenTO:kundenListe)
			this.kundenTOListe.add(einKundenTO);	
	}
	
	public KundenTableModel(IKundenlisteErstellenRemote listeErstellen) throws AnwendungskernException {
		
		try {
			for (KundeTO einKundenTO:listeErstellen.kundenListeAusgebenR())
				this.kundenTOListe.add(einKundenTO);
		} catch (RemoteException e) {
			e.printStackTrace();
			throw new AnwendungskernException();
			
		}
			
	}
	
		
	public int getRowCount()   {
			return kundenTOListe.size();   
		}    
		
	public int getColumnCount()   {
			return 9;   
		}    
		
	public Object getValueAt( int row, int col )   {
		
			if ( col == 0) 
				return (Object) kundenTOListe.get(row).getKundennummer();     
			else if ( col == 1) 
				return (Object) kundenTOListe.get(row).getNachname();     
			else if ( col == 2)
				return (Object) kundenTOListe.get(row).getVorname();     
			else if ( col == 3)
				return (Object) kundenTOListe.get(row).getStr();
			else if ( col == 4)
				return (Object) kundenTOListe.get(row).getNr();
			else if ( col == 5)
				return (Object) kundenTOListe.get(row).getPlz();
			else if ( col == 6)
				return (Object) kundenTOListe.get(row).getOrt();
			else if ( col == 7 ) {
				if ( kundenTOListe.get(row) instanceof PrivatkundeTO)
					return ((PrivatkundeTO) kundenTOListe.get(row)).getGeschlecht();
				else 
					return "";
			}
			else
				return "";
		}
	
	public String getColumnName (int col) {
		if ( col == 0) 
			return "KdNr";     
		else if ( col == 1) 
			return "Nachname";     
		else if ( col == 2)
			return "Vorname";     
		else if ( col == 3)
			return "Strasse";
		else if ( col == 4)
			return "Nr";
		else if ( col == 5)
			return "PLZ";
		else if ( col == 6)
			return "Ort";
		else if ( col == 7)
			return "Geschlecht";
		else
			return "UstID";
	}	
}
