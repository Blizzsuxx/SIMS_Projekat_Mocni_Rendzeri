package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;

import org.jdesktop.swingx.JXTable;

import controler.KorisniciMenadzer;
import model.Korisnik;
import model.Sesija;


public class BlokiranjeNaloga extends MojDialog implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JXTable nalozi;
	private Sesija sesija;
	private String title;
	private JButton btnNewButton;
	private JButton btnOdblokiraj;
	
	public BlokiranjeNaloga(Sesija sesija, String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		setResizable(false);
		this.title = title;
		this.sesija = sesija;
		setTitle(title);
		getContentPane().setLayout(null);
		
		nalozi = new JXTable();
		nalozi.setBorder(null);
		nalozi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nalozi.getTableHeader().setReorderingAllowed(false);
		nalozi.getTableHeader().setResizingAllowed(false);
		nalozi.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(nalozi);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(10, 11, 414, 205);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		nalozi.setFillsViewportHeight(true);
		
		btnNewButton = new JButton("Blokiraj");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 227, 89, 23);
		getContentPane().add(btnNewButton);
		
		btnOdblokiraj = new JButton("Odblokiraj");
		btnOdblokiraj.addActionListener(this);
		btnOdblokiraj.setBounds(109, 227, 89, 23);
		getContentPane().add(btnOdblokiraj);
		
		ucitajNaloge();
		setVisible(true);
	}
	
	private void ucitajNaloge() throws Exception {
		KorisniciMenadzer km = sesija.getKorisnici();
		TableModelWrapper tmw = km.getTabelaKorisnika();
		nalozi.setModel(tmw);
	}
	
	private void setujStatus(int selektovaniRed, boolean status) {
		if (nalozi.getSelectionModel().isSelectionEmpty()) {
			JOptionPane.showMessageDialog(null, "Morate odabrati nalog");
			return;
		}
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
				k.setStatus(status);
				korisnici.replace((String)pair.getKey(), k);
				break;
			}
	    }
		sesija.getKorisnici().setKorisnici(korisnici);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int selektovaniRed = nalozi.getSelectedRow();
		if (e.getSource() == btnNewButton)
			setujStatus(selektovaniRed, false);
		if (e.getSource() == btnOdblokiraj)
			setujStatus(selektovaniRed, true);
		
	}
}
