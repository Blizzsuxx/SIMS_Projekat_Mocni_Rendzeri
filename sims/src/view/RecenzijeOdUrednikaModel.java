package view;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

import model.Recenzija;


public class RecenzijeOdUrednikaModel extends AbstractTableModel{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String[] kolone = { "Datum upisa", "Naslov recenzije", "Naziv dela" };
	private Collection<Recenzija> odradene;

	
	public RecenzijeOdUrednikaModel(Collection<Recenzija> odradene) {
		super();
		this.odradene = odradene;
	}

	@Override
	public int getRowCount() {
		return odradene.size();
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Recenzija s=((ArrayList<Recenzija>)odradene).get(rowIndex); 
		
		switch (columnIndex) {
		case 0:
			return s.getDatumUpisa();
		case 1:
			return s.getNaslov();
		case 2:
			return s.getDelo().getNaziv();
		default:
			break;
		}
		return null;
	}
	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		
		return getValueAt(0, columnIndex).getClass();

	}

}