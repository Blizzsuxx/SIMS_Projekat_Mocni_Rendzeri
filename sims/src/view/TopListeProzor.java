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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import model.MuzickiSadrzaj;
import model.TipMuzickogSadrzaja;
import model.TopLista;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class TopListeProzor extends MojDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel base, base2, base3;
	private JTextField nazivTf;
	private JButton kreiraj = new JButton("Kreiraj");
	
	private ImageIcon searchI = new ImageIcon("slike/search.jpg");
	private ImageIcon scaledS = new ImageIcon(searchI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private ImageIcon refreshI = new ImageIcon("slike/refresh.jpg");
	private ImageIcon scaledR = new ImageIcon(refreshI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	
	private ImageIcon addI = new ImageIcon("slike/add.gif");
	private JButton btnAdd = new JButton(addI);
	private ComboZanr cz;
	private List<Zanr> izabraniZanrovi;
	private JCheckBox albumCB, muzickoCB;
	private JButton btnSearch = new JButton(scaledS);
	private JButton btnRefresh = new JButton(scaledR);
	private JTable table1;
	
	private ImageIcon deleteI = new ImageIcon("slike/remove.gif");
	private JButton btnDelete = new JButton(deleteI);
	private JTable table2;
	
	private String[] imenaKolona;
	private List<MuzickiSadrzaj> muzickiSadrzaj;
	private List<Zanr> zanrovi;
	private JFrame parent;
	
	private List<MuzickiSadrzaj> trenutniSadrzaj;
	private String nazivTrenutnogKorisnika;
	private TopLista tp;
	private List<TopLista> toplistaKorisnika;
	
	public TopListeProzor(JFrame parent, String naziv, int dim1, int dim2,
			List<MuzickiSadrzaj> muzickiSadrzaj, String[] imenaKolona, String nazivTrenutnogKorisnika, TopLista tp, 
			List<TopLista> toplistaKorisnika) {
		super(parent, naziv, dim1, dim2);
		
		this.parent = parent;
		this.imenaKolona = imenaKolona;
		this.muzickiSadrzaj = muzickiSadrzaj;
		this.zanrovi = ((Homepage)parent).getSesija().getZanroviMenadzer().vratiAktivneZanrove();
		this.izabraniZanrovi = new ArrayList<>();
		
		this.nazivTrenutnogKorisnika = nazivTrenutnogKorisnika;
		this.tp = tp;
		if (this.tp == null) {
			this.trenutniSadrzaj = new ArrayList<>();
		} else {
			this.trenutniSadrzaj = tp.getMuzickiSadrzaj();
			kreiraj.setEnabled(false);
		}
		this.toplistaKorisnika = toplistaKorisnika;
		
		this.setLayout(new BorderLayout());
		
		this.initGUI();
		this.actionGUI();
		this.pack();
	}

	private void initGUI() {
		base = new JPanel(new MigLayout("wrap 3", "[]10[]10[]", "[]"));
		JLabel nazivLb = new JLabel("Unesi naziv:");
		base.add(nazivLb);
		nazivTf = new JTextField(10);
		base.add(nazivTf);
		base.add(kreiraj);
		this.add(base, BorderLayout.NORTH);
		
		base2 = new JPanel(new BorderLayout());
		JPanel dugmici1 = new JPanel(new MigLayout("wrap 6", "[]10[]10[]10[]10[]10[]", "[]"));
		dugmici1.add(btnAdd);
		cz = new ComboZanr(); // treba napuniti kombo boxove
		cz.kreirajSadrzaj(zanrovi);
		dugmici1.add(cz);
		albumCB = new JCheckBox("Album"); muzickoCB = new JCheckBox("Muzicko Delo");
		dugmici1.add(albumCB);
		dugmici1.add(muzickoCB);
		dugmici1.add(btnSearch);
		dugmici1.add(btnRefresh);
		base2.add(dugmici1, BorderLayout.NORTH);
		table1 = new JTable(new MuzickiSadrzajModel(imenaKolona, muzickiSadrzaj, null));
		table1.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp1 = new JScrollPane(table1);
		base2.add(sp1, BorderLayout.CENTER);
		this.add(base2, BorderLayout.WEST);
		
		base3 = new JPanel(new BorderLayout());
		JPanel dugmici2 = new JPanel(new MigLayout("wrap 1", "30[]", "[]"));
		dugmici2.add(btnDelete);
		base3.add(dugmici2, BorderLayout.NORTH); // na ovo se vratiti 
		table2 = new JTable(new MuzickiSadrzajModel(imenaKolona, trenutniSadrzaj, null));
		table2.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table2.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp2 = new JScrollPane(table2);
		base3.add(sp2, BorderLayout.CENTER);
		this.add(base3, BorderLayout.EAST);
		
	}
	
	private void actionGUI() {
		kreiraj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) { // provjera postojanja toplisti
				if (!trenutniSadrzaj.isEmpty() && !nazivTf.getText().isEmpty()) {
					String naziv = nazivTf.getText();
					if (((Homepage)parent).getSesija().getToplisteMenadzer().
							kreirajTopListu(naziv, nazivTrenutnogKorisnika, trenutniSadrzaj, toplistaKorisnika)) {
						TopListeProzor.this.dispose();
					} else {
						  JOptionPane.showMessageDialog(TopListeProzor.this,"Doslo do greske.");  
					}
				}
				
			}
			
		});
		
		btnAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table1.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(TopListeProzor.this, "Morate selektovati sadrzaj.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String nazivSadrzaja = table1.getModel().getValueAt(rIndex, 0).toString();
					MuzickiSadrzaj ms = ((Homepage)parent).getSesija().
							getMuzickiSadrzajMenadzer().vratiNaOsnovuNazive(nazivSadrzaja);
					if (!trenutniSadrzaj.contains(ms)) {
						trenutniSadrzaj.add(ms);
						TopListeProzor.this.refreshData2();
					}
				}
				
			}
			
		});
		
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table2.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(TopListeProzor.this, "Morate selektovati sadrzaj.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (trenutniSadrzaj.size() > 1) {
						String nazivSadrzaja = table2.getModel().getValueAt(rIndex, 0).toString();
						MuzickiSadrzaj ms = ((Homepage)parent).getSesija().
							getMuzickiSadrzajMenadzer().vratiNaOsnovuNazive(nazivSadrzaja);
						trenutniSadrzaj.remove(ms);
						TopListeProzor.this.refreshData2();
					}
				}
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				izabraniZanrovi.clear();
				cz.vratiSelektovaneZanrove(izabraniZanrovi);
				if (!izabraniZanrovi.isEmpty()) {
					TipMuzickogSadrzaja indikator = null;
					if (muzickoCB.isSelected() && !albumCB.isSelected())
						indikator = TipMuzickogSadrzaja.MUZICKO_DELO;
					else if (!muzickoCB.isSelected() && albumCB.isSelected())
						indikator = TipMuzickogSadrzaja.ALBUM;
					((Homepage)parent).getSesija().getMuzickiSadrzajMenadzer().
					pretrageMuzickogSadrzajaNaOsnovuZanrova(muzickiSadrzaj, izabraniZanrovi, indikator);
					TopListeProzor.this.refreshData1();
				}
				
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!((Homepage)parent).getSesija().getMuzickiSadrzajMenadzer().vratiAktivanMuzickiSadrzaj().isEmpty()){
					TopListeProzor.this.muzickiSadrzaj.clear();
					for (MuzickiSadrzaj ms: ((Homepage)parent).getSesija().
							getMuzickiSadrzajMenadzer().vratiAktivanMuzickiSadrzaj()) {
						TopListeProzor.this.muzickiSadrzaj.add(ms);
					}
					TopListeProzor.this.refreshData1();
				}
				
			}
			
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	private void refreshData1() {
		MuzickiSadrzajModel km = (MuzickiSadrzajModel)table1.getModel();
		km.fireTableDataChanged();
	}
	
	private void refreshData2() {
		MuzickiSadrzajModel km = (MuzickiSadrzajModel)table2.getModel();
		km.fireTableDataChanged();
	}
}
