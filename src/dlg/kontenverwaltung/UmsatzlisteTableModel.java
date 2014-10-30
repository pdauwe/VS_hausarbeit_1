package dlg.kontenverwaltung;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;

import awk.kontenverwaltung.entity.KontobewegungTO;


public class UmsatzlisteTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<KontobewegungTO> bewegungTOliste = new ArrayList<KontobewegungTO>();

	public UmsatzlisteTableModel(Collection<KontobewegungTO> bewegungTOliste) {
		for (KontobewegungTO bewegung:bewegungTOliste)
			this.bewegungTOliste.add(bewegung);	
	}
	
//	public UmsatzlisteTableModel(IKundenPflegen einKundenpfleger) throws AnwendungskernException {
//		for (KundeTO einKundenTO:einKundenpfleger.kundenListeAusgeben())
//			this.kundenTOListe.add(einKundenTO);
//			
//	}
		
	public int getRowCount()   {
			return bewegungTOliste.size();   
		}    
		
	public int getColumnCount()   {
			return 2;   
		}    
		
	public Object getValueAt( int row, int col )   {
			if ( col == 0) 
				return (Object) bewegungTOliste.get(row).getTyp();     
			else 
				return (Object) bewegungTOliste.get(row).getBetrag();
		}
	
	public String getColumnName (int col) {
		if ( col == 0) 
			return "Typ";     
		else 
			return "Betrag";
	}	
}
