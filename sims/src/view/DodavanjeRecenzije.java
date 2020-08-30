package view;

import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Izvodjac;
import model.MuzickoDjelo;
import model.Recenzija;
import model.Sesija;
import model.Urednik;
import net.miginfocom.swing.MigLayout;


public class DodavanjeRecenzije extends JFrame{
	public Urednik urednik;
	public JTextField naslov;
	public JTextArea text;
	public JButton btnZavrsi, btnBack;
	public Recenzija novaRecenzija;
	public JComboBox listaizvodjaca;
	public JComboBox listaDela;
	public String[] imenaIzvodjaca, imenaDela;
	public Sesija sesija;
	public int oznaka;
	public MuzickoDjelo deloo; 
	public Izvodjac izvodjacc;
	public DodavanjeRecenzije(Urednik urednik, Sesija sesija) throws HeadlessException {
		super();
		oznaka=1;
		this.urednik = urednik;
		this.sesija=sesija;
		novaRecenzija=new Recenzija(urednik, "");
		imenaIzvodjaca=sesija.izvadiImenaIzvodjaca();
		listaizvodjaca=new JComboBox(imenaIzvodjaca);
		imenaDela = new String[1]; imenaDela[0]="";
		listaDela=new JComboBox(imenaDela);
		setSize(600,600);
		initGui();
		initAction();
	}
	
	public DodavanjeRecenzije(Urednik urednik, Sesija sesija, MuzickoDjelo md, Izvodjac izv) throws HeadlessException {
		super();
		oznaka=2;
		this.urednik = urednik;
		this.sesija=sesija;
		deloo=md;
		izvodjacc=izv;
		novaRecenzija=new Recenzija(urednik, "");
		imenaIzvodjaca=sesija.izvadiImenaIzvodjaca();
		listaizvodjaca=new JComboBox(imenaIzvodjaca);
		imenaDela = new String[1]; imenaDela[0]="";
		listaDela=new JComboBox(imenaDela);
		setSize(600,600);
		initGui();
		initAction();
	}
	
	private void initAction() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeRecenzije.this.dispose();
				
			}
		});
		listaizvodjaca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int br=listaizvodjaca.getSelectedIndex();
				String s=imenaIzvodjaca[br];
				imenaDela= sesija.izvadiImenaDela(s);
				listaDela=new JComboBox(imenaDela);
				
			}
		});
		btnZavrsi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(DodavanjeRecenzije.this.oznaka==2) {
					DodavanjeRecenzije.this.novaRecenzija.setAll(DodavanjeRecenzije.this.text.getText(), DodavanjeRecenzije.this.naslov.getText(),izvodjacc,deloo);
					
				}else {
				String br=(String) listaizvodjaca.getSelectedItem();
				String br1=(String) listaDela.getSelectedItem();
				if(br.equals("") || br1.equals("")) {
					JOptionPane.showMessageDialog(DodavanjeRecenzije.this, "Morate selektovati jedanog muzicara,i jedno delo", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
					 izvodjacc=sesija.getIzvodjac(br);
					 deloo=izvodjacc.pronadiDelo(br1);
					if(izvodjacc==null || deloo==null) {
					JOptionPane.showMessageDialog(DodavanjeRecenzije.this, "Nema tog dela ili izvodjaca", "Info", JOptionPane.INFORMATION_MESSAGE);
					}else {
					DodavanjeRecenzije.this.novaRecenzija.setAll(DodavanjeRecenzije.this.text.getText(), DodavanjeRecenzije.this.naslov.getText(),izvodjacc,deloo);
				}
					}
			}}
		});
		
	}
	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]10[]10[]10[]");
		setLayout(mig);
		naslov=new JTextField(30);
		add(new JLabel("Naslov: "));
		add(naslov);
		text=new JTextArea(15,50);
		add(new JLabel("Tekst: "));//TODO: napraviti lepo za text
		add(text, "span 2 3");
		if(oznaka!=2) {
			add(new Label("Izvodjaci:"));
			
			add(listaizvodjaca);
			add(new Label("Dela: "));
			add(listaDela);
		}
		btnBack=new JButton();
		btnBack.setText("Nazad");
		btnZavrsi=new JButton();
		btnZavrsi.setText("Zavrsi recenziju");
		add(btnBack);
		add(btnZavrsi);
	}
	
	
	
}
