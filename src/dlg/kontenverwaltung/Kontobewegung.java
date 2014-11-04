package dlg.kontenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

import awk.AnwendungskernException;
import awk.depotverwaltung.entity.DepotTO;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;

import javax.swing.JTable;

public class Kontobewegung extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6222125491087343397L;
	private JPanel contentPane;
	private JTextField tf_kontonummer;
	private JTextField tf_saldo;
	private JTextField tf_name;
	private JTextField tf_vorname;
	private JTextField tf_betrag;
	private JTable t_umsatzliste;
	
	private IWertpapiertransaktionBuchenRemote kontobewegungBuchen;

	/**
	 * Launch the application.
	 */
	public static void main(final IWertpapiertransaktionBuchenRemote kontobewegungBuchen2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kontobewegung frame = new Kontobewegung(kontobewegungBuchen2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param kontobewegungBuchen2 
	 */
	public Kontobewegung(IWertpapiertransaktionBuchenRemote kontobewegungBuchen2) {
		
		this.kontobewegungBuchen = kontobewegungBuchen2;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKontonummer = new JLabel("Kontonummer:");
		lblKontonummer.setBounds(29, 25, 93, 16);
		contentPane.add(lblKontonummer);
		
		tf_kontonummer = new JTextField();
		tf_kontonummer.setBounds(159, 19, 134, 28);
		contentPane.add(tf_kontonummer);
		tf_kontonummer.setColumns(10);
		
		JButton jb_kontoauskunft = new JButton("Kontoauskunft");
		jb_kontoauskunft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actionPerformedKontoauskunft(arg0);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		jb_kontoauskunft.setBounds(327, 20, 117, 29);
		contentPane.add(jb_kontoauskunft);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(29, 59, 61, 16);
		contentPane.add(lblName);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(175, 59, 61, 16);
		contentPane.add(lblVorname);
		
		tf_saldo = new JTextField();
		tf_saldo.setBounds(331, 87, 134, 28);
		contentPane.add(tf_saldo);
		tf_saldo.setColumns(10);
		
		JLabel lblSaldo = new JLabel("Saldo");
		lblSaldo.setBounds(335, 59, 61, 16);
		contentPane.add(lblSaldo);
		
		tf_name = new JTextField();
		tf_name.setBounds(29, 87, 134, 28);
		contentPane.add(tf_name);
		tf_name.setColumns(10);
		
		tf_vorname = new JTextField();
		tf_vorname.setBounds(175, 87, 134, 28);
		contentPane.add(tf_vorname);
		tf_vorname.setColumns(10);
		
		JLabel lblBetrag = new JLabel("Betrag");
		lblBetrag.setBounds(29, 233, 61, 16);
		contentPane.add(lblBetrag);
		
		tf_betrag = new JTextField();
		tf_betrag.setBounds(102, 233, 134, 28);
		contentPane.add(tf_betrag);
		tf_betrag.setColumns(10);
		
		JButton jb_einzahlen = new JButton("Einzahlen");
		jb_einzahlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedEinzahlen(e);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		jb_einzahlen.setBounds(259, 233, 117, 29);
		contentPane.add(jb_einzahlen);
		
		JButton jb_auszahlen = new JButton("Auszahlen");
		jb_auszahlen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedAuszahlen(e);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		jb_auszahlen.setBounds(259, 261, 117, 29);
		contentPane.add(jb_auszahlen);
		
		t_umsatzliste = new JTable();
		t_umsatzliste.setBounds(29, 130, 433, 88);
		contentPane.add(t_umsatzliste);
	}

	protected void actionPerformedAuszahlen(ActionEvent e) throws RemoteException {
		System.out.println("b_auszahlen.actionPerformed, event="+e);
		int kontoNr = Integer.parseInt(tf_kontonummer.getText());
		double betrag =Double.parseDouble(tf_betrag.getText());
		boolean erfolgreich = false;
		try {
			erfolgreich = kontobewegungBuchen.abhebenR(kontoNr, betrag);
		} catch (AnwendungskernException evt) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung!");
			evt.printStackTrace();
		}
		
		if ( erfolgreich )
			JOptionPane.showMessageDialog(null, "Abhebung erfolgreich");
		else
			JOptionPane.showMessageDialog(null, "Abhebung nicht erfolgreich");
		
	}

	protected void actionPerformedEinzahlen(ActionEvent e) throws RemoteException {
		System.out.println("b_einzahlen.actionPerformed, event="+e);
		int kontoNr = Integer.parseInt(tf_kontonummer.getText());
		double betrag =Double.parseDouble(tf_betrag.getText());
		boolean erfolgreich = false;
		try {
			erfolgreich = kontobewegungBuchen.einzahlenR(kontoNr, betrag);
		} catch (AnwendungskernException evt) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung!");
			evt.printStackTrace();
		}
		
		if ( erfolgreich )
			JOptionPane.showMessageDialog(null, "Einzahlung erfolgreich");
		else
			JOptionPane.showMessageDialog(null, "Einzahlung nicht erfolgreich");
		
	}

	protected void actionPerformedKontoauskunft(ActionEvent arg0) throws RemoteException {
		System.out.println("b_auskunftGeben.actionPerformed, event="+arg0);
		int kontoNr = Integer.parseInt(tf_kontonummer.getText());
		DepotTO einKontoTO = null;
		try {
			einKontoTO = kontobewegungBuchen.depotSuchenR(kontoNr);
		} catch (AnwendungskernException e) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung!");
			e.printStackTrace();
		}
		if (einKontoTO!=null) {
			tf_name.setText(einKontoTO.getInhaberNachname());
			tf_vorname.setText(einKontoTO.getInhaberVorname());
			tf_saldo.setText(String.valueOf(einKontoTO.getSaldo()));
			t_umsatzliste.setModel(new UmsatzlisteTableModel(einKontoTO.getWertpapiertransaktionen()));
		}
		else JOptionPane.showMessageDialog(null, "Es existiert kein Konto mit dieser Nummer");
			
		
	}
}
