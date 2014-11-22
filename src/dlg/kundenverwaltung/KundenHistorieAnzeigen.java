package dlg.kundenverwaltung;

import java.awt.EventQueue;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dlg.kundenverwaltung.tablemodels.WertpapierHistorieModel;
import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenHistorieAnzeigen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	private IWertpapiertransaktionBuchenRemote wpbuchen;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IWertpapiertransaktionBuchenRemote wpbuchen2, final int depotnummer2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KundenHistorieAnzeigen frame = new KundenHistorieAnzeigen(wpbuchen2, depotnummer2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KundenHistorieAnzeigen(IWertpapiertransaktionBuchenRemote wpbuchen, int depotnummer) {
		this.wpbuchen = wpbuchen;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 688, 266);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try{
			table.setModel(new WertpapierHistorieModel(this.wpbuchen.wertpapierHistorieFuerDepot(depotnummer)));;
		}catch(AnwendungskernException | RemoteException e){
			e.printStackTrace();
		}
		
	}
}
