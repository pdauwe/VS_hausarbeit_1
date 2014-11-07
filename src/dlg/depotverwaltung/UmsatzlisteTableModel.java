package dlg.depotverwaltung;

import java.util.ArrayList;
import java.util.Collection;
import javax.swing.table.AbstractTableModel;

import awk.depotverwaltung.entity.WertpapiertransaktionTO;


public class UmsatzlisteTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<WertpapiertransaktionTO> bewegungTOliste = new ArrayList<WertpapiertransaktionTO>();

	public UmsatzlisteTableModel(Collection<WertpapiertransaktionTO> bewegungTOliste) {
		for (WertpapiertransaktionTO bewegung:bewegungTOliste)
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
				return (Object) bewegungTOliste.get(row).getPreis();
		}
	
	public String getColumnName (int col) {
		if ( col == 0) 
			return "Typ";     
		else 
			return "Betrag";
	}	
}
