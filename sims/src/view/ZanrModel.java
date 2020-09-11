package view;

import java.util.List;

import model.Zanr;

public class ZanrModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<Zanr> zanrovi;
	
	public ZanrModel(String[] columnNames, List<Zanr> zanrovi) {
		super(columnNames);
		this.zanrovi = zanrovi;
	}

	@Override
	public int getRowCount() {
		return this.zanrovi.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		Zanr z = zanrovi.get(row);
		if (col == 0) {
			temp = z.getNazivZanra();
		} else if (col == 1) {
			temp = z.isStatus();
		}
		return temp;
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		if (col == 1)
			return true;
	    return false;
	}
	
	@Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        Zanr row = zanrovi.get(rowIndex);
        if (columnIndex == 1 && !(Boolean)aValue) {
        	row.setStatus(false);
        }
   
    }
}
