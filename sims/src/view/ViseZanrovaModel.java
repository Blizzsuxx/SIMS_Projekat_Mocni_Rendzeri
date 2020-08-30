package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.IzvestajSvihZanrova;

public class ViseZanrovaModel extends AbstractTableModel {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String[] naslovi = { "Naziv", "Broj muzickih dela", "Broj recenzija" };
	ArrayList<IzvestajSvihZanrova> lista;

	public ViseZanrovaModel(ArrayList<IzvestajSvihZanrova> lista) {
		this.lista=lista;
	}

	@Override
	public int getRowCount() {
		
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		
		return naslovi.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		IzvestajSvihZanrova l=lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return l.getNaziv();
		case 1:
			return l.getBrojMuzdela();
		case 2:
			return l.getBrojRecenzija();
		default:
			break;
		}
		return null;
	}

}
