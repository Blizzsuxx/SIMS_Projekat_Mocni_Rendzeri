package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import controler.IzvestajViseUrednikaMenadzer;
import model.Sesija;
import model.Urednik;
import net.miginfocom.swing.MigLayout;


public class IzvestajViseUrednika extends JFrame {
	private Collection<Urednik> urednici;
	private JButton btnBack, btnOk, btnPregledaj;
	private JTable table;
	private JDatePickerImpl DatePicker2, DatePicker1;
	private UtilDateModel model1, model2;
	private Sesija s;
	
	public IzvestajViseUrednika(Sesija s) {
		this.s=s;
		this.urednici=s.getUrednici();
		initGui();
		setSize(700, 700);
		setResizable(false);
		initActions();
	}
	public void  initGui() {
	MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]"); //dodati datume
	setLayout(mig);
	
	table = new JTable(new UrednikModel(this.urednici));
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	table.getTableHeader().setReorderingAllowed(false);
	JScrollPane sp = new JScrollPane(table);
	//this.
	add(sp);
	
	TableRowSorter<TableModel> tableSorter=new TableRowSorter<TableModel>();
	tableSorter.setModel(table.getModel());
	table.setRowSorter(tableSorter);
	
	JPanel pSerch=new JPanel(new FlowLayout(FlowLayout.LEFT));
    pSerch.add(new JLabel("Pretraga:"));
	JTextField tfSerch=new JTextField(20);
	pSerch.add(tfSerch);
	add(pSerch, BorderLayout.SOUTH);
	tfSerch.getDocument().addDocumentListener(new DocumentListener() {

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
			
			String sSerch=tfSerch.getText().trim();
			if (sSerch.isEmpty()) {
				tableSorter.setRowFilter(null);
			}else {
				tableSorter.setRowFilter(RowFilter.regexFilter("(?i)"+sSerch));
			}
			
		}
		
	});
	
	Properties p=new Properties();//ovo je za odabir ko je radio u zadatom periodu i sta je uradio
	p.put("text.today", "Today");
	p.put("text.month", "Month");
	p.put("text.year", "Year");
	
	model1=new UtilDateModel();
	JDatePanelImpl datePanel1=new JDatePanelImpl(model1, p);
	this.DatePicker1=new JDatePickerImpl(datePanel1, new DataLabelFormatter());
	
	model2=new UtilDateModel();
	JDatePanelImpl datePanel2=new JDatePanelImpl(model2, p);
	this.DatePicker2=new JDatePickerImpl(datePanel2, new DataLabelFormatter());
	add(DatePicker1);
	
	add(btnBack);
	
	add(DatePicker2);
	btnOk.setText("Filtriraj");
	add(btnOk);
	
	}
	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent.setVisible(true);
				IzvestajViseUrednika.this.dispose();
				
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
				
				LocalDate dan=convertToLocalDateViaInstant(d1, month, year); //uzima datume i pretrazuje koliko je recenzija svaki imao u odredenom preiodu, i ostalih stvari, i ocena u tom periodu?
				//TODO mozda ne radi??? i proveri da li moze ovako ili mora preko sesije??
				LocalDate dan1=convertToLocalDateViaInstant(d2, month2, year2);
				IzvestajViseUrednikaMenadzer men=new IzvestajViseUrednikaMenadzer(dan, dan1, (ArrayList<Urednik>)s.getUrednici());
				table=new JTable(new UrednikModel(men.getPodaci()));
				refreshData();
				
			}
		});
	}
	public LocalDate convertToLocalDateViaInstant(int d, int m, int y) {
		LocalDate dan=LocalDate.of(y, m+1, d);
		return dan;
	}
	public void refreshData() {
		
		 UrednikModel sm=(UrednikModel) table.getModel();
			sm.fireTableDataChanged();
			
	}
}
