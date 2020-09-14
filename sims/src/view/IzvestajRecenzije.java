package view;

import java.text.DateFormat;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controler.Constants;
import model.Recenzija;
import model.Sesija;

public class IzvestajRecenzije extends MojDialog {
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Recenzija recenzija;
	private String title;
	private DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;
	private static DecimalFormat decimalFormat = new DecimalFormat("0.00");
	private JTextField tfImeUrednika;
	private JTextField tfNaslov;
	private JTextField tfdatum;
	private JTextField tfDelo;
	private JTextField tfIzvodjac;
	private JTextField tfOcenaKorisnika;
	private JTextField tfOcenaUrednika;
	private JTextField tfBrojKomentara;

	public IzvestajRecenzije(Sesija s, Recenzija r, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.title = title;
		this.sesija = s;
		this.recenzija = r;
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblUrednik = new JLabel("Urednik");
		lblUrednik.setBounds(10, 15, 50, 14);
		getContentPane().add(lblUrednik);
		
		tfImeUrednika = new JTextField();
		tfImeUrednika.setEditable(false);
		tfImeUrednika.setBounds(144, 8, 494, 29);
		getContentPane().add(tfImeUrednika);
		tfImeUrednika.setColumns(10);
		tfImeUrednika.setText(recenzija.getUrednik().getIme()+" "+recenzija.getUrednik().getPrezime());
		
		JLabel lblNaslov = new JLabel("Naslov");
		lblNaslov.setBounds(10, 57, 48, 14);
		getContentPane().add(lblNaslov);
		
		tfNaslov = new JTextField();
		tfNaslov.setEditable(false);
		tfNaslov.setColumns(10);
		tfNaslov.setBounds(144, 50, 494, 29);
		tfNaslov.setText(recenzija.getNaslov());
		getContentPane().add(tfNaslov);
		
		JLabel lblDatumUpisaRecenzije = new JLabel("Datum upisa recenzije");
		lblDatumUpisaRecenzije.setBounds(10, 108, 124, 14);
		getContentPane().add(lblDatumUpisaRecenzije);
		
		tfdatum = new JTextField();
		tfdatum.setEditable(false);
		tfdatum.setColumns(10);
		tfdatum.setBounds(144, 101, 494, 29);
		tfdatum.setText(df.format(recenzija.getDatumUpisa())+"");
		getContentPane().add(tfdatum);
		
		JLabel lblNazivDela = new JLabel("Naziv dela");
		lblNazivDela.setBounds(10, 162, 65, 14);
		getContentPane().add(lblNazivDela);
		
		tfDelo = new JTextField();
		tfDelo.setEditable(false);
		tfDelo.setColumns(10);
		tfDelo.setBounds(144, 153, 494, 29);
		tfDelo.setText(recenzija.getDelo().getNaslov()+"");
		getContentPane().add(tfDelo);
		
		JLabel lblIzvodjac = new JLabel("Izvodjac");
		lblIzvodjac.setBounds(10, 216, 50, 14);
		getContentPane().add(lblIzvodjac);
		
		tfIzvodjac = new JTextField();
		tfIzvodjac.setEditable(false);
		tfIzvodjac.setColumns(10);
		tfIzvodjac.setBounds(144, 205, 494, 29);
		tfIzvodjac.setText(recenzija.getDelo().getIzvodjac().getUmetnickoIme()+"");
		getContentPane().add(tfIzvodjac);
		
		JLabel lblTekst = new JLabel("Tekst");
		lblTekst.setBounds(10, 260, 48, 14);
		getContentPane().add(lblTekst);
		
		JTextArea taTekst = new JTextArea();
		taTekst.setEditable(false);
		taTekst.setBounds(144, 260, 494, 86);
		taTekst.setText(recenzija.getText());
		getContentPane().add(taTekst);
		
		JLabel lblOcenaKorisnika = new JLabel("Ocena korisnika");
		lblOcenaKorisnika.setBounds(10, 442, 92, 14);
		getContentPane().add(lblOcenaKorisnika);
		
		tfOcenaKorisnika = new JTextField();
		tfOcenaKorisnika.setEditable(false);
		tfOcenaKorisnika.setColumns(10);
		tfOcenaKorisnika.setBounds(144, 435, 494, 29);
		tfOcenaKorisnika.setText(decimalFormat.format(recenzija.getDelo().getProsecnaOcenaKorisnika()) + "");
		getContentPane().add(tfOcenaKorisnika);
		
		JLabel lblOcenaUrednika = new JLabel("Ocena urednika");
		lblOcenaUrednika.setBounds(10, 501, 92, 14);
		getContentPane().add(lblOcenaUrednika);
		
		tfOcenaUrednika = new JTextField();
		tfOcenaUrednika.setEditable(false);
		tfOcenaUrednika.setColumns(10);
		tfOcenaUrednika.setBounds(144, 494, 494, 29);
		tfOcenaUrednika.setText(decimalFormat.format(recenzija.getDelo().getProsecnaOcenaUrednika()) + "");
		getContentPane().add(tfOcenaUrednika);
		
		JLabel lblBrojKomentara = new JLabel("Broj komentara");
		lblBrojKomentara.setBounds(10, 388, 92, 14);
		getContentPane().add(lblBrojKomentara);
		
		tfBrojKomentara = new JTextField();
		tfBrojKomentara.setEditable(false);
		tfBrojKomentara.setColumns(10);
		tfBrojKomentara.setBounds(144, 381, 494, 29);
		tfBrojKomentara.setText(recenzija.getDelo().getKomentari().size() + "");
		getContentPane().add(tfBrojKomentara);
		
		setVisible(true);
	}
}