package dlg.menue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import dlg.kundenverwaltung.KundenbestandAnzeigen;
import awk.AnwendungskernException;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.entity.PrivatkundeTO;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

/*
 * 
 * Philip Dauwe
 * 579407
 * 
 */
public class KundenAnmeldenMenue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfBenutzerkennung;
	private JTextField tfPasswort;

	private IKundenSuchenRemote kundenSuchen;
	private IWertpapiertransaktionBuchenRemote wpbuchen;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IKundenSuchenRemote kundenSuchen2, final IWertpapiertransaktionBuchenRemote wpbuchen2) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KundenAnmeldenMenue frame = new KundenAnmeldenMenue(kundenSuchen2, wpbuchen2);
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
	public KundenAnmeldenMenue(IKundenSuchenRemote kundenSuchen, IWertpapiertransaktionBuchenRemote wpbuchen) {
		this.kundenSuchen = kundenSuchen;
		this.wpbuchen = wpbuchen;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAnmelden = new JLabel("Benutzerdaten eingeben:");
		lblAnmelden.setBounds(6, 6, 154, 16);
		contentPane.add(lblAnmelden);
		
		tfBenutzerkennung = new JTextField();
		tfBenutzerkennung.setBounds(131, 68, 134, 28);
		contentPane.add(tfBenutzerkennung);
		tfBenutzerkennung.setColumns(10);
		
		JLabel lblBenutzerkennung = new JLabel("Benutzerkennung:");
		lblBenutzerkennung.setBounds(6, 74, 113, 16);
		contentPane.add(lblBenutzerkennung);
		
		JLabel lblPasswort = new JLabel("Passwort:");
		lblPasswort.setBounds(6, 129, 60, 16);
		contentPane.add(lblPasswort);
		
		tfPasswort = new JTextField();
		tfPasswort.setBounds(131, 123, 134, 28);
		contentPane.add(tfPasswort);
		tfPasswort.setColumns(10);
		
		JButton btnAnmelden = new JButton("Anmelden");
		btnAnmelden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedAnmelden(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAnmelden.setBounds(131, 184, 134, 29);
		contentPane.add(btnAnmelden);
	}
	
	
	private void actionPerformedAnmelden(ActionEvent e) throws RemoteException{
		String b = this.tfBenutzerkennung.getText();
		String p = this.tfPasswort.getText();
		KundeTO kunde = new PrivatkundeTO();
		try{
			kunde = kundenSuchen.kundenSuchenByBenutzerkennungUndPasswort(b, p);
		}catch (AnwendungskernException e1){
			e1.printStackTrace();
		}
		
		if(kunde == null){
			JOptionPane.showMessageDialog(null, "Benutzername/Passwort falsch");
		}else{
			//JOptionPane.showMessageDialog(null, "Benutzer gefunden: " + kunde.getDepots());
			KundenbestandAnzeigen.main(kunde,this.wpbuchen);
		}
	}
	
}
