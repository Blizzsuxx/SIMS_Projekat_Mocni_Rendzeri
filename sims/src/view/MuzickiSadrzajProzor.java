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
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import model.Album;
import model.MuzickiSadrzaj;
import model.MuzickoDelo;
import model.Slikovit;
import model.TipMuzickogSadrzaja;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class MuzickiSadrzajProzor extends MojDialog implements ActionListener {

private static final long serialVersionUID = 1L;
	
	private List<MuzickiSadrzaj> muzickiSadrzaj;
	private String[] imenaKolona;
	private TipMuzickogSadrzaja indikator;
	
	private JFrame parent;
	private JXTable table;
	
	private ComboZanr cz;
	private List<Zanr> izabraniZanrovi;
	private TablePopupMenu popupMenu = new TablePopupMenu();
	private JMenuItem infoItem = new JMenuItem("Pregled");
	private List<MuzickiSadrzaj> temp = new ArrayList<>(); // potrebna dodatna kolekcija zbog refresh i search-a
	
	private ImageIcon searchI = new ImageIcon("slike/search.jpg");
	private ImageIcon scaledS = new ImageIcon(searchI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private JButton btnSearch = new JButton(scaledS);
	private ImageIcon refreshI = new ImageIcon("slike/refresh.jpg");
	private ImageIcon scaledR = new ImageIcon(refreshI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private JButton btnRefresh = new JButton(scaledR);
	private ImageIcon slikovitoI = new ImageIcon("slike/slikovito.png");
	private ImageIcon scaledSl = new ImageIcon(slikovitoI.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH));
	private JButton btnSlikovito = new JButton(scaledSl);
	
	public MuzickiSadrzajProzor(JFrame parent, String ime, int dim1, int dim2,
			List<MuzickiSadrzaj> muzickiSadrzaj, String[] imenaKolona, TipMuzickogSadrzaja indikator) {
		super(ime, dim1, dim2);
		this.parent = parent;
		this.muzickiSadrzaj = muzickiSadrzaj;
		this.imenaKolona = imenaKolona;
		this.indikator = indikator;
		
		this.setLayout(new BorderLayout());
		
		this.initGUI();
		this.actionGUI();
	}
	
	private void initGUI() {
		for (MuzickiSadrzaj ms: muzickiSadrzaj)
			temp.add(ms);
		
		table = new JXTable(new MuzickiSadrzajModel(imenaKolona, muzickiSadrzaj, indikator));
		table.getTableHeader().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		
		JScrollPane sp = new JScrollPane(table);
		this.add(sp, BorderLayout.CENTER);
		
		JPanel base = new JPanel(new MigLayout());
		this.add(base, BorderLayout.NORTH);
		cz = new ComboZanr();
		cz.kreirajSadrzaj(((Homepage)parent).getSesija().getZanroviMenadzer().vratiAktivneZanrove());
		izabraniZanrovi = new ArrayList<>();
		base.add(cz);
		base.add(btnSearch);
		base.add(btnRefresh);
		base.add(btnSlikovito);
		
		
		table.setComponentPopupMenu(popupMenu);
		table.addMouseListener(new TableMouseListener(table));
		popupMenu.menuItemAdd.setVisible(false);
		popupMenu.menuItemEdit.setVisible(false);
		popupMenu.menuItemDelete.setVisible(false);
		
		popupMenu.add(infoItem);
	}
	
	private void actionGUI() {
		infoItem.addActionListener(this);
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				izabraniZanrovi.clear();
				cz.vratiSelektovaneZanrove(izabraniZanrovi);
				if (!izabraniZanrovi.isEmpty()) {
					((Homepage)parent).getSesija().getMuzickiSadrzajMenadzer().
					pretrageMuzickogSadrzajaNaOsnovuZanrova(muzickiSadrzaj, izabraniZanrovi, indikator);
					MuzickiSadrzajProzor.this.refreshData();
				}
				
			}
			
		});
		
		btnRefresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!temp.isEmpty()) {
					muzickiSadrzaj.clear();
					for (MuzickiSadrzaj ms: temp)
						muzickiSadrzaj.add(ms);
					MuzickiSadrzajProzor.this.refreshData();
				}
				
			}
			
		});
		
		btnSlikovito.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MojDialog md = new MojDialog("Slikoviti Prikaz Sadrzaja", 500, 400);
				@SuppressWarnings("unchecked")
				SearchResults sr = new SearchResults( (List<Slikovit>)(List<?>)muzickiSadrzaj);
				JScrollPane sp = new JScrollPane(sr);
				md.setContentPane(sp);
				
				md.setVisible(true);
			}
			
		});
	}
	
	private void refreshData() {
		MuzickiSadrzajModel km = (MuzickiSadrzajModel)table.getModel();
		km.fireTableDataChanged();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		int rIndex = table.getSelectedRow();
		if (rIndex < 0) {
			JOptionPane.showMessageDialog(MuzickiSadrzajProzor.this, "Morate selektovati muzicki sadrzaj.",
					 "Info", JOptionPane.INFORMATION_MESSAGE);
		} else {
			String naziv = table.getModel().getValueAt(rIndex, 0).toString();
			MuzickiSadrzaj ms = ((Homepage)parent).getSesija().getMuzickiSadrzajMenadzer().vratiNaOsnovuNazive(naziv);
			if (ms instanceof MuzickoDelo) {
				MediaPlayer player = new MediaPlayer(null, (MuzickoDelo)ms);
		        player.setVisible(true);
			} else {
				AlbumInfo ai = new AlbumInfo(null,((Album)ms).getNaslov(), 100, 100, (Album)ms);
				ai.setVisible(true);
			}
		}
		
	}
}
