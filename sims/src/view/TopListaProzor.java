package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.MuzickiSadrzaj;
import model.Sesija;
import model.TopLista;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class TopListaProzor extends MojDialog implements ActionListener {
	private static final long serialVersionUID = 1L;

	private Homepage parent;
	private List<TopLista> topListe;
	private String[] imenaKolona;
	
	private JButton pregledBtn;
	private JTable table;
	private TablePopupMenu popupMenu = new TablePopupMenu();
	private JMenuItem pregledMenuItem;
	
	public TopListaProzor(Homepage parent, String ime, int dimension1, int dimension2, List<TopLista> topListe,
			String[] imenaKolona) {
		super(parent, ime, dimension1, dimension2);
		
		this.parent = parent;
		this.topListe = topListe;
		this.imenaKolona = imenaKolona;
		
		this.setLayout(new BorderLayout());
		
		if (this.parent instanceof KorisnikAplikacijeHomepage && ime.equals("Top Liste Urednika")) {
			popupMenu.menuItemAdd.setVisible(false);
			popupMenu.menuItemEdit.setVisible(false);
			popupMenu.menuItemDelete.setVisible(false);
		}
		this.initGUI();
		this.actionGUI();
	}

	private void initGUI() {
		pregledBtn = new JButton("Pregled");
		JPanel base = new JPanel(new MigLayout());
		base.add(pregledBtn);
		this.add(base, BorderLayout.NORTH);
		
		table = new JTable(new TopListeModel(imenaKolona, topListe));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		
		table.setComponentPopupMenu(popupMenu);
        table.addMouseListener(new TableMouseListener(table));
        popupMenu.addSeparator();
        pregledMenuItem = new JMenuItem("Pregled");
        popupMenu.add(pregledMenuItem);
	}
	
	private void actionGUI() {
		pregledMenuItem.addActionListener(this);
		pregledBtn.addActionListener(this);
		
		popupMenu.menuItemAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String trenutniUser = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
				Urednik u = (Urednik) parent.getSesija().getKorisnici().trazi(trenutniUser);
				List<MuzickiSadrzaj> temp = parent.getSesija().getMuzickiSadrzajMenadzer().
						vratiMuzickiSadrzajUrednika(u.getZanrovi());
				TopListeProzor tlp = new TopListeProzor
						(parent, "Kreiranje Top Liste", 1200, 500,
								temp
								, parent.koloneMuzickogSadrzaja, trenutniUser, null, topListe);
				tlp.setVisible(true);
				refreshData();
				
			}
			
		});
		
		popupMenu.menuItemEdit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(TopListaProzor.this, "Izaberi top listu.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String trenutniUser = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
					Urednik u = (Urednik) parent.getSesija().getKorisnici().trazi(trenutniUser);
					List<MuzickiSadrzaj> temp = parent.getSesija().getMuzickiSadrzajMenadzer().
							vratiMuzickiSadrzajUrednika(u.getZanrovi());
					String imeTabele = table.getModel().getValueAt(rIndex, 0).toString();
					TopLista tp = ((Homepage)parent).getSesija().getToplisteMenadzer().vratiTopListuNaOsnovuImena(imeTabele);
					TopListeProzor tlp = new TopListeProzor
							(parent, "Kreiranje Top Liste", 1200, 500,
									temp
									, parent.koloneMuzickogSadrzaja, trenutniUser, tp, topListe);
					tlp.setVisible(true);
					refreshData();
				}
				
			}
			
		});
		
		popupMenu.menuItemDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int rIndex = table.getSelectedRow();
				if (rIndex < 0) {
					JOptionPane.showMessageDialog(TopListaProzor.this, "Izaberi top listu.",
							 "Info", JOptionPane.INFORMATION_MESSAGE);
				} else {
					String imeTabele = table.getModel().getValueAt(rIndex, 0).toString();
					TopLista tp = ((Homepage)parent).getSesija().getToplisteMenadzer().vratiTopListuNaOsnovuImena(imeTabele);
					tp.setStatus(false);
					topListe.remove(tp);
					refreshData();
				}
				
			}
			
		});
	}
	
	private void refreshData() {
		TopListeModel km = (TopListeModel)table.getModel();
		km.fireTableDataChanged();
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rIndex = table.getSelectedRow();
		if (rIndex < 0) {
			JOptionPane.showMessageDialog(TopListaProzor.this, "Morate selektovati neku top listu.",
					 "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String nazivListe = table.getModel().getValueAt(rIndex, 0).toString();
			String nazivKorisnika = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
			TopLista tp = ((Homepage)parent).getSesija().getToplisteMenadzer().vratiTopListu(nazivListe, nazivKorisnika);
			MojDialog md = new MojDialog("Sadrzaj Top Liste", 1000, 1000);
			@SuppressWarnings("unchecked")
			SearchResults sr = new SearchResults( (List<Slikovit>)(List<?>)tp.getMuzickiSadrzaj());
			md.setContentPane(sr);
			md.pack();
			md.setVisible(true);
		}

	}

}
