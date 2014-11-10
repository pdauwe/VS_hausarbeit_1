package dlg.menue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import dlg.depotverwaltung.Kontenverwaltung;
import dlg.depotverwaltung.Kontoanlage;
import dlg.depotverwaltung.WertpapiertransaktionErfassung;
import dlg.kundenverwaltung.Kundenanlage;
import dlg.kundenverwaltung.Kundenverwaltung;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

public class MitarbeiterMenue extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 12323L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(final IKundenPflegenRemote kundenPflegen,
			final IDepotPflegenRemote depotPflegen,
			final IWertpapiertransaktionBuchenRemote wertpapiertransaktionBuchen) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MitarbeiterMenue frame = new MitarbeiterMenue(kundenPflegen, depotPflegen, wertpapiertransaktionBuchen);
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
	public MitarbeiterMenue(
			final IKundenPflegenRemote kundenPflegen,
			final IDepotPflegenRemote depotPflegen,
			final IWertpapiertransaktionBuchenRemote wertpapiertransaktionBuchen
			) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnKundenAnlegen = new JButton("Kunden anlegen");
		btnKundenAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kundenanlage.main(kundenPflegen);
			}
		});
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		contentPane.add(btnKundenAnlegen);
		
		JButton btnDepotAnlegen = new JButton("Depot anlegen");
		btnDepotAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontoanlage.main(depotPflegen);
			}
		});
		contentPane.add(btnDepotAnlegen);
		
		JButton btnWertpapiertransaktion = new JButton("Neue Wertpapiertransaktion anlegen");
		btnWertpapiertransaktion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WertpapiertransaktionErfassung.main(wertpapiertransaktionBuchen);
			}
		});
		contentPane.add(btnWertpapiertransaktion);
	}
}
