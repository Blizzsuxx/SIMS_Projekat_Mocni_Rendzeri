package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Sesija;

public class UrednikHomepage extends Homepage {
	 private static final long serialVersionUID = 1L;

	 private JMenu listaMenu, korisniciMenu;
	 private JMenuItem listaItem1, listaItem2, korisniciItem1, korisniciItem2;
	 
	  
	public UrednikHomepage(Sesija sesija) {
	    super(sesija);
	    
	    this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(UrednikHomepage.this, 
						"Jeste sigurni da zelite odjavu?", "Odjava", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
				
					dispose();
				}
			}
		});
		
	    this.initGUI();
	    this.initAction();
	}
	    
	private void initGUI() {
		listaMenu = new JMenu("Liste");
    	listaMenu.setBackground(Color.BLACK);
    	listaMenu.setForeground(Color.WHITE);
		    	
		listaItem1 = new JMenuItem("Top Liste");
		listaItem1.setBackground(Color.BLACK);
		listaItem1.setForeground(Color.WHITE);
		listaMenu.add(listaItem1);
		listaItem2 = new JMenuItem("Recenzirani Sadrzaj");
		listaItem2.setBackground(Color.BLACK);
		listaItem2.setForeground(Color.WHITE);
		listaMenu.add(listaItem2);
		menubar.add(listaMenu);
		    	
		korisniciMenu = new JMenu("Korisnici");
		korisniciMenu.setBackground(Color.BLACK);
		korisniciMenu.setForeground(Color.WHITE);
		    	
		korisniciItem1 = new JMenuItem("Korisnici");
		korisniciItem1.setBackground(Color.BLACK);
		korisniciItem1.setForeground(Color.WHITE);
		korisniciMenu.add(korisniciItem1);
		korisniciItem2 = new JMenuItem("Zahtjevi Registracije");
		korisniciItem2.setBackground(Color.BLACK);
		korisniciItem2.setForeground(Color.WHITE);
		korisniciMenu.add(korisniciItem2);
		menubar.add(korisniciMenu);
	}
	    
	private void initAction() {
		listaItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
					
			}

				
	    		
	    });
	    	
	    listaItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					
			}
	    		
	    });
	    	
	    korisniciItem1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					
			}
	    		
	    });
	    	
	    korisniciItem2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				RegistracijaIzvodjaca ri;
				try {
					ri = new RegistracijaIzvodjaca(UrednikHomepage.this.getSesija());
					ri.setVisible(true);
				} catch (Exception e1) {
						// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
					
			}
	    		
	    });
	}

}