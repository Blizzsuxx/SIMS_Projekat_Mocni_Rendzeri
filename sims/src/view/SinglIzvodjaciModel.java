package view;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.IzvestajJednogIzvodjaca;

public class SinglIzvodjaciModel extends AbstractTableModel { //srediti
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	String[] naslovi = { "Izvodjac", "Broj dela", "Broj recenzija", "Broj komentara", "Ocena urednika",
			"Ocena korisnika" };
	ArrayList<IzvestajJednogIzvodjaca> sviIzv;
	
	
	public SinglIzvodjaciModel(ArrayList<IzvestajJednogIzvodjaca> umetnici) {
		this.sviIzv=umetnici;
	}
	@Override
	public String getColumnName(int index) {
	    return naslovi[index];
	}
	@Override
	public int getRowCount() {
		
		return sviIzv.size();
	}

	@Override
	public int getColumnCount() {
		
		return naslovi.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		IzvestajJednogIzvodjaca i=sviIzv.get(rowIndex);
		switch(columnIndex) {
		case 0:
			return i.getIme();
		case 1:
			return i.getBrojDela();
		case 2:
			return i.getBrojRecenzija();
		case 3:
			return i.getBrojKomentara();
		case 4:
			return i.getOcenaUrednika();
		case 5:
			return i.getOcenaKorisnika();
			default:
				break;
		}
		
		return null;
	}

}
