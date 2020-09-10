package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import model.Zanr;

public class ComboZanr extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JComboBox<ComboZanrStanje> combo;
	private List<ComboZanrStanje> stanja;
	
	public ComboZanr() {
		this.stanja = new ArrayList<>();
		combo = new JComboBox<ComboZanrStanje>(new Vector<ComboZanrStanje>(stanja));
		this.add(combo);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (!stanja.isEmpty()) {
			@SuppressWarnings("unchecked")
			JComboBox<ComboZanrStanje> cb = (JComboBox<ComboZanrStanje>)e.getSource();
			ComboZanrStanje stanje = (ComboZanrStanje)cb.getSelectedItem();
			ComboZanrRenderer czr = (ComboZanrRenderer)cb.getRenderer();
			czr.checkBox.setSelected(stanje.stanje = !stanje.stanje);
		}
	}

	public void kreirajSadrzaj(List<Zanr> zanrovi) {
		if (!zanrovi.isEmpty()) {
			stanja.clear();
			combo.removeAllItems();
			for (Zanr z: zanrovi)
				stanja.add(new ComboZanrStanje(z, false));
			for (ComboZanrStanje czr: stanja)
				combo.addItem(czr);
			combo.setRenderer(new ComboZanrRenderer());
			combo.addActionListener(this);
			this.add(combo);
		}
	}
	
	public void vratiSelektovaneZanrove(List<Zanr> zanrovi) {
		for (ComboZanrStanje czs: this.stanja)
			if(czs.stanje)
				zanrovi.add(czs.getZanr());
	}
}
