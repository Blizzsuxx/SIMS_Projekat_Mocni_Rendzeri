package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Properties;

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

import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.IzvestajSvihIzvodjacaMenadzer;
import controler.ZanroviMenadzer;
import model.Grupa;
import model.Izvodjac;
import model.Pojedinacanizvodjac;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;
import view.DataLabelFormatter;

public class IzvestajViseIzvodjaca extends JFrame{
	private Sesija sesija;
	private IzvestajSvihIzvodjacaMenadzer men;
	private JButton btnBack, btnOk, btnPregled;
	private JTable  table;
	private JComboBox<Zanr> cbZanr;
	private JDatePickerImpl DatePicker1;
	private JDatePickerImpl DatePicker2;
	private UtilDateModel model1, model2;
	
	
	
	public IzvestajViseIzvodjaca(Sesija s, ZanroviMenadzer zm) {
		this.sesija=s;
		this.men=s.namestiIzvestajIzvodjaca();
		cbZanr = new JComboBox(zm.izlistajSveZanrove()); //ili zm da bude u sesiji i samo iz toga da se izvadi, u sustini je isto, samo je bitno da ostanemo svi pri istoj logici
		setSize(700, 700);
		setResizable(false);
		initGui();
		initActions();
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]"); //dodati datume, odabir zanra
		setLayout(mig);
		
		
		
		Properties p=new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		
		model1=new UtilDateModel();
		JDatePanelImpl datePanel1=new JDatePanelImpl(model1, p);
		this.DatePicker1=new JDatePickerImpl(datePanel1, new DataLabelFormatter());
		
		model2=new UtilDateModel();
		JDatePanelImpl datePanel2=new JDatePanelImpl(model2, p);
		this.DatePicker2=new JDatePickerImpl(datePanel2, new DataLabelFormatter());
		
		//ovde dodu datumi 1
		add(this.DatePicker1);
		add(this.DatePicker2);
		
		table = new JTable(new SinglIzvodjaciModel(men.getIzvodjaci()));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp1 = new JScrollPane(table);
		//this.
		add(sp1);
		
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
		

		add(cbZanr);
		add(btnBack);
		btnOk.setName("Filtriraj");
		add(btnOk);//dugme za filtriranje
		add(btnPregled);
	}
	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent.setVisible(true);
				IzvestajViseIzvodjaca.this.dispose();
				
			}

			
		});
		btnPregled.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int rInd = table.getSelectedRow();
				Izvodjac izv=men.pronadjiRed(rInd);
				if(izv==null) {JOptionPane.showMessageDialog(null, "Morate selektovati bar jedan red", "Info", JOptionPane.INFORMATION_MESSAGE);
				}else {
					IzvestajIzvodjaca nov=new IzvestajIzvodjaca(sesija, izv);
					nov.setVisible(true);
					
				}
				
				
				
			}
		});
	
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int d1= model1.getDay();
				int month=model1.getMonth();
				int year=model1.getYear();
				int d2= model2.getDay();
				int month2=model2.getMonth();
				int year2=model2.getYear();
				
				LocalDate dan=convertToLocalDateViaInstant(d1, month, year);
				LocalDate dan1=convertToLocalDateViaInstant(d2, month2, year2);
				String imeZanra=(String) cbZanr.getSelectedItem(); 
				IzvestajViseIzvodjaca.this.men.izlistajPoDatumimaIZanru(dan, dan1, imeZanra);
				//table1.new JTable(new SinglIzvodjaciModel(men.getIzvodjaci()));
				refreshData(); 
				
				
				
			}
		});
		
	}
	public LocalDate convertToLocalDateViaInstant(int d, int m, int y) {
		LocalDate dan=LocalDate.of(y, m+1, d);
		return dan;
	}
	public void refreshData() {
		
		 
		SinglIzvodjaciModel si=(SinglIzvodjaciModel) table.getModel();
		si.fireTableDataChanged();
			
	}
}
