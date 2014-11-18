package dlg.depotverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.WertpapierTO;
import awk.depotverwaltung.entity.internal.Boersenplatz;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class WertpapiertransaktionErfassung extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfDepotnummer;
	private JTextField tfMenge;
	private JTextField tfDatum;
	private JTextField tfPreis;
	private JRadioButton rdbtnKauf;
	private JRadioButton rdbtnVerkauf;
	private JComboBox<Object> cbBoersenplatz;
	private JComboBox<Object> cbWertpapiere;

	private ArrayList<WertpapierTO> wertpapiere;
	private IWertpapiertransaktionBuchenRemote wptBuchen;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IWertpapiertransaktionBuchenRemote wptBuchen2) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					WertpapiertransaktionErfassung frame = new WertpapiertransaktionErfassung(wptBuchen2);
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
	public WertpapiertransaktionErfassung (IWertpapiertransaktionBuchenRemote wptBuchen) {
		this.wptBuchen = wptBuchen;
		try{
			this.wertpapiere = wptBuchen.getWertpapiere();
		}catch (AnwendungskernException | RemoteException e){
			e.printStackTrace();
		}
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 321);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tfDepotnummer = new JTextField();
		tfDepotnummer.setBounds(112, 5, 180, 28);
		contentPane.add(tfDepotnummer);
		tfDepotnummer.setColumns(10);
		
		JLabel lblDepotnummer = new JLabel("Depotnummer:");
		lblDepotnummer.setBounds(6, 11, 94, 16);
		contentPane.add(lblDepotnummer);
		
		JLabel lblWertpapiere = new JLabel("Wertpapier:");
		lblWertpapiere.setBounds(6, 39, 78, 16);
		contentPane.add(lblWertpapiere);
		
		cbWertpapiere = new JComboBox<Object>(this.wertpapiere.toArray());
		cbWertpapiere.setBounds(112, 35, 180, 27);
		contentPane.add(cbWertpapiere);
		
		JLabel lblMenge = new JLabel("Menge:");
		lblMenge.setBounds(304, 39, 45, 16);
		contentPane.add(lblMenge);
		
		tfMenge = new JTextField();
		tfMenge.setBounds(361, 33, 134, 28);
		contentPane.add(tfMenge);
		tfMenge.setColumns(10);
		
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(6, 80, 46, 16);
		contentPane.add(lblDatum);
		
		tfDatum = new JTextField();
		tfDatum.setBounds(112, 74, 180, 28);
		contentPane.add(tfDatum);
		tfDatum.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Preis:");
		lblNewLabel.setBounds(6, 120, 34, 16);
		contentPane.add(lblNewLabel);
		
		tfPreis = new JTextField();
		tfPreis.setBounds(112, 114, 180, 28);
		contentPane.add(tfPreis);
		tfPreis.setColumns(10);
		
		cbBoersenplatz = new JComboBox<Object>(this.fillBoersenplaetze().toArray());
		cbBoersenplatz.setBounds(112, 154, 180, 27);
		contentPane.add(cbBoersenplatz);
		
		JLabel lblBrsenplatz = new JLabel("Boersenplatz:");
		lblBrsenplatz.setBounds(6, 158, 84, 16);
		contentPane.add(lblBrsenplatz);
		
		JButton btnEintragen = new JButton("Eintragen");
		btnEintragen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					actionPerformedInhalteErfassen(e);
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnEintragen.setBounds(420, 264, 117, 29);
		contentPane.add(btnEintragen);
		
		JButton btnInhalteLoeschen = new JButton("Inhalte loeschen");
		btnInhalteLoeschen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					actionPerformedInhalteLoeschen(e);
				}catch (Exception e1){
					e1.printStackTrace();
				}
			}
		});
		btnInhalteLoeschen.setBounds(6, 264, 147, 29);
		contentPane.add(btnInhalteLoeschen);
		
		rdbtnKauf = new JRadioButton("Kauf");
		rdbtnKauf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnVerkauf.setSelected(false);
			}
		});
		rdbtnKauf.setBounds(112, 193, 141, 23);
		contentPane.add(rdbtnKauf);
		
		rdbtnVerkauf = new JRadioButton("Verkauf");
		rdbtnVerkauf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rdbtnKauf.setSelected(false);
			}
		});
		rdbtnVerkauf.setBounds(112, 229, 141, 23);
		contentPane.add(rdbtnVerkauf);
		
		JLabel lblTransaktion = new JLabel("Transaktion:");
		lblTransaktion.setBounds(6, 197, 79, 16);
		contentPane.add(lblTransaktion);
	}
	
	private void actionPerformedInhalteErfassen(ActionEvent e) throws RemoteException{
		
		char art = (this.rdbtnKauf.isSelected()) ? 'K' : 'V';
		int depotnummer = Integer.parseInt(this.tfDepotnummer.getText());
		int menge = Integer.parseInt(this.tfMenge.getText());
		double preis = Double.parseDouble(this.tfPreis.getText());
		int boersenplatz = this.cbBoersenplatz.getSelectedIndex();
		WertpapierTO wp = (WertpapierTO)this.cbWertpapiere.getSelectedItem();
		
		
		String datum = this.tfDatum.getText();
		
		boolean ok = false;
		try{
			ok = this.wptBuchen.wertpapierBuchenR(depotnummer, wp, preis, art, menge, boersenplatz, datum);
		}catch(AnwendungskernException e1){
			e1.printStackTrace();
		}
		
		if(!ok){
			JOptionPane.showMessageDialog(null, "Fehler, die Transaktion wurde nicht angelegt");
		}else{
			JOptionPane.showMessageDialog(null, "Transaktion angelegt");
		}
	
	}
	
	/* Setzt die Inhalte der Textfelder und die Auswahl der ComboBoxen zurueck. 
	 * */
	private void actionPerformedInhalteLoeschen(ActionEvent e) throws Exception{
		this.tfDepotnummer.setText("");
		this.tfDatum.setText("");
		this.tfMenge.setText("");
		this.tfPreis.setText("");
		this.cbBoersenplatz.setSelectedIndex(0);
		this.cbWertpapiere.setSelectedIndex(0);
	}
	
	private ArrayList<Boersenplatz> fillBoersenplaetze(){
	
		Boersenplatz newyork = new Boersenplatz(1, "New York");
		Boersenplatz frankfurt = new Boersenplatz(2, "Frankfurt");
		Boersenplatz london = new Boersenplatz (3, "London");
		Boersenplatz tokyo = new Boersenplatz (4, "Tokyo");
		
		ArrayList<Boersenplatz> bps = new ArrayList<Boersenplatz>();
		bps.add(newyork);
		bps.add(frankfurt);
		bps.add(london);
		bps.add(tokyo);
		return bps;
	}
	
	
}
