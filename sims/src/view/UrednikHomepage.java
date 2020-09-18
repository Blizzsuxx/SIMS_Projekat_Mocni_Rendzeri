package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import controler.Constants;
import controler.UtisakMenadzer;
import controler.ZakazanaRecenzijaMenadzer;
import model.MuzickiSadrzaj;
import model.Recenzija;
import model.Sesija;
import model.Slikovit;
import model.TopLista;
import model.Uloga;
import model.Urednik;
import model.ZakazanaRecenzija;

public class UrednikHomepage extends Homepage {
	 private static final long serialVersionUID = 1L;

	 private JMenu recenzijeMenu, korisniciMenu, listeMenu, glasanjeMenu, muzickaSadrzajMenu;
	 private JMenuItem recenzijeItem2, recenzijeItem3,
	 korisniciItem1, korisniciItem2, korisniciItem3, korisniciItem4, korisniciItem5, korisniciItem6,
	 korisniciItem7, listeItem1, listeItem2, glasanjeItem1, muzickiSadrzajItem1, muzickiSadrzajItem2;
	 
	public UrednikHomepage(Sesija sesija) {
	    super(sesija);
	    
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(UrednikHomepage.this, 
						"Jeste sigurni da zelite odjavu?", "Odjava", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				
					dispose();
				}
			}
		});
		
	    this.initGUI();
	    this.initAction();
	}
	    
	private void initGUI() {
		recenzijeMenu = new JMenu("Recenzije");  	
		recenzijeItem2= new JMenuItem("Istorija Recenzija");
		recenzijeMenu.add(recenzijeItem2);
		recenzijeItem3 = new JMenuItem("Zakazane recenzije");
		recenzijeMenu.add(recenzijeItem3);
		//recenzijeItem4 = new JMenuItem("Dodeljene recenzije");
		//recenzijeMenu.add(recenzijeItem4);
		menubar.add(recenzijeMenu);
		    	
		korisniciMenu = new JMenu("Korisnici");
		korisniciItem1 = new JMenuItem("Korisnici");
		korisniciMenu.add(korisniciItem1);
		korisniciItem2 = new JMenuItem("Korisnici Aplikacije");
		korisniciMenu.add(korisniciItem2);
		korisniciItem3 = new JMenuItem("Urednici");
		korisniciMenu.add(korisniciItem3);
		korisniciItem4 = new JMenuItem("Admini");
		korisniciMenu.add(korisniciItem4);
		korisniciMenu.addSeparator();
		korisniciItem5 = new JMenuItem("Registracija Izvodjaca");
		korisniciMenu.add(korisniciItem5);
		korisniciMenu.addSeparator();
		korisniciItem6 = new JMenuItem("Neprihvaceni Izvodjaci");
		korisniciMenu.add(korisniciItem6);
		korisniciItem7 = new JMenuItem("Prihvaceni Izvodjaci");
		korisniciMenu.add(korisniciItem7);
		menubar.add(korisniciMenu);
		
		listeMenu = new JMenu("Liste");
		listeItem1 = new JMenuItem("Kreiranje Top Liste");
		listeMenu.add(listeItem1);
		listeItem2 = new JMenuItem("Pregled Top Listi");
		listeMenu.add(listeItem2);
		menubar.add(listeMenu);
		
		glasanjeMenu = new JMenu("Glasanje");
		glasanjeItem1 = new JMenuItem("Glasaj");
		glasanjeMenu.add(glasanjeItem1);
		menubar.add(glasanjeMenu);
		
		muzickaSadrzajMenu = new JMenu("Muzicki sadrzaj");
		muzickiSadrzajItem1 = new JMenuItem("Dodaj muzicko delo");
		muzickaSadrzajMenu.add(muzickiSadrzajItem1);
		muzickiSadrzajItem2 = new JMenuItem("Registarcija albuma");
		muzickaSadrzajMenu.add(muzickiSadrzajItem2);
		menubar.add(muzickaSadrzajMenu);
		
	}
	    
	private void initAction() {
	    	
		recenzijeItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					MojDialog recenzije = new MojDialog(UrednikHomepage.this, "Moje Recenzije");
					ArrayList<Slikovit> dela = new ArrayList<Slikovit>();
					UtisakMenadzer menadzerUtisaka = getSesija().getUtisakMenadzer();
					for(Recenzija recenzija : menadzerUtisaka.getRecenzije())
					{
						if(recenzija.getPisac().equals(Sesija.getTrenutniKorisnik()))
						{
							dela.add(recenzija.getDelo());
						}
					}
					SearchResults mojeRecenzije = new SearchResults(dela);
					recenzije.setContentPane(mojeRecenzije);
					recenzije.setVisible(true);
			}
	    		
	    });
	    
		recenzijeItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MojDialog recenzije = new MojDialog(UrednikHomepage.this, "Zakazane Recenzije");
				ArrayList<Slikovit> dela = new ArrayList<Slikovit>();
				ZakazanaRecenzijaMenadzer menadzerZakazanih = getSesija().getZakazanaRecenzijaMenadzer();
				for (ZakazanaRecenzija zakazana : menadzerZakazanih.getSve()) {
					if(zakazana.getUrednik().equals(Sesija.getTrenutniKorisnik()))
					{
						dela.add(zakazana.getRecenzija().getDelo());
					}
				}
				SearchResults mojeRecenzije = new SearchResults(dela);
				recenzije.setContentPane(mojeRecenzije);
				recenzije.setVisible(true);
			}
	    	
	    });
		/*
		recenzijeItem4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MojDialog recenzije = new MojDialog(UrednikHomepage.this, "Dodeljene recenzije Recenzije");
				//Search-u se prosledjuju dela za koje je trenutni korisnik urednik treba da napise recenzije
				//Koriscena hardkodovana konstanta Dela3
				SearchResults mojeRecenzije = new SearchResults(Constants.DELA3);
				recenzije.setContentPane(mojeRecenzije);
				recenzije.setVisible(true);
			}
		});
	    */
	    
	    korisniciItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				KorisnikProzor kp = new KorisnikProzor(UrednikHomepage.this,
						"Svi Korisnici", 700, 300,
						UrednikHomepage.this.getSesija().getKorisnici().vratiSveAktivneKorisnike(),
						koloneSvihKorisnika, null);
				kp.setVisible(true);
					
			}
	    		
	    });
	    
	    korisniciItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				KorisnikProzor kp = new KorisnikProzor(UrednikHomepage.this,
						"Korisnici Aplikacije", 700, 300,
						UrednikHomepage.this.getSesija().getKorisnici().vratiObicneKorisnike(),
						koloneSvihObicnihKorisnika, Uloga.KORISNIK);
				kp.setVisible(true);
				
			}
	    	
	    });
	    
	    korisniciItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikProzor kp = new KorisnikProzor(UrednikHomepage.this,
						"Urednici", 700, 300,
						UrednikHomepage.this.getSesija().getKorisnici().vratiUrednike(),
						koloneSvihUrednika, Uloga.UREDNIK);
				kp.setVisible(true);
				
			}
	    	
	    });
	    
	    korisniciItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikProzor kp = new KorisnikProzor(UrednikHomepage.this,
						"Admini", 700, 300,
						UrednikHomepage.this.getSesija().getKorisnici().vratiAdmine(),
						koloneSvihAdmina, Uloga.ADMIN);
				kp.setVisible(true);
				
			}
	    	
	    });;
	    
	    korisniciItem5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistracijaIzvodjaca ri;
				try {
					ri = new RegistracijaIzvodjaca(UrednikHomepage.this.getSesija());
					ri.setVisible(true);
				} catch (Exception e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
			}
	    		
	    });
	    
	    korisniciItem6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				IzvodjaciProzor ip = new IzvodjaciProzor(UrednikHomepage.this, "Neprihvaceni Izvodjaci",
						700, 300,
						UrednikHomepage.this.getSesija().getIzvodjacMenadzer().vratiIzvodjaceNaOsnovuOdobrenosti(false));
				ip.setVisible(true);
				
			}
	    	
	    });
	    
	    korisniciItem7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IzvodjaciProzor ip = new IzvodjaciProzor(UrednikHomepage.this, "Prihvaceni Izvodjaci",
						700, 300, 
						UrednikHomepage.this.getSesija().getIzvodjacMenadzer().vratiIzvodjaceNaOsnovuOdobrenosti(true));
				ip.setVisible(true);
				
				
			}
	    	
	    });
	    
	    listeItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String trenutniUser = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
				Urednik u = (Urednik) UrednikHomepage.this.getSesija().getKorisnici().trazi(trenutniUser);
				List<MuzickiSadrzaj> temp = UrednikHomepage.this.getSesija().getMuzickiSadrzajMenadzer().
						vratiMuzickiSadrzajUrednika(u.getZanrovi());
				TopListeProzor tlp = new TopListeProzor
						((Homepage)UrednikHomepage.this, "Kreiranje Top Liste", 1200, 500,
								temp
								, koloneMuzickogSadrzaja, trenutniUser, null, null);
				tlp.setVisible(true);
				
			}
	    	
	    });
	    
	    listeItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String trenutniUser = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
				List<TopLista> liste = UrednikHomepage.this.getSesija().getToplisteMenadzer().topListeKorisnika(trenutniUser);
				TopListaProzor tlp = new TopListaProzor(UrednikHomepage.this, "Top liste", 700, 300, liste, koloneTopListi);
				tlp.setVisible(true);
			}
	    	
	    });
	    
	    glasanjeItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GlasanjeProzor(UrednikHomepage.this.getSesija(), "Glasanje", 620, 413, Uloga.UREDNIK);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
	    	
	    });
	    
	    muzickiSadrzajItem1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DodajMuzickoDelo(UrednikHomepage.this.getSesija(), "Dodavanje muzickog dela izvodjacu", 422, 422, null, 1);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
	    
	    muzickiSadrzajItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new RegistarcijaAlbuma(UrednikHomepage.this.getSesija(), "Registarcija albuma", 700, 550);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
	}

}