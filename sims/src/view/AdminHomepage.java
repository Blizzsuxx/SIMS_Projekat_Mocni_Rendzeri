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

	private JMenu korisniciMenu, izvodjaciMenu;
	private JMenuItem korisniciItem1, korisniciItem2, korisniciItem3, korisniciItem4,
	izvodjaciItem1, izvodjaciItem2;
	
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
		menubar.add(korisniciMenu);
		
		izvodjaciMenu = new JMenu("Izvodjaci");
		
		izvodjaciItem1 = new JMenuItem("Neprihvaceni Izvodjaci");
		izvodjaciMenu.add(izvodjaciItem1);
		izvodjaciItem2 = new JMenuItem("Prihvaceni Izvodjaci");
		izvodjaciMenu.add(izvodjaciItem2);
		menubar.add(izvodjaciMenu);
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
	}
}
