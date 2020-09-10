package view;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.IzvodjacMenadzer;
import controler.KorisniciMenadzer;
import controler.MuzickiSadrzajMenadzer;
import model.Album;
import model.Izvodjac;
import model.MuzickoDelo;
import model.Sesija;
import model.Urednik;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class RegistarcijaAlbuma extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTextField txtNaziv;
	private JTable pesme;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbIzvodjac;
	private SpringLayout sl_dtDor;
	private JDatePickerImpl dtDor;
	private Sesija sesija;
	private JButton btnDodajPesmu;
	private Urednik urednik;
	private String title;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RegistarcijaAlbuma(Sesija sesija, String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		setResizable(false);
		setTitle(title);
		getContentPane().setLayout(null);
		
		JLabel lblNaziv = new JLabel("Naziv:");
		lblNaziv.setBounds(24, 21, 48, 14);
		getContentPane().add(lblNaziv);
		
		txtNaziv = new JTextField();
		txtNaziv.setBounds(24, 35, 627, 40);
		getContentPane().add(txtNaziv);
		txtNaziv.setColumns(10);
		
		JLabel lblPesme = new JLabel("Pesme:");
		lblPesme.setBounds(24, 170, 48, 14);
		getContentPane().add(lblPesme);
		
		pesme = new JTable();
		pesme.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		pesme.setBounds(24, 183, 627, 139);
		getContentPane().add(pesme);
		
		JLabel lblDatumRegistracije = new JLabel("Datum registracije:");
		lblDatumRegistracije.setBounds(24, 114, 107, 20);
		getContentPane().add(lblDatumRegistracije);
		
		JButton btnRegistruj = new JButton("Registruj");
		btnRegistruj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					registrujAlbum();
				} 
				catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnRegistruj.setBounds(572, 356, 89, 23);
		getContentPane().add(btnRegistruj);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtDor = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtDor = new SpringLayout();
		sl_dtDor.putConstraint(SpringLayout.NORTH, dtDor.getJFormattedTextField(), 0, SpringLayout.NORTH, dtDor);
		sl_dtDor.putConstraint(SpringLayout.WEST, dtDor.getJFormattedTextField(), 33, SpringLayout.WEST, dtDor);
		sl_dtDor.putConstraint(SpringLayout.EAST, dtDor.getJFormattedTextField(), 211, SpringLayout.WEST, dtDor);
		sl_dtDor = (SpringLayout) dtDor.getLayout();
		dtDor.setBounds(24, 134, 274, 25);
		getContentPane().add(dtDor);
		
		JLabel lblIzvodjaci = new JLabel("Izvodjac:");
		lblIzvodjaci.setBounds(24, 66, 48, 14);
		getContentPane().add(lblIzvodjaci);
		
		cmbIzvodjac = new JComboBox();
		cmbIzvodjac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (cmbIzvodjac.getSelectedIndex() == -1) {
						btnDodajPesmu.setEnabled(false);
						return;
					}
					ucitajPesme((String)cmbIzvodjac.getSelectedItem());
					btnDodajPesmu.setEnabled(true);
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		cmbIzvodjac.setMaximumRowCount(20);
		cmbIzvodjac.setBounds(24, 81, 338, 22);
		getContentPane().add(cmbIzvodjac);
		
		btnDodajPesmu = new JButton("Dodaj pesmu");
		btnDodajPesmu.setEnabled(false);
		btnDodajPesmu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					dodajPesmu((String)cmbIzvodjac.getSelectedItem());
				} 
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnDodajPesmu.setBounds(24, 356, 107, 23);
		getContentPane().add(btnDodajPesmu);
		
	
		
		IzvodjacMenadzer im = sesija.getIzvodjacMenadzer();
		for (Izvodjac i : im.getSvi())
		{
			if (i.isOdobrenost())
				cmbIzvodjac.addItem(i.getUmetnickoIme());
		}
		
		urednik = ucitajZahteve();
		
		if (urednik == null) {
			JOptionPane.showMessageDialog(null, "Nemaju zahtevi za registraciju albuma.");
			return;
		}
		
		setVisible(true);
	}
	
	private void ucitajPesme(String umetnickoIme) throws Exception {
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		Izvodjac i = sesija.getIzvodjac(umetnickoIme);
		TableModelWrapper tmw = mdm.getTabelaMuzickihDela(i);
		pesme.setModel(tmw);
	}
	
	private Urednik ucitajZahteve() {
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String, Urednik> zahteviUrednika = km.getZahteviUrednika();
		Iterator<Entry<String, Urednik>> it = zahteviUrednika.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Urednik u = (Urednik)pair.getValue();
			txtNaziv.setText(u.getAlbumZaRegistracju());
			txtNaziv.setEditable(false);
			return u;
	    }
		return null;
	}
	
	private void registrujAlbum() throws ParseException {
		String msg = validiraj();
		if (!msg.equals("")) {
			JOptionPane.showMessageDialog(null, msg);
			return;
		}
		String naziv = txtNaziv.getText();
		ArrayList<MuzickoDelo> dela = new ArrayList<MuzickoDelo>();
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		int[] redovi = pesme.getSelectedRows();
		for (int i = 0; i < redovi.length; i++) {
			for (MuzickoDelo m : mdm.getMuzickaDela()) {
				if (pesme.getValueAt(redovi[i], 0).equals(m.getNaslov())) {
					dela.add(m);
				}
			}
		}
		String txtRegistracije = dtDor.getJFormattedTextField().getText();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd.MM.yyyy.");
		String txtRegistracije2 = sdf2.format(sdf1.parse(txtRegistracije));
		Date danRegistracije = new SimpleDateFormat("dd.MM.yyyy.").parse(txtRegistracije2);
		Izvodjac izvodjac = sesija.getIzvodjac((String)cmbIzvodjac.getSelectedItem());
		//Album noviAlbum = new Album(naziv, dela, urednik, izvodjac, danRegistracije, true); // NA OVO SE VRATITI
		Album noviAlbum = new Album(txtRegistracije2, txtRegistracije2, danRegistracije, izvodjac, urednik, rootPaneCheckingEnabled);
		noviAlbum.getIzvodjac().addIzdatAlbum(noviAlbum);
		refresh();
	}
	
	private void refresh() {
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String, Urednik> zahtevi = km.getZahteviUrednika();
		Iterator<Entry<String, Urednik>> it = zahtevi.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Urednik u = (Urednik)pair.getValue();
			if (u.getAlbumZaRegistracju().equals(txtNaziv.getText())) {
				zahtevi.remove(u.getNalog().getKorisnickoIme());
				break;
			}
	    }
		km.setZahteviUrednika(zahtevi);
		sesija.setKorisnici(km);
		urednik = ucitajZahteve();
		if (urednik == null) {
			JOptionPane.showMessageDialog(null, "Nemaju zahtevi za registraciju albuma.");
			return;
		}
		pesme.setModel(new DefaultTableModel(null, new String[] {"Naziv" ,"Opis", "Datum izdavanja"}));
	}
	
	private String validiraj() {
		if (txtNaziv.getText().isEmpty()) {
			return "Naziv je obavezno polje.";
		}
		if (pesme.getSelectionModel().isSelectionEmpty()) {
			return "Morate odabrati pesmu.";
		
		}
		if (cmbIzvodjac.getSelectedIndex() == -1) {
			return "Morate odabrati izvodjaca.";
		}
		if (dtDor.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati datum.";
		}
		return "";
	}
	
	private void dodajPesmu(String umetnickoIme) throws Exception {
		new DodajMuzickoDelo(sesija, "Dodavanje muzickog dela", 422, 422, sesija.getIzvodjac(umetnickoIme), 0);
		ucitajPesme(umetnickoIme);
	}
}
