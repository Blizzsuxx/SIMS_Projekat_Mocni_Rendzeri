package view;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class TablePopupMenu extends JPopupMenu {
	private static final long serialVersionUID = 1L;
	protected JMenuItem menuItemAdd, menuItemEdit, menuItemDelete;
	
	public TablePopupMenu() {
		this.menuItemAdd = new JMenuItem("Dodaj");
		this.menuItemEdit = new JMenuItem("Izmjeni");
		this.menuItemDelete = new JMenuItem("Izbrisi");
		
		this.add(menuItemAdd);
		this.add(menuItemEdit);
		this.add(menuItemDelete);
	}
}
