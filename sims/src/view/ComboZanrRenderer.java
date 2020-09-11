package view;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class ComboZanrRenderer implements ListCellRenderer<ComboZanrStanje> {
	protected JCheckBox checkBox;
	
	public ComboZanrRenderer() {
		this.checkBox = new JCheckBox();
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ComboZanrStanje> list, ComboZanrStanje value, 
			int index,
			boolean isSelected, boolean cellHasFocus) {
		checkBox.setText(String.format("Naziv Zanra: %s", value.getZanr().getNazivZanra()));
		checkBox.setSelected(((Boolean)value.stanje).booleanValue());
		return checkBox;
	}

}
