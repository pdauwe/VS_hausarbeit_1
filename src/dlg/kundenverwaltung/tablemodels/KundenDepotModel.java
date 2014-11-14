package dlg.kundenverwaltung.tablemodels;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import awk.depotverwaltung.entity.BestandContainerTO;

public class KundenDepotModel extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<BestandContainerTO> bcs = new ArrayList<BestandContainerTO>();
	
	
	public KundenDepotModel(Collection<BestandContainerTO> papiere){
		for(BestandContainerTO wpt : papiere){
			this.bcs.add(wpt);
		}
		JOptionPane.showMessageDialog(null, this.bcs);
	}
	
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.bcs.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		switch(columnIndex){
		case 0: return this.bcs.get(rowIndex).getBezeichung(); 
		case 1: return this.bcs.get(rowIndex).getMenge();
		case 2: return this.bcs.get(rowIndex).getSumme(); 
		default: return null;
		}
	}
	
	public String getColumnName(int column) {
		switch(column){
		case 0: return "Wertpapier";
		case 1: return "Menge";
		case 2: return "Summe";
		default: return null;
		}
	}


	public ArrayList<BestandContainerTO> getWertpapiere() {
		return bcs;
	}

	public void setWertpapiere(ArrayList<BestandContainerTO> bcs) {
		this.bcs = bcs;
	}

}
