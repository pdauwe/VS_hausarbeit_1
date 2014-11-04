package dlg.kontenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
public class Kontenverwaltung extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6360753535595456280L;


	private JPanel contentPane;
	
	
	IDepotPflegenRemote kontenPflegen;
	IWertpapiertransaktionBuchenRemote kontobewegungBuchen;
	IKundenPflegenRemote kundenPflegen;
	/**
	 * Launch the application.
	 */
	public static void main(final IDepotPflegenRemote kontenPflegen2, final IWertpapiertransaktionBuchenRemote kontobewegungBuchen2,
			final IKundenPflegenRemote kundenPflegen2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kontenverwaltung frame = new Kontenverwaltung(kontenPflegen2, kontobewegungBuchen2, kundenPflegen2);
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
	public Kontenverwaltung(IDepotPflegenRemote kontenPflegen, 
			IWertpapiertransaktionBuchenRemote kontobewegungBuchen, 
			IKundenPflegenRemote kundenPflegen) {
		this.kontenPflegen = kontenPflegen;
		this.kontobewegungBuchen = kontobewegungBuchen;
		this.kundenPflegen = kundenPflegen;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JButton btnKontoAnlegen = new JButton("Konto anlegen");
		btnKontoAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedKtoAnlegen(e);
			}
		});
		GridBagConstraints gbc_btnKontoAnlegen = new GridBagConstraints();
		gbc_btnKontoAnlegen.anchor = GridBagConstraints.WEST;
		gbc_btnKontoAnlegen.insets = new Insets(0, 0, 5, 0);
		gbc_btnKontoAnlegen.gridx = 2;
		gbc_btnKontoAnlegen.gridy = 1;
		contentPane.add(btnKontoAnlegen, gbc_btnKontoAnlegen);
		btnKontoAnlegen.setText("Depot anlegen");

		JButton btnKontobewegung = new JButton("Kontobewegung");
		btnKontobewegung.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedKtoBewegungBuchen(e);
			}
		});
		GridBagConstraints gbc_btnKontobewegung = new GridBagConstraints();
		gbc_btnKontobewegung.gridx = 2;
		gbc_btnKontobewegung.gridy = 3;
		contentPane.add(btnKontobewegung, gbc_btnKontobewegung);
		btnKontobewegung.setText("Wertpapiertransaktion");
	}

	protected void actionPerformedKtoBewegungBuchen(ActionEvent e) {
		Kontobewegung.main(kontobewegungBuchen);
	}

	protected void actionPerformedKtoAnlegen(ActionEvent e) {
		Kontoanlage.main(kontenPflegen);
		
	}

}
