package dlg.menue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import javax.swing.JButton;

import dlg.kundenverwaltung.Kundenverwaltung;
import dlg.kontenverwaltung.Kontenverwaltung;
import awk.depotverwaltung.usecase.IDepotPflegenRemote;
import awk.depotverwaltung.usecase.IWertpapiertransaktionBuchenRemote;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

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
public class Hauptmenue extends org.eclipse.swt.widgets.Composite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	IKundenPflegenRemote kundenPflegen;
	
	IDepotPflegenRemote kontenPflegen;
	private Button btnKundenverwaltung;
	private Button btnKontoverwaltung;
	private Composite contentPane;
	IWertpapiertransaktionBuchenRemote kontobewegungBuchen;
	
	/**
	 * Launch the application.
	 */
	public static void main( 
			final IKundenPflegenRemote kundenPflegen,
			final IDepotPflegenRemote kontenPflegen,
			final IWertpapiertransaktionBuchenRemote kontobewegungBuchen
			) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hauptmenue frame = new Hauptmenue(
							kundenPflegen,
							kontenPflegen, kontobewegungBuchen
							);
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
	public Hauptmenue (
			final IKundenPflegenRemote kundenPflegen,
			final IDepotPflegenRemote kontenPflegen,
			final IWertpapiertransaktionBuchenRemote kontobewegungBuchen
			) {
				{
				}
				{
				}
				{
					GridLayout thisLayout = new GridLayout();
					thisLayout.makeColumnsEqualWidth = true;
					getContentPane().setLayout(thisLayout);
					this.setBounds(0, 0, 450, 300);
					{
						contentPane = new Composite(this, SWT.NONE);
						contentPane.setLayout(null);
						{
							btnKontoverwaltung = new Button(contentPane, SWT.NONE);
							btnKontoverwaltung.setText("Kontoverwaltung");
							btnKontoverwaltung.setBounds(48, 74, 161, 29);
							btnKontoverwaltung.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									Kontenverwaltung.main(kontenPflegen, kontobewegungBuchen, kundenPflegen);
								}
							});
						}
						{
							btnKundenverwaltung = new Button(contentPane, SWT.NONE);
							btnKundenverwaltung.setText("Kundenverwaltung");
							btnKundenverwaltung.setBounds(47, 19, 162, 29);
							btnKundenverwaltung.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent arg0) {
									Kundenverwaltung.main(kundenPflegen);
								}
							});
						}
					}
				}

		this.kundenPflegen = kundenPflegen;
		
		this.kontenPflegen = kontenPflegen;
		this.kontobewegungBuchen = kontobewegungBuchen;

	}

}
