package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.jdesktop.swingx.JXSearchField;
import org.jdesktop.swingx.search.RecentSearches;

import controler.ZanroviMenadzer;
import model.Album;
import model.DeljivPoZanrovima;
import model.Grupa;
import model.Izvodjac;
import model.MuzickiSadrzaj;
import model.Sesija;
import model.Slikovit;
import model.Urednik;
import model.Zanr;

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
	private Collection<JCheckBoxMenuItem> zanrovi;

	public SearchField(Sesija sesija, JFrame owner) {
		super();
		
		this.owner = owner;
		this.sesija = sesija;
		this.zanrovi = new ArrayList<>();
		
		String korisnickoIme = null;
		if(Sesija.getTrenutniKorisnik() != null) { // promjene
			korisnickoIme = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();//
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


		JMenu zanrMenu = new JMenu("zanrovi");
		for(Zanr z : sesija.getZanroviMenadzer().getZanrovi()){
			JCheckBoxMenuItem zanrMenuItem = new JCheckBoxMenuItem(z.getNazivZanra());
			zanrovi.add(zanrMenuItem);
			zanrMenuItem.setSelected(true);
			zanrMenu.add(zanrMenuItem);
		}
		selectSearchCriterium.add(zanrMenu);

		
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
		LinkedList<Slikovit> rezultati = new LinkedList<Slikovit>();



		ArrayList<String> selektovaniZanrovi = new ArrayList<>();
		for(JCheckBoxMenuItem selektovaniZanr : zanrovi){
			if(selektovaniZanr.isSelected()){
				selektovaniZanrovi.add(selektovaniZanr.getText());
			}
		}

		ZanroviMenadzer menadzerZanrova = sesija.getZanroviMenadzer();
		
		if(this.muzickaDela.isSelected()) {
			Collection<MuzickiSadrzaj> dela = sesija.getMuzickiSadrzajMenadzer().traziMuzickaDela(textZaSearch);

			

			menadzerZanrova.filter( (Collection<DeljivPoZanrovima>)  (Collection<?>) dela, selektovaniZanrovi, rezultati);
			//rezultati.addAll(sesija.getMuzickiSadrzajMenadzer().trazi(textZaSearch));
		}
		if(this.grupe.isSelected()) {

			Collection<Grupa> grupe = sesija.getIzvodjacMenadzer().traziGrupe(textZaSearch);

			menadzerZanrova.filter((Collection<DeljivPoZanrovima>)  (Collection<?>)grupe, selektovaniZanrovi, rezultati);
			//rezultati.addAll(sesija.getIzvodjacMenadzer().traziGrupe(textZaSearch));
		}
		if(this.izvodjaci.isSelected()) {
			Collection<Izvodjac> izvodjaci = sesija.getIzvodjacMenadzer().traziSoloIzvodjace(textZaSearch);

			menadzerZanrova.filter((Collection<DeljivPoZanrovima>)  (Collection<?>)izvodjaci, selektovaniZanrovi, rezultati);
		}
		if(this.albumi.isSelected()) {
			Collection<Album> albumi = sesija.getMuzickiSadrzajMenadzer().traziAlbume(textZaSearch);

			menadzerZanrova.filter((Collection<DeljivPoZanrovima>)  (Collection<?>)albumi, selektovaniZanrovi, rezultati);
		}
		if(this.korisnici.isSelected()) {
			Collection<Urednik> urednici = sesija.getKorisnici().traziZaSearch(textZaSearch);

			menadzerZanrova.filter((Collection<DeljivPoZanrovima>)  (Collection<?>)urednici, selektovaniZanrovi, rezultati);
		}
		


		
		
		SearchResults rezultatiPanel = new SearchResults(rezultati);
		MojDialog dialog = new MojDialog(this.owner, "Rezultati pretrage");
		dialog.setContentPane(rezultatiPanel);
		dialog.setVisible(true);
		
	}




}
