package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;

import awk.AnwendungskernException;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

import javax.swing.JScrollPane;

public class Kundenlistenausgabe extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2343119111088832019L;
	private JPanel contentPane;
	private JTable table;
	
	private IKundenlisteErstellenRemote listeErstellen;
	private JScrollPane scrollPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IKundenlisteErstellenRemote kundenlisteErstellen) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kundenlistenausgabe frame = new Kundenlistenausgabe(kundenlisteErstellen);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} catch (AnwendungskernException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param kundenlisteErstellen 
	 * @throws AnwendungskernException 
	 */
	public Kundenlistenausgabe(IKundenlisteErstellenRemote kundenlisteErstellen) throws AnwendungskernException {
		
		this.listeErstellen = kundenlisteErstellen;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(33, 64, 390, 181);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		try {
			table.setModel(new KundenTableModel(listeErstellen));
		} catch (AnwendungskernException e) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung!!");
			this.dispose();	
		}
		
		JLabel lblAlleAdressen = new JLabel("Alle Adressen");
		lblAlleAdressen.setBounds(33, 24, 104, 16);
		contentPane.add(lblAlleAdressen);
	}
}
