package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import model.Recenzija;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public class IzvestajRecenzije extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Recenzija recenzija;
	private JButton btnBack;

	private JTextField tfImeUrednika, tfNaslov, tfdatum, tfDelo, tfIzvodjac;

	public IzvestajRecenzije(Sesija s, Recenzija r) {
		this.setSesija(s);
		this.recenzija = r;
		setSize(400, 400);
		setResizable(false);
		initGui();
		initActions();
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

	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent..
				IzvestajRecenzije.this.dispose();
				
			}
		});
		
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]20[]");
		setLayout(mig);
		
		add(new JLabel("Urednik: "));
		tfImeUrednika = new JTextField(20);
		add(tfImeUrednika);
		tfImeUrednika.setText(recenzija.getUrednik().getIme()+" "+recenzija.getUrednik().getPrezime());
		
		add(new JLabel("Naslov"));
		tfNaslov = new JTextField(20);
		add(tfNaslov);
		tfNaslov.setText(recenzija.getNaslov());
		
		add(new JLabel("Datum upisa recenzije"));
		tfdatum = new JTextField(20);
		add(tfdatum);
		tfdatum.setText(recenzija.getDatumUpisa()+"");
		
		add(new JLabel("Naziv dela"));
		tfDelo = new JTextField(20);
		add(tfDelo);
		tfDelo.setText(recenzija.getDelo().getNaziv()+"");
		
		add(new JLabel("Izvodjac")); //ovo menjati!!! jer moze biti vise izvodjaca
		tfIzvodjac = new JTextField(20);
		add(tfIzvodjac);
		tfIzvodjac.setText(recenzija.getDelo().getNaziv()+"");
		btnBack=new JButton("Nazad");
		add(btnBack);
		
		//prikaz teksta recenzije,, broja kon=mentara ocena, komentara.....
		
		}
}
