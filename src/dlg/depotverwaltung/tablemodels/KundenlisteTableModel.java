package dlg.depotverwaltung.tablemodels;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenlisteTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<PrivatkundeTO> kunden;
	
	public KundenlisteTableModel(IKundenlisteErstellenRemote kundenlisteErstellenRemote){
		try{
			this.kunden = kundenlisteErstellenRemote.erstelleKundenListe();
		}catch(AnwendungskernException | RemoteException e){
			e.printStackTrace();
		}
	}
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return kunden.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		switch(columnIndex){
		case 0: return kunden.get(rowIndex).getKundennummer();
		case 1: return kunden.get(rowIndex).getVorname();
		case 2: return kunden.get(rowIndex).getNachname();
		case 3: return new SimpleDateFormat("dd.MM.yyyy").format(kunden.get(rowIndex).getGeburtsdatum());
		default: return null;
		}
	}
	
	public String getColumnName(int column){
		switch(column){
		case 0: return "Kundennummer";
		case 1: return "Vorname";
		case 2: return "Nachname";
		case 3: return "Geburtsdatum";
		default: return null;
		}
	}

}
