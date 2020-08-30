package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import model.Izvodjac;
import model.MuzickoDjelo;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public class RegistarcijaAlbuma extends JFrame {
	public JComboBox listaIzvodjaca;
	public JButton btnZavrsiAlbum, btnDodajPesmu, btnNazad;
	public JTextField naslov;
	public Sesija sesija;
	public Izvodjac trenutnoSelektovan;
	public ArrayList<MuzickoDjelo> delaizvodjaca;
	public JTable table;
	public RegistarcijaAlbuma(Sesija sesija) throws HeadlessException {
		super();
		this.sesija = sesija;
		setSize(600,600);
		listaIzvodjaca=new JComboBox(sesija.izvadiImenaIzvodjaca());
		setTitle("Registracija albuma");
		delaizvodjaca=new ArrayList<MuzickoDjelo>();
		initGui();
		initActions();
	}
	private void initActions() {
		btnNazad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				RegistarcijaAlbuma.this.dispose();
				
			}
		});
		btnDodajPesmu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String naziv=(String) listaIzvodjaca.getSelectedItem();
				if(naziv==null) {
					JOptionPane.showMessageDialog(RegistarcijaAlbuma.this, "Morate selektovati jedanog muzicara", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
				trenutnoSelektovan=sesija.getIzvodjac(naziv);
				if(trenutnoSelektovan==null) {
					JOptionPane.showMessageDialog(RegistarcijaAlbuma.this, "Morate selektovati jedanog muzicara", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}else {
					trenutnoSelektovan=sesija.getIzvodjac(naziv);
					DodajMuzickoDelo dm=new DodajMuzickoDelo(RegistarcijaAlbuma.this.sesija, trenutnoSelektovan, 0);
					dm.setVisible(true);
					delaizvodjaca=trenutnoSelektovan.getMuzickaDela();
					refreshData();
					
				}
				}
			}
		});
		
		btnZavrsiAlbum.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String imeAlbuma=naslov.getText();
				if(imeAlbuma==null) {
					JOptionPane.showMessageDialog(RegistarcijaAlbuma.this, "Morate uneti naslov", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else if(imeAlbuma=="") {
					JOptionPane.showMessageDialog(RegistarcijaAlbuma.this, "Morate uneti naslov", "Info", JOptionPane.INFORMATION_MESSAGE);
					
				}
				else { //TODO: prosledi urednika nekako!!!!!!!!
				Album album=new Album(trenutnoSelektovan,imeAlbuma );
				for(int row=0;row<table.getRow();row++) {
					if(table.getModel().getValueAt(row, 4)) {
						//ako je selektovan, cekiran uzmi taj elem
						MuzickoDjelo novoDelo=table.getDelo(row);
						album.getListaPesama().add(novoDelo);
					}
				}
				trenutnioSelektovan.getNeizdatiAlbumi().add(album);
			}}
		});
		listaIzvodjaca.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			String naziv=(String) listaIzvodjaca.getSelectedItem();
			trenutnoSelektovan=sesija.getIzvodjac(naziv);
			delaizvodjaca=trenutnoSelektovan.getMuzickaDela();
			//table = new JTable(new MuzickaDelaModel(delaizvodjaca));
			refreshData();
			
			}
		});
		
	}
	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]");
		setLayout(mig);
		add(new JLabel("Naslov albuma: "));
		add(naslov);
		add(new JLabel("Odaberite izvodjaca: "));
		add(listaIzvodjaca);
		
		table = new JTable(new MuzickaDelaModel(delaizvodjaca, 0));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp1 = new JScrollPane(table);
		//this.
		add(sp1,"span");
		
		TableRowSorter<TableModel> tableSorter1=new TableRowSorter<TableModel>();
		tableSorter1.setModel(table.getModel());
		table.setRowSorter(tableSorter1);
		
		JPanel pSerch1=new JPanel(new FlowLayout(FlowLayout.LEFT));
        pSerch1.add(new JLabel("Pretraga:"));
		JTextField tfSerch1=new JTextField(20);
		pSerch1.add(tfSerch1);
		add(pSerch1, BorderLayout.SOUTH);
		tfSerch1.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void insertUpdate(DocumentEvent e) {
				
				changedUpdate(e); 
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				
				changedUpdate(e);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				
				String sSerch=tfSerch1.getText().trim();
				if (sSerch.isEmpty()) {
					tableSorter1.setRowFilter(null);
				}else {
					tableSorter1.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
				
			}
			
		});
		
		btnDodajPesmu=new JButton("Napravi pesmu");
		add(btnDodajPesmu);
		btnNazad=new JButton("Nazad");
		add(btnNazad);
		btnZavrsiAlbum=new JButton("Zavrsi album");
		add(btnZavrsiAlbum);
		
	}
	
	public void refreshData() { 
		MuzickaDelaModel si=(MuzickaDelaModel) table.getModel();
		si.fireTableDataChanged();
			
	}
	
	
	

}
