package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import awk.AnwendungskernException;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class Kundenanlage extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944704739598697853L;
	private JPanel contentPane;
	private JTextField tf_geschlecht;
	private JTextField tf_nachname;
	private JTextField tf_vorname;
	private JTextField tf_strasse;
	private JTextField tf_hausnummer;
	private JTextField tf_plz;
	private JTextField tf_ort;
	
	private JLabel lb_ustid;
	private JLabel lb_geschlecht;

	private IKundenPflegenRemote kundenPflegen;
	private JTextField tf_ustid;
	private JRadioButton rdbtnPrivatkunde;
	private JRadioButton rdbtnGeschaeftskunde;
	
	/**
	 * Launch the application.
	 * @param kundenPflegen2 
	 */
	public static void main(final IKundenPflegenRemote kundenPflegen2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kundenanlage frame = new Kundenanlage(kundenPflegen2);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param kundenPflegen2 
	 */
	public Kundenanlage(final IKundenPflegenRemote kundenPflegen) {
		this.kundenPflegen = kundenPflegen;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnPrivatkunde = new JRadioButton("Privatkunde");
		rdbtnPrivatkunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnGeschaeftskunde.setSelected(false);
				actionPerformedPrivatkunde(arg0);
			}
		});
		rdbtnPrivatkunde.setBounds(31, 21, 141, 23);
		contentPane.add(rdbtnPrivatkunde);
		
		rdbtnGeschaeftskunde = new JRadioButton("Gesch\u00E4ftskunde");
		rdbtnGeschaeftskunde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnPrivatkunde.setSelected(false);
				actionPerformedGeschaeftskunde(e);
			}
		});
		rdbtnGeschaeftskunde.setBounds(204, 21, 141, 23);
		contentPane.add(rdbtnGeschaeftskunde);
		
		lb_geschlecht = new JLabel("Geschlecht:");
		lb_geschlecht.setBounds(41, 55, 89, 16);
		contentPane.add(lb_geschlecht);
		
		tf_geschlecht = new JTextField();
		tf_geschlecht.setBounds(126, 49, 30, 28);
		contentPane.add(tf_geschlecht);
		tf_geschlecht.setColumns(10);
		
		JLabel lblNachname = new JLabel("Nachname:");
		lblNachname.setBounds(41, 83, 110, 16);
		contentPane.add(lblNachname);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(41, 111, 61, 16);
		contentPane.add(lblVorname);
		
		JLabel lblStrasse = new JLabel("Strasse:");
		lblStrasse.setBounds(41, 139, 61, 16);
		contentPane.add(lblStrasse);
		
		JLabel lblPlz = new JLabel("PLZ:");
		lblPlz.setBounds(41, 171, 61, 16);
		contentPane.add(lblPlz);
		
		JLabel lblOrt = new JLabel("Ort");
		lblOrt.setBounds(204, 171, 61, 16);
		contentPane.add(lblOrt);
		
		tf_nachname = new JTextField();
		tf_nachname.setBounds(126, 77, 134, 28);
		contentPane.add(tf_nachname);
		tf_nachname.setColumns(10);
		
		tf_vorname = new JTextField();
		tf_vorname.setBounds(126, 105, 134, 28);
		contentPane.add(tf_vorname);
		tf_vorname.setColumns(10);
		
		tf_strasse = new JTextField();
		tf_strasse.setBounds(126, 133, 134, 28);
		contentPane.add(tf_strasse);
		tf_strasse.setColumns(10);
		
		JLabel lblHausnummer = new JLabel("Hausnummer");
		lblHausnummer.setBounds(284, 139, 61, 16);
		contentPane.add(lblHausnummer);
		
		tf_hausnummer = new JTextField();
		tf_hausnummer.setBounds(373, 133, 77, 28);
		contentPane.add(tf_hausnummer);
		tf_hausnummer.setColumns(10);
		
		tf_plz = new JTextField();
		tf_plz.setBounds(126, 165, 61, 28);
		contentPane.add(tf_plz);
		tf_plz.setColumns(10);
		
		tf_ort = new JTextField();
		tf_ort.setBounds(261, 165, 183, 28);
		contentPane.add(tf_ort);
		tf_ort.setColumns(10);
		
		JButton jb_anlegen = new JButton("Anlegen");
		jb_anlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedKundeAnlegen(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jb_anlegen.setBounds(284, 243, 117, 29);
		contentPane.add(jb_anlegen);
		
		lb_ustid = new JLabel("Ust-ID:");
		lb_ustid.setBounds(41, 209, 61, 16);
		contentPane.add(lb_ustid);
		
		tf_ustid = new JTextField();
		tf_ustid.setBounds(126, 199, 134, 28);
		contentPane.add(tf_ustid);
		tf_ustid.setColumns(10);
	}
	
	private void actionPerformedPrivatkunde(ActionEvent arg0) {
		System.out.println("rb_Privatkunde.actionPerformed, event="+arg0);
		this.rdbtnGeschaeftskunde.setSelected(false);
		tf_ustid.setVisible(false);
		lb_ustid.setVisible(false);
		tf_geschlecht.setVisible(true);
		lb_geschlecht.setVisible(true);
	}
	
	private void actionPerformedGeschaeftskunde(ActionEvent arg0) {
		System.out.println("rb_Privatkunde.actionPerformed, event="+arg0);
		this.rdbtnPrivatkunde.setSelected(false);
		tf_ustid.setVisible(true);
		lb_ustid.setVisible(true);
		tf_geschlecht.setVisible(false);
		lb_geschlecht.setVisible(false);
	}
	
	public void actionPerformedKundeAnlegen(ActionEvent evt) throws RemoteException {
		System.out.println("b_anlegen.actionPerformed, event="+evt);
		String nachname = tf_nachname.getText();
		String vorname = tf_vorname.getText();
		String str = tf_strasse.getText();
		String hausnr = tf_hausnummer.getText();
		String plz = tf_plz.getText();
		String ort = tf_ort.getText();
		
		boolean ok = false;
		if ( rdbtnGeschaeftskunde.isSelected() ) {
			String ustId = tf_ustid.getText();
			try {
				ok = kundenPflegen.geschaeftskundeAnlegenR(vorname, nachname, str, hausnr, plz, ort, ustId);
			} catch (AnwendungskernException e) {
				e.printStackTrace();
			}
		}
		else {
			String geschlecht = tf_geschlecht.getText();
			try {
				ok = kundenPflegen.privatkundeAnlegenR(vorname, nachname, str, hausnr, plz, ort, geschlecht);
			} catch (AnwendungskernException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (!ok)
			JOptionPane.showMessageDialog(null, "Kunde kann nicht angelegt werden!");
		else {
				JOptionPane.showMessageDialog(null, "Kunde wurde gespeichert!");
				this.dispose();
			}
		}
}
