package view;

import javax.swing.ImageIcon;

public class IzvodjacLabel extends ImageLabel {

    @Override
    protected void clickedEvent() {
        // TODO Auto-generated method stub
        super.clickedEvent();
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
