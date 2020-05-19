package sale;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.MainFrame;

public class SalesInputService implements KeyListener, ActionListener{
	
	SaleBtn salebtn = new SaleBtn();
	MainFrame mainframe;
	
	public SalesInputService(MainFrame mainframe) {
		super();
		this.mainframe = mainframe;

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
	
	@Override
	public void keyPressed(KeyEvent e) {


	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void actionPerformed(ActionEvent e) {
		
	}

}
