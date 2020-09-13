package view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controler.Constants;
import model.MuzickiSadrzaj;
import model.Sesija;
import model.TopLista;
import net.miginfocom.swing.MigLayout;

public class KorisnikAplikacijeHomepage extends Homepage {
	private static final long serialVersionUID = -7920247537466287175L;
	
	private List<MuzickoDeloLabel> trending;
	private List<MuzickoDeloLabel> pratite;
	private List<MuzickoDeloLabel> preporucujemo;
	
	private JMenu listaMenu;
	private JMenuItem listaItem1, listaItem2, listaItem3;
	
	public KorisnikAplikacijeHomepage(Sesija sesija) {
		super(sesija);
		trending = new ArrayList<>();
		pratite = new ArrayList<>();
		preporucujemo = new ArrayList<>();

		JLabel trendingl = new JLabel("U trendu");
		this.add(trendingl, "wrap, gaptop 20, align center");
		initRow(trending, 10);

		JLabel pratitel = new JLabel("Pratite");
		this.add(pratitel, "wrap, gaptop 20, align center");
		initRow(pratite, 5);

		JLabel preporucujemol = new JLabel("Preporucujemo");
		this.add(preporucujemol, "wrap, gaptop 20, align center");
		initRow(preporucujemo, 5);
		
		this.initGUI();
		this.actionGUI();
	}

	private void initRow(List<MuzickoDeloLabel> lista, int numberOfElements){
		

		JPanel pane = new JPanel(new MigLayout());
		for(int i = 0; i < numberOfElements; ++i){
			MuzickoDeloLabel labela = new MuzickoDeloLabel(Constants.BARBIE_GIRL);
			pane.add(labela, "gapleft 10");
			lista.add(labela);
		}
		JScrollPane scroll = new JScrollPane(pane);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		this.add(scroll, "wrap");
	}

	private void initGUI() {
		listaMenu = new JMenu("Liste");
		menubar.add(listaMenu);
		listaItem1 = new JMenuItem("Kreiranje Sopstvenih Listi");
		listaItem2 = new JMenuItem("Moje Liste");
		listaItem3 = new JMenuItem("Pregled Top Listi");
		listaMenu.add(listaItem1);
		listaMenu.add(listaItem2);
		listaMenu.add(listaItem3);
	}
	
	private void actionGUI() {
		listaItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Sesija.getTrenutniKorisnik() == null) {
					JOptionPane.showMessageDialog(null, "Morate se prijaviti!");
				} else {
					String trenutniUser = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
					List<MuzickiSadrzaj> temp = KorisnikAplikacijeHomepage.this.getSesija()
							.getMuzickiSadrzajMenadzer().vratiAktivanMuzickiSadrzaj();
					TopListeProzor tlp = new TopListeProzor((Homepage)KorisnikAplikacijeHomepage.this,
							"Kreiranje Sopstvenih Listi", 1200, 500, temp, koloneMuzickogSadrzaja, trenutniUser, null, null);
					tlp.setVisible(true);
					
				}
			}
			
		});
		
		listaItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Sesija.getTrenutniKorisnik() == null) {
					JOptionPane.showMessageDialog(null, "Morate se prijaviti!");
				} else {
					String trenutniUser = Sesija.getTrenutniKorisnik().getNalog().getKorisnickoIme();
					List<TopLista> liste = KorisnikAplikacijeHomepage.this.getSesija().getToplisteMenadzer().topListeKorisnika(trenutniUser);
					TopListaProzor tlp = new TopListaProzor(KorisnikAplikacijeHomepage.this, "Moje Liste", 700, 300, liste, koloneTopListi);
					tlp.setVisible(true);
				}
			}
			
		});
		
		listaItem3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (Sesija.getTrenutniKorisnik() == null) {
					JOptionPane.showMessageDialog(null, "Morate se prijaviti!");
				} else {
					List<TopLista> liste = KorisnikAplikacijeHomepage.this.getSesija().getToplisteMenadzer().vratiTopListeUrednika();
					TopListaProzor tlp = new TopListaProzor(KorisnikAplikacijeHomepage.this, "Top Liste Urednika", 700, 300, liste, koloneTopListi);
					tlp.setVisible(true);
				}
			}
			
		});
	}
}
