 package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

import javax.swing.JButton;
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

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdesktop.swingx.JXTable;

import controler.IzvestajViseUrednikaMenadzer;
import model.Korisnik;
import model.PodaciUrednikaZaIzvestaj;
import model.Sesija;
import model.Urednik;
import net.miginfocom.swing.MigLayout;



public class IzvestajViseUrednika extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Collection<Urednik> urednici;
	@SuppressWarnings("unused")
	private JButton btnBack, btnOk, btnPregledaj;
	private JXTable table;
	private JDatePickerImpl DatePicker2, DatePicker1;
	private UtilDateModel model1, model2;
	private Sesija s;
	private IzvestajViseUrednikaMenadzer men;
	private JButton btnPregledJednog;
	
	public IzvestajViseUrednika(Sesija s) {
		this.s=s;
		this.urednici=s.getUrednici();
		this.men=new IzvestajViseUrednikaMenadzer(null, null, (ArrayList<Urednik>)this.urednici);
		initGui();
		setSize(700, 700);
		setResizable(false);
		initActions();
	}
	public void  initGui() {
	MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]"); //dodati datume
	setLayout(mig);
	
	table = new JXTable(new UrednikModel(this.men.getPodaci()));
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
	btnBack =new JButton("Nazad");
	add(btnBack);
	
	add(DatePicker2);
	btnOk=new JButton();
	btnOk.setText("Filtriraj");
	add(btnOk);
	btnPregledaj= new JButton("Pogledaj Jednog");
	add(btnPregledaj);
	
	}
	
	private void initActions() {
		btnPregledaj.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int i=IzvestajViseUrednika.this.table.getSelectedRow();
			
				if (i<0) {
					JOptionPane.showMessageDialog(IzvestajViseUrednika.this, "Morate selektovati bar jedan red", "Info", JOptionPane.INFORMATION_MESSAGE);
				}
				else {
				String ime=(String) IzvestajViseUrednika.this.table.getValueAt(i, 0);
				String[] imePrezime=ime.split(" ");
				for(Urednik u: IzvestajViseUrednika.this.urednici) {
					if(u.getIme().equals(imePrezime[0]) && ((Korisnik)u).getPrezime().equals(imePrezime[1])) {
						IzvestajUrednika iz=new IzvestajUrednika(IzvestajViseUrednika.this.s, u);
						iz.setVisible(true);
						break;
					}
				}
				
			}}
			
				
		});
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
				java.util.Date dan=model1.getValue(); 
				java.util.Date dan1=model2.getValue();
				 men=new IzvestajViseUrednikaMenadzer(dan, dan1, (ArrayList<Urednik>)s.getUrednici());
				 ((UrednikModel)table.getModel()).setIzvestaj(men.getPodaci());
			  
				refreshData();
				
			}
		});
	}
	
	
	public void refreshData() {
		 UrednikModel sm=(UrednikModel) table.getModel();
			sm.fireTableDataChanged();
			
	}
}
