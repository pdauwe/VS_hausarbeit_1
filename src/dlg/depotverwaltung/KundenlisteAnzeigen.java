package dlg.depotverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import dlg.depotverwaltung.tablemodels.KundenlisteTableModel;
import awk.kundenverwaltung.usecase.IKundenlisteErstellenRemote;

public class KundenlisteAnzeigen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(final IKundenlisteErstellenRemote kundenlisteErstellenRemote) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KundenlisteAnzeigen frame = new KundenlisteAnzeigen(kundenlisteErstellenRemote);
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
	public KundenlisteAnzeigen(IKundenlisteErstellenRemote kundenlisteErstellenRemote) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 266);
		contentPane.add(scrollPane);
		
		table = new JTable(new KundenlisteTableModel(kundenlisteErstellenRemote));
		scrollPane.setViewportView(table);
	}
}
