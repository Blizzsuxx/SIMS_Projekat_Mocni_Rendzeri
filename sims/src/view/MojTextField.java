package view;

import java.util.Date;

import javax.swing.JTextField;
import javax.swing.text.Document;

import controler.Constants;
import model.Pol;

public class MojTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 TextField kod koga je automatski podeseno editable false u pojedinim konstruktorima
	 */
	
	public MojTextField() {
		// TODO Auto-generated constructor stub
	}

	public MojTextField(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}

	public MojTextField(int columns) {
		super(columns);
		this.setEditable(false);
		// TODO Auto-generated constructor stub
	}
	
	public MojTextField(int columns, boolean modifiable) {
		super(columns);
		this.setEditable(modifiable);
	}

	public MojTextField(String text, int columns) {
		super(text, columns);
		// TODO Auto-generated constructor stub
	}

	public MojTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		// TODO Auto-generated constructor stub
	}

	public void setText(Pol pol) {
		this.setText(pol.toString());
	}
	
	public void setText(Date datum) {
		this.setText(Constants.NATASIN_FORMAT_ZA_DATUM.format(datum));
	}

}
