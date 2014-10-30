package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import awk.AnwendungskernException;
import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class Kundenloeschung extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7874362676412907540L;
	private JPanel contentPane;
	private JTextField tf_kundennummer;

	private IKundenPflegenRemote kundenPflegen;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IKundenPflegenRemote kundenPflegen2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kundenloeschung frame = new Kundenloeschung(kundenPflegen2);
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
	public Kundenloeschung(IKundenPflegenRemote kundenPflegen) {
		this.kundenPflegen = kundenPflegen;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblKundennummer = new JLabel("Kundennummer");
		lblKundennummer.setBounds(45, 39, 122, 16);
		contentPane.add(lblKundennummer);
		
		tf_kundennummer = new JTextField();
		tf_kundennummer.setBounds(167, 33, 134, 28);
		contentPane.add(tf_kundennummer);
		tf_kundennummer.setColumns(10);
		
		JButton jb_loeschen = new JButton("L\u00F6schen");
		jb_loeschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					actionPerformedKundeLoeschen(arg0);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		jb_loeschen.setBounds(167, 67, 117, 29);
		contentPane.add(jb_loeschen);
	}
	
	private void actionPerformedKundeLoeschen(ActionEvent arg0) throws Exception, RemoteException {
		System.out.println("b_loeschen.actionPerformed, event="+arg0);
		
		int kundenNr = Integer.parseInt(tf_kundennummer.getText());
		try {
			if ( kundenPflegen.kundenLoeschenR(kundenNr) ) {
				JOptionPane.showMessageDialog(null, "Kunde erfolgreich gel�scht");
				this.dispose();
			}
			else 
				JOptionPane.showMessageDialog(null, "Kunde kann nicht geloescht werden" +
						"\n(Kunde mit Kundennr existiert nicht oder Kunde hat noch Konten)");
		} catch (AnwendungskernException e) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung!!");
			this.dispose();
		}
		
	}
}
