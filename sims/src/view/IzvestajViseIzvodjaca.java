package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

import controler.IzvestajSvihIzvodjacaMenadzer;
import model.Izvodjac;
import model.Sesija;

public class IzvestajViseIzvodjaca extends MojDialog{
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private IzvestajSvihIzvodjacaMenadzer men;
	private String title;
	private JTable izvodjaci;
	private JDatePickerImpl dtD1;
	private SpringLayout sl_dtD1;
	private JDatePickerImpl dtD2;
	private SpringLayout sl_dtD2;
	private JTextField txtPretraga;
	private JButton btnPogledajJednog;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbZanr;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public IzvestajViseIzvodjaca(Sesija s, String title, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.title = title;
		this.sesija=s;
		this.men = s.namestiIzvestajIzvodjaca();
		cmbZanr = new JComboBox(sesija.getZanroviMenadzer().izlistajSveZanrove());
		cmbZanr.setSelectedIndex(0);
		setTitle(title);
		setResizable(false);
		getContentPane().setLayout(null);
		
		izvodjaci = new JTable(new SinglIzvodjaciModel(men.getIzvodjaci()));
		izvodjaci.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		izvodjaci.setBorder(null);
		izvodjaci.getTableHeader().setReorderingAllowed(false);
		izvodjaci.getTableHeader().setResizingAllowed(false);
		izvodjaci.setAutoCreateRowSorter(true);
		
		JScrollPane scrollPaneGrid = new JScrollPane(izvodjaci);
		scrollPaneGrid.setViewportBorder(null);
		scrollPaneGrid.setBounds(10, 11, 514, 213);
		scrollPaneGrid.setLayout(new ScrollPaneLayout());
		getContentPane().add(scrollPaneGrid, BorderLayout.CENTER);
		izvodjaci.setFillsViewportHeight(true);
		
		
		btnPogledajJednog = new JButton("Pogledaj jedan");
		btnPogledajJednog.setBounds(410, 235, 114, 23);
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
		dtD1.setBounds(10, 235, 175, 25);
		getContentPane().add(dtD1);
		
		UtilDateModel model1 = new UtilDateModel();
		JDatePanelImpl datePanel1 = new JDatePanelImpl(model1, p);
		dtD2 = new JDatePickerImpl(datePanel1, new DataLabelFormatter());
		sl_dtD2 = new SpringLayout();
		sl_dtD2.putConstraint(SpringLayout.NORTH, dtD2.getJFormattedTextField(), 0, SpringLayout.NORTH, dtD2);
		sl_dtD2.putConstraint(SpringLayout.WEST, dtD2.getJFormattedTextField(), 33, SpringLayout.WEST, dtD2);
		sl_dtD2.putConstraint(SpringLayout.EAST, dtD2.getJFormattedTextField(), 211, SpringLayout.WEST, dtD2);
		sl_dtD2 = (SpringLayout) dtD2.getLayout();
		dtD2.setBounds(195, 235, 175, 25);
		getContentPane().add(dtD2);
		
		cmbZanr.setBounds(10, 271, 175, 22);
		getContentPane().add(cmbZanr);
		
		JButton btnFiltriraj = new JButton("Filtriraj");
		btnFiltriraj.setBounds(437, 271, 89, 23);
		getContentPane().add(btnFiltriraj);
		
		JLabel lblPretraga = new JLabel("Pretraga");
		lblPretraga.setBounds(10, 378, 57, 14);
		getContentPane().add(lblPretraga);
		
		txtPretraga = new JTextField();
		txtPretraga.setBounds(77, 369, 447, 33);
		getContentPane().add(txtPretraga);
		txtPretraga.setColumns(10);
		
		TableRowSorter<TableModel> tableSorter = new TableRowSorter<TableModel>();
		tableSorter.setModel(izvodjaci.getModel());
		izvodjaci.setRowSorter(tableSorter);
		
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
				try {
					if (!izvodjaci.getSelectionModel().isSelectionEmpty()) {
						Izvodjac izvodjac = sesija.getIzvodjac((String)izvodjaci.getValueAt(izvodjaci.getSelectedRow(), 0));
						new IzvestajIzvodjaca(IzvestajViseIzvodjaca.this.sesija, izvodjac.getUmetnickoIme(), izvodjac,  615, 455);
					}
				} 
				catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Greska kod ucitavanja izvestaja za jednog izvodjaca, morate selektovati 1 red");
					//System.out.println("Greska kod ucitavanja izvestaja za jednog izvodjaca");
				}
			}
		});
		
		btnFiltriraj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String msg = validiraj();
				if (!msg.equals("")) {
					JOptionPane.showMessageDialog(null, msg);
					return;
				}
				Date dan = model.getValue(); 
				Date dan1 = model1.getValue();
				String imeZanra = (String)cmbZanr.getSelectedItem(); 
				IzvestajViseIzvodjaca.this.men.izlistajPoDatumimaIZanru(dan, dan1, imeZanra);
				refreshData();
			}
		});
		
	}
	
	public void refreshData() {
		SinglIzvodjaciModel si = (SinglIzvodjaciModel) izvodjaci.getModel();
		si.fireTableDataChanged();	
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