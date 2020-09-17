package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneLayout;
import javax.swing.table.TableModel;

import org.jdesktop.swingx.JXTable;

import controler.KorisniciMenadzer;
import model.Korisnik;
import model.Sesija;


public class BlokiranjeNaloga extends MojDialog {
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

		
		ucitajNaloge();
		
		
		this.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				TableModelWrapper model = (TableModelWrapper) nalozi.getModel();
				for(Object[] red : model.getData()) {
					sesija.getKorisnici().nadji( (String) red[0]).setStatus((boolean) red[red.length-1]);
				}
			}
		});
		
		
		setVisible(true);
	}
	
	private void ucitajNaloge() throws Exception {
		KorisniciMenadzer km = sesija.getKorisnici();
		TableModelWrapper tmw = km.getTabelaKorisnika();
		tmw.setColumnEditable(tmw.getColumnCount()-1, true);
		nalozi.setModel(tmw);
	}

}
