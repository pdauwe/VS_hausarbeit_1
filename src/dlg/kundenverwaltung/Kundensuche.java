package dlg.kundenverwaltung;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;

import awk.AnwendungskernException;
import awk.kundenverwaltung.entity.KundeTO;
import awk.kundenverwaltung.usecase.IKundenSuchenRemote;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JScrollPane;

public class Kundensuche extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1361629890719415204L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JTextField tf_name;
	private JTextField tf_vorname;

	private IKundenSuchenRemote kundenSuchen;
	
	/**
	 * Launch the application.
	 */
	public static void main(final IKundenSuchenRemote kundenSuchen2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kundensuche frame = new Kundensuche(kundenSuchen2);
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
	public Kundensuche(IKundenSuchenRemote kundensuchen) {
		this.kundenSuchen = kundensuchen;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 496, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(50, 39, 61, 16);
		contentPane.add(lblName);
		
		tf_name = new JTextField();
		tf_name.setBounds(50, 60, 134, 28);
		contentPane.add(tf_name);
		tf_name.setColumns(10);
		
		JLabel lblVorname = new JLabel("Vorname");
		lblVorname.setBounds(196, 39, 61, 16);
		contentPane.add(lblVorname);
		
		tf_vorname = new JTextField();
		tf_vorname.setBounds(196, 60, 134, 28);
		contentPane.add(tf_vorname);
		tf_vorname.setColumns(10);
		
		JButton jb_suchen = new JButton("Suchen");
		jb_suchen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					actionPerformedSuchen(e);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		jb_suchen.setBounds(342, 61, 117, 29);
		contentPane.add(jb_suchen);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(60, 106, 399, 146);
		contentPane.add(scrollPane);
	}
	
	private void actionPerformedSuchen(ActionEvent e) throws RemoteException {
		String aName = tf_name.getText();
		String aVorName = tf_vorname.getText();
		
		
		table = new JTable();
		scrollPane.setViewportView(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		Collection<KundeTO> gefundeneAdressen = new ArrayList<KundeTO>();
		try {
			gefundeneAdressen = this.kundenSuchen.kundenSuchenByNameR(aVorName, aName);
		} catch (AnwendungskernException  | RemoteException evt) {
			JOptionPane.showMessageDialog(null, "Fehler in der Anwendung!!");
			this.dispose();
		}
		
		table.setModel(new KundenTableModel(gefundeneAdressen));
		
	}
}
