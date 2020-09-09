package view;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import controler.GlasanjeMenadzer;
import controler.MuzickiSadrzajMenadzer;
import model.Glasanje;
import model.MuzickoDelo;
import model.Sesija;
import model.Uloga;
import model.Urednik;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ListSelectionModel;

public class GlasanjeProzor extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTable muzickaDela;
	private JButton btnPokreniNovoGlasanje;
	private JButton btnZaustavi;
	private JButton btnGlasaj;
	private Uloga uloga;
	private String title;
	private Sesija sesija;
	public GlasanjeProzor(Sesija sesija, String title, int dim1, int dim2, Uloga uloga) throws Exception {
		super(title, dim1, dim2);
		this.uloga = uloga;
		this.sesija = sesija;
		setResizable(false);
		getContentPane().setLayout(null);
		setTitle(title);
		
		muzickaDela = new JTable();
		muzickaDela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		muzickaDela.setBounds(10, 11, 585, 295);
		getContentPane().add(muzickaDela);
		
		btnPokreniNovoGlasanje = new JButton("Pokreni novo glasanje");
		btnPokreniNovoGlasanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setujStatusGlasanja(true);
			}
		});
		btnPokreniNovoGlasanje.setBounds(357, 328, 139, 23);
		getContentPane().add(btnPokreniNovoGlasanje);
		
		btnZaustavi = new JButton("Zaustavi");
		btnZaustavi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setujStatusGlasanja(false);
			}
		});
		btnZaustavi.setBounds(506, 328, 89, 23);
		getContentPane().add(btnZaustavi);
		
		btnGlasaj = new JButton("Glasaj");
		btnGlasaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				glasaj();
			}
		});
		btnGlasaj.setBounds(10, 328, 89, 23);
		getContentPane().add(btnGlasaj);
		

		btnPokreniNovoGlasanje.setVisible(uloga == Uloga.ADMIN);
		btnZaustavi.setVisible(uloga == Uloga.ADMIN);
		btnGlasaj.setVisible(uloga == Uloga.UREDNIK);
		
		GlasanjeMenadzer gm = sesija.getGlasanjeMenadzer();
		if (uloga == Uloga.UREDNIK && !gm.isPokrenutoGlasanje()) {
			btnGlasaj.setEnabled(false);
			JOptionPane.showMessageDialog(null, "Glasanje nije zapoceto.");
			return;
		}
		
		ucitajMuzickaDela();
		setVisible(true);
	}
	
	private void ucitajMuzickaDela() throws Exception {
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		TableModelWrapper tmw = mdm.getTabelaMuzickihDela();
		muzickaDela.setModel(tmw);
	}
	
	private void setujStatusGlasanja(boolean status) {
		GlasanjeMenadzer glasanjeMenadzer = sesija.getGlasanjeMenadzer();
		glasanjeMenadzer.setPokrenutoGlasanje(status);
		if (status) {
			glasanjeMenadzer.clearListuUrednika();
			for (Glasanje g : glasanjeMenadzer.getGlasovi()) {
				g.setBrojGlasova(0);
			}
		}
		sesija.setGlasanjeMenadzer(glasanjeMenadzer);
	}
	
	private void glasaj() {
		String msg = validacijaGlasanja();
		if (!msg.equals("")) {
			JOptionPane.showMessageDialog(null, msg);
			return;
		}
		int selektovanoMuzickoDelo = muzickaDela.getSelectedRow();
		String nazivDela = (String) muzickaDela.getValueAt(selektovanoMuzickoDelo, 0);
		MuzickiSadrzajMenadzer mdm = sesija.getMuzickiSadrzajMenadzer();
		GlasanjeMenadzer gm = sesija.getGlasanjeMenadzer();
		boolean dodan = false;
		for (Glasanje g : gm.getGlasovi()) {
			if (g.getMuzickoDelo().getNaslov().equals(nazivDela)) {
				g.setBrojGlasova(g.getBrojGlasova() + 1);
				gm.addUrednik((Urednik)(Sesija.getTrenutniKorisnik()));
				dodan = true;
				break;
			}
		}
		if (!dodan) {
			for (MuzickoDelo md : mdm.getMuzickaDela()) {
				if (md.getNaslov().equals(nazivDela)) {
					Glasanje g = new Glasanje(md, 1);
					gm.addGlas(g);
					gm.addUrednik((Urednik)(Sesija.getTrenutniKorisnik()));
					break;
				}
			}
		}
		sesija.setGlasanjeMenadzer(gm);
	}
	
	private String validacijaGlasanja() {
		GlasanjeMenadzer gm = sesija.getGlasanjeMenadzer();
		for (Urednik urednik : gm.getUredniciKojiSuGlasali()) {
			if (urednik.getNalog().getKorisnickoIme().equals(Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme())) {
				return "Ne mozete glasati vise.";
			}
		}
		return "";
	}
	
}
