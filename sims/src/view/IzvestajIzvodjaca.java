package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;

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
	@SuppressWarnings("unused")
	private JXTable table;
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
