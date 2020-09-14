package view;

import java.text.DateFormat;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controler.Constants;
import model.Recenzija;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public class IzvestajRecenzije extends MojDialog {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Recenzija recenzija;
	private String title;
	private DateFormat df = Constants.NATASIN_FORMAT_ZA_DATUM;

	private JTextField tfImeUrednika, tfNaslov, tfdatum, tfDelo, tfIzvodjac;

	public IzvestajRecenzije(Sesija s, Recenzija r, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.title = title;
		this.setSesija(s);
		this.recenzija = r;
		setSize(400, 400);
		setResizable(false);
		initGui();
	}

	/**
	 * @return the sesija
	 */
	public Sesija getSesija() {
		return sesija;
	}

	/**
	 * @param sesija the sesija to set
	 */
	public void setSesija(Sesija sesija) {
		this.sesija = sesija;
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]20[]");
		setLayout(mig);
		
		add(new JLabel("Urednik: "));
		tfImeUrednika = new JTextField(20);
		add(tfImeUrednika);
		tfImeUrednika.setText(recenzija.getUrednik().getIme()+" "+recenzija.getUrednik().getPrezime());
		tfImeUrednika.setEditable(false);
		
		add(new JLabel("Naslov"));
		tfNaslov = new JTextField(20);
		add(tfNaslov);
		tfNaslov.setText(recenzija.getNaslov());
		tfNaslov.setEditable(false);
		
		add(new JLabel("Datum upisa recenzije"));
		tfdatum = new JTextField(20);
		add(tfdatum);
		tfdatum.setText(df.format(recenzija.getDatumUpisa())+"");
		tfdatum.setEditable(false);
		
		add(new JLabel("Naziv dela"));
		tfDelo = new JTextField(20);
		add(tfDelo);
		tfDelo.setText(recenzija.getDelo().getNaslov()+"");
		tfDelo.setEditable(false);
		
		add(new JLabel("Izvodjac")); //ovo menjati!!! jer moze biti vise izvodjaca
		tfIzvodjac = new JTextField(20);
		add(tfIzvodjac);
		tfIzvodjac.setText(recenzija.getDelo().getIzvodjac().getUmetnickoIme()+"");
		tfIzvodjac.setEditable(false);
		
		//prikaz teksta recenzije,, broja kon=mentara ocena, komentara.....
		
		setVisible(true);
		
		}
}
