package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Administrator;
import model.Korisnik;
import model.KorisnikAplikacije;
import model.Nalog;
import model.Pol;
import model.Uloga;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class KorisnikAddEdit extends MojDialog {
	private static final long serialVersionUID = 1L;

	private JTextField tfKorIme, tfSifra, tfIme, tfPrezime, tfMail, tfDatumRodjenja;
	private JRadioButton musko, zensko;
	private ButtonGroup grupa;
	private JButton btnOk, btnCancel;
	
	private Pol p;
	private Uloga indikator;
	
	public KorisnikAddEdit(JDialog parent, String nazivProzora, Uloga indikator) {
		super(nazivProzora, 400, 400);
	
		this.indikator = indikator;
		
		this.initGUI();
		this.actionGUI();
		this.pack();
	}

	private void initGUI() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]10[]20[]");
		setLayout(mig);
		
		add(new JLabel("Korisnicko ime:"));
		tfKorIme = new JTextField(10);
		add(tfKorIme);
		
		add(new JLabel("Lozinka:"));
		tfSifra = new JTextField(10);
		add(tfSifra);
		
		add(new JLabel("Ime:"));
		tfIme = new JTextField(10);
		add(tfIme);
		
		add(new JLabel("Prezime:"));
		tfPrezime = new JTextField(10);
		add(tfPrezime);
		
		add(new JLabel("E-Mail:"));
		tfMail = new JTextField(10);
		add(tfMail);
		
		add(new JLabel("Datum rodjenja (dd-MM-yyyy):"));
		tfDatumRodjenja = new JTextField(10);
		add(tfDatumRodjenja);
		
		grupa = new ButtonGroup();
		musko = new JRadioButton("Musko");
		zensko = new JRadioButton("Zensko");
		grupa.add(musko);
		grupa.add(zensko);
		musko.setSelected(true);
		add(new JLabel("Pol:"));
		add(musko, "split 2");
		add(zensko);
		
		btnOk = new JButton("Ok");
		btnCancel = new JButton("Cancel");
		add(new JLabel());
		add(btnOk, "split 2");
		add(btnCancel);
	}
	
	private void actionGUI() {
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String korisnickoIme = tfKorIme.getText().trim();
				String lozinka = tfSifra.getText().trim();
				String ime = tfIme.getText().trim();
				String prezime = tfPrezime.getText().trim();
				String eMail = tfMail.getText().trim();
				Date datumRodjenja = null;
				try {
					 datumRodjenja = new SimpleDateFormat("dd-MM-yyyy").parse(tfDatumRodjenja.getText().trim());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if (musko.isSelected())
					p = Pol.muski;
				else if (zensko.isSelected())
					p = Pol.zenski;
				
				Korisnik k;
				Nalog n = new Nalog(korisnickoIme, lozinka, new Date(), true);
				if (indikator == Uloga.ADMIN) {
					k = new Administrator(ime, prezime, eMail, p, datumRodjenja, lozinka, korisnickoIme, new Date(), true);
				} else if (indikator == Uloga.KORISNIK) {
					k = new KorisnikAplikacije(ime, prezime, eMail, p, datumRodjenja, lozinka, korisnickoIme, new Date(), true);
				} else { // UREDNIK
					k = new Urednik(ime, prezime, eMail, p, datumRodjenja, lozinka, korisnickoIme, new Date(), true);
				}
				// DOSAO DO DODAVANJE MENADZERA U GUI-ju
			}
			
		});
		
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				KorisnikAddEdit.this.dispose();
				
			}
			
		});
	}
}
