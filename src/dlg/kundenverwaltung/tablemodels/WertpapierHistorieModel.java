package dlg.kundenverwaltung.tablemodels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import awk.depotverwaltung.entity.WertpapiertransaktionTO;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class WertpapierHistorieModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<WertpapiertransaktionTO> wpts;
	
	public WertpapierHistorieModel(ArrayList<WertpapiertransaktionTO> wpts){
		this.wpts = wpts;
		
		if(this.wpts.size() == 0){
			JOptionPane.showMessageDialog(null, "Keine Historie gefunden");
		}
		
	}
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.wpts.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		switch(columnIndex){
		case 0: return wpts.get(rowIndex).getVorgangsnummer();
		case 1: return wpts.get(rowIndex).getWertpapierTO().getBezeichnung();
		case 2: return wpts.get(rowIndex).getMenge();
		case 3: return wpts.get(rowIndex).getPreis();
		case 4: return new SimpleDateFormat("dd.MM.yyyy").format(wpts.get(rowIndex).getDate());
		case 5: return wpts.get(rowIndex).getTyp();
		case 6: return wpts.get(rowIndex).getBoersenplatz();
		}
	
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		switch (column){
			case 0: return "Vorgangsnummer";
			case 1: return "Wertpapier";
			case 2: return "Menge";
			case 3: return "Preis";
			case 4: return "Datum";
			case 5: return "Transaktionsart";
			case 6: return "Boersenplatz";
		}
		return null;
	}

}
