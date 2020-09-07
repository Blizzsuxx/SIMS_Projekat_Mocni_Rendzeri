package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.Korisnik;
import model.KorisnikModel;
import model.Uloga;
import net.miginfocom.swing.MigLayout;

public class KorisnikProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private List<Korisnik> korisnici;
	private String[] imenaKolona;
	private Uloga indikator;
	
	private JTable table;
	private JButton info;
	
	public KorisnikProzor(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public KorisnikProzor(JFrame parent, String ime, int dim1, int dim2, 
			List<Korisnik> korisnici, String[] imenaKolona, Uloga indikator) {
		super(ime, dim1, dim2);
		this.korisnici = korisnici;
		this.imenaKolona = imenaKolona;
		this.indikator = indikator;
		
		this.setLayout(new BorderLayout());
			
		this.initGUI();
		this.actionGUI();
		
	}
	
	private void initGUI() {
		info = new JButton("Informacije");
		JPanel base = new JPanel(new MigLayout());
		base.add(info);
		this.add(base, BorderLayout.NORTH);
		
		table = new JTable(new KorisnikModel(imenaKolona, korisnici, indikator));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
	}
	
	private void actionGUI() {
		info.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}