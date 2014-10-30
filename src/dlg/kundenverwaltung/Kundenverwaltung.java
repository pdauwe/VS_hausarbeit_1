package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import awk.kundenverwaltung.usecase.IKundenPflegenRemote;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kundenverwaltung extends JFrame {

	private static final long serialVersionUID = 8157074376502838015L;

	private JPanel contentPane;
	
	IKundenPflegenRemote kundenPflegen;
	IKundenlisteErstellenRemote kundenlisteErstellen;
	IKundenSuchenRemote kundenSuchen;
	/**
	 * Launch the application.
	 * @param kundenSuchen 
	 * @param kundenlisteErstellen 
	 * @param kundenPflegen 
	 */
	public static void main(
			 final IKundenPflegenRemote kundenPflegen2, 
			 final IKundenlisteErstellenRemote kundenlisteErstellen2, 
			 final IKundenSuchenRemote kundenSuchen2) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kundenverwaltung frame = new Kundenverwaltung(kundenPflegen2, kundenlisteErstellen2, kundenSuchen2);
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
	public Kundenverwaltung(
			IKundenPflegenRemote kundenPflegen1, 
			IKundenlisteErstellenRemote kundenlisteErstellen1, 
			IKundenSuchenRemote kundenSuchen1) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.kundenPflegen = kundenPflegen1;
		this.kundenlisteErstellen = kundenlisteErstellen1;
		this.kundenSuchen = kundenSuchen1;
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKundeAnlegen = new JButton("Kunde anlegen");
		btnKundeAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedKundenanlage(arg0);
			}
		});
		btnKundeAnlegen.setBounds(89, 38, 178, 29);
		contentPane.add(btnKundeAnlegen);
		
		JButton btnKundenlisteAnzeigen = new JButton("Kundenliste anzeigen");
		btnKundenlisteAnzeigen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedKundenlisteAusgeben(arg0);
			}
		});
		btnKundenlisteAnzeigen.setBounds(89, 79, 178, 29);
		contentPane.add(btnKundenlisteAnzeigen);
		
		JButton btnKundeLoeschen = new JButton("Kunde l\u00F6schen");
		btnKundeLoeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedKundeLoeschen(e);
			}
		});
		btnKundeLoeschen.setBounds(89, 120, 178, 29);
		contentPane.add(btnKundeLoeschen);
		
		JButton btnKundeSuchen = new JButton("Kunde suchen");
		btnKundeSuchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedSuchen(e);
			}

		});
		btnKundeSuchen.setBounds(89, 161, 178, 29);
		contentPane.add(btnKundeSuchen);
	}
	
	private void actionPerformedKundenanlage(ActionEvent arg0) {
		Kundenanlage.main(this.kundenPflegen);
	}
	
	private void actionPerformedKundenlisteAusgeben(ActionEvent arg0) {
		Kundenlistenausgabe.main(this.kundenlisteErstellen);
	}
	
	private void actionPerformedKundeLoeschen(ActionEvent e) {
		Kundenloeschung.main(this.kundenPflegen);;
	}
	
	private void actionPerformedSuchen(ActionEvent e) {
		Kundensuche.main(this.kundenSuchen);
		
	}
}
