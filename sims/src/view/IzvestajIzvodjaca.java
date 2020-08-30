package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

import model.IzvestajJednogIzvodjaca;
import model.Izvodjac;
import model.Sesija;
import net.miginfocom.swing.MigLayout;

public class IzvestajIzvodjaca extends JFrame {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Izvodjac izvodjac;
	private JButton btnBack;
	private JTable table1;
	private JTextField tfImeUrednika, tfUkupnoDela, tfbrojRec, tfBrojkom, tfOcenaUr, tfOcenaKor;
	private IzvestajJednogIzvodjaca jedan;

	public IzvestajIzvodjaca(Sesija s, Izvodjac i) {
		this.setSesija(s);
		this.izvodjac = i;
		this.jedan = s.namestiJedanizvestaj(i);
		setSize(600, 600);
		setResizable(false);
		initGui();
		initActions();
	}

	/**
	 * @return the sesija
	 */
	public Sesija getSesija() {
		return sesija;
	}

	/**
	 * @param sesija the sesija to set
	 */
	public void setSesija(Sesija sesija) {
		this.sesija = sesija;
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]10[]10[]10[]");
		setLayout(mig);
		
		add(new JLabel("Umetnicko ime: "));
		tfImeUrednika = new JTextField(10);
		add(tfImeUrednika);
		tfImeUrednika.setText(izvodjac.getUmetnickoIme());
		
		add(new JLabel("Broj dela: "));
		tfUkupnoDela = new JTextField(10);
		add(tfUkupnoDela);
		tfUkupnoDela.setText(jedan.getBrojDela()+"");
		 
		add(new JLabel("Broj recenzija"));
		tfbrojRec=new JTextField(10);
		add(tfbrojRec);
		tfbrojRec.setText(jedan.getBrojRecenzija()+"");
		
		add(new JLabel("Broj komentara"));
		tfBrojkom=new JTextField(10);
		add(tfBrojkom);
		tfBrojkom.setText(jedan.getBrojKomentara()+"");
		
		add(new JLabel("Ocena urednika"));
		tfOcenaUr=new JTextField(10);
		add(tfOcenaUr);
		tfOcenaUr.setText(jedan.getOcenaUrednika()+"");
		
		add(new JLabel("Ocena korisnika"));
		tfOcenaKor=new JTextField(10);
		add(tfOcenaKor);
		tfOcenaKor.setText(jedan.getOcenaKorisnika()+"");
		//i spisak dela fali
		
		
		table1 = new JTable(new MuzickaDelaModel(this.izvodjac.getMuzickaDela()));
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.getTableHeader().setReorderingAllowed(false);
		JScrollPane sp1 = new JScrollPane(table1);
		
		add(sp1);
		
		TableRowSorter<TableModel> tableSorter1=new TableRowSorter<TableModel>();
		tableSorter1.setModel(table1.getModel());
		table1.setRowSorter(tableSorter1);
		
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
		
		add(btnBack);
	}
	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent.setVisible(true);
				IzvestajIzvodjaca.this.dispose();
				
			}

			
		});}




}
