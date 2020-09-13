package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ReklameProzor extends MojDialog {
	private static final long serialVersionUID = 1L;

	private AdminHomepage parent;
	
	private JButton btnOk, btnCancel;
	private JTextField putanjaPrva, putanjaDruga;
	
	public ReklameProzor(String ime, int dimension1, int dimension2, AdminHomepage parent) {
		super(ime, dimension1, dimension2);
		this.parent = parent;
		
		this.setLayout(new MigLayout("wrap 2", "[]10[]", "[]10[]10[]"));
		this.initGUI();
		this.actionGUI();
		this.pack();
	}
	
	private void initGUI() {
		JLabel labelaPrva = new JLabel("Putanja prve reklame:");
		this.add(labelaPrva);
		putanjaPrva = new JTextField(25);
		putanjaPrva.setText(parent.getSesija().getReklameMenadzer().getPutanjaPrveReklame());
		this.add(putanjaPrva);
		
		JLabel labelaDruga = new JLabel("Putanja druge reklame:");
		this.add(labelaDruga);
		putanjaDruga = new JTextField(25);
		putanjaDruga.setText(parent.getSesija().getReklameMenadzer().getPutanjaDrugeReklame());
		this.add(putanjaDruga);
		
		this.add(new JLabel());
		btnOk = new JButton("OK");
		this.add(btnOk, "split");
		btnCancel = new JButton("Cancel");
		this.add(btnCancel);
	}
	
	private void actionGUI() {
		btnCancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ReklameProzor.this.dispose();
				
			}
			
		});
		
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String prvaReklama = putanjaPrva.getText().trim();
				String drugaReklama = putanjaDruga.getText().trim();
				parent.getSesija().getReklameMenadzer().setPutanjaPrveReklame(prvaReklama);
				parent.getSesija().getReklameMenadzer().setPutanjaDrugeReklame(drugaReklama);
				ReklameProzor.this.dispose();
			}
			
		});
	}
}
