package view;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.KorisniciMenadzer;
import model.Administrator;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Nalog;
import model.Pol;
import model.Sesija;
import model.Uloga;
import model.Urednik;
import model.Zanr;

public class KorisnikAddEdit extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public Sesija sesija;
	private JTextField txtIme;
	private JTextField txtPrezime;
	private JTextField txtEmail;
	private SpringLayout sl_dtDob;
	private JTextField txtKorisnickoIme;
	private JTextField txtLozinka;
	private JRadioButton rbMusko;
	private JRadioButton rbZensko;
	private JDatePickerImpl dtDob;
	private Uloga uloga;
	private JPanel panelBase;
	
	private List<Korisnik> korisnici; // privremena kolekcija u kojoj su isti entiteti koji se nalaze u sesiji
	
	// ova dva atiributa su bitna prilikom kreiranja urednika da mu se dodjele zanrovi!
	private ComboZanr cz;
	private List<Zanr> izabraniZanrovi;
	
	public KorisnikAddEdit(String naslov, Uloga uloga, Sesija sesija, List<Korisnik> korisnici) {
		this.sesija = sesija;
		this.uloga = uloga;
		this.korisnici = korisnici;
		
		if (uloga == null) // u slucaju kada ovaj prozor pozivamo iz statusa korisnici
			uloga = Uloga.KORISNIK;
		
		this.setSize(450, 450);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		setBackground(Color.BLACK);
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
		
		setTitle(naslov);
		getContentPane().setLayout(null);
		
		panelBase = new JPanel();
		panelBase.setBounds(10, 11, 402, 181);
		getContentPane().add(panelBase);
		panelBase.setLayout(null);
		
		JLabel lblIme = new JLabel("Ime:");
		lblIme.setBounds(10, 24, 40, 14);
		panelBase.add(lblIme);
		
		txtIme = new JTextField();
		txtIme.setBounds(95, 21, 292, 20);
		panelBase.add(txtIme);
		txtIme.setColumns(10);
		
		JLabel lblPrezime = new JLabel("Prezime:");
		lblPrezime.setBounds(10, 56, 48, 14);
		panelBase.add(lblPrezime);
		
		txtPrezime = new JTextField();
		txtPrezime.setBounds(95, 52, 292, 20);
		panelBase.add(txtPrezime);
		txtPrezime.setColumns(10);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(10, 87, 48, 14);
		panelBase.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(95, 83, 292, 20);
		panelBase.add(txtEmail);
		txtEmail.setColumns(10);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtDob = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDob = new SpringLayout();
		sl_dtDob.putConstraint(SpringLayout.NORTH, dtDob.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDob);
		sl_dtDob.putConstraint(SpringLayout.WEST, dtDob.getJFormattedTextField(), 33, SpringLayout.WEST, dtDob);
		sl_dtDob.putConstraint(SpringLayout.EAST, dtDob.getJFormattedTextField(), 211, SpringLayout.WEST, dtDob);
		sl_dtDob = (SpringLayout) dtDob.getLayout();
		dtDob.setBounds(95, 115, 292, 20);
		panelBase.add(dtDob);
		
		JLabel lblDatumRodjenja = new JLabel("Datum rodjenja:");
		lblDatumRodjenja.setBounds(10, 120, 79, 14);
		panelBase.add(lblDatumRodjenja);
		
		rbMusko = new JRadioButton("Musko");
		rbMusko.setSelected(true);
		rbMusko.setBounds(10, 150, 68, 23);
		panelBase.add(rbMusko);
		
		rbZensko = new JRadioButton("Zensko");
		rbZensko.setBounds(125, 150, 109, 23);
		panelBase.add(rbZensko);
		
		ButtonGroup poloviGroup = new ButtonGroup();
		poloviGroup.add(rbMusko);
		poloviGroup.add(rbZensko);
		
		JPanel pnlKorisnik = new JPanel();
		pnlKorisnik.setBounds(10, 203, 402, 106);
		getContentPane().add(pnlKorisnik);
		pnlKorisnik.setLayout(null);
		
		JLabel lblKorisnickoIme = new JLabel("Korisnicko ime:");
		lblKorisnickoIme.setBounds(10, 12, 74, 14);
		pnlKorisnik.add(lblKorisnickoIme);
		
		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setBounds(94, 9, 292, 20);
		pnlKorisnik.add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);
		
		JLabel lblLozinka = new JLabel("Lozinka:");
		lblLozinka.setBounds(10, 43, 40, 14);
		pnlKorisnik.add(lblLozinka);
		
		txtLozinka = new JTextField();
		txtLozinka.setBounds(94, 40, 292, 20);
		pnlKorisnik.add(txtLozinka);
		txtLozinka.setColumns(10);
		
		if (uloga == Uloga.UREDNIK) { // Mozdaa se moze bolje pozicionirati, al me mrzilo :D
			JLabel lblZanrovi = new JLabel("Zanrovi:");
			lblZanrovi.setBounds(10, 73, 50, 14);
			pnlKorisnik.add(lblZanrovi);
			
			cz = new ComboZanr();
			cz.kreirajSadrzaj(sesija.getZanroviMenadzer().vratiAktivneZanrove());
			izabraniZanrovi = new ArrayList<>();
			cz.setBounds(94, 73, 300, 140);
			pnlKorisnik.add(cz);
		}
		
		
		JButton btnKreiraj = new JButton("Kreiraj");
		btnKreiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					kreirajKorisnika();
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnKreiraj.setBounds(323, 333, 89, 23);
		getContentPane().add(btnKreiraj);
	
	}
	
	private void kreirajKorisnika() throws ParseException {
		String msg = validiraj();
		
		if (!msg.equals(""))
		{
			JOptionPane.showMessageDialog(null, msg);
			return;
		}
		String korisnickoIme = txtKorisnickoIme.getText().trim();
		if (!sesija.getKorisnici().provjeriKorisnickoIme(korisnickoIme)) { // znaci ne postoji isto korisnicko ime
			String ime = txtIme.getText().trim();
			String prezime = txtPrezime.getText().trim();
			String eMail = txtEmail.getText().trim();
		
			String dob = dtDob.getJFormattedTextField().getText(); // string trenutno u formi dd-MM-yyyy
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
			String dob2 = sdf2.format(sdf1.parse(dob)); // string trenutno u formi dd/MM/yyyy i moguce je parsirati u datum
		
			Pol pol = null;
			if (rbMusko.isSelected())
				pol = Pol.muski;
			else if (rbZensko.isSelected())
				pol = Pol.zenski;
		
			String lozinka = txtLozinka.getText().trim();
			Korisnik k;
			Nalog n = new Nalog(korisnickoIme, lozinka, new Date(), true);
			if (uloga == Uloga.KORISNIK) {
				k = new KorisnikAplikacije(ime, prezime, eMail, pol, new SimpleDateFormat("dd/MM/yyyy").parse(dob2), lozinka, korisnickoIme, new Date(), true);
			}
			else if (uloga == Uloga.UREDNIK) {
				izabraniZanrovi.clear();
				cz.vratiSelektovaneZanrove(izabraniZanrovi); // I ONDA OVE IZABRANE ZANROVE DODJELIMO UREDNIKU
				k = new Urednik(ime, prezime, eMail, pol, new SimpleDateFormat("dd/MM/yyyy").parse(dob2), lozinka, korisnickoIme, new Date(), true);
				((Urednik)k).setZanrovi(izabraniZanrovi);
			}
			else {
				k = new Administrator(ime, prezime, eMail, pol, new SimpleDateFormat("dd/MM/yyyy").parse(dob2), lozinka, korisnickoIme, new Date(), true);
			}
			k.setNalog(n);
			KorisniciMenadzer km = sesija.getKorisnici();
			km.dodaj(k);
			sesija.setKorisnici(km);
			korisnici.add(k); // dodavanje u privremenu kolekciju
		
			this.dispose();  
		}
	}
	
	private String validiraj() {
		if (txtIme.getText().isEmpty()) {
			return "Ime je obavezno polje.";
		}
		if (txtPrezime.getText().isEmpty()) {
			return "Prezime je obavezno polje.";
		}
		if (txtEmail.getText().isEmpty()) {
			return "Email je obavezno polje.";
		}
		if (txtKorisnickoIme.getText().isEmpty()) {
			return "Korisnicko ime je obavezno polje.";
		}
		if (txtLozinka.getText().isEmpty()) {
			return "Lozinka je obavezno polje.";
		}
		if (dtDob.getJFormattedTextField().getText().isEmpty()) {
			return "Datum nije unet.";
		}
		return "";
	}
}
