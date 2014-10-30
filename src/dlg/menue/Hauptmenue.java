package dlg.menue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import dlg.kundenverwaltung.Kundenverwaltung;
import dlg.kontenverwaltung.Kontenverwaltung;
import awk.kontenverwaltung.usecase.IKontenPflegenRemote;
import awk.kontenverwaltung.usecase.IKontobewegungBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Hauptmenue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	IKundenPflegenRemote kundenPflegen;
	IKundenSuchenRemote kundenSuchen;
	IKundenlisteErstellenRemote kundenlisteErstellen;
	
	IKontenPflegenRemote kontenPflegen;
	IKontobewegungBuchenRemote kontobewegungBuchen;
	
	/**
	 * Launch the application.
	 */
	public static void main( 
			final IKundenPflegenRemote kundenPflegen,
			final IKundenSuchenRemote kundenSuchen,
			final IKundenlisteErstellenRemote kundenlisteErstellen,
			final IKontenPflegenRemote kontenPflegen,
			final IKontobewegungBuchenRemote kontobewegungBuchen
			) {
		
		System.out.println("Hauptmenue fertig");
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hauptmenue frame = new Hauptmenue(
							kundenPflegen,
							kundenSuchen, kundenlisteErstellen,
							kontenPflegen, kontobewegungBuchen
							);
					frame.setVisible(true);
					System.out.println("Hauptmenue fertig");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public Hauptmenue (
			final IKundenPflegenRemote kundenPflegen,
			final IKundenSuchenRemote kundenSuchen,
			final IKundenlisteErstellenRemote kundenlisteErstellen,
			final IKontenPflegenRemote kontenPflegen,
			final IKontobewegungBuchenRemote kontobewegungBuchen
			) {
	
		this.kundenPflegen = kundenPflegen;
		this.kundenSuchen = kundenSuchen;
		this.kundenlisteErstellen = kundenlisteErstellen;
		
		this.kontenPflegen = kontenPflegen;
		this.kontobewegungBuchen = kontobewegungBuchen;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKontoverwaltung = new JButton("Kontoverwaltung");
		btnKontoverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontenverwaltung.main(kontenPflegen, kontobewegungBuchen, kundenPflegen);
			}
		});
		btnKontoverwaltung.setBounds(48, 74, 161, 29);
		contentPane.add(btnKontoverwaltung);
		
		JButton btnKundenverwaltung = new JButton("Kundenverwaltung");
		btnKundenverwaltung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kundenverwaltung.main(kundenPflegen, kundenlisteErstellen, kundenSuchen);
			}
		});
		btnKundenverwaltung.setBounds(47, 19, 162, 29);
		contentPane.add(btnKundenverwaltung);
	}

}
