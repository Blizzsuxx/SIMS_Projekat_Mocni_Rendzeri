package view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Par;
import net.miginfocom.swing.MigLayout;

public class EditorPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<JTextField> redovi;
	private JButton dugme;
	
	public EditorPanel() {
		super(new MigLayout());
		redovi = new ArrayList<JTextField>();
		JPanel donjiPanel = new JPanel();
		JButton potvrda = new JButton("Potvrdi");
		donjiPanel.add(potvrda);
		add(donjiPanel, "south");
		//add(donjiPanel, "span 2, grow, pushy, wrap push");
		dugme = potvrda;
	}
	
	public EditorPanel(Collection<Par<String, Boolean>> atributi) {
		this();
		initRedovi(atributi);
	}
	
	private void initRedovi(Collection<Par<String, Boolean>> atributi) {
		for(Par<String, Boolean> par : atributi) {
			dodajRed(par);
		}
	}
	
	public void dodajRed(Par<String, Boolean> par) {
		JLabel nazivAtributa = new JLabel(par.getElement0());
		JTextField editorAtributa = new JTextField(25);
		editorAtributa.setName(par.getElement0());
		add(nazivAtributa);
		add(editorAtributa, "wrap");
		editorAtributa.setEditable(par.getElement1());
		redovi.add(editorAtributa);
	}
	
	public void setEditable(String ime, boolean b) {
		for(JTextField red : redovi) {
			if(red.getName().equals(ime)) {
				red.setEditable(b);
				return;
			}
		}
	}

	public List<JTextField> getTekstFields() {
		return Collections.unmodifiableList(redovi);
	}

	public void addActionListener(ActionListener actionListener) {
		// TODO Auto-generated method stub
		dugme.addActionListener(actionListener);
		
	}
	
	public void setRedovi(Collection<Par<String, Boolean>> atributi) {
		if(this.redovi == null || this.redovi.isEmpty()) {
			initRedovi(atributi);
		}
	}
	
}
