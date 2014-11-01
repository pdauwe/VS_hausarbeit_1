package dlg.kontenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Component;
import java.rmi.RemoteException;

import javax.swing.Box;

public class Kontoanlage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8670270853554936786L;
	private JPanel contentPane;
	private JTextField tf_kundennummer;
	private JTextField tf_kontonummer;

	private IDepotPflegenRemote kontenPflegen;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IDepotPflegenRemote kontenPflegen2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kontoanlage frame = new Kontoanlage(kontenPflegen2);
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
	public Kontoanlage(IDepotPflegenRemote kontenPflegen) {
		
		this.kontenPflegen = kontenPflegen;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		JLabel lblKontonummer = new JLabel("Kontonummer:");
		lblKontonummer.setBounds(33, 106, 130, 16);
		contentPane.add(lblKontonummer);
		
		tf_kontonummer = new JTextField();
		tf_kontonummer.setEnabled(false);
		tf_kontonummer.setEditable(false);
		tf_kontonummer.setBounds(180, 100, 134, 28);
		contentPane.add(tf_kontonummer);
		tf_kontonummer.setColumns(10);
		
		JButton btnKontoAnlegen = new JButton("Konto anlegen");
		btnKontoAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedKontoAnlegen(e);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnKontoAnlegen.setBounds(326, 16, 117, 29);
		contentPane.add(btnKontoAnlegen);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setBounds(6, 55, 437, 1);
		contentPane.add(horizontalStrut);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setBounds(23, 55, 420, 60);
		contentPane.add(horizontalStrut_1);
	}

	protected void actionPerformedKontoAnlegen(ActionEvent evt) throws RemoteException {
		System.out.println("b_anlegen.actionPerformed, event="+evt);
		int kundenNr = Integer.parseInt(tf_kundennummer.getText());
		int kontoNr = 0;
		try {
			System.out.println("Kundennummer: "+kundenNr+"\n");
			kontoNr = kontenPflegen.depotAnlegenR(kundenNr);
		} catch (AnwendungskernException e) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung");
			e.printStackTrace();
		}
		if (kontoNr == 0)
			JOptionPane.showMessageDialog(null, "Konto kann nicht angelegt werden");
		else
			tf_kontonummer.setText(String.valueOf(kontoNr));
		
	}
}
