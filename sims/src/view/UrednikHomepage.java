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

public class UrednikHomepage extends Homepage {
	 private static final long serialVersionUID = 1L;

	 private JMenu listaMenu, korisniciMenu;
	 private JMenuItem listaItem1, listaItem2, listaItem3, listaItem4,
	 korisniciItem1, korisniciItem2, korisniciItem3, korisniciItem4, korisniciItem5, korisniciItem6,
	 korisniciItem7;
	 
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
		listaMenu = new JMenu("Liste");
		    	
		listaItem1 = new JMenuItem("Top Liste");
		listaMenu.add(listaItem1);
		listaItem2 = new JMenuItem("Recenzirani Sadrzaj");
		listaMenu.add(listaItem2);
		listaMenu.addSeparator();
		listaItem3 = new JMenuItem("Istorija Recenzija");
		listaMenu.add(listaItem3);
		listaItem4 = new JMenuItem("Istorija Zakazanih Recenzija");
		listaMenu.add(listaItem4);
		menubar.add(listaMenu);
		    	
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
	}
	    
	private void initAction() {
		listaItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					
			}

				
	    		
	    });
	    	
	    listaItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
			}
	    		
	    });
	    
	    listaItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    listaItem4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
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
	}

}