package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.html.HTMLDocument;

import model.Sesija;
import net.miginfocom.swing.MigLayout;

public abstract class Homepage extends JFrame{
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	protected JMenu menu;
	protected JMenuBar menubar;
	
	 protected String[] koloneSvihKorisnika = {"Korisnicko Ime", "Ime", "Prezime", "Email", "Pol", "Datum Rodjenja"};
	 protected String[] koloneSvihObicnihKorisnika = {"Korisnicko Ime", "Ime", "Prezime", "Email", "Pol", "Datum Rodjenja",
			 "Broj Korisnika", "Broj Izvodjaca"};
	 protected String[] koloneSvihUrednika = {"Korisnicko Ime", "Ime", "Prezime", "Email", "Pol", "Datum Rodjenja",
			 "Broj Recenzija"};
	 protected String[] koloneSvihAdmina = {"Korisnicko Ime", "Ime", "Prezime", "Email", "Pol", "Datum Rodjenja",
			 "Broj Zakazanih", "Broj Za Izmjenu"};
	 
	 protected String[] koloneMuzickogSadrzaja = {"Naslov", "Opis", "Datum Izdavanja", "Izvodjac", "Urednik"};
	 protected String[] koloneAlbuma = {"Naslov", "Opis", "Datum Izdavanja", "Izvodjac", "Urednik", "Odobren"};
	 protected String[] koloneMuzickihDela = {"Naslov", "Opis", "Datum Izdavanja", "Izvodjac", "Urednik", 
			 "P.O. Korisnika", "P.O. Urednika"};
	 
	 protected String[] koloneTopListi = {"Naslov", "Korisnicko Ime", "Broj Muzickog Sadrzaja", "Status"};
	 
	 protected JPanel panelReklama = new JPanel(new MigLayout());
	 protected ImageIcon reklamaPrva, obradjenaReklamaPrva, reklamaDruga, obradjenaReklamaDruga;
	 
	public Homepage(Sesija sesija) {
		this.getContentPane().setLayout(new MigLayout("fillx"));
		this.sesija = sesija;
		initGui();
	}



	private void initGui(){
		this.setSize(550, 600);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("Pocetna strana");

		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);

		menu = new JMenu("Meni");
		menubar = new JMenuBar();
		JMenuItem profil = new JMenuItem("Profil");
		JMenuItem odjava = new JMenuItem("Odjava");
		menu.add(profil);
		menu.add(odjava);
		menubar.add(menu);
		

		
		SearchField search = new SearchField(sesija, this);
		
		
		
		this.add(search, "north");
		

		profil.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuProfilTriggered();
			}
		});

		odjava.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				menuOdjavaTriggered();
			}
		});

		this.setJMenuBar(menubar);
		
		reklamaPrva = new ImageIcon(this.sesija.getReklameMenadzer().getPutanjaPrveReklame());
		obradjenaReklamaPrva = new ImageIcon(reklamaPrva.getImage().getScaledInstance(528, 100, Image.SCALE_DEFAULT));
		reklamaDruga = new ImageIcon(this.sesija.getReklameMenadzer().getPutanjaDrugeReklame());
		obradjenaReklamaDruga = new ImageIcon(reklamaDruga.getImage().getScaledInstance(528, 100, Image.SCALE_DEFAULT));
		JLabel labelaPrva = new JLabel(obradjenaReklamaPrva);
		JLabel labelaDruga = new JLabel(obradjenaReklamaDruga);
		panelReklama.add(labelaPrva, "wrap");
		panelReklama.add(labelaDruga);
		 this.add(panelReklama, "south");
	}



	public Sesija getSesija() {
		return sesija;
	}

	private void menuOdjavaTriggered() {
		// TODO Auto-generated method stub
		this.dispose();
	}

	private void menuProfilTriggered() {
		//Pregled profila
		
		//DUMMY - Hardcoded KorisnikAplikacije
		//sesija.getTrenutniKorisnik()
		if(Sesija.getTrenutniKorisnik() == null)
		{
			JOptionPane.showMessageDialog(this, "Da bi ste pregledali svoj profil morate imati nalog.", "Profil", JOptionPane.INFORMATION_MESSAGE);
		}
		else 
		{
			DijalogRadSaNalogom radSaNalogom = new DijalogRadSaNalogom(this, Sesija.getTrenutniKorisnik(), "Rad sa nalogom");
			radSaNalogom.setVisible(true);
		}	
	}

}
