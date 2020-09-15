package view;

import java.awt.Component;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JPopupMenu;
import javax.swing.MenuElement;

public class MojPopupMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public MojPopupMenu() {
		this(null);
	}

	public MojPopupMenu(String label) {
		super(label);
		this.setFocusable(false);
	}
	
	
	private static boolean isMouseWithinComponent(Component c)
	{
		Point mousePos = MouseInfo.getPointerInfo().getLocation();
		c.setVisible(true);
	    Rectangle bounds = c.getBounds();
	    bounds.setLocation(c.getLocationOnScreen());
		boolean contains = bounds.contains(mousePos);
		if(!contains){
			c.setVisible(false);
		}
	    return contains;
	}
	
	
	@Override
	public void menuSelectionChanged(boolean isIncluded) {
		if(!isIncluded) {
			boolean b = false;
			try {
				b = mouseWithinSubElements(this);
			} catch(Exception e) {
				
			}
			if( b ) {
				this.setVisible(false);
				this.setVisible(true);
			} else {
				
				super.menuSelectionChanged(isIncluded);
			}
		}
	}
	
	
	private static boolean mouseWithinSubElements(MenuElement e) {
		if( isMouseWithinComponent((Component) e) ) {
			return true;
		}
		
		for( MenuElement element : e.getSubElements()) {
			if(mouseWithinSubElements(element)) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public void show(Component invoker, int x, int y) {
		// TODO Auto-generated method stub
		super.show(invoker, x, invoker.getHeight());
	}
	
	
}
