package view;

import model.Izvodjac;
import model.MuzickoDelo;
import model.Recenzija;
import model.Sesija;
import model.Urednik;
import model.ZakazanaRecenzija;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controler.MuzickiSadrzajMenadzer;
import controler.UtisakMenadzer;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class DodavanjeRecenzije extends MojDialog {
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private String title;
	private Recenzija recenzija;
	private JTextField txtNaslov;
	private JTable dela;
	private JTextArea txtTekst;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbIzvodjaci;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DodavanjeRecenzije(Sesija sesija, Recenzija recenzija, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.recenzija = recenzija;
		this.sesija = sesija;
		this.title = title;
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblNaslov = new JLabel("Naslov");
		lblNaslov.setBounds(10, 22, 38, 14);
		getContentPane().add(lblNaslov);
		
		txtNaslov = new JTextField();
		txtNaslov.setBounds(68, 19, 636, 20);
		getContentPane().add(txtNaslov);
		txtNaslov.setColumns(10);
		
		JLabel lblText = new JLabel("Tekst");
		lblText.setBounds(10, 62, 38, 14);
		getContentPane().add(lblText);
		
		txtTekst = new JTextArea();
		txtTekst.setRows(4);
		txtTekst.setBounds(68, 57, 636, 58);
		getContentPane().add(txtTekst);
		
		JLabel lblIzvodjac = new JLabel("Izvodjac");
		lblIzvodjac.setBounds(10, 144, 48, 14);
		getContentPane().add(lblIzvodjac);
		
		String[] imenaIzvodjaca = sesija.izvadiImenaIzvodjaca();
		cmbIzvodjaci = new JComboBox(imenaIzvodjaca);
		cmbIzvodjaci.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cmbIzvodjaci.getSelectedIndex() == -1) {
					return;
				}
				try {
					ucitajPesme((String)cmbIzvodjaci.getSelectedItem());
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbIzvodjaci.setMaximumRowCount(20);
		cmbIzvodjaci.setBounds(68, 140, 205, 22);
		getContentPane().add(cmbIzvodjaci);
		
		dela = new JTable();
		dela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		dela.setBounds(68, 204, 636, 135);
		getContentPane().add(dela);
		
		JLabel lblDela = new JLabel("Dela");
		lblDela.setBounds(10, 204, 38, 14);
		getContentPane().add(lblDela);
		
		JButton btnDodaj;
		if (recenzija != null) {
			setTitle(title);
			btnDodaj = new JButton("Izmeni");
			cmbIzvodjaci.setEnabled(false);
			dela.setEnabled(false);
		}
		else {
			setTitle(title);
			btnDodaj = new JButton("Dodaj");
		}
		
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (recenzija != null)
					izmeniRecenziju();
				else	
					dodajRecenziju();
			}
		});
		btnDodaj.setBounds(615, 365, 89, 23);
		getContentPane().add(btnDodaj);
		
		setVisible(true);
	}
	
	private void ucitajPesme(String umetnickoIme) throws Exception {
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer(); // PROMJENA
		Izvodjac i = sesija.getIzvodjac(umetnickoIme);
		TableModelWrapper tmw = mdm.getTabelaMuzickihDela(i);
		dela.setModel(tmw);
	}
	
	private void dodajRecenziju() {
		if (txtNaslov.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Naslov je obavezno polje.");
			return;
		}
		if (dela.getSelectionModel().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Morate odabrati muzicko delo.");
			return;
		}
		String naslov = txtNaslov.getText();
		String tekst = txtTekst.getText();
		int selektovaniRed = dela.getSelectedRow();
		String sDelo = (String)dela.getValueAt(selektovaniRed, 0);
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		MuzickoDelo muzickoDelo = null;
		for (MuzickoDelo md : mdm.getMuzickaDela()) {
			if (md.getNaslov().equals(sDelo)) {
				muzickoDelo = md;
				break;
			}
		}
		Urednik urednik = (Urednik)Sesija.getTrenutniKorisnik();
		Recenzija recenzija = new Recenzija(tekst, new Date(), true, urednik, muzickoDelo, naslov);
		sesija.addRecenzije(recenzija);
	}
	
	private void izmeniRecenziju() {
		if (txtNaslov.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Naslov je obavezno polje.");
			return;
		}
		if (txtTekst.getText().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Tekst je obavezno polje.");
			return;
		}
		UtisakMenadzer um = sesija.getUtisakMenadzer();
		for (Recenzija r : um.getRecenzije()) {
			if (recenzija.getNaslov().equals(r.getNaslov()))
				r.setNaslov(txtNaslov.getText());
				r.setText(txtTekst.getText());
				r.setUrednik((Urednik)Sesija.getTrenutniKorisnik());
				sesija.setUtisakMenadzer(um);
				break;
		}
		Urednik urednik = (Urednik) Sesija.getTrenutniKorisnik();
		for (ZakazanaRecenzija zr : urednik.getZakazaneRecenzije()) {
			if (recenzija.getNaslov().equals(zr.getRecenzija().getNaslov())) {
					zr.setUradeno(true);
					break;
			}
		}
		Sesija.setTrenutniKorisnik(urednik);
		DodavanjeRecenzije.this.dispose();
	}
}
