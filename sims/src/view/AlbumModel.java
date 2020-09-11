package view;

import java.util.List;

import model.Album;

public class AlbumModel extends ApstraktniModel {
	private static final long serialVersionUID = 1L;

	private List<Album> albumi;
	private boolean indikator;
	
	public AlbumModel(String[] columnNames, List<Album> albumi, boolean indikator) {
		super(columnNames);
		this.albumi = albumi;
		this.indikator = indikator;
	}

	@Override
	public int getRowCount() {
		return this.albumi.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		Object temp = null;
		if (!albumi.isEmpty()) {
			Album a = albumi.get(row);
			if (col == 0) {
				temp = a.getNaslov();
			} else if (col == 1) {
				temp = a.getOpis();
			} else if (col == 2) {
				temp = a.getDatumIzadavanja();
			} else if (col == 3) {
				temp = a.getIzvodjac().getUmetnickoIme();
			} else if (col == 4) {
				temp = a.isOdobreno();
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
        Album row = albumi.get(rowIndex);
        if (columnIndex == 4 && (Boolean)aValue) {
        	row.setOdobreno((Boolean)aValue);
        }
   
    }
}
