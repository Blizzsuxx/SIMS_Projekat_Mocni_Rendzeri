package view;


import java.awt.BorderLayout;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import controler.MuzickiSadrzajMenadzer;
import model.IzvestajJednogIzvodjaca;
import model.Izvodjac;
import model.Sesija;

public class IzvestajIzvodjaca extends MojDialog {
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private IzvestajJednogIzvodjaca jedan;
	private String title;
	private JTextField txtUmetnickoIme;
	private JTextField txtBrojDela;
	private JTextField txtBrojRecenzija;
	private JTextField txtBrojKomentara;
	private JTextField txtOcenaKorisnika;
	private JTextField txtOcenaUrednika;
	private JTable muzickaDela;
	private static DecimalFormat df = new DecimalFormat("0.00");

	public IzvestajIzvodjaca(Sesija sesija, String title, Izvodjac i, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		
		Izvodjac izvodjac = sesija.getIzvodjac(i.getUmetnickoIme());
		this.jedan = sesija.namestiJedanizvestaj(izvodjac);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblUmetnickoIme = new JLabel("Umetnicko ime");
		lblUmetnickoIme.setBounds(10, 48, 91, 19);
		getContentPane().add(lblUmetnickoIme);
		
		txtUmetnickoIme = new JTextField();
		txtUmetnickoIme.setEditable(false);
		txtUmetnickoIme.setBounds(102, 42, 489, 30);
		getContentPane().add(txtUmetnickoIme);
		txtUmetnickoIme.setColumns(10);
		txtUmetnickoIme.setText(izvodjac.getUmetnickoIme());
		
		JLabel lblBrojDela = new JLabel("Broj dela");
		lblBrojDela.setBounds(10, 113, 64, 14);
		getContentPane().add(lblBrojDela);
		
		txtBrojDela = new JTextField();
		txtBrojDela.setEditable(false);
		txtBrojDela.setBounds(102, 105, 96, 30);
		getContentPane().add(txtBrojDela);
		txtBrojDela.setColumns(10);
		txtBrojDela.setText(jedan.getBrojDela() + "");
		
		JLabel lblBrojRecenzija = new JLabel("Broj recenzija");
		lblBrojRecenzija.setBounds(10, 182, 84, 14);
		getContentPane().add(lblBrojRecenzija);
		
		txtBrojRecenzija = new JTextField();
		txtBrojRecenzija.setEditable(false);
		txtBrojRecenzija.setColumns(10);
		txtBrojRecenzija.setBounds(102, 174, 96, 30);
		getContentPane().add(txtBrojRecenzija);
		txtBrojRecenzija.setText(jedan.getBrojRecenzija() + "");
		
		JLabel lblBrojKomentara = new JLabel("Broj komentara");
		lblBrojKomentara.setBounds(10, 250, 91, 14);
		getContentPane().add(lblBrojKomentara);
		
		txtBrojKomentara = new JTextField();
		txtBrojKomentara.setEditable(false);
		txtBrojKomentara.setColumns(10);
		txtBrojKomentara.setBounds(102, 242, 96, 30);
		getContentPane().add(txtBrojKomentara);
		txtBrojKomentara.setText(jedan.getBrojKomentara() + "");
		
		JLabel lblOcenaKorisnika = new JLabel("Ocena korisnika");
		lblOcenaKorisnika.setBounds(10, 320, 91, 14);
		getContentPane().add(lblOcenaKorisnika);
		
		txtOcenaKorisnika = new JTextField();
		txtOcenaKorisnika.setEditable(false);
		txtOcenaKorisnika.setColumns(10);
		txtOcenaKorisnika.setBounds(102, 312, 96, 30);
		getContentPane().add(txtOcenaKorisnika);
		txtOcenaKorisnika.setText(df.format(jedan.getOcenaKorisnika()) + "");
		
		JLabel lblOcenaUrednika = new JLabel("Ocena urednika");
		lblOcenaUrednika.setBounds(10, 387, 91, 14);
		getContentPane().add(lblOcenaUrednika);
		
		txtOcenaUrednika = new JTextField();
		txtOcenaUrednika.setEditable(false);
		txtOcenaUrednika.setColumns(10);
		txtOcenaUrednika.setBounds(102, 379, 96, 30);
		getContentPane().add(txtOcenaUrednika);
		txtOcenaUrednika.setText(df.format(jedan.getOcenaUrednika()) + "");
		
		muzickaDela = new JTable();
		muzickaDela.setBorder(null);
		muzickaDela.getTableHeader().setReorderingAllowed(false);
		muzickaDela.getTableHeader().setResizingAllowed(false);
		muzickaDela.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(muzickaDela);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(220, 105, 371, 237);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		muzickaDela.setFillsViewportHeight(true);
		
		JLabel lblMuzickaDela = new JLabel("Muzicka dela");
		lblMuzickaDela.setBounds(220, 91, 91, 14);
		getContentPane().add(lblMuzickaDela);
		
		ucitajPesme(izvodjac.getUmetnickoIme());
		setVisible(true);
	}
	
	private void ucitajPesme(String umetnickoIme) throws Exception {
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		Izvodjac i = sesija.getIzvodjac(umetnickoIme);
		TableModelWrapper tmw = mdm.getTabelaMuzickihDela(i);
		muzickaDela.setModel(tmw);
	}
}
