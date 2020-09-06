package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;

import controler.Constants;
import controler.EditorBuilder;
import model.Korisnik;
import model.Pol;

public class DijalogKorisnickihInformacija extends MojDialog {
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private EditorPanel panel;
	private EditorBuilder editor;
	
	private void init(Korisnik korisnik) {
			EditorBuilder editor = new EditorBuilder(Constants.POLJA_ZA_REGISTRACIJU, korisnik, Constants.ATRIBUTI_ZA_REGISTRACIJU);
		   EditorPanel panel = new EditorPanel();
		   JRadioButton muski = new JRadioButton("Muski");
		   JRadioButton zenski = new JRadioButton("Zenski");
		   if(korisnik.getPol() == (Pol.zenski)) {
			   zenski.setSelected(true);
		   } else {
			   muski.setSelected(true);
		   }
		   ButtonGroup pol = new ButtonGroup();
		   pol.add(muski);
		   pol.add(zenski);
		   panel.add(muski);
		   panel.add(zenski, "wrap");
		   this.setContentPane(panel);
		   this.panel = panel;
		   this.editor = editor;
		   this.setSize(this.getWidth(), this.getHeight()+50);
		   panel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				buttonTriggered();
				if(muski.isSelected()) {
					korisnik.setPol(Pol.muski);
				} else {
					korisnik.setPol(Pol.zenski);
				}
			}
		});
	}
	
	
	protected void buttonTriggered() {
		// TODO Auto-generated method stub
		
	}
	
	public void setFieldEditable(String fieldName, boolean editable) {
		this.panel.setEditable(fieldName, editable);
	}


	public DijalogKorisnickihInformacija(JFrame parent, String ime, int dimension1, int dimension2, Korisnik korisnik) {
		super(parent, ime, dimension1, dimension2);
		
		
		   
		   init(korisnik);
		   editor.napraviPanel(panel);
		
	}
	
	public DijalogKorisnickihInformacija(JFrame parent, Korisnik korisnik) {
		this(parent, "Registrovanje", 450, 250, korisnik);
		
	}
	
	public DijalogKorisnickihInformacija(Korisnik korisnik) {
		this(null, korisnik);
	}
}
