package view;

import java.util.List;

import model.Izvodjac;

public class IzvodjaciModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<Izvodjac> izvodjaci;
	private boolean indikator;
	
	public IzvodjaciModel(String[] columnNames, List<Izvodjac> izvodjaci, boolean indikator) {
		super(columnNames);
		this.izvodjaci = izvodjaci;
		this.indikator = indikator;
	}

	@Override
	public int getRowCount() {
		return this.izvodjaci.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		if (!izvodjaci.isEmpty()) {
			Izvodjac i = izvodjaci.get(row);
			if (col == 0) {
				temp = i.getUmetnickoIme();
			} else if (col == 1) {
				temp = i.getZanr().getNazivZanra();
			} else if (col == 2) {
				temp = i.getIzdatiAlbumi().size();
			} else if (col == 3) {
				temp = i.getMuzickaDela().size();
			} else if (col == 4) {
				temp = i.isOdobrenost();
			}
		}
		return temp;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 4 && indikator)
			return true;
	    return false;
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Izvodjac row = izvodjaci.get(rowIndex);
        if (columnIndex == 4 && (Boolean)aValue) {
        	row.setOdobrenost((Boolean)aValue);
        }
   
    }
}
