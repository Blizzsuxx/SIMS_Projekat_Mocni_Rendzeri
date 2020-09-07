package view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Sesija;

public class AdminHomepage extends Homepage {
	private static final long serialVersionUID = 1L;

	public AdminHomepage(Sesija sesija) {
		super(sesija);
		
		 this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent windowEvent) {
					if (JOptionPane.showConfirmDialog(AdminHomepage.this, 
							"Jeste sigurni da zelite odjavu?", "Odjava", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					
						dispose();
					}
				}
			});
			
		this.initGUI();
		this.actionGUI();
	}

	private void initGUI() {
		
	}
	
	private void actionGUI() {
		
	}
}
