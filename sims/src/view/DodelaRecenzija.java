package view;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ListSelectionModel;

import org.jdesktop.swingx.JXTable;

import controler.KorisniciMenadzer;
import controler.RecenzijeZaIzmenuMenadzer;
import controler.ZakazanaRecenzijaMenadzer;
import model.Korisnik;
import model.Recenzija;
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

public class DodelaRecenzija extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JXTable zakazaneRecenzije;
	private JXTable recenzijeZaIzmenu;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbUrednici;
	private Sesija sesija;
	private String title;

	@SuppressWarnings("rawtypes")
	public DodelaRecenzija(Sesija sesija, String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.sesija = sesija;
		this.title = title;
		setResizable(false);
		setTitle(title);
		getContentPane().setLayout(null);
		
		zakazaneRecenzije = new JXTable();
		zakazaneRecenzije.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		zakazaneRecenzije.setBounds(10, 26, 362, 189);
		getContentPane().add(zakazaneRecenzije);
		
		recenzijeZaIzmenu = new JXTable();
		recenzijeZaIzmenu.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		recenzijeZaIzmenu.setBounds(400, 26, 362, 189);
		getContentPane().add(recenzijeZaIzmenu);
		
		JLabel lblZakazaneRecenzije = new JLabel("Zakazane recenzije");
		lblZakazaneRecenzije.setBounds(10, 11, 111, 14);
		getContentPane().add(lblZakazaneRecenzije);
		
		JLabel lblRecenzijeZaIzmenu = new JLabel("Recenzije za izmenu");
		lblRecenzijeZaIzmenu.setBounds(400, 11, 157, 14);
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
		
		setVisible(true);
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
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Korisnik k = (Korisnik)pair.getValue();
			if (k.getNalog().getKorisnickoIme().equals(sUrednik)) {
				urednik = (Urednik)k;
				break;
			}
	    }
		if (urednik != null)
			setuj(urednik, zakazaneRecenzije, recenzijeZaIzmenu);
	}
	
	private void setuj(Urednik urednik, ArrayList<ZakazanaRecenzija> zakazaneRecenzije, 
			ArrayList<RecezijaZaIzmenu> recenzijeZaIzemnu) {
		for (Recenzija recenzija : sesija.getRecenzije()) {
			for (ZakazanaRecenzija zr : zakazaneRecenzije) {
				if (zr.getRecenzija().getNaslov().equals(recenzija.getNaslov())) {
					recenzija.setUrednik(urednik);
					sesija.setZakazanaRecenzija(recenzija, urednik);
					urednik.addZakazaneRecenzije(zr);
					urednik.addIstorijaRecenzija(zr.getRecenzija());
					break;
				}
			}
			for (RecezijaZaIzmenu rzi : recenzijeZaIzemnu) {
				if (rzi.getRecenzija().getNaslov().equals(recenzija.getNaslov())) {
					recenzija.setUrednik(urednik);
					sesija.setRecenzijaZaIzmenu(recenzija);
					urednik.addRecezijaZaIzmenu(rzi);
					urednik.addIstorijaRecenzija(rzi.getRecenzija());
					break;
				}
			}
		}
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String,Korisnik> korisnici = km.getKorisnici();
		korisnici.replace(urednik.getNalog().getKorisnickoIme(), urednik);
		km.setKorisnici(korisnici);
		sesija.setKorisnici(km);
	}
	
}
