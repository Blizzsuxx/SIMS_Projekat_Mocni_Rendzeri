package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXTable;

import controler.IzvodjacMenadzer;
import model.Grupa;
import model.Pojedinacanizvodjac;
import model.Sesija;
import model.Zanr;


public class PromenaZanraIzvodjaca extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JXTable pojedninacniIzvodjaci;
	private JXTable grupe;
	private String title;
	private Sesija sesija;
	private ComboZanr cmbZanr;
	
	
	public PromenaZanraIzvodjaca(Sesija sesija, String title, int dim1, int dim2) throws Exception {
		super(title, dim1, dim2);
		this.title = title;
		this.sesija = sesija;
		setResizable(false);
		setTitle(title);
		getContentPane().setLayout(null);
		
		pojedninacniIzvodjaci = new JXTable();
		pojedninacniIzvodjaci.setBorder(null);
		pojedninacniIzvodjaci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pojedninacniIzvodjaci.getTableHeader().setReorderingAllowed(false);
		pojedninacniIzvodjaci.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(pojedninacniIzvodjaci);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(10, 26, 674, 207);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		pojedninacniIzvodjaci.setFillsViewportHeight(true);
		
		cmbZanr = new ComboZanr();
		cmbZanr.setBounds(312, 244, 273, 22);
		cmbZanr.kreirajSadrzaj(sesija.getZanroviMenadzer().getZanrovi());
		getContentPane().add(cmbZanr);
		
		JLabel lblZanr = new JLabel("Zanr:");
		lblZanr.setHorizontalAlignment(SwingConstants.RIGHT);
		lblZanr.setBounds(237, 244, 48, 14);
		getContentPane().add(lblZanr);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promeniZanr();
			}
		});
		btnPromeni.setBounds(595, 238, 89, 29);
		getContentPane().add(btnPromeni);
		
		grupe = new JXTable();

		grupe.setBorder(null);
		grupe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		grupe.getTableHeader().setReorderingAllowed(false);
		grupe.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid1 = new JScrollPane(grupe);
		scrollPaneGrid1.setViewportBorder(null);
		scrollPaneGrid1.setBounds(10, 274, 674, 218);
		scrollPaneGrid1.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid1, BorderLayout.CENTER);
		grupe.setFillsViewportHeight(true);
		
		JLabel lblGrupe = new JLabel("Grupe");
		lblGrupe.setBounds(10, 255, 48, 18);
		getContentPane().add(lblGrupe);
		
		JLabel lblPojedinacniIzvodjaci = new JLabel("Pojedinacni izvodjaci");
		lblPojedinacniIzvodjaci.setBounds(10, 11, 130, 14);
		getContentPane().add(lblPojedinacniIzvodjaci);
		
		ucitajIzvodjace();
		
		
		setVisible(true);
		
	}
	
	private void ucitajIzvodjace() throws Exception {
		IzvodjacMenadzer izvodjacMenadzer = sesija.getIzvodjacMenadzer();
		TableModelWrapper tmw1 = izvodjacMenadzer.getTabelaPojednicanihIzvodjaca();
		pojedninacniIzvodjaci.setModel(tmw1);
		TableModelWrapper tmw2 = izvodjacMenadzer.getTabelaGrupa();
		grupe.setModel(tmw2);
	}
	
	private void promeniZanr() {
		if (cmbZanr.vratiSelektovaneZanrove().size() == 0) {
			JOptionPane.showMessageDialog(null, "Morate odabrati bar jedan zanr");
			return;
		}
		if (!pojedninacniIzvodjaci.getSelectionModel().isSelectionEmpty()) {
			for (Pojedinacanizvodjac pi : sesija.getUmetnici()) {
				if (pi.getUmetnickoIme().equals((String)pojedninacniIzvodjaci.getValueAt(pojedninacniIzvodjaci.getSelectedRow(), 0))) {
					
					ArrayList<Zanr> zanrovi = (ArrayList<Zanr>) cmbZanr.vratiSelektovaneZanrove();
					pi.setZanr(zanrovi);
					sesija.getIzvodjacMenadzer().setSolo(sesija.getUmetnici());
					break;
				}
			}
		}
		if (!grupe.getSelectionModel().isSelectionEmpty()) {
			for (Grupa g : sesija.getGrupe()) {
				if (g.getUmetnickoIme().equals((String)grupe.getValueAt(grupe.getSelectedRow(), 0))) {
					ArrayList<Zanr> zanrovi = (ArrayList<Zanr>) cmbZanr.vratiSelektovaneZanrove();
					g.setZanr(zanrovi);
					sesija.getIzvodjacMenadzer().setGrupe(sesija.getGrupe());
					break;
				}
			}
		}
		try {
			ucitajIzvodjace();
		} 
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
