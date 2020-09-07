package view;

import javax.swing.ImageIcon;

import model.Korisnik;
import model.MuzickoDelo;

public class MuzickoDeloLabel extends ImageLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Korisnik trenutniKorisnik;

	@Override
	protected void clickedEvent() {
		// TODO Auto-generated method stub
        if(!isClickable()){
            return;
        }
        MediaPlayer player = new MediaPlayer(null, (MuzickoDelo) getDelo(), trenutniKorisnik);
        player.setVisible(true);
		
	}

	public MuzickoDeloLabel(Korisnik trenutniKorisnik, ImageIcon ikona, Slikovit delo) {
		super(ikona, delo);
		this.trenutniKorisnik = trenutniKorisnik;
		// TODO Auto-generated constructor stub
	}

	public MuzickoDeloLabel(Korisnik trenutniKorisnik, int sirina, int duzina, Slikovit delo) {
		super(sirina, duzina, delo);
		this.trenutniKorisnik = trenutniKorisnik;
		// TODO Auto-generated constructor stub
	}

	public MuzickoDeloLabel(Korisnik trenutniKorisnik, Slikovit delo) {
		super(delo);
		this.trenutniKorisnik = trenutniKorisnik;
		// TODO Auto-generated constructor stub
	}

    
    
}