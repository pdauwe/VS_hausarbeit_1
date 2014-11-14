package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import awk.kundenverwaltung.usecase.IKundenPflegenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Kundenverwaltung extends JFrame {

	private static final long serialVersionUID = 8157074376502838015L;

	private JPanel contentPane;
	
	IKundenPflegenRemote kundenPflegen;
	/**
	 * Launch the application.
	 * @param kundenSuchen 
	 * @param kundenlisteErstellen 
	 * @param kundenPflegen 
	 */
	public static void main(
			 final IKundenPflegenRemote kundenPflegen2
			) {
		
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Kundenverwaltung frame = new Kundenverwaltung(kundenPflegen2);
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
			IKundenPflegenRemote kundenPflegen1) {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.kundenPflegen = kundenPflegen1;

		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnKundeAnlegen = new JButton("Kunde anlegen");
		btnKundeAnlegen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actionPerformedKundenanlage(arg0);
			}
		});
		btnKundeAnlegen.setBounds(89, 38, 178, 29);
		contentPane.add(btnKundeAnlegen);
	}
	
	private void actionPerformedKundenanlage(ActionEvent arg0) {
		Kundenanlage.main(this.kundenPflegen);
	}
}
