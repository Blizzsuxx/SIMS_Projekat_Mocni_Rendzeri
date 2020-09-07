package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controler.KorisniciMenadzer;
import model.Korisnik;
import model.Sesija;


public class BlokiranjeNaloga extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTable nalozi;
	public Sesija sesija;
	
	public BlokiranjeNaloga(Sesija sesija) throws Exception {
		setResizable(false);
		this.sesija = sesija;
		setTitle("Blokiranje naloga");
		getContentPane().setLayout(null);
		
		
		nalozi = new JTable();
		nalozi.setFillsViewportHeight(true);
		nalozi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nalozi.setBounds(10, 11, 414, 205);
		getContentPane().add(nalozi);
		
		JButton btnNewButton = new JButton("Blokiraj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selektovaniRed = nalozi.getSelectedRow();
				setujStatus(selektovaniRed, false);
			}
			
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnOdblokiraj = new JButton("Odblokiraj");
		btnOdblokiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selektovaniRed = nalozi.getSelectedRow();
				setujStatus(selektovaniRed, true);
			}
		});
		btnOdblokiraj.setBounds(109, 227, 89, 23);
		getContentPane().add(btnOdblokiraj);
		
		ucitajNaloge();
	}
	
	private void ucitajNaloge() throws Exception {
		KorisniciMenadzer km = sesija.getKorisnici();
		TableModelWrapper tmw = km.getTabelaKorisnika();
		nalozi.setModel(tmw);
	}
	
	private void setujStatus(int selektovaniRed, boolean status) {
		nalozi.setValueAt(status, selektovaniRed, 4);
		KorisniciMenadzer km = sesija.getKorisnici();
		HashMap<String,Korisnik> korisnici = km.getKorisnici();
		Iterator<Entry<String, Korisnik>> it = korisnici.entrySet().iterator();
		while (it.hasNext()) {
			@SuppressWarnings("rawtypes")
			HashMap.Entry pair = (HashMap.Entry)it.next();
			Korisnik k = (Korisnik)pair.getValue();
			String korisnickoIme = (String)nalozi.getValueAt(selektovaniRed, 0);
			if (korisnickoIme.equals((String)pair.getKey())) {
				k.getNalog().setStatus(status);
				korisnici.replace((String)pair.getKey(), k);
				break;
			}
	        it.remove();
	    }
		km.setKorisnici(korisnici);
		sesija.setKorisnici(km);
	}
}
