package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Sesija;
import model.Uloga;

public class AdminHomepage extends Homepage {
	private static final long serialVersionUID = 1L;

	private JMenu korisniciMenu, izvodjaciMenu, zanroviMenu, recenzijeMenu;
	private JMenuItem korisniciItem1, korisniciItem2, korisniciItem3, korisniciItem4, korisniciItem5,
	izvodjaciItem1, izvodjaciItem2, izvodjaciItem3, zanroviItem1, recenzijeItem1, recenzijeItem2, recenzijeItem3;
	
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
		korisniciMenu = new JMenu("Korisnici");    	
		korisniciItem1 = new JMenuItem("Korisnici");
		korisniciMenu.add(korisniciItem1);
		korisniciItem2 = new JMenuItem("Korisnici Aplikacije");
		korisniciMenu.add(korisniciItem2);
		korisniciItem3 = new JMenuItem("Urednici");
		korisniciMenu.add(korisniciItem3);
		korisniciItem4 = new JMenuItem("Admini");
		korisniciMenu.add(korisniciItem4);
		korisniciItem5 = new JMenuItem("Nalozi");
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
		
	}
	
	private void actionGUI() {
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
					new BlokiranjeNaloga(AdminHomepage.this.getSesija(), "Blokiranje naloga", 450, 300);
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
					new DodavanjeZakRecIRecZaIzemnu(AdminHomepage.this.getSesija(), "Narucivanje recenzija", 415, 500);
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
	}
}
