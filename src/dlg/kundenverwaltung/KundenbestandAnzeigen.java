package dlg.kundenverwaltung;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import dlg.kundenverwaltung.tablemodels.KundenDepotModel;
import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.entity.KundeTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import javax.swing.JTextField;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenbestandAnzeigen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JComboBox<Object> cbDepots; 
	private KundeTO kunde;
	private IWertpapiertransaktionBuchenRemote wpbuchen;
	private JTable tblBestand;
	private JTextField tfWert;
	
	/**
	 * Launch the application.
	 */
	public static void main(final KundeTO kunde2, final IWertpapiertransaktionBuchenRemote wpbuchen2) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KundenbestandAnzeigen frame = new KundenbestandAnzeigen(kunde2, wpbuchen2);
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
	public KundenbestandAnzeigen(KundeTO kunde, final IWertpapiertransaktionBuchenRemote wpbuchen) {
		this.kunde = kunde;
		this.wpbuchen = wpbuchen;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 373);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDepot = new JLabel("Depot:");
		lblDepot.setBounds(6, 27, 61, 16);
		contentPane.add(lblDepot);
		
		cbDepots = new JComboBox<Object>(this.kunde.toKunde().getDepots().toArray());
		cbDepots.setBounds(79, 23, 136, 27);
		contentPane.add(cbDepots);
		
		JButton btnHistorie = new JButton("Historie");
		btnHistorie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedHistorieAnzeigen(e);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnHistorie.setBounds(327, 316, 117, 29);
		contentPane.add(btnHistorie);
		
		JLabel lblBestand = new JLabel("Bestand:");
		lblBestand.setBounds(6, 71, 61, 16);
		contentPane.add(lblBestand);
		
		JButton btnAnzeigen = new JButton("Anzeigen");
		btnAnzeigen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					actionPerformedComboBoxChanged(e);
				}catch(RemoteException e1){
					e1.printStackTrace();
				}
			}
		});
		btnAnzeigen.setBounds(227, 22, 117, 29);
		contentPane.add(btnAnzeigen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 99, 438, 110);
		contentPane.add(scrollPane);
		
		tblBestand = new JTable();
		scrollPane.setViewportView(tblBestand);
		
		JLabel lblWert = new JLabel("Wert:");
		lblWert.setBounds(6, 221, 61, 16);
		contentPane.add(lblWert);
		
		tfWert = new JTextField();
		tfWert.setEditable(false);
		tfWert.setBounds(79, 215, 134, 28);
		contentPane.add(tfWert);
		tfWert.setColumns(10);
	}
	
	public void actionPerformedComboBoxChanged(ActionEvent e) throws RemoteException{
		try{
			this.tblBestand.setModel(new KundenDepotModel(wpbuchen.wertpapierBestandFuerDepot((int)this.cbDepots.getSelectedItem())));
			this.tfWert.setText(String.valueOf(wpbuchen.depotWert((int)this.cbDepots.getSelectedItem())));
		}catch(AnwendungskernException e1){
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "Fehler!");
		}
	}
	
	public void actionPerformedHistorieAnzeigen(ActionEvent e) throws RemoteException{
			KundenHistorieAnzeigen.main(this.wpbuchen, (int) this.cbDepots.getSelectedItem());
	}
}
