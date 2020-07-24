package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;

public abstract class MojDialog extends JDialog {
	private static final long serialVersionUID = 1L;

	public MojDialog(String ime, int dimension1, int dimension2) {
		this.setTitle(ime);
		this.setSize(dimension1, dimension2);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setModal(true);
		
		// Za centriranje dijaloga
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();
		int x = (screenSize.width - this.getWidth()) / 2;
		int y = (screenSize.height - this.getHeight()) / 2;
		this.setLocation(x, y);
	}
}
