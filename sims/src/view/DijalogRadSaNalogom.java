package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import model.Korisnik;
import net.miginfocom.swing.MigLayout;

public class DijalogRadSaNalogom extends MojDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel imeLabela, prezLabela, mailLabela, polLabela, dateLabela, sifraLabela, userLabela;
	private MojTextField poljeIme, poljePrez, poljeMail, poljePol, poljeDate, poljeSifra, poljeUser;
	private JButton izadjiBtn, potvrdiBtn;
	private Korisnik korisnik;

	public DijalogRadSaNalogom(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}

	public DijalogRadSaNalogom(JFrame parent, String naziv) {
		super(parent, naziv);
		// TODO Auto-generated constructor stub
	}

	public DijalogRadSaNalogom(String ime, int dimension1, int dimension2) {
		super(ime, dimension1, dimension2);
		// TODO Auto-generated constructor stub
	}
	
	public DijalogRadSaNalogom(JFrame parent, Korisnik korisnik, String naziv) {
		super(parent, naziv);
		this.korisnik = korisnik;
		initGui();
		setListeners();
	}

	private void initGui() {
		this.setSize(450, 380);
		MigLayout mig = new MigLayout("",
									  "50 [] 50 []", //Kolone
									  "25 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10 [] 10"); //Vrste
		this.setLayout(mig);
		
		imeLabela  = new JLabel("Ime"); prezLabela = new JLabel("Prezime"); 		mailLabela = new JLabel("eMail");
		polLabela  = new JLabel("Pol"); dateLabela = new JLabel("Datum Rodjenja"); 	sifraLabela = new JLabel("Sifra");
		userLabela = new JLabel("Korisnicko ime");
		
		poljeIme   = new MojTextField(20); poljeIme.setText(korisnik.getIme()); 
		poljePrez  = new MojTextField(20); poljePrez.setText(korisnik.getPrezime());
		poljeMail  = new MojTextField(20); poljeMail.setText(korisnik.geteMail());
		poljePol   = new MojTextField(20); poljePol.setText(korisnik.getPol());
		poljeDate  = new MojTextField(20); poljeDate.setText(korisnik.getDatumRodjenja());
		poljeUser =  new MojTextField(20); poljeUser.setText(korisnik.getNalog().getKorisnickoIme());
		
		poljeSifra = new MojTextField(20, true); poljeSifra.setText(korisnik.getNalog().getSifra());
		
		izadjiBtn  = new JButton("Izadji");
		potvrdiBtn = new JButton("Potvrdi");
		
		this.add(imeLabela);   this.add(poljeIme,   "wrap");
		this.add(prezLabela);  this.add(poljePrez,  "wrap");
		this.add(mailLabela);  this.add(poljeMail,  "wrap");
		this.add(polLabela);   this.add(poljePol,   "wrap");
		this.add(dateLabela);  this.add(poljeDate,  "wrap");
		this.add(userLabela);  this.add(poljeUser,  "wrap");
		this.add(sifraLabela); this.add(poljeSifra, "wrap");
		
		this.add(izadjiBtn);
		this.add(potvrdiBtn);
	}
	
	void setListeners() {
		izadjiBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DijalogRadSaNalogom.this.dispose();
			}
		});
		
		potvrdiBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(poljeSifra.getText().equals("")) {
					JOptionPane.showMessageDialog(DijalogRadSaNalogom.this, "Popunite sva polja", "Prazna polja", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if(!(korisnik.getNalog().getSifra().equals(poljeSifra.getText()))) {
				
					korisnik.getNalog().setSifra(poljeSifra.getText());
					System.out.println(korisnik.getNalog().getSifra());
					
					JOptionPane.showMessageDialog(DijalogRadSaNalogom.this, "Uspesno ste izmenili sifru", "Izmena sifre", JOptionPane.INFORMATION_MESSAGE);
				}
				else
				{
					JOptionPane.showMessageDialog(DijalogRadSaNalogom.this, "Nista niste menjali", "Izmena", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
}
