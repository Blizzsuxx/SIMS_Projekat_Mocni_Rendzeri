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
import net.miginfocom.swing.MigLayout;

public abstract class ImageLabel extends JPanel {
	
	/**
    *
    */
   private static final long serialVersionUID = 1L;
   private Slikovit delo;
   private boolean clickable = true;

   public ImageLabel(Slikovit delo) {
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
   public Slikovit getDelo() {
       return delo;
   }

   /**
    * @param delo the delo to set
    */
   public void setDelo(Slikovit delo) {
       this.delo = delo;
   }

   private static ImageIcon proveriZaIkonu(Slikovit delo, int sirina, int duzina){
       
       BufferedImage image = CitacDatoteka.procitajSliku(delo.putDoSlike());

       if(image == null){
           image = delo.defaultSlika();
       }

       return new ImageIcon(image.getScaledInstance(sirina, duzina, Image.SCALE_DEFAULT));
   }

   public ImageLabel(int sirina, int duzina, Slikovit delo) {
       this(proveriZaIkonu(delo, sirina, duzina), delo);

   }

   

   public ImageLabel(ImageIcon ikona, Slikovit delo) {
       super(new MigLayout());
       setBackground(Color.BLACK);
       setPreferredSize(new Dimension(120, 120));
       JLabel labela = new JLabel(ikona);
       this.setDelo(delo);
       this.add(labela, "wrap, align center");
       labela.setBackground(Color.BLACK);
       this.addTekst(delo.Ime());

       labela.addMouseListener(new MouseInputAdapter(){
           
           @Override
           public void mouseClicked(java.awt.event.MouseEvent arg0) {
               clickedEvent();
           }


       });
   }



   abstract protected void clickedEvent();

void addTekst(String tekst){
       JLabel labela = new JLabel(tekst);
       labela.setSize(getPreferredSize());
       this.add(labela, "wrap, align center");
       labela.setBackground(Color.BLACK);
       labela.setForeground(Color.WHITE);
   }

}