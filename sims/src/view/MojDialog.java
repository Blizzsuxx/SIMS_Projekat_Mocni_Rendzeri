package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class MojDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public MojDialog(JFrame parent, String ime, int dimension1, int dimension2) {
		super(parent, ime, true);
		this.setSize(dimension1, dimension2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		setBackground(Color.BLACK);
		
		// Za centriranje dijaloga
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent windowEvent) {
				if(parent == null){
					return;
				}
				getParent().setVisible(true);
			}
		});

		
	}

	public MojDialog(JFrame parent, String naziv){
		this(parent, naziv, 450, 450);
	}

	public MojDialog(String ime, int dimension1, int dimension2){
		this(null, ime, dimension1, dimension2);
	}
}