package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneLayout;
import javax.swing.SpringLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.IzvestajViseUrednikaMenadzer;
import model.Korisnik;
import model.Sesija;
import model.Urednik;



public class IzvestajViseUrednika extends MojDialog {
	private static final long serialVersionUID = 1L;
	private Collection<Urednik> urednici;
	private Sesija s;
	private String title;
	private IzvestajViseUrednikaMenadzer men;
	private JTable tabelaUrednika;
	private JDatePickerImpl dtD1;
	private SpringLayout sl_dtD1;
	private JDatePickerImpl dtD2;
	private SpringLayout sl_dtD2;
	private JButton btnFiltriraj;
	private JButton btnPogledajJednog;
	private JTextField txtPretraga;
	
	public IzvestajViseUrednika(Sesija s, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.s = s;
		this.title = title;
		this.urednici = s.getUrednici();
		this.men = new IzvestajViseUrednikaMenadzer(null, null, (ArrayList<Urednik>)this.urednici);
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		tabelaUrednika = new JTable(new UrednikModel(this.men.getPodaci()));
		tabelaUrednika.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabelaUrednika.setBorder(null);
		tabelaUrednika.getTableHeader().setReorderingAllowed(false);
		tabelaUrednika.getTableHeader().setResizingAllowed(false);
		tabelaUrednika.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(tabelaUrednika);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(10, 11, 493, 207);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		tabelaUrednika.setFillsViewportHeight(true);
		
		btnPogledajJednog = new JButton("Pogledaj jednog");
		btnPogledajJednog.setBounds(371, 229, 132, 23);
		getContentPane().add(btnPogledajJednog);
		
		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Danas");
		p.put("text.month", "Mesec");
		p.put("text.year", "Godina");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		dtD1 = new JDatePickerImpl(datePanel, new DataLabelFormatter());
		sl_dtD1 = new SpringLayout();
		sl_dtD1.putConstraint(SpringLayout.NORTH, dtD1.getJFormattedTextField(), 0, SpringLayout.NORTH, dtD1);
		sl_dtD1.putConstraint(SpringLayout.WEST, dtD1.getJFormattedTextField(), 33, SpringLayout.WEST, dtD1);
		sl_dtD1.putConstraint(SpringLayout.EAST, dtD1.getJFormattedTextField(), 211, SpringLayout.WEST, dtD1);
		sl_dtD1 = (SpringLayout) dtD1.getLayout();
		dtD1.setBounds(10, 227, 175, 25);
		getContentPane().add(dtD1);
		
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		dtD2 = new JDatePickerImpl(datePanel1, new DataLabelFormatter());
		sl_dtD2 = new SpringLayout();
		sl_dtD2.putConstraint(SpringLayout.NORTH, dtD2.getJFormattedTextField(), 0, SpringLayout.NORTH, dtD2);
		sl_dtD2.putConstraint(SpringLayout.WEST, dtD2.getJFormattedTextField(), 33, SpringLayout.WEST, dtD2);
		sl_dtD2.putConstraint(SpringLayout.EAST, dtD2.getJFormattedTextField(), 211, SpringLayout.WEST, dtD2);
		sl_dtD2 = (SpringLayout) dtD2.getLayout();
		dtD2.setBounds(10, 264, 175, 25);
		getContentPane().add(dtD2);
		
		btnFiltriraj = new JButton("Filtriraj");
		btnFiltriraj.setBounds(10, 307, 89, 23);
		getContentPane().add(btnFiltriraj);
		
		JLabel lblPretraga = new JLabel("Pretraga");
		lblPretraga.setBounds(10, 350, 57, 14);
		getContentPane().add(lblPretraga);
		
		txtPretraga = new JTextField();
		txtPretraga.setBounds(77, 341, 426, 33);
		getContentPane().add(txtPretraga);
		txtPretraga.setColumns(10);
		
		TableRowSorter<TableModel> tableSorter=new TableRowSorter<TableModel>();
		tableSorter.setModel(tabelaUrednika.getModel());
		tabelaUrednika.setRowSorter(tableSorter);
		
		txtPretraga.getDocument().addDocumentListener(new DocumentListener() {

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
				
				String sSerch = txtPretraga.getText().trim();
				if (sSerch.isEmpty()) {
					tableSorter.setRowFilter(null);
				}
				else {
					tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
				}
				
			}
			
		});
		
		btnPogledajJednog.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = IzvestajViseUrednika.this.tabelaUrednika.getSelectedRow();
			
				if (i < 0) {
					JOptionPane.showMessageDialog(IzvestajViseUrednika.this, "Morate selektovati bar jedan red", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
				String ime = (String) IzvestajViseUrednika.this.tabelaUrednika.getValueAt(i, 0);
				String[] imePrezime = ime.split(" ");
				for(Urednik u : IzvestajViseUrednika.this.urednici) {
					if (u.getIme().equals(imePrezime[0]) && ((Korisnik)u).getPrezime().equals(imePrezime[1])) {
						IzvestajUrednika iz = new IzvestajUrednika(IzvestajViseUrednika.this.s, u, u.getNalog().getKorisnickoIme(), 400, 400);
						iz.setVisible(true);
						break;
					}
				}
				
			}}	
		});
		
		btnFiltriraj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = validiraj();
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
					return;
				}
				java.util.Date dan = model.getValue(); 
				java.util.Date dan1 = model1.getValue();
				men = new IzvestajViseUrednikaMenadzer(dan, dan1, (ArrayList<Urednik>)s.getUrednici());
				 	((UrednikModel)tabelaUrednika.getModel()).setIzvestaj(men.getPodaci());
				refreshData();
				
			}
		});
	}
	
	public void refreshData() {
		 UrednikModel sm = (UrednikModel) tabelaUrednika.getModel();
			sm.fireTableDataChanged();
			
	}
	public String validiraj() {
		if (dtD1.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati prvi datum.";
		}
		if (dtD2.getJFormattedTextField().getText().isEmpty()) {
			return "Morate odabrati drugi datum.";
		}
		return "";
	}
	
}
