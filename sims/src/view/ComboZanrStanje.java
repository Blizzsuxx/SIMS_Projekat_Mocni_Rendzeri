package view;

import model.Zanr;

public class ComboZanrStanje {
	private Zanr zanr;
	protected boolean stanje;
	
	public ComboZanrStanje(Zanr zanr, boolean stanje) {
		super();
		this.zanr = zanr;
		this.stanje = stanje;
	}

	public Zanr getZanr() {
		return zanr;
	}

	public void setZanr(Zanr zanr) {
		this.zanr = zanr;
	}

	public boolean isStanje() {
		return stanje;
	}

	public void setStanje(boolean stanje) {
		this.stanje = stanje;
	}
	
	
}
