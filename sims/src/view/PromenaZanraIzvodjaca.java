package view;

import javax.swing.JTable;

import controler.IzvodjacMenadzer;
import controler.ZanroviMenadzer;
import model.Grupa;
import model.Pojedinacanizvodjac;
import model.Sesija;
import model.Zanr;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import org.jdesktop.swingx.JXList;

public class PromenaZanraIzvodjaca extends MojDialog {
	private static final long serialVersionUID = 1L;
	private JTable pojedninacniIzvodjaci;
	private JTable grupe;
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
		
		pojedninacniIzvodjaci = new JTable();
		pojedninacniIzvodjaci.setBorder(null);
		pojedninacniIzvodjaci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		pojedninacniIzvodjaci.getTableHeader().setReorderingAllowed(false);
		pojedninacniIzvodjaci.getTableHeader().setResizingAllowed(false);
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
		lblZanr.setBounds(254, 248, 48, 14);
		getContentPane().add(lblZanr);
		
		JButton btnPromeni = new JButton("Promeni");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				promeniZanr();
			}
		});
		btnPromeni.setBounds(595, 244, 89, 23);
		getContentPane().add(btnPromeni);
		
		grupe = new JTable();

		grupe.setBorder(null);
		grupe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		grupe.getTableHeader().setReorderingAllowed(false);
		grupe.getTableHeader().setResizingAllowed(false);
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
