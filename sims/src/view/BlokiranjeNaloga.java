package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;


public class BlokiranjeNaloga extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable nalozi;
	
	public BlokiranjeNaloga() throws Exception {
		setTitle("Blokiranje naloga");
		getContentPane().setLayout(null);
		
		
		nalozi = new JTable();
		nalozi.setFillsViewportHeight(true);
		nalozi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		nalozi.setBounds(10, 11, 414, 205);
		getContentPane().add(nalozi);
		
		JButton btnNewButton = new JButton("Blokiraj");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selektovaniRed = nalozi.getSelectedRow();
				//setujStatus(selektovaniRed, false);
			}
			
		});
		btnNewButton.setBounds(10, 227, 89, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnOdblokiraj = new JButton("Odblokiraj");
		btnOdblokiraj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selektovaniRed = nalozi.getSelectedRow();
				//setujStatus(selektovaniRed, true);
			}
		});
		btnOdblokiraj.setBounds(109, 227, 89, 23);
		getContentPane().add(btnOdblokiraj);
		
		ucitajNaloge();
	}
	
	private void ucitajNaloge() throws Exception
	{
		/*
		NalogMenadzer nm = new NalogMenadzer();
		TableModelWrapper tmw = nm.getTabelaNaloga();
		nalozi.setModel(tmw);
		*/
	}
	
	private void setujStatus(int selektovaniRed, boolean status)
	{
		/*
		nalozi.setValueAt(false, selektovaniRed, 2);
		NalogMenadzer nm = new NalogMenadzer();
		nm.setujStatus((String)nalozi.getValueAt(selektovaniRed, 0), status);
		*/
	}
}
