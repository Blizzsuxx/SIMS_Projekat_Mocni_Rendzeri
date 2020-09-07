package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.search.RecentSearches;

import model.Sesija;

public class SearchField extends JXSearchField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private JFrame owner;
	private JCheckBoxMenuItem muzickaDela;
	private JCheckBoxMenuItem grupe;
	private JCheckBoxMenuItem albumi;
	private JCheckBoxMenuItem izvodjaci;
	private JCheckBoxMenuItem korisnici;

	public SearchField(Sesija sesija, JFrame owner) {
		super();
		
		this.owner = owner;
		this.sesija = sesija;
		
		String korisnickoIme = null;
		if(sesija.getTrenutniKorisnik() != null) {
			korisnickoIme = sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
		}
		
		this.setSearchMode(SearchMode.REGULAR);
		
		RecentSearches recentSearches = new RecentSearches(korisnickoIme);
		recentSearches.install(this);
		this.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				recentSearches.put(arg0.getActionCommand());
				searchTriggered(arg0.getActionCommand());
			}

		});
		

		JPopupMenu selectSearchCriterium = new MojPopupMenu();
		
		JMenu muzikaSegment = new JMenu("Muzika");
		JCheckBoxMenuItem pesme = new JCheckBoxMenuItem("Pesme");
		JCheckBoxMenuItem albumi = new JCheckBoxMenuItem("Albumi");
		muzikaSegment.add(pesme);
		muzikaSegment.add(albumi);
		
		JMenu izvodjaciSegment = new JMenu("Izvodjaci");
		JCheckBoxMenuItem grupe = new JCheckBoxMenuItem("Grupe");
		JCheckBoxMenuItem izvodjaci = new JCheckBoxMenuItem("Solo Izvodjaci");
		izvodjaciSegment.add(grupe);
		izvodjaciSegment.add(izvodjaci);
		
		JCheckBoxMenuItem korisnici = new JCheckBoxMenuItem("Urednici");
		
		selectSearchCriterium.add(muzikaSegment);
		selectSearchCriterium.add(izvodjaciSegment);
		selectSearchCriterium.add(korisnici);
		
		this.albumi = albumi;
		this.korisnici = korisnici;
		this.muzickaDela = pesme;
		this.izvodjaci = izvodjaci;
		this.grupe = grupe;
		pesme.setSelected(true);
		
		
		this.add(selectSearchCriterium);
		this.setComponentPopupMenu(selectSearchCriterium);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				if(SearchField.this.getComponentPopupMenu().isVisible()) {
					SearchField.this.getComponentPopupMenu().setVisible(false);
				} else {
					SearchField.this.getComponentPopupMenu().show(SearchField.this, 30, 0);
				}
			}
		});
		
		this.getPopupButton().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					SearchField.this.getComponentPopupMenu().setVisible(false);
					SearchField.this.getFindPopupMenu().show(SearchField.this, 0, SearchField.this.getHeight());
				} catch(Exception e2) {
					SearchField.this.getComponentPopupMenu().setVisible(false);
					SearchField.this.getFindPopupMenu().show(SearchField.this, 0, SearchField.this.getHeight());
				}
			}
		});
		
		
	}
	
	
	
	private void searchTriggered(String textZaSearch) {
		ArrayList<Slikovit> rezultati = new ArrayList<>();
		
		if(this.muzickaDela.isSelected()) {
			rezultati.addAll(sesija.getMuzickoDeloMenadzer().trazi(textZaSearch));
		}
		if(this.grupe.isSelected()) {
			rezultati.addAll(sesija.getIzvodjacMenadzer().traziGrupe(textZaSearch));
		}
		if(this.izvodjaci.isSelected()) {
			rezultati.addAll(sesija.getIzvodjacMenadzer().traziSoloIzvodjace(textZaSearch));
		}
		if(this.albumi.isSelected()) {
			rezultati.addAll(sesija.getAlbumKontroler().trazi(textZaSearch));
		}
		if(this.korisnici.isSelected()) {
			rezultati.addAll(sesija.getKorisnici().traziZaSearch(textZaSearch));
		}
		
		
		SearchResults rezultatiPanel = new SearchResults(rezultati);
		MojDialog dialog = new MojDialog(this.owner, "Rezultati pretrage");
		dialog.setContentPane(rezultatiPanel);
		dialog.setVisible(true);
	}

}
