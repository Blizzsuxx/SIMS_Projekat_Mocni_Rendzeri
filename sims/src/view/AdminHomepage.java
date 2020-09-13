package view;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Sesija;
import model.TipMuzickogSadrzaja;
import model.Uloga;

public class AdminHomepage extends Homepage implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JMenu korisniciMenu, izvodjaciMenu, muzickiSadrzajMenu, zanroviMenu, recenzijeMenu, glasanjeMenu, izvestajiMenu;
	private JMenuItem korisniciItem1, korisniciItem2, korisniciItem3, korisniciItem4, korisniciItem5,
	izvodjaciItem1, izvodjaciItem2, izvodjaciItem3, muzickiSadrzajItem1, muzickiSadrzajItem2,
	muzickiSadrzajItem3, muzickiSadrzajItem4, muzickiSadrzajItem5,
	zanroviItem1, recenzijeItem1, recenzijeItem2, recenzijeItem3, glasanjeItem1, izvestajiItem1, izvestajiItem2, izvestajiItem3, reklameItem;
	
	public AdminHomepage(Sesija sesija) {
		super(sesija);
		
		 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent windowEvent) {
					if (JOptionPane.showConfirmDialog(AdminHomepage.this, 
							"Jeste sigurni da zelite odjavu?", "Odjava", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					
						dispose();
					}
				}
			});
			
		this.initGUI();
		this.actionGUI();
	}

	private void initGUI() {
		reklameItem = new JMenuItem("Reklame");
		menu.addSeparator();
		menu.add(reklameItem);
		
		korisniciMenu = new JMenu("Korisnici");    	
		korisniciItem1 = new JMenuItem("Korisnici");
		korisniciMenu.add(korisniciItem1);
		korisniciItem2 = new JMenuItem("Korisnici Aplikacije");
		korisniciMenu.add(korisniciItem2);
		korisniciItem3 = new JMenuItem("Urednici");
		korisniciMenu.add(korisniciItem3);
		korisniciItem4 = new JMenuItem("Admini");
		korisniciMenu.add(korisniciItem4);
		korisniciItem5 = new JMenuItem("Blokiranje/Odblokiranje naloga");
		korisniciMenu.add(korisniciItem5);
		menubar.add(korisniciMenu);
		
		izvodjaciMenu = new JMenu("Izvodjaci");
		izvodjaciItem1 = new JMenuItem("Neprihvaceni Izvodjaci");
		izvodjaciMenu.add(izvodjaciItem1);
		izvodjaciItem2 = new JMenuItem("Prihvaceni Izvodjaci");
		izvodjaciMenu.add(izvodjaciItem2);
		izvodjaciItem3 = new JMenuItem("Promena zanra izvodjaca");
		izvodjaciMenu.add(izvodjaciItem3);
		menubar.add(izvodjaciMenu);
		
		muzickiSadrzajMenu = new JMenu("Muzicki Sadrzaj");
		muzickiSadrzajItem1 = new JMenuItem("Muzicki Sadrzaj");
		muzickiSadrzajMenu.add(muzickiSadrzajItem1);
		muzickiSadrzajItem2 = new JMenuItem("Albumi");
		muzickiSadrzajMenu.add(muzickiSadrzajItem2);
		muzickiSadrzajItem3 = new JMenuItem("Muzicka Dela");
		muzickiSadrzajMenu.add(muzickiSadrzajItem3);
		muzickiSadrzajMenu.addSeparator();
		muzickiSadrzajItem4 = new JMenuItem("Neprihvaceni Albumi");
		muzickiSadrzajMenu.add(muzickiSadrzajItem4);
		muzickiSadrzajItem5 = new JMenuItem("Prihvaceni Albumi");
		muzickiSadrzajMenu.add(muzickiSadrzajItem5);
		menubar.add(muzickiSadrzajMenu);
		
		zanroviMenu = new JMenu("Zanrovi");
		zanroviItem1 = new JMenuItem("Aktivni Zanrovi");
		zanroviMenu.add(zanroviItem1);
		menubar.add(zanroviMenu);
		
		recenzijeMenu = new JMenu("Recenzije");
		recenzijeItem1 = new JMenuItem("Narucivanje recenzija");
		recenzijeMenu.add(recenzijeItem1);
		recenzijeItem2 = new JMenuItem("Dodela recenzija");
		recenzijeMenu.add(recenzijeItem2);
		recenzijeItem3 = new JMenuItem("Uvid u zavrsene recenzije");
		recenzijeMenu.add(recenzijeItem3);
		menubar.add(recenzijeMenu);
		
		glasanjeMenu = new JMenu("Glasanje");
		glasanjeItem1 = new JMenuItem("Pokreni/Zaustavi");
		glasanjeMenu.add(glasanjeItem1);
		menubar.add(glasanjeMenu);
		
		izvestajiMenu = new JMenu("Izvestaji");
		izvestajiItem1 = new JMenuItem("Izvestaj izvodjaca");
		izvestajiMenu.add(izvestajiItem1);
		izvestajiItem2 = new JMenuItem("Izvestaj urednika");
		izvestajiMenu.add(izvestajiItem2);
		izvestajiItem3 = new JMenuItem("Izvestaj zanrova");
		izvestajiMenu.add(izvestajiItem3);
		menubar.add(izvestajiMenu);
		
	}
	
	private void actionGUI() {
		reklameItem.addActionListener(this);
		
		korisniciItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				KorisnikProzor kp = new KorisnikProzor(AdminHomepage.this,
						"Svi Korisnici", 700, 300,
						AdminHomepage.this.getSesija().getKorisnici().vratiSveAktivneKorisnike(),
						koloneSvihKorisnika, null);
				kp.setVisible(true);
				
			}
			
		});
		
		korisniciItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikProzor kp = new KorisnikProzor(AdminHomepage.this,
						"Korisnici Aplikacije", 700, 300,
						AdminHomepage.this.getSesija().getKorisnici().vratiObicneKorisnike(),
						koloneSvihObicnihKorisnika, Uloga.KORISNIK);
				kp.setVisible(true);
				
			}
			
		});
		
		korisniciItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikProzor kp = new KorisnikProzor(AdminHomepage.this,
						"Urednici", 700, 300,
						AdminHomepage.this.getSesija().getKorisnici().vratiUrednike(),
						koloneSvihUrednika, Uloga.UREDNIK);
				kp.setVisible(true);
				
			}
			
		});
		
		korisniciItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikProzor kp = new KorisnikProzor(AdminHomepage.this,
						"Admini", 700, 300,
						AdminHomepage.this.getSesija().getKorisnici().vratiAdmine(),
						koloneSvihAdmina, Uloga.ADMIN);
				kp.setVisible(true);
				
			}
		});
		
		korisniciItem5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new BlokiranjeNaloga(AdminHomepage.this.getSesija(), "Blokiranje/Odblokiranje naloga", 450, 300);
				} 
				catch (Exception e1) {
	
					e1.printStackTrace();
				}	
			}
		});
		
		izvodjaciItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				IzvodjaciProzor ip = new IzvodjaciProzor(AdminHomepage.this, "Neprihvaceni Izvodjaci",
						700, 300,
						AdminHomepage.this.getSesija().getIzvodjacMenadzer().vratiIzvodjaceNaOsnovuOdobrenosti(false));
				ip.setVisible(true);
				
			}
			
		});
		
		izvodjaciItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				IzvodjaciProzor ip = new IzvodjaciProzor(AdminHomepage.this, "Prihvaceni Izvodjaci",
						700, 300, 
						AdminHomepage.this.getSesija().getIzvodjacMenadzer().vratiIzvodjaceNaOsnovuOdobrenosti(true));
				ip.setVisible(true);
				
			}
			
		});
		
		izvodjaciItem3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new PromenaZanraIzvodjaca(AdminHomepage.this.getSesija(), "Promena zanra izvodjaca", 710, 550);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		muzickiSadrzajItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				MuzickiSadrzajProzor msp = new MuzickiSadrzajProzor(AdminHomepage.this, "Muzicki Sadrzaj",
						700, 300, AdminHomepage.this.getSesija().getMuzickiSadrzajMenadzer().vratiAktivanMuzickiSadrzaj(),
						koloneMuzickogSadrzaja, null);
				msp.setVisible(true);
				
			}
			
		});
		
		muzickiSadrzajItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MuzickiSadrzajProzor msp = new MuzickiSadrzajProzor(AdminHomepage.this, "Albumi",
						700, 300, AdminHomepage.this.getSesija().getMuzickiSadrzajMenadzer().vratiAktivneAlbumeSadrzaja(),
						koloneMuzickogSadrzaja, TipMuzickogSadrzaja.ALBUM);
				msp.setVisible(true);
				
			}
			
		});
		
		muzickiSadrzajItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MuzickiSadrzajProzor msp = new MuzickiSadrzajProzor(AdminHomepage.this, "Muzicka Dela",
						700, 300, AdminHomepage.this.getSesija().getMuzickiSadrzajMenadzer().vratiAktivnaMuzickaDjelaSadrzaja(),
						koloneMuzickogSadrzaja, TipMuzickogSadrzaja.MUZICKO_DELO);
				msp.setVisible(true);
				
			}
			
		});
		
		muzickiSadrzajItem4.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					new AlbumiProzor(AdminHomepage.this, "Neprihvaceni Albumi", 700, 300, 
						AdminHomepage.this.getSesija().getMuzickiSadrzajMenadzer().vratiAlbumeNaOsnovuOdobrenosti(false));
				
			}
			
		});
		
		muzickiSadrzajItem5.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				new AlbumiProzor(AdminHomepage.this, "Prihvaceni Albumi", 700, 300, 
						AdminHomepage.this.getSesija().getMuzickiSadrzajMenadzer().vratiAlbumeNaOsnovuOdobrenosti(true));
			}
			
		});
		
		zanroviItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ZanrProzor zp = new ZanrProzor(AdminHomepage.this, "Aktivni Zanrovi", 500, 300, 
						AdminHomepage.this.getSesija().getZanroviMenadzer().vratiAktivneZanrove());
				zp.setVisible(true);
				
			}
			
		});
		
		recenzijeItem1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DodavanjeZakRecIRecZaIzemnu(AdminHomepage.this.getSesija(), "Narucivanje recenzija", 430, 500);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		recenzijeItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new DodelaRecenzija(AdminHomepage.this.getSesija(), "Dodela recenzija", 790, 350);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		recenzijeItem3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new UvidUZavrseneRecenzije(AdminHomepage.this.getSesija(), "Uvid u zavrsene recenzije", 625, 326);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		glasanjeItem1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new GlasanjeProzor(AdminHomepage.this.getSesija(), "Pokretanje/Zaustavljanje glasanje", 620, 413, Uloga.ADMIN);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		
		izvestajiItem1.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajViseIzvodjaca ivi = new IzvestajViseIzvodjaca(AdminHomepage.this.getSesija(), "Izvestaj izvodjaca", 547, 447);
				ivi.setVisible(true);
				
			}
			
		});
		
		izvestajiItem2.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					IzvestajViseUrednika ivu = new IzvestajViseUrednika(AdminHomepage.this.getSesija(), "Izvestaj urednika", 524, 416);
					ivu.setVisible(true);
			}
		});
		
		izvestajiItem3.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
					IzvestajViseZanrova ivz = new IzvestajViseZanrova(AdminHomepage.this.getSesija(), "Izvestaj zanrova", 300, 500);
					ivz.setVisible(true);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ReklameProzor rp = new ReklameProzor("Reklame", 100, 100, AdminHomepage.this);
		rp.setVisible(true);
		
		panelReklama.removeAll();
		panelReklama.revalidate();
		panelReklama.repaint();
		
		reklamaPrva = new ImageIcon(super.getSesija().getReklameMenadzer().getPutanjaPrveReklame());
		obradjenaReklamaPrva = new ImageIcon(reklamaPrva.getImage().getScaledInstance(528, 100, Image.SCALE_DEFAULT));
		reklamaDruga = new ImageIcon(super.getSesija().getReklameMenadzer().getPutanjaDrugeReklame());
		obradjenaReklamaDruga = new ImageIcon(reklamaDruga.getImage().getScaledInstance(528, 100, Image.SCALE_DEFAULT));
		JLabel labelaPrva = new JLabel(obradjenaReklamaPrva);
		JLabel labelaDruga = new JLabel(obradjenaReklamaDruga);
		panelReklama.add(labelaPrva, "wrap");
		panelReklama.add(labelaDruga);
	}
}
