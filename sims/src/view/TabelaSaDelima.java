package view;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.IzvestajJednogIzvodjaca;
import model.MuzickoDelo;

public class TabelaSaDelima  extends AbstractTableModel {
	String[] naslovi = { "Izvodjac", "Delo" };
ArrayList<MuzickoDelo> svaDela;
private static DecimalFormat df = new DecimalFormat("0.00");


public TabelaSaDelima(ArrayList<MuzickoDelo> delo) {
this.svaDela=delo;
}
@Override
public String getColumnName(int index) {
return naslovi[index];
}
@Override
public int getRowCount() {

return svaDela.size();
}

@Override
public int getColumnCount() {

return naslovi.length;
}

@Override
public Object getValueAt(int rowIndex, int columnIndex) {
MuzickoDelo i=svaDela.get(rowIndex);
switch(columnIndex) {
case 0:
	return i.getNaslov();
case 1:
	return i.getIzvodjac().getUmetnickoIme();
	default:
		break;
}

return null;
}
}
