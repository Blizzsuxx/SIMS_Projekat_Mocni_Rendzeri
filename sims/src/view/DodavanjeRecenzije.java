package view;

import javax.swing.JFrame;

import model.Izvodjac;
import model.MuzickoDelo;
import model.Recenzija;
import model.Sesija;
import model.Urednik;

import javax.swing.JLabel;
import javax.swing.JTextField;

import controler.MuzickoDeloMenadzer;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class DodavanjeRecenzije extends JFrame {
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private JTextField txtNaslov;
	private JTable dela;
	private JTextArea txtTekst;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbIzvodjaci;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public DodavanjeRecenzije(Sesija sesija) {
		this.sesija = sesija;
		setTitle("Dodavanje recenzije");
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
		
		JButton btnDodaj = new JButton("Dodaj");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodajRecenziju();
			}
		});
		btnDodaj.setBounds(615, 365, 89, 23);
		getContentPane().add(btnDodaj);
	}
	
	private void ucitajPesme(String umetnickoIme) throws Exception {
		MuzickoDeloMenadzer mdm = sesija.getMuzickoDeloMenadzer();
		Izvodjac i = sesija.getIzvodjac(umetnickoIme);
		TableModelWrapper tmw = mdm.getTabelaMuzickihDela(i);
		dela.setModel(tmw);
	}
	
	private void dodajRecenziju() {
		String naslov = txtNaslov.getText();
		String tekst = txtTekst.getText();
		int selektovaniRed = dela.getSelectedRow();
		String sDelo = (String)dela.getValueAt(selektovaniRed, 0);
		MuzickoDeloMenadzer mdm = sesija.getMuzickoDeloMenadzer();
		MuzickoDelo muzickoDelo = null;
		for (MuzickoDelo md : mdm.getDela()) {
			if (md.getNaziv().equals(sDelo)) {
				muzickoDelo = md;
				break;
			}
		}
		Urednik urednik = (Urednik)sesija.getTrenutniKorisnik();
		Recenzija recenzija = new Recenzija(tekst, new Date(), true, urednik, muzickoDelo, naslov);
		sesija.addRecenzije(recenzija);
	}
}
