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

import model.Recenzija;
import model.Sesija;
import model.Urednik;
import net.miginfocom.swing.MigLayout;

public class IzvestajRecenzije extends JFrame {
	private Sesija sesija;
	private Recenzija recenzija;
	private JButton btnBack;
	
	private JTextField tfImeUrednika, tfNaslov, tfdatum, tfDelo, tfIzvodjac;
	
	public IzvestajRecenzije(Sesija s, Recenzija r) {
		this.sesija=s;
		this.recenzija=r;
		setSize(400, 400);
		setResizable(false);
		initGui();
		initActions();
	}

	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent..
				IzvestajRecenzije.this.dispose();
				
			}
		});
		
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]10[]20[]");
		setLayout(mig);
		
		add(new JLabel("Urednik: "));
		tfImeUrednika = new JTextField(20);
		add(tfImeUrednika);
		tfImeUrednika.setText(recenzija.getUrednik().getIme()+" "+recenzija.getUrednik().getPrezime());
		
		add(new JLabel("Naslov"));
		tfNaslov = new JTextField(20);
		add(tfNaslov);
		tfNaslov.setText(recenzija.getNaslov());
		
		add(new JLabel("Datum upisa recenzije"));
		tfdatum = new JTextField(20);
		add(tfdatum);
		tfdatum.setText(recenzija.getDatumUpisa()+"");
		
		add(new JLabel("Naziv dela"));
		tfDelo = new JTextField(20);
		add(tfDelo);
		tfDelo.setText(recenzija.getDelo().getNaziv()+"");
		
		add(new JLabel("Izvodjac")); //ovo menjati!!! jer moze biti vise izvodjaca
		tfIzvodjac = new JTextField(20);
		add(tfIzvodjac);
		tfIzvodjac.setText(recenzija.getDelo().getNaziv()+"");
		add(btnBack);
		btnBack.setText("Nazad");
		//prikaz teksta recenzije,, broja kon=mentara ocena, komentara.....
		
		}
}
