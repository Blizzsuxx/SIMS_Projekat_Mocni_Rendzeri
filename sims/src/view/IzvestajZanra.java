package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.IzvestajSvihZanrova;
import model.Sesija;
import model.Zanr;
import net.miginfocom.swing.MigLayout;

public class IzvestajZanra extends JFrame {

	private Sesija sesija;
	private Zanr zanr;
	private JButton btnBack;
	private JTable table;
	private JTextField tfUkupnoKom, tfUkupno, tfProsecnaOcena;
	private IzvestajSvihZanrova jedanZanr;
	
	public IzvestajZanra(Sesija s, Zanr z) {
		this.sesija=s;
		this.zanr=z;
		this.jedanZanr=s.pronadjiPodatkejednogZanra(z.getNazivZanra());
		setSize(400, 400);
		setResizable(false);
		initGui();
		initActions();
	}

	private void initGui() {
		MigLayout mig =  new MigLayout("wrap 2", "[]10[]", "[]10[]10[]20[]");
		setLayout(mig);
		
		add(new JLabel("Ukupno recenzija zanra: "));
		tfUkupno = new JTextField(10);
		add(tfUkupno);
		tfUkupno.setText(jedanZanr.getBrojRecenzija()+"");
		
		add(new JLabel("Ukupno komentara: "));
		tfUkupnoKom = new JTextField(10);
		add(tfUkupnoKom);
		tfUkupnoKom.setText(jedanZanr.getBrojKOmentara()+"");
		
		add(new JLabel("Broj dela zanra: "));
		tfProsecnaOcena = new JTextField(10);
		add(tfProsecnaOcena);
		tfProsecnaOcena.setText(jedanZanr.getBrojMuzdela()+" ");
		
		
	}
	private void initActions() {
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//parent.setVisible(true);
				IzvestajZanra.this.dispose();
				
			}

			
		});}
}
