package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jdesktop.swingx.JXTable;

import model.IzvestajSvihZanrova;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class IzvestajZanra extends MojDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private Sesija sesija;
	private Zanr zanr;
	private JButton btnBack;
	private JXTable table;
	private String title;
	private JTextField tfUkupnoKom, tfUkupno, tfProsecnaOcena;
	private IzvestajSvihZanrova jedanZanr;

	public IzvestajZanra(Sesija s, String title, Zanr z, int dim1, int dim2) {
		super(title, dim1, dim2);
		this.setSesija(s);
		this.setZanr(z);
		this.jedanZanr = s.pronadjiPodatkejednogZanra(z.getNazivZanra());
		setTitle(title);
		setResizable(false);
		initGui();
		initActions();
	}

	/**
	 * @return the table
	 */
	public JXTable getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(JXTable table) {
		this.table = table;
	}

	/**
	 * @return the zanr
	 */
	public Zanr getZanr() {
		return zanr;
	}

	/**
	 * @param zanr the zanr to set
	 */
	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
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
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]20[]");
		setLayout(mig);
		btnBack=new JButton("Nazad");
		add(new JLabel("Ukupno recenzija zanra: "));
		tfUkupno = new JTextField(10);
		add(tfUkupno);
		tfUkupno.setText(jedanZanr.getBrojRecenzija()+"");
		tfUkupno.setEditable(false);
		
		add(new JLabel("Ukupno komentara: "));
		tfUkupnoKom = new JTextField(10);
		add(tfUkupnoKom);
		tfUkupnoKom.setText(jedanZanr.getBrojKOmentara()+"");
		tfUkupnoKom.setEditable(false);
		
		add(new JLabel("Broj dela zanra: "));
		tfProsecnaOcena = new JTextField(10);
		add(tfProsecnaOcena);
		tfProsecnaOcena.setText(jedanZanr.getBrojMuzdela()+" ");
		tfProsecnaOcena.setEditable(false);
		add(btnBack);
		
		setVisible(true);
		
	}
	
	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				IzvestajZanra.this.dispose();
			}
		});}
}
