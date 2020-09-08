package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.Korisnik;
import model.Uloga;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class KorisnikProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private List<Korisnik> korisnici;
	private String[] imenaKolona;
	private Uloga indikator;
	
	private JFrame parent;
	private JTable table;
	private JButton info;
	
	private ImageIcon addI = new ImageIcon("slike/add.gif");
	private ImageIcon editI = new ImageIcon("slike/edit.gif");
	private ImageIcon deleteI = new ImageIcon("slike/remove.gif");
	private JButton btnAdd = new JButton(addI);
	private JButton btnEdit = new JButton(editI);
	private JButton btnDelete = new JButton(deleteI);
	private ImageIcon searchI = new ImageIcon("slike/search.jpg");
	private ImageIcon scaledS = new ImageIcon(searchI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private JButton btnSearch = new JButton(scaledS);
	private ImageIcon refreshI = new ImageIcon("slike/refresh.jpg");
	private ImageIcon scaledR = new ImageIcon(refreshI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private JButton btnRefresh = new JButton(scaledR);
	
	private ComboZanr cz;
	private List<Zanr> izabraniZanrovi;
	
	public KorisnikProzor(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public KorisnikProzor(JFrame parent, String ime, int dim1, int dim2, 
			List<Korisnik> korisnici, String[] imenaKolona, Uloga indikator) {
		super(ime, dim1, dim2);
		this.parent = parent;
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
		
		
		if (parent instanceof AdminHomepage) {
			base.add(btnAdd);
			base.add(btnEdit);
			base.add(btnDelete);
			
			if (indikator == Uloga.UREDNIK) {
				cz = new ComboZanr();
				cz.kreirajSadrzaj(((AdminHomepage)parent).getSesija().getZanroviMenadzer().vratiAktivneZanrove());
				izabraniZanrovi = new ArrayList<>();
				base.add(cz);
				base.add(btnSearch);
				base.add(btnRefresh);
			}
			
		}
		
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
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(KorisnikProzor.this, "Morate selektovati korisnika.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String korisnickoIme = table.getModel().getValueAt(rIndex, 0).toString();
					Korisnik k = ((AdminHomepage)parent).getSesija().getKorisnici().trazi(korisnickoIme);
					DijalogRadSaNalogom drsn = new DijalogRadSaNalogom(null, k, k.getNalog().getKorisnickoIme(), true);
					drsn.setVisible(true);
				}
			}
			
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				KorisnikAddEdit kae = new KorisnikAddEdit("Dodavanje Korisnika", indikator, ((AdminHomepage)parent).getSesija(), korisnici);
				kae.setVisible(true);
				KorisnikProzor.this.refreshData();
				
			}
			
		});
		
		btnEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(KorisnikProzor.this, "Morate selektovati korisnika.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String korisnickoIme = table.getModel().getValueAt(rIndex, 0).toString();
					Korisnik k = ((AdminHomepage)parent).getSesija().getKorisnici().trazi(korisnickoIme);
					DijalogRadSaNalogom drsn = new DijalogRadSaNalogom(null, k, k.getNalog().getKorisnickoIme());
					drsn.setVisible(true);
					KorisnikProzor.this.refreshData();
				}
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(KorisnikProzor.this, "Morate selektovati korisnika.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (JOptionPane.showConfirmDialog(null, "Jeste sigurno da zelite obrisati?", "Brisanje Korisnika",
					        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
						String korisnickoIme = table.getModel().getValueAt(rIndex, 0).toString();
						Korisnik k = ((AdminHomepage)parent).getSesija().getKorisnici().trazi(korisnickoIme);
						korisnici.remove(k);
						k.setStatus(false);
						KorisnikProzor.this.refreshData();
					}
				}
				
			}
			
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				izabraniZanrovi.clear();
				KorisnikProzor.this.refreshData();
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	private void refreshData() {
		KorisnikModel km = (KorisnikModel)table.getModel();
		km.fireTableDataChanged();
	}
}
