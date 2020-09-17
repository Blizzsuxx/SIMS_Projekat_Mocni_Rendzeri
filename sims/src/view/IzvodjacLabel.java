package view;

import javax.swing.ImageIcon;

import model.Grupa;
import model.Pojedinacanizvodjac;
import model.Slikovit;

public class IzvodjacLabel extends ImageLabel {

    @Override
    protected void clickedEvent() {
        // TODO Auto-generated method stub
        IzvodjacView prikazIzvodjaca;
        
        if(this.getDelo() instanceof Pojedinacanizvodjac) prikazIzvodjaca = new PojedinacanIzvodjacView(null, (Pojedinacanizvodjac) this.getDelo());
        else prikazIzvodjaca = new GrupaView(null, (Grupa) this.getDelo());

        prikazIzvodjaca.setVisible(true);
    }

    public IzvodjacLabel(int sirina, int duzina, Slikovit delo) {
        super(sirina, duzina, delo);
    }

    public IzvodjacLabel(ImageIcon ikona, Slikovit delo) {
        super(ikona, delo);
    }

    public IzvodjacLabel(Slikovit delo){
        super(delo);
    }


    public IzvodjacLabel(ImageIcon ikona, Slikovit delo, String align) {
        super(ikona, delo, align);
        // TODO Auto-generated constructor stub
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;





    
}
