package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import controler.CitacDatoteka;
import controler.Constants;
import model.MuzickoDelo;
import net.miginfocom.swing.MigLayout;

public class MuzickoDeloLabel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private MuzickoDelo delo;
    private boolean clickable = true;

    public MuzickoDeloLabel(MuzickoDelo delo) {
        this(60, 60, delo);

    }

    /**
     * @return the clickable
     */
    public boolean isClickable() {
        return clickable;
    }

    /**
     * @param clickable the clickable to set
     */
    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    /**
     * @return the delo
     */
    public MuzickoDelo getDelo() {
        return delo;
    }

    /**
     * @param delo the delo to set
     */
    public void setDelo(MuzickoDelo delo) {
        this.delo = delo;
    }

    private static ImageIcon proveriZaIkonu(MuzickoDelo delo, int sirina, int duzina){
        
        BufferedImage image = CitacDatoteka.procitajSliku("fajlovi/muzika/"+delo.getNaziv() + ".jpg");

        if(image == null){
            image = Constants.MUZICKA_IKONA;
        }

        return new ImageIcon(image.getScaledInstance(sirina, duzina, Image.SCALE_DEFAULT));
    }

    public MuzickoDeloLabel(int sirina, int duzina, MuzickoDelo delo) {
        this(proveriZaIkonu(delo, sirina, duzina), delo);

    }

    

    public MuzickoDeloLabel(ImageIcon ikona, MuzickoDelo delo) {
        super(new MigLayout());
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(120, 120));
        JLabel labela = new JLabel(ikona);
        this.setDelo(delo);
        this.add(labela, "wrap, align center");
        labela.setBackground(Color.BLACK);
        this.addTekst(delo.getNaziv());

        labela.addMouseListener(new MouseInputAdapter(){
            
            @Override
            public void mouseClicked(java.awt.event.MouseEvent arg0) {
                // TODO Auto-generated method stub
                if(!clickable){
                    return;
                }
                MediaPlayer player = new MediaPlayer(null, delo);
                player.setVisible(true);
            }


        });
    }



    void addTekst(String tekst){
        JLabel labela = new JLabel(tekst);
        labela.setSize(getPreferredSize());
        this.add(labela, "wrap, align center");
        labela.setBackground(Color.BLACK);
        labela.setForeground(Color.WHITE);
    }
    
}