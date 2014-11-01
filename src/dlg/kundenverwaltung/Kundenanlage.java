package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import awk.AnwendungskernException;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Kundenanlage extends JFrame {

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
		this.setBounds(0, 0, 461, 365);
		this.setPreferredSize(new java.awt.Dimension(461, 360));
		this.setSize(461, 360);
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
		lblHausnummer.setBounds(284, 139, 84, 16);
		contentPane.add(lblHausnummer);
		
		tf_hausnummer = new JTextField();
		tf_hausnummer.setBounds(368, 134, 77, 28);
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
				} catch (RemoteException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		jb_anlegen.setBounds(317, 298, 117, 29);
		contentPane.add(jb_anlegen);
		{
			tf_Geburtsdatum_Tag = new JTextField();
			contentPane.add(tf_Geburtsdatum_Tag);
			tf_Geburtsdatum_Tag.setBounds(126, 205, 38, 22);
			tf_Geburtsdatum_Tag.setSize(40, 22);
		}
		{
			tf_Geburtsdatum_Monat = new JTextField();
			contentPane.add(tf_Geburtsdatum_Monat);
			tf_Geburtsdatum_Monat.setBounds(181, 205, 40, 22);
		}
		{
			tf_Geburtsdatum_Jahr = new JTextField();
			contentPane.add(tf_Geburtsdatum_Jahr);
			tf_Geburtsdatum_Jahr.setBounds(241, 205, 40, 22);
		}
		{
			punkt = new JLabel();
			contentPane.add(punkt);
			punkt.setText(".");
			punkt.setBounds(171, 212, 10, 15);
		}
		{
			jLabel1 = new JLabel();
			contentPane.add(jLabel1);
			jLabel1.setText(".");
			jLabel1.setBounds(229, 212, 10, 15);
		}
		{
			lbGeburtsdatum = new JLabel();
			contentPane.add(lbGeburtsdatum);
			lbGeburtsdatum.setText("Geburtsdatum:");
			lbGeburtsdatum.setBounds(27, 208, 87, 15);
		}
		{
			lbBenutzername = new JLabel();
			contentPane.add(lbBenutzername);
			lbBenutzername.setText("Benutzername:");
			lbBenutzername.setBounds(27, 244, 87, 15);
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
			tf_Benutzername.setBounds(126, 241, 155, 22);
		}
		{
			tf_Passwort = new JTextField();
			contentPane.add(tf_Passwort);
			tf_Passwort.setBounds(126, 269, 155, 22);
		}

		tf_geschlecht.setVisible(true);
		lb_geschlecht.setVisible(true);
	}
	
	private Date generateBirthday (String tag, String monat, String jahr) throws ParseException{
		String dateString = tag + "." + monat + "." + jahr;
		return new SimpleDateFormat("dd.MM.yyyy", Locale.GERMANY).parse(dateString);
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
		
		
		boolean ok = false;
		String geschlecht = tf_geschlecht.getText();
		try {
			ok = kundenPflegen.privatkundeAnlegenR(vorname, nachname, str, hausnr, plz, ort, geschlecht, benutzername, passwort, geburtsdatum);
		} catch (AnwendungskernException e) {
			// TODO Auto-generated catch block
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

	
