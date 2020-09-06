package view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import controler.KorisniciMenadzer;
import controler.RecenzijeZaIzmenuMenadzer;
import controler.ZakazanaRecenzijaMenadzer;
import model.Korisnik;
import model.RecezijaZaIzmenu;
import model.Sesija;
import model.Urednik;
import model.ZakazanaRecenzija;

import javax.swing.JComboBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DodelaRecenzija extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable zakazaneRecenzije;
	private JTable recenzijeZaIzmenu;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbUrednici;
	private Sesija sesija;

	@SuppressWarnings("rawtypes")
	public DodelaRecenzija(Sesija sesija) throws Exception {
		this.sesija = sesija;
		setResizable(false);
		setTitle("Dodela recenzija");
		getContentPane().setLayout(null);
		
		zakazaneRecenzije = new JTable();
		zakazaneRecenzije.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		zakazaneRecenzije.setBounds(10, 26, 362, 189);
		getContentPane().add(zakazaneRecenzije);
		
		recenzijeZaIzmenu = new JTable();
		recenzijeZaIzmenu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		recenzijeZaIzmenu.setBounds(400, 26, 362, 189);
		getContentPane().add(recenzijeZaIzmenu);
		
		JLabel lblZakazaneRecenzije = new JLabel("Zakazane recenzije");
		lblZakazaneRecenzije.setBounds(10, 11, 111, 14);
		getContentPane().add(lblZakazaneRecenzije);
		
		JLabel lblRecenzijeZaIzmenu = new JLabel("Recenzije za izmenu");
		lblRecenzijeZaIzmenu.setBounds(400, 11, 111, 14);
		getContentPane().add(lblRecenzijeZaIzmenu);
		
		cmbUrednici = new JComboBox();
		cmbUrednici.setMaximumRowCount(20);
		cmbUrednici.setBounds(10, 282, 197, 22);
		getContentPane().add(cmbUrednici);
		
		JLabel lblUrednik = new JLabel("Urednik");
		lblUrednik.setBounds(10, 264, 48, 14);
		getContentPane().add(lblUrednik);
		
		JButton btnDodeli = new JButton("Dodeli");
		btnDodeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dodeliRecenzijeUredniku();
			}
		});
		btnDodeli.setBounds(673, 282, 89, 23);
		getContentPane().add(btnDodeli);
		
		ucitajZakazaneRecenzije();
		
		ucitajRecenzijeZaIzmenu();
		
		ucitajUrednike();
	}
	
	private void ucitajZakazaneRecenzije() throws Exception {
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		TableModelWrapper tmw = zrm.getTabelaZavrsenihRecenzija(false);
		zakazaneRecenzije.setModel(tmw);
	}
	
	private void ucitajRecenzijeZaIzmenu() throws Exception {
		RecenzijeZaIzmenuMenadzer rzi = sesija.getRecenzijeZaIzmenuMenadzer();
		TableModelWrapper tmw = rzi.getTabelaRecenzijaZaIzmenu();
		recenzijeZaIzmenu.setModel(tmw);
	}
	
	@SuppressWarnings("unchecked")
	private void ucitajUrednike() {
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String,Korisnik> korisnici = km.getKorisnici();
		Iterator<Entry<String, Korisnik>> it = korisnici.entrySet().iterator();
		while (it.hasNext()) 
		{
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Korisnik k = (Korisnik)pair.getValue();
			if (k.getClass() == Urednik.class) {
				cmbUrednici.addItem(k.getNalog().getKorisnickoIme());
			}
	        it.remove();
	    }
	}
	
	private void dodeliRecenzijeUredniku() {
		ArrayList<ZakazanaRecenzija> zakazaneRecenzije = new ArrayList<ZakazanaRecenzija>();
		ArrayList<RecezijaZaIzmenu> recenzijeZaIzmenu = new ArrayList<RecezijaZaIzmenu>();
		ZakazanaRecenzijaMenadzer zrm = sesija.getZakazanaRecenzijaMenadzer();
		RecenzijeZaIzmenuMenadzer rzim = sesija.getRecenzijeZaIzmenuMenadzer();
		int[] selektovaneZakRec = this.zakazaneRecenzije.getSelectedRows();
		for (int i = 0; i < selektovaneZakRec.length; i++) {
			for (ZakazanaRecenzija zr : zrm.getSve()) {
				if (this.zakazaneRecenzije.getValueAt(selektovaneZakRec[i], 0).equals(zr.getRecenzija().getNaslov())) {
					zakazaneRecenzije.add(zr);
				}
			}
		}
		int[] selektovaneRecZaIzmenu = this.recenzijeZaIzmenu.getSelectedRows();
		for (int i = 0; i < selektovaneRecZaIzmenu.length; i++) {
			for (RecezijaZaIzmenu rzi : rzim.getSveizmene()) {
				if (this.recenzijeZaIzmenu.getValueAt(selektovaneRecZaIzmenu[i], 0).equals(rzi.getRecenzija().getNaslov())) {
					recenzijeZaIzmenu.add(rzi);
				}
			}
		}
		Urednik urednik = null;
		String sUrednik = (String)cmbUrednici.getSelectedItem();
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String,Korisnik> korisnici = km.getKorisnici();
		Iterator<Entry<String, Korisnik>> it = korisnici.entrySet().iterator();
		while (it.hasNext()) 
		{
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Korisnik k = (Korisnik)pair.getValue();
			if (k.getNalog().getKorisnickoIme().equals(sUrednik)) {
				urednik = (Urednik)k;
				break;
			}
	        it.remove();
	    }
		for (ZakazanaRecenzija zr : zakazaneRecenzije) {
			urednik.addZakazaneRecenzije(zr);
			urednik.addIstorijaRecenzija(zr.getRecenzija());
		}
		for (RecezijaZaIzmenu rzi : recenzijeZaIzmenu) {
			urednik.addRecezijaZaIzmenu(rzi);
			urednik.addIstorijaRecenzija(rzi.getRecenzija());
		}
		korisnici.replace(urednik.getNalog().getKorisnickoIme(), (Korisnik)urednik);
		km.setKorisnici(korisnici);
		sesija.setKorisnici(km);
	}
}
