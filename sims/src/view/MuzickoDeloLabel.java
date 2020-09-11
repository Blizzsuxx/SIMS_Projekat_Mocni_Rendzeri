package view;

import javax.swing.ImageIcon;

import model.Korisnik;
import model.MuzickoDelo;

public class MuzickoDeloLabel extends ImageLabel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void clickedEvent() {
		// TODO Auto-generated method stub
        if(!isClickable()){
            return;
        }
        MediaPlayer player = new MediaPlayer(null, (MuzickoDelo) getDelo());
        player.setVisible(true);
		
	}

	public MuzickoDeloLabel(ImageIcon ikona, Slikovit delo) {
		super(ikona, delo);
		// TODO Auto-generated constructor stub
	}

	public MuzickoDeloLabel(int sirina, int duzina, Slikovit delo) {
		super(sirina, duzina, delo);
		// TODO Auto-generated constructor stub
	}

	public MuzickoDeloLabel(Slikovit delo) {
		super(delo);
		// TODO Auto-generated constructor stub
	}

    
    
}