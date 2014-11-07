package dlg.depotverwaltung;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class WertpapiertransaktionErfassung extends JFrame {

	private JPanel contentPane;
	private JTextField tfDepotnummer;
	private JTextField tfMenge;
	private JTextField tfDatum;
	private JTextField tfPreis;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WertpapiertransaktionErfassung frame = new WertpapiertransaktionErfassung();
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
	public WertpapiertransaktionErfassung() {
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
		
		JComboBox<String> cbWertpapiere = new JComboBox<String>();
		cbWertpapiere.addItem("APPL");
		cbWertpapiere.addItem("MSFT");
		cbWertpapiere.addItem("GOOG");
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
		
		JComboBox<String> cbBoersenplatz = new JComboBox<String>();
		cbBoersenplatz.addItem("New York");
		cbBoersenplatz.addItem("Frankfurt");
		cbBoersenplatz.addItem("Tokyo");
		cbBoersenplatz.addItem("London");
		cbBoersenplatz.setBounds(112, 154, 180, 27);
		contentPane.add(cbBoersenplatz);
		
		JLabel lblBrsenplatz = new JLabel("Boersenplatz:");
		lblBrsenplatz.setBounds(6, 158, 84, 16);
		contentPane.add(lblBrsenplatz);
		
		JButton btnEintragen = new JButton("Eintragen");
		btnEintragen.setBounds(420, 264, 117, 29);
		contentPane.add(btnEintragen);
		
		JButton btnInhalteLoeschen = new JButton("Inhalte loeschen");
		btnInhalteLoeschen.setBounds(6, 264, 147, 29);
		contentPane.add(btnInhalteLoeschen);
		
		JRadioButton rdbtnKauf = new JRadioButton("Kauf");
		rdbtnKauf.setBounds(112, 193, 141, 23);
		contentPane.add(rdbtnKauf);
		
		JRadioButton rdbtnVerkauf = new JRadioButton("Verkauf");
		rdbtnVerkauf.setBounds(112, 229, 141, 23);
		contentPane.add(rdbtnVerkauf);
		
		JLabel lblTransaktion = new JLabel("Transaktion:");
		lblTransaktion.setBounds(6, 197, 79, 16);
		contentPane.add(lblTransaktion);
	}
}
