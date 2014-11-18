package dlg.kundenverwaltung;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import awk.AnwendungskernException;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

public class Kundenanlegen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6944704739598697853L;
	private JPanel contentPane;
	private JTextField tf_geschlecht;
	private JTextField tf_nachname;
	private JTextField tf_vorname;
	private JLabel lbBenutzername;
	private JTextField tf_Passwort;
	private JTextField tf_Benutzername;
	private JLabel lbPasswort;
	private JLabel lbGeburtsdatum;
	private JLabel jLabel1;
	private JLabel punkt;
	private JTextField tf_Geburtsdatum_Jahr;
	private JTextField tf_strasse;
	private JTextField tf_hausnummer;
	private JTextField tf_plz;
	private JTextField tf_ort;
	
	private JLabel lb_geschlecht;
	private JTextField tf_Geburtsdatum_Monat;
	private JTextField tf_Geburtsdatum_Tag;

	private IKundenPflegenRemote kundenPflegen;
	
	/**
	 * Launch the application.
	 * @param kundenPflegen2 
	 */
	public static void main(final IKundenPflegenRemote kundenPflegen2) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Kundenanlegen frame = new Kundenanlegen(kundenPflegen2);
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
	public Kundenanlegen(final IKundenPflegenRemote kundenPflegen) 
	{
		this.kundenPflegen = kundenPflegen;
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(0, 0, 461, 365);
		this.setPreferredSize(new java.awt.Dimension(461, 360));
		this.setSize(494, 361);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setPreferredSize(new java.awt.Dimension(461, 360));
		contentPane.setSize(461, 360);

		lb_geschlecht = new JLabel("Geschlecht:");
		lb_geschlecht.setBounds(41, 55, 89, 16);
		contentPane.add(lb_geschlecht);
		
		tf_geschlecht = new JTextField();
		tf_geschlecht.setBounds(142, 53, 30, 28);
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
		lblOrt.setBounds(220, 175, 61, 16);
		contentPane.add(lblOrt);
		
		tf_nachname = new JTextField();
		tf_nachname.setBounds(142, 81, 134, 28);
		contentPane.add(tf_nachname);
		tf_nachname.setColumns(10);
		
		tf_vorname = new JTextField();
		tf_vorname.setBounds(142, 109, 134, 28);
		contentPane.add(tf_vorname);
		tf_vorname.setColumns(10);
		
		tf_strasse = new JTextField();
		tf_strasse.setBounds(142, 137, 134, 28);
		contentPane.add(tf_strasse);
		tf_strasse.setColumns(10);
		
		JLabel lblHausnummer = new JLabel("Hausnummer");
		lblHausnummer.setBounds(300, 143, 84, 16);
		contentPane.add(lblHausnummer);
		
		tf_hausnummer = new JTextField();
		tf_hausnummer.setBounds(384, 138, 77, 28);
		contentPane.add(tf_hausnummer);
		tf_hausnummer.setColumns(10);
		
		tf_plz = new JTextField();
		tf_plz.setBounds(142, 169, 61, 28);
		contentPane.add(tf_plz);
		tf_plz.setColumns(10);
		
		tf_ort = new JTextField();
		tf_ort.setBounds(277, 169, 183, 28);
		contentPane.add(tf_ort);
		tf_ort.setColumns(10);
		
		JButton jb_anlegen = new JButton("Anlegen");
		jb_anlegen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedKundeAnlegen(e);
				} catch (RemoteException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jb_anlegen.setBounds(371, 304, 117, 29);
		contentPane.add(jb_anlegen);
		{
			tf_Geburtsdatum_Tag = new JTextField();
			contentPane.add(tf_Geburtsdatum_Tag);
			tf_Geburtsdatum_Tag.setBounds(142, 209, 38, 22);
			tf_Geburtsdatum_Tag.setSize(40, 22);
		}
		{
			tf_Geburtsdatum_Monat = new JTextField();
			contentPane.add(tf_Geburtsdatum_Monat);
			tf_Geburtsdatum_Monat.setBounds(197, 209, 40, 22);
		}
		{
			tf_Geburtsdatum_Jahr = new JTextField();
			contentPane.add(tf_Geburtsdatum_Jahr);
			tf_Geburtsdatum_Jahr.setBounds(257, 209, 40, 22);
		}
		{
			punkt = new JLabel();
			contentPane.add(punkt);
			punkt.setText(".");
			punkt.setBounds(187, 216, 10, 15);
		}
		{
			jLabel1 = new JLabel();
			contentPane.add(jLabel1);
			jLabel1.setText(".");
			jLabel1.setBounds(245, 216, 10, 15);
		}
		{
			lbGeburtsdatum = new JLabel();
			contentPane.add(lbGeburtsdatum);
			lbGeburtsdatum.setText("Geburtsdatum:");
			lbGeburtsdatum.setBounds(27, 208, 93, 16);
		}
		{
			lbBenutzername = new JLabel();
			contentPane.add(lbBenutzername);
			lbBenutzername.setText("Benutzername:");
			lbBenutzername.setBounds(27, 244, 92, 16);
		}
		{
			lbPasswort = new JLabel();
			contentPane.add(lbPasswort);
			lbPasswort.setText("Passwort:");
			lbPasswort.setBounds(59, 272, 55, 15);
		}
		{
			tf_Benutzername = new JTextField();
			contentPane.add(tf_Benutzername);
			tf_Benutzername.setBounds(142, 245, 155, 22);
		}
		{
			tf_Passwort = new JTextField();
			contentPane.add(tf_Passwort);
			tf_Passwort.setBounds(142, 273, 155, 22);
		}

		tf_geschlecht.setVisible(true);
		lb_geschlecht.setVisible(true);
	}
	
	private Date generateBirthday (String tag, String monat, String jahr) throws ParseException{
		String dateString = tag + "-" + monat + "-" + jahr + " 00:00:00";
		return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.GERMANY).parse(dateString);
	}
	
	public void actionPerformedKundeAnlegen(ActionEvent evt) throws RemoteException, ParseException {
		System.out.println("b_anlegen.actionPerformed, event="+evt);
		String nachname = tf_nachname.getText();
		String vorname = tf_vorname.getText();
		String str = tf_strasse.getText();
		String hausnr = tf_hausnummer.getText();
		String plz = tf_plz.getText();
		String ort = tf_ort.getText();
		String benutzername = tf_Benutzername.getText();
		String passwort = tf_Passwort.getText();
		Date geburtsdatum = this.generateBirthday(tf_Geburtsdatum_Tag.getText(), tf_Geburtsdatum_Monat.getText(), tf_Geburtsdatum_Jahr.getText());
		System.out.println(geburtsdatum);
		
		boolean ok = false;
		String geschlecht = tf_geschlecht.getText();
		try {
			ok = kundenPflegen.privatkundeAnlegenR(vorname, nachname, str, hausnr, plz, ort, geschlecht, benutzername, passwort, geburtsdatum);
		} catch (AnwendungskernException e) {
			e.printStackTrace();
		}
	
		if (!ok)
			JOptionPane.showMessageDialog(null, "Kunde kann nicht angelegt werden!");
		else {
				JOptionPane.showMessageDialog(null, "Kunde wurde gespeichert!");
				this.dispose();
			}
		}
}

	
