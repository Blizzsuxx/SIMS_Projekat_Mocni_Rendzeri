package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Izvodjac;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class DodajMuzickoDelo extends JDialog {
	public Sesija sesija;
	public Izvodjac izv;
	public JTextField naziv, opis, datum;
	public String naslov, opisDela, datumIzdavanja;
	public JButton btnNazad, btnNapravi;
	public JComboBox izvodjaci;
	public int br; //ako se ovo poziva iz pravljenja albuma onda je 0, tj ima Izvodjaca, ako je 1 nema i ide combo
	public DodajMuzickoDelo(Sesija sesija, Izvodjac izv, int br) {
		super();
		this.sesija = sesija;
		this.izv = izv;
		if(br==1) {
			izvodjaci=new JComboBox(sesija.izvadiImenaIzvodjaca());
			izvodjaci.setSelectedIndex(0);
		}
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(400,400);
		setTitle("Dodavanje muzickog dela");
		initGui();
		initActions();
	}
	private void initActions() {
		btnNapravi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				datumIzdavanja= datum.getText();
				naslov=naziv.getText();
				opisDela=opis.getText();
				if(br==1) {
					izv=sesija.getIzvodjac((String) izvodjaci.getSelectedItem());
				}
				boolean tf=sesija.napraviDelo(datumIzdavanja, naslov, opisDela, izv,new ArrayList<Zanr>()); //TODO treba ovu listu namestiti
				if(!tf) {
					JOptionPane.showMessageDialog(DodajMuzickoDelo.this, "Datum nije dobar", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					JOptionPane.showMessageDialog(DodajMuzickoDelo.this, "Dodali ste novo muzicko delo", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		btnNazad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodajMuzickoDelo.this.dispose();
				
			}
		});
		
	}
	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]");
		setLayout(mig);
		add(new JLabel("Naziv: "));
		naziv=new JTextField(10);
		add(naziv);
		
		add(new JLabel("Opis: "));
		opis=new JTextField(10);
		add(opis);
	
		add(new JLabel("Datum izdavanja u obliku dd.mm.yyyy.: "));
		datum=new JTextField(10);
		add(datum);
		btnNapravi=new JButton("Dodaj delo");
		add(btnNapravi);
		btnNazad=new JButton("Nazad");
		add(btnNazad);
		
	}
	
	
	

}
