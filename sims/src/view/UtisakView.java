package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import controler.UtisakMenadzer;
import model.Sesija;
import model.Utisak;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;

public class UtisakView extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private static UtisakMenadzer utisakMenadzer;
    
    private int brojUtisaka=1;//Dodaj izmenjenu recenziju u utiske

    public void addKomentar(Utisak komentar){
    	komentar.getDelo().dodajUtisak(komentar);
        JLabel username = new JLabel(komentar.getPisac().getNalog().getKorisnickoIme());
        JTextArea sadrzaj = new JTextArea(komentar.getText());
        
        sadrzaj.setEditable(false);
        JScrollPane skrol = new JScrollPane(sadrzaj);
        skrol.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JPanel panel = new JPanel(new BorderLayout());
        
        CC componentConstraints = new CC();
        componentConstraints.alignX("center").spanX();
        componentConstraints.wrap();
        componentConstraints.grow();


        panel.add(username, BorderLayout.NORTH);
        panel.add(skrol, BorderLayout.CENTER);
        this.add(panel, componentConstraints);

        sadrzaj.setSize(panel.getSize());
        sadrzaj.setPreferredSize(panel.getPreferredSize());
        brojUtisaka++;
        podesiRecenzije();
        
        
        if(komentar.getPisac().equals(Sesija.getTrenutniKorisnik()))
        {
        	JPopupMenu recenzijaMeni = new JPopupMenu();
        	JMenuItem izmeniItem = new JMenuItem("Izmeni");
        	JMenuItem izbrisiItem = new JMenuItem("Izbrisi");
        	recenzijaMeni.add(izmeniItem);
        	recenzijaMeni.add(izbrisiItem);
        	sadrzaj.setComponentPopupMenu(recenzijaMeni);
        	setListeners(izmeniItem, izbrisiItem, sadrzaj, komentar, panel);
        }
        
    }

    private void setListeners(JMenuItem izmeniItem, JMenuItem izbrisiItem, JTextArea sadrzaj, Utisak kom, JPanel panel) {
  
		izmeniItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sadrzaj.setEditable(true);
			}
		});
		
		izbrisiItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				brojUtisaka--;
				UtisakView.this.remove(panel);
				UtisakView.this.setVisible(false);
				invpodesiRecenzije();
				UtisakView.this.setVisible(true);
				kom.getDelo().getUtisci().remove(kom);
				utisakMenadzer.getRecenzije().remove(kom);
			}
		});
		
		sadrzaj.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if((e.getKeyCode() == KeyEvent.VK_ENTER) && e.isShiftDown())
				{
					sadrzaj.append(" \n");
				}
				
				else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
				{
					sadrzaj.setText(kom.getText());
					sadrzaj.setEditable(false);
				}
				else if(e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					e.consume();
					sadrzaj.setEditable(false);
					kom.setText(sadrzaj.getText());
				}
			}
		});
		
	}

	private int adjust(int number){
        return (number/(brojUtisaka-1)) * brojUtisaka;
	}
	
	private int invadjust(int number){
        return (number/(brojUtisaka)) * (brojUtisaka-1);
	}

    

    public UtisakView() {
        super(new MigLayout("fillx"));

    }
    
    private void podesiRecenzije()
    {
    	try
    	{
            this.setPreferredSize(new Dimension(adjust(this.getWidth()), adjust(this.getHeight())));
            this.setSize(adjust(this.getWidth()), adjust(this.getHeight()));
        } 
    	catch(Exception e) 
    	{

        }
	}
	
	private void invpodesiRecenzije()
    {
    	try
    	{
            this.setPreferredSize(new Dimension(invadjust(this.getWidth()), invadjust(this.getHeight())));
            this.setSize(invadjust(this.getWidth()), invadjust(this.getHeight()));
        } 
    	catch(Exception e) 
    	{

        }
    }

	public static void setUtisakMenadzer(UtisakMenadzer utisakmenadzer2) {
		utisakMenadzer = utisakmenadzer2;
	}
	
	public static UtisakMenadzer getUtisakMenadzer()
	{
		return utisakMenadzer;
	}
    
}