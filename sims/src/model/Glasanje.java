package model;


public class Glasanje {
	private MuzickoDelo muzickoDelo;
	private int brojGlasova;
	
	public Glasanje() {
		
	}
	
	public Glasanje(MuzickoDelo muzickoDelo, int brojGlasova) {
		this.muzickoDelo = muzickoDelo;
		this.brojGlasova = brojGlasova;
	}

	public MuzickoDelo getMuzickoDelo() {
		return muzickoDelo;
	}

	public void setMuzickoDelo(MuzickoDelo muzickoDelo) {
		this.muzickoDelo = muzickoDelo;
	}

	public int getBrojGlasova() {
		return brojGlasova;
	}

	public void setBrojGlasova(int brojGlasova) {
		this.brojGlasova = brojGlasova;
	}
	
	public static String Glasanje2String(Glasanje glasanje) {
		return glasanje.getMuzickoDelo().getNaslov() + ";" + glasanje.getBrojGlasova() + System.lineSeparator();
	}
}

