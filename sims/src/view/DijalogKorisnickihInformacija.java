package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXDatePicker;

import controler.Constants;
import model.Korisnik;
import model.Pol;

public class DijalogKorisnickihInformacija extends MojDialog {
	JPasswordField sifra;
	JTextField username, ime, prezime, email;
	JXDatePicker datumRodjenja;
	JRadioButton muski, zenski;
	ButtonGroup pol;

	Korisnik korisnik;
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private void addLabel(String tekst){
		this.add(new JLabel(tekst));
	}
	
	private void init(Korisnik korisnik) {
		this.korisnik = korisnik;
		//JLabel usernamel, imel, prezimel, emaill, datumRodjenjal, poll, sifral;
		

		this.addLabel("Korisnicko Ime: ");
		username = new JTextField(20);
		this.add(username, "wrap");

		this.addLabel("Sifra: ");
		sifra = new JPasswordField(20);
		this.add(sifra, "wrap");

		this.addLabel("Ime");
		ime = new JTextField(20);
		this.add(ime, "wrap");

		this.addLabel("Prezime: ");
		prezime = new JTextField(20);
		this.add(prezime, "wrap");

		this.addLabel("Email: ");
		email = new JTextField(20);
		this.add(email, "wrap");

		this.addLabel("Datum rodjenja: ");
		datumRodjenja = new JXDatePicker();
		datumRodjenja.setFormats(Constants.FORMAT_ZA_DATUM);
		this.add(datumRodjenja, "wrap");

		this.addLabel("Pol: ");
		muski = new JRadioButton();
		zenski = new JRadioButton();
		pol = new ButtonGroup();
		pol.add(muski);
		pol.add(zenski);
		muski.setSelected(true);
		this.add(muski);
		this.add(zenski, "wrap");

		JButton registruj = new JButton("Registruj se");
		registruj.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				registruj();
				
			}
		});
		this.add(registruj);

	}
	
	
	protected void registruj() {
		
		String tekst;

		tekst = username.getText();
		if(tekst == null || tekst.trim().equals("")){
			throw new RuntimeException("polje username nije popunjeno");
		}
		korisnik.setKorisnickoIme(tekst);

		tekst = sifra.getText();
		if(tekst == null || tekst.trim().equals("")){
			throw new RuntimeException("polje sifra nije popunjeno");
		}
		korisnik.setSifra(tekst);

		tekst = ime.getText();
		if(tekst == null || tekst.trim().equals("")){
			throw new RuntimeException("polje ime nije popunjeno");
		}
		korisnik.setIme(tekst);

		tekst = prezime.getText();
		if(tekst == null || tekst.trim().equals("")){
			throw new RuntimeException("polje prezime nije popunjeno");
		}
		korisnik.setPrezime(tekst);

		tekst = email.getText();
		if(tekst == null || tekst.trim().equals("")){
			throw new RuntimeException("polje email nije popunjeno");
		}
		korisnik.seteMail(tekst);

		Date datum = datumRodjenja.getDate();
		if(datum == null){
			throw new RuntimeException("polje datum rodjenja nije popunjeno");
		}
		korisnik.setDatumRodjenja(datum);

		if(muski.isSelected()){
			korisnik.setPol(Pol.muski);
		} else {
			korisnik.setPol(Pol.zenski);
		}

		korisnik.setStatus(true);
		
	}
	



	public DijalogKorisnickihInformacija(JFrame parent, String ime, int dimension1, int dimension2, Korisnik korisnik) {
		super(parent, ime, dimension1, dimension2);

		   init(korisnik);
		
	}
	
	public DijalogKorisnickihInformacija(JFrame parent, Korisnik korisnik) {
		this(parent, "Registrovanje", 450, 300, korisnik);
		
	}
	
	public DijalogKorisnickihInformacija(Korisnik korisnik) {
		this(null, korisnik);
	}
}
