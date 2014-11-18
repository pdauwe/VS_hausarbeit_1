package dlg.depotverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.WindowConstants;

import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.Box;

public class Depotanlage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8670270853554936786L;
	private JPanel contentPane;
	private JTextField tf_kundennummer;
	private JTextField tf_depotnummer;

	private IDepotPflegenRemote kontenPflegen;
	private IKundenlisteErstellenRemote kundenlisteErstellenRemote;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IDepotPflegenRemote kontenPflegen2, final IKundenlisteErstellenRemote kundenlisteErstellenRemote) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Depotanlage frame = new Depotanlage(kontenPflegen2, kundenlisteErstellenRemote);
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
	public Depotanlage(IDepotPflegenRemote kontenPflegen, IKundenlisteErstellenRemote kundenlisteErstellenRemote) {
		
		this.kontenPflegen = kontenPflegen;
		this.kundenlisteErstellenRemote = kundenlisteErstellenRemote;
		
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKundennummer = new JLabel("Kundennummer:");
		lblKundennummer.setBounds(23, 21, 130, 16);
		contentPane.add(lblKundennummer);
		
		tf_kundennummer = new JTextField();
		tf_kundennummer.setBounds(180, 15, 134, 28);
		contentPane.add(tf_kundennummer);
		tf_kundennummer.setColumns(10);
		
		JLabel lblDepotnummer = new JLabel("Depotnummer:");
		lblDepotnummer.setBounds(33, 106, 130, 16);
		contentPane.add(lblDepotnummer);
		
		tf_depotnummer = new JTextField();
		tf_depotnummer.setEnabled(false);
		tf_depotnummer.setEditable(false);
		tf_depotnummer.setBounds(180, 100, 134, 28);
		contentPane.add(tf_depotnummer);
		tf_depotnummer.setColumns(10);
		
		JButton btnDepotAnlegen = new JButton("Depot anlegen");
		btnDepotAnlegen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedDepotAnlegen(e);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDepotAnlegen.setBounds(190, 44, 117, 29);
		contentPane.add(btnDepotAnlegen);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(6, 55, 437, 1);
		contentPane.add(horizontalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(23, 55, 420, 60);
		contentPane.add(horizontalStrut_1);
		
		JButton btnKundenliste = new JButton("Kundenliste");
		btnKundenliste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					actionPerformedKundenlisteAnzeigen(e);
				}catch(RemoteException e1){
					e1.printStackTrace();
				}
			}
		});
		btnKundenliste.setBounds(326, 16, 117, 29);
		contentPane.add(btnKundenliste);
	}

	protected void actionPerformedDepotAnlegen(ActionEvent evt) throws RemoteException {
		System.out.println("b_anlegen.actionPerformed, event="+evt);
		int kundenNr = Integer.parseInt(tf_kundennummer.getText());
		int depotNr = 0;
		try {
			System.out.println("Kundennummer: "+kundenNr+"\n");
			depotNr = kontenPflegen.depotAnlegenR(kundenNr);
		} catch (AnwendungskernException e) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung");
			e.printStackTrace();
		}
		if (depotNr == 0)
			JOptionPane.showMessageDialog(null, "Depot kann nicht angelegt werden");
		else
			tf_depotnummer.setText(String.valueOf(depotNr));
		
	}
	
	protected void actionPerformedKundenlisteAnzeigen(ActionEvent evt) throws RemoteException {
		KundenlisteAnzeigen.main(this.kundenlisteErstellenRemote);
	}
}
