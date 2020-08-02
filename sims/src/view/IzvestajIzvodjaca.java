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
	private Sesija sesija;
	private Izvodjac izvodjac;
	private JButton btnBack;
	private JTable table;
	private JTextField tfImeUrednika, tfUkupnoDela, tfbrojRec, tfBrojkom, tfOcenaUr, tfOcenaKor;
	private IzvestajJednogIzvodjaca jedan;
	
	public IzvestajIzvodjaca(Sesija s, Izvodjac i) {
		this.sesija=s;
		this.izvodjac=i;
		this.jedan=s.namestiJedanizvestaj(i);
		setSize(400, 400);
		setResizable(false);
		initGui();
		initActions();
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]10[]10[]10[]");
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
